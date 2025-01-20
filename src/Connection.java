import java.util.List;
import java.util.Optional;

public class Connection {
    private int weight;
    private String requiredPermit;
    private Node destination;

    public Optional<String> getRequiredPermit() {
        return Optional.ofNullable(requiredPermit);
    }

    public int getWeight() {
        return weight;
    }

    public Node getDestination() {
        return destination;
    }
}
