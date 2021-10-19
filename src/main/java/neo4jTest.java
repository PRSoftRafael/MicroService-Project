import org.neo4j.driver.*;
import org.neo4j.driver.Record;
import org.neo4j.driver.exceptions.Neo4jException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.neo4j.driver.Config.TrustStrategy.trustAllCertificates;

public class neo4jTest implements AutoCloseable {
    private static final Logger LOGGER = Logger.getLogger(neo4jTest.class.getName());
    private final Driver driver;
    private boolean exists;

    public neo4jTest(String uri, String user, String password, Config config) {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password), config);
    }

    @Override
    public void close() {
        driver.close();
    }

    public void createPerson(final String personName, final String password) {
        String createPersonQuery = "CREATE (n:Person {name: $personName, password: $password}) RETURN n";

        Map<String, Object> params = new HashMap<>();
        params.put("personName", personName);
        params.put("password", password);

        try(Session session = driver.session()) {
            Record record = session.writeTransaction(tx -> {
                Result result = tx.run(createPersonQuery, params);
                return result.single();
            });
            System.out.println("New person " + record.get("name").asString());
        }
        catch (Neo4jException ex) {
            LOGGER.log(Level.SEVERE, createPersonQuery + " raised an exception", ex);
            throw ex;
        }
    }

    public void createFollowConnection(final String person1Name, final String person2Name) {
        String createConnectionQuery = "MATCH (a:Person {name:$person1Name}), (b:Person {name:$person2Name}) "
                + "WHERE a.name = $person1Name AND b.name = $person2Name "
                + "CREATE (a)-[:FOLLOWS]->(b) "
                + "RETURN a, b";

        Map<String, Object> params = new HashMap<>();
        params.put("person1Name", person1Name);
        params.put("person2Name", person2Name);

        try(Session session = driver.session()) {
            Record record = session.writeTransaction(tx -> {
                Result result = tx.run(createConnectionQuery, params);
                return result.single();
            });
            System.out.println(String.format("A new connection was created between: "
                    + record.get("a").get("name") + " and " + record.get("b").get("name")));
        }
        catch (Neo4jException ex) {
            LOGGER.log(Level.SEVERE, createConnectionQuery + " raised an exception", ex);
            throw ex;
        }
    }
}
