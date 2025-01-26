import java.util.*;

public class Node {
    private List<Connection> connections;
    private String availablePermit;
    private int h;

    public List<Connection> getConnections() {
        return connections;
    }

    public Optional<String> getAvailablePermit() {
        return Optional.ofNullable(availablePermit);
    }

    public int getH() {
        return h;
    }

    public Node(String availablePermit, int h) {
        this.connections = new ArrayList<>();
        this.availablePermit = availablePermit;
        this.h = h;
    }

    public void addConnection(Connection connection) {
        connections.add(connection);
    }
}
