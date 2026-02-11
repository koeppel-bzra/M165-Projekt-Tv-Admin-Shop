package persistence;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import model.Fernseher;
import model.Kunde;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class KundenRepo {

    CodecProvider codecProvider = PojoCodecProvider.builder().automatic(true).build();
    CodecRegistry codecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(), fromProviders(codecProvider));

    MongoClient mongoClient = new MongoClient("localhost", 27017);
    MongoDatabase database = mongoClient.getDatabase("tvshop").withCodecRegistry(codecRegistry);
    MongoCollection<Kunde> collection = database.getCollection("kunden", Kunde.class);

    public void addKunde(Kunde kunde) {
        collection.insertOne(kunde);
    }

    public void deleteKunde(Kunde kunde) {
        collection.deleteOne(eq("_id", kunde.getKundeId()));
    }

    public void updateKunde(Kunde kunde) {
        if (kunde.getKundeId() != null) {
            collection.replaceOne(
                    eq("_id", kunde.getKundeId()), kunde
            );
        }
    }

    public List<Kunde> getAllKunden() {
        List<Kunde> result = new ArrayList<>();
        collection.find().into(result);
        return result;
    }
}
