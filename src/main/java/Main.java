
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.neo4j.driver.Config;
import org.neo4j.driver.exceptions.Neo4jException;

public class Main {

    public static void main(String ... args) {
        MongoClient client = MongoClients.create("mongodb+srv://testUser:testUser@cluster0.kql6i.mongodb.net/sampleDB?retryWrites=true&w=majority");

        try(mongodbTest mongodb = new mongodbTest(client)){
            //mongodb.insertPerson(client, "Simon", "1489123434s6s6", "14.10.2021", "simon@gmail.com");
            mongodb.insertArray(client);
        }
        catch(Exception exception){
            System.out.println("Array could not be inserted!");
        }



    /*
        String uri = "neo4j+s://bd8deffd.databases.neo4j.io:7687";

        String user = "neo4j";
        String password = "b1qmJ_kAC94PmkV-Pd01Hrf9zOLstS3St1MRgrqSVmM";

        try (neo4jTest app = new neo4jTest(uri, user, password, Config.defaultConfig())) {
            //app.createConnection("Alice", "Max", "KNOWS");
            //app.findPerson("Alice");
            app.createPerson("Max", "123456");
        }
        catch (Exception ex){
            throw ex;
        }

     */
    }
}
