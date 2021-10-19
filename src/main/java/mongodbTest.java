import com.mongodb.CursorType;
import com.mongodb.client.*;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Filter;

import static com.mongodb.client.model.Filters.eq;

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

    public void mongodbCreatePerson(MongoClient client, String name, String password, String date, String email){
        MongoDatabase db = client.getDatabase("RegisteredPeopleDB");
        MongoCollection<Document> col = db.getCollection("RegisteredPeopleCollection");

        Document newPerson;
        newPerson = new Document("_id", "0")
                .append("name", name)
                .append("password", password)
                .append("date", date)
                .append("e-mail", email);
        col.insertOne(newPerson);
    }

    public void mongodbInsertPost(MongoClient client, int id, String post){
        MongoDatabase db = client.getDatabase("PostsDB");
        MongoCollection<Document> col = db.getCollection("PostsCollection");

        Document document = col.find().first().append("post", post);

        col.insertOne(document);
    }

    public void findPost(MongoClient client, String post, int id){
        MongoDatabase db = client.getDatabase("PostsDB");
        MongoCollection<Document> col = db.getCollection("PostsCollection");

        Document testDoc = new Document().append("_id", id);

        MongoCursor test = col.find(testDoc).iterator();

        if(test.hasNext()){
            Bson queryFilter = eq("_id", new ObjectId(String.valueOf(id)));
        }
        else {
            List<String> testList = new ArrayList<>();
            testList.add(post);
            testDoc.append("posts", testList);

            col.insertOne(testDoc);
        }
    }

    @Override
    public void close(){
        this.client.close();
    }
}
