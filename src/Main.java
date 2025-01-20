import org.javatuples.Pair;

import javax.swing.text.html.Option;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Iterator<File> files = Arrays.stream(new File("test").listFiles()).sorted().iterator();
        while (files.hasNext()) {
            File cities = files.next();
            File connections = files.next();
            Pair<Node, Node> nodes = Node.getFromFile(cities, connections);
            Optional<State> result = AStar.aStar(nodes.getValue0(), nodes.getValue1());
            if (result.isPresent()) {
                System.out.println(result.get().getCost());
            } else {
                System.out.println("No result");
            }
        }
    }
}