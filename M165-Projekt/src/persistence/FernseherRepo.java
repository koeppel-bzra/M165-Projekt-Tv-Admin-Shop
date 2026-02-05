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

    public void addFernseher(Fernseher f) {
        Document document = new Document()
                .append("marke", f.getMarke())
                .append("modell", f.getModell());

        collection.insertOne(document);

        f.setFernseherId(document.getObjectId("_id"));
    }

    public List<Fernseher> getAll() {
        List<Fernseher> result = new ArrayList<>();
        for (Document doc : collection.find()) {
            result.add(new Fernseher(
                    doc.getObjectId("_id"),
                    doc.getString("marke"),
                    doc.getString("modell")));
        }
        return result;
     }
}
