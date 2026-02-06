package persistence;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import model.Fernseher;
import model.DisplayTechnologie;
import model.Bildschirmaufloesung;
import model.Pixelaufloesung;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FernseherRepo {

    MongoClient mongoClient = new MongoClient("localhost", 27017);
    MongoDatabase database = mongoClient.getDatabase("tvshop");
    MongoCollection<Document> collection = database.getCollection("fernseher");

    // Hilfsmethoden für Safe Parsing
    private double getDoubleSafe(Document doc, String field) {
        Object value = doc.get(field);
        if (value == null) return 0.0;
        if (value instanceof Double) return (Double) value;
        if (value instanceof Integer) return ((Integer) value).doubleValue();
        if (value instanceof Long) return ((Long) value).doubleValue();
        return 0.0;
    }

    private int getIntegerSafe(Document doc, String field) {
        Object value = doc.get(field);
        if (value == null) return 0;
        if (value instanceof Integer) return (Integer) value;
        if (value instanceof Double) return ((Double) value).intValue();
        if (value instanceof Long) return ((Long) value).intValue();
        return 0;
    }



    // Null-sichere Enum-Konvertierung
    private DisplayTechnologie getDisplayTechnologieSafe(Document doc) {
        String value = doc.getString("displayTechnologie");
        if (value == null) return DisplayTechnologie.LED; // Standardwert
        try {
            return DisplayTechnologie.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            return DisplayTechnologie.LED; // Fallback, falls falscher Wert
        }
    }

    private Bildschirmaufloesung getBildschirmAufloesungSafe(Document doc) {
        String value = doc.getString("bildschirmAufloesung");
        if (value == null) return Bildschirmaufloesung.HD;
        try {
            return Bildschirmaufloesung.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Bildschirmaufloesung.HD;
        }
    }

    private Pixelaufloesung getPixelAufloesungSafe(Document doc) {
        String value = doc.getString("pixelaufloesung");
        if (value == null) return Pixelaufloesung.P720;
        try {
            return Pixelaufloesung.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Pixelaufloesung.P720;
        }
    }



    private Fernseher fromDocument(Document doc) {
        DisplayTechnologie display = getDisplayTechnologieSafe(doc);
        Bildschirmaufloesung aufloesung = getBildschirmAufloesungSafe(doc);
        Pixelaufloesung pixel = getPixelAufloesungSafe(doc);

        Date date = doc.getDate("releasedatum");
        LocalDate releaseDatum = null;

        if (date != null) {
            releaseDatum = date.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
        }

        return new Fernseher(
                doc.getObjectId("_id"),
                doc.getString("marke"),
                doc.getString("modell"),
                getDoubleSafe(doc, "preis"),
                getIntegerSafe(doc, "bildschirmdiagonale"),
                display,
                aufloesung,
                getIntegerSafe(doc, "bildwiederholfrequenz"),
                getDoubleSafe(doc, "gewicht"),
                releaseDatum,
                pixel,
                getIntegerSafe(doc, "nennleistung")
        );
    }


    // CRUD-Methoden
    public void addFernseher(Fernseher f) {
        Document document = new Document()
                .append("marke", f.getMarke())
                .append("modell", f.getModell())
                .append("preis", f.getPreis())
                .append("bildschirmdiagonale", f.getBildschirmdiagonale())
                .append("displayTechnologie", f.getDisplayTechnologie().name())
                .append("bildschirmAufloesung", f.getBildschirmAufloesung().name())
                .append("bildwiederholfrequenz", f.getBildwiederholFrequenz())
                .append("gewicht", f.getGewicht())
                .append("releasedatum", f.getReleaseDatum())
                .append("pixelaufloesung", f.getPixelaufloesung().name())
                .append("nennleistung", f.getNennleistung());

        collection.insertOne(document);

        f.setFernseherId(document.getObjectId("_id"));
    }

    public void updateFernseher(Fernseher f) {
        if (f.getFernseherId() == null) {
            return;
        }

        Document updateDoc = new Document().append("marke", f.getMarke())
                .append("modell", f.getModell())
                .append("preis", f.getPreis())
                .append("bildschirmdiagonale", f.getBildschirmdiagonale())
                .append("displayTechnologie", f.getDisplayTechnologie().name())
                .append("bildschirmAufloesung", f.getBildschirmAufloesung().name())
                .append("bildwiederholfrequenz", f.getBildwiederholFrequenz())
                .append("gewicht", f.getGewicht())
                .append("releasedatum", f.getReleaseDatum())
                .append("pixelaufloesung", f.getPixelaufloesung().name())
                .append("nennleistung", f.getNennleistung());

        Document filter = new Document("_id", f.getFernseherId());
        collection.updateOne(filter, new Document("$set", updateDoc));
    }

    public void deleteFernseher(ObjectId _id) {
        if (_id != null) {
            collection.deleteOne(new Document("_id", _id));
        }
    }

    public List<Fernseher> getAll() {
        List<Fernseher> result = new ArrayList<>();
        for (Document doc : collection.find()) {
            result.add(fromDocument(doc));
        }
        return result;
    }
}
