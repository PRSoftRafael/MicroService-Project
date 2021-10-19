
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.neo4j.driver.Config;
import org.neo4j.driver.exceptions.Neo4jException;

public class Main {

    public static void main(String ... args) {

        /*
        MongoClient registeredClient = MongoClients.create("mongodb+srv://testUser:testUser@cluster0.kql6i.mongodb.net/RegisteredPeopleDB?retryWrites=true&w=majority");

        try(mongodbTest mongodb = new mongodbTest(registeredClient)){
            mongodb.mongodbCreatePerson(registeredClient, "Simon", "1489123434s6s6", "14.10.2021", "simon@gmail.com");
        }
        catch(Exception exception){
            System.out.println("Person could not be inserted!");
        }


        MongoClient postClient = MongoClients.create("mongodb+srv://testUser:testUser@cluster0.kql6i.mongodb.net/PostsDB?retryWrites=true&w=majority");

        try(mongodbTest mongodb = new mongodbTest(postClient)){
            mongodb.mongodbInsertPost(postClient, 0,"Test");
        }
        catch(Exception exception){
            System.out.println("Post could not be inserted!");
        }

         */

        MongoClient registeredClient = MongoClients.create("mongodb+srv://testUser:testUser@cluster0.kql6i.mongodb.net/RegisteredPeopleDB?retryWrites=true&w=majority");

        try(mongodbTest mongodb = new mongodbTest(registeredClient)){
            mongodb.findPost(registeredClient, "Hello World", 0);
        }
        catch(Exception exception){
            System.out.println("Post could not be inserted!");
        }




        /*

        String uri = "neo4j+s://bd8deffd.databases.neo4j.io:7687";

        String user = "neo4j";
        String password = "b1qmJ_kAC94PmkV-Pd01Hrf9zOLstS3St1MRgrqSVmM";

        try (neo4jTest app = new neo4jTest(uri, user, password, Config.defaultConfig())) {
            app.createFollowConnection("Alice", "Max");
            //app.createPerson("Alice", "123456");
        }
        catch (Exception ex){
            System.out.println("Person could not be created!");
        }
        */
    }
}
