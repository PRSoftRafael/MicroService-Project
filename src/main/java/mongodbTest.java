import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Filter;

public class mongodbTest implements AutoCloseable{

    MongoClient client;

    public mongodbTest(MongoClient client){
        this.client = client;
    }

    /*
    public static void main(String[] args){
        MongoClient client = MongoClients.create("mongodb+srv://testUser:testUser@cluster0.kql6i.mongodb.net/sampleDB?retryWrites=true&w=majority");

        MongoDatabase db = client.getDatabase("sampleDB");
        MongoCollection<Document> col = db.getCollection("sampleCollection");

        Document newPerson = new Document("_id", "0")
                .append("name", "Simon")
                .append("password", "test")
                .append("date", "14.10.2021")
                .append("e-mail", "test@gmail.com");
        col.insertOne(newPerson);
    }
    */

    public void insertPerson(MongoClient client, String name, String password, String date, String email){
        MongoDatabase db = client.getDatabase("sampleDB");
        MongoCollection<Document> col = db.getCollection("sampleCollection");

        Document newPerson;
        newPerson = new Document("_id", "0")
                .append("name", name)
                .append("password", password)
                .append("date", date)
                .append("e-mail", email);
        col.insertOne(newPerson);
    }

    public void insertArray(MongoClient client){
        MongoDatabase db = client.getDatabase("sampleDB");
        MongoCollection<Document> col = db.getCollection("sampleCollection");

        List<Document> array = new ArrayList<>();
        array.add(new Document("picture", "bild").append("Kommentar", "new world").append("datum", "14.10.2021"));
        array.add(new Document("picture", "bild").append("Kommentar", "new world").append("datum", "14.10.2021"));
        array.add(new Document("picture", "bild").append("Kommentar", "new world").append("datum", "14.10.2021"));

        Document newPerson;
        newPerson = new Document("_id", "2").append("array", array);
        col.insertOne(newPerson);
    }

    @Override
    public void close(){
        this.client.close();
    }
}
