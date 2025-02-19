import org.javatuples.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class InputParser {
    public static Pair<Node, Node> readFromFile(File cities, File connections)  throws FileNotFoundException {
        Map<String, Node> nodes = new HashMap<>();
        Scanner cityScanner = new Scanner(cities);
        cityScanner.useDelimiter(";|\\n");
        cityScanner.nextLine();
        while (cityScanner.hasNext()) {
            String key = cityScanner.next();
            int h = cityScanner.nextInt();
            String availablePermit = cityScanner.next();
            if (availablePermit.equals("NONE")||availablePermit.equals("NONE\r")) {
                availablePermit=null;
            }
            Node node = new Node(availablePermit, h);
            nodes.put(key, node);
        }
        Scanner connectionScanner = new Scanner(connections);
        connectionScanner.useDelimiter(";|\\n");
        connectionScanner.nextLine();
        while (connectionScanner.hasNext()) {
            Node firstNode = nodes.get(connectionScanner.next());
            Node secondNode = nodes.get(connectionScanner.next());
            int weight = connectionScanner.nextInt();
            String requiredPermit = connectionScanner.next();
            if (requiredPermit.equals("NONE")||requiredPermit.equals("NONE\r")) {
                requiredPermit=null;
            }
            firstNode.getConnections().add(new Connection(weight, requiredPermit, secondNode));
            secondNode.getConnections().add(new Connection(weight, requiredPermit, firstNode));
        }
        return new Pair<>(nodes.get("A"), nodes.get("B"));
    }
}
