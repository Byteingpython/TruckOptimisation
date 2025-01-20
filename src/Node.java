import java.util.List;
import java.util.Optional;

public class Node {
    private List<Connection> connections;
    private String availablePermit;

    public List<Connection> getConnections() {
        return connections;
    }

    public Optional<String> getAvailablePermit() {
        return Optional.ofNullable(availablePermit);
    }
}
