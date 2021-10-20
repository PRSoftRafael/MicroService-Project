import com.mongodb.BasicDBObject;
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

public class UserManagement implements AutoCloseable{

    private MongoClient client;
    private MongoDatabase db;
    private MongoCollection col;

    public UserManagement(MongoClient client, String db, String col){
        this.client = client;
        this.db = client.getDatabase(db);
        this.col = this.db.getCollection(col);
    }

    public boolean createUser(String name, String password, String date, String email){
        if(col.find(new Document("name", name)).iterator().hasNext()){
            return false;
        }
        else {
            MongoCursor idCursor = col.find().iterator();
            int id = 0;
            while(idCursor.hasNext()){
                idCursor.next();
                id++;
            }

            Document newPerson = new Document("_id", id)
                    .append("name", name)
                    .append("password", password)
                    .append("date", date)
                    .append("e-mail", email);
            this.col.insertOne(newPerson);
            return true;
        }
    }

    public void updateName(int id, String currentName, String newName){
        BasicDBObject query = new BasicDBObject();
        query.put("_id", id);
        query.put("name", currentName);

        BasicDBObject newDocument = new BasicDBObject();
        newDocument.put("name", newName);

        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$set", newDocument);

        col.updateOne(query, updateObject);
    }

    public void updatePassword(int id, String currentPassword, String newPassword){
        BasicDBObject query = new BasicDBObject();
        query.put("_id", id);
        query.put("password", currentPassword);

        BasicDBObject newDocument = new BasicDBObject();
        newDocument.put("password", newPassword);

        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$set", newDocument);

        col.updateOne(query, updateObject);
    }

    public void updateDate(int id, String currentDate, String newDate){
        BasicDBObject query = new BasicDBObject();
        query.put("_id", id);
        query.put("date", currentDate);

        BasicDBObject newDocument = new BasicDBObject();
        newDocument.put("date", newDate);

        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$set", newDocument);

        col.updateOne(query, updateObject);
    }

    public void updateEmail(int id, String currentEmail, String newEmail){
        BasicDBObject query = new BasicDBObject();
        query.put("_id", id);
        query.put("e-mail", currentEmail);

        BasicDBObject newDocument = new BasicDBObject();
        newDocument.put("e-mail", newEmail);

        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$set", newDocument);

        col.updateOne(query, updateObject);
    }

    @Override
    public void close(){
        this.client.close();
    }
}
