package persistence;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;

import com.mongodb.client.MongoDatabase;
import model.Bestellposition;
import model.Bestellung;
import model.Fernseher;
import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class BestellungRepo {

    // Umsetzung mit Pojo
    CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
    CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

    MongoClient mongoClient = new MongoClient("localhost", 27017);
    MongoDatabase database = mongoClient.getDatabase("tvshop").withCodecRegistry(pojoCodecRegistry);
    MongoCollection<Bestellung> collection = database.getCollection("bestellungen", Bestellung.class);

    public void addBestellung(Bestellung b) {
        collection.insertOne(b);
    }

    public void updateBestellung(Bestellung b) {
        if (b.getBestellungId() != null) {
            collection.replaceOne(
                    eq("_id", b.getBestellungId()), b
            );
        }
    }

    public void deleteBestellung(Bestellung b) {
        collection.deleteOne(eq("_id", b.getBestellungId()));
    }

    public List<Bestellung> getAll() {
        List<Bestellung> result = new ArrayList<>();
        collection.find().into(result);
        return result;
    }
}
