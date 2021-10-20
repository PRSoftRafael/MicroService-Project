
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.neo4j.driver.Config;

public class Main {

    public static void main(String ... args) {

        /**
         * MongoDB setUp
         */
        MongoClient registeredClient = MongoClients.create("mongodb+srv://testUser:testUser@cluster0.kql6i.mongodb.net/RegisteredUsersDB?retryWrites=true&w=majority");
        UserManagement mongoDbUser = new UserManagement(registeredClient, "RegisteredUsersDB", "RegisteredUsersCol");

        /**
         * MongoDB createUser
         */
        mongoDbUser.createUser("Simon", "123456789", "20.10.2021", "simon@gmail.com");
        mongoDbUser.createUser("Max", "abc", "20.12.2021", "max@gmail.com");

        /**
         * MongoDB update user information
         */
        mongoDbUser.updateName(0, "Simon", "Rafael");
        mongoDbUser.updatePassword(0, "123456789", "987654321");
        mongoDbUser.updateDate(0, "20.10.2021", "30.10.2021");
        mongoDbUser.updateEmail(0, "simon@gmail.com", "rafael@gmail.com");

        //-----------------------------------------------------------------------

        /**
         * Neo4J setUp
         */

        String neo4jUri = "neo4j+s://bd8deffd.databases.neo4j.io:7687";
        String neo4jUser = "neo4j";
        String neo4jPassword = "b1qmJ_kAC94PmkV-Pd01Hrf9zOLstS3St1MRgrqSVmM";

        FollowRelation app = new FollowRelation(neo4jUri, neo4jUser, neo4jPassword, Config.defaultConfig());

        /**
         * Neo4J createUser
         */

        app.createPerson("Alice");
        app.createPerson("Max");


        /**
         * Neo4J follows relation
         */

        app.createFollowConnection("Alice", "Max");
    }
}
