package persistence;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;

import com.mongodb.client.MongoDatabase;
import model.Fernseher;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class FernseherRepo {

    CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
    CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

    MongoClient mongoClient = new MongoClient("localhost", 27017);
    MongoDatabase database = mongoClient.getDatabase("tvshop").withCodecRegistry(pojoCodecRegistry);
    MongoCollection<Fernseher> collection = database.getCollection("fernseher", Fernseher.class);

    public void addFernseher(Fernseher f) {
        collection.insertOne(f);
    }

    public void updateFernseher(Fernseher f) {
        if (f.getFernseherId() != null) {
            collection.replaceOne(
                    eq("_id", f.getFernseherId()), f
            );
        }
    }

    public void deleteFernseher(Fernseher f) {
        collection.deleteOne(eq("_id", f.getFernseherId()));
    }

    public List<Fernseher> getAll() {
        List<Fernseher> result = new ArrayList<>();
        collection.find().into(result);
        return result;
    }
}
