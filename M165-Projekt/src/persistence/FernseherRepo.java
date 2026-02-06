package persistence;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import model.Fernseher;
import org.bson.Document;
import com.mongodb.client.MongoDatabase;

import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FernseherRepo {
    MongoClient mongoClient = new MongoClient("localhost", 27017);
    MongoDatabase database = mongoClient.getDatabase("tvshop");
    MongoCollection<Document> collection = database.getCollection("fernseher");

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


    public void addFernseher(Fernseher f) {
        Document document = new Document()
                .append("marke", f.getMarke())
                .append("modell", f.getModell())
                .append("preis", f.getPreis())
                .append("bildschirmdiagonale", f.getBildschirmdiagonale())
                .append("displayTechnologie", f.getDisplayTechnologie())
                .append("bildschirmAufloesung", f.getBildschirmAufloesung())
                .append("bildwiederholfrequenz", f.getBildwiederholFrequenz())
                .append("gewicht", f.getGewicht())
                .append("releasedatum", f.getReleaseDatum())
                .append("pixelaufloesung", f.getPixelaufloesung())
                .append("nennleistung", f.getNennleistung());

        collection.insertOne(document);

        f.setFernseherId(document.getObjectId("_id"));
    }

    public List<Fernseher> getAll() {
        List<Fernseher> result = new ArrayList<>();
        for (Document doc : collection.find()) {
            result.add(new Fernseher(
                    doc.getObjectId("_id"),
                    doc.getString("marke"),
                    doc.getString("modell"),
                    getDoubleSafe(doc, "preis"),
                    getIntegerSafe(doc, "bildschirmdiagonale"),
                    doc.getString("displayTechnologie"),
                    doc.getString("bildschirmAufloesung"),
                    getIntegerSafe(doc, "bildwiederholfrequenz"),
                    getDoubleSafe(doc, "gewicht"),
                    doc.getDate("releasedatum"),
                    doc.getString("pixelaufloesung"),
                    getIntegerSafe(doc, "nennleistung")
            ));
        }
        return result;
    }

}
