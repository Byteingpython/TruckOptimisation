import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class State {
    public State(int h, List<String> permits, Node node, State parent, int cost) {
        this.h = h;
        this.permits = permits;
        this.node = node;
        this.parent = parent;
        this.cost = cost;
    }

    private int h;
    private List<String> permits;
    private Node node;
    private State parent;
    private int cost;


    public List<State> reachableStates() {
        List<State> states = new ArrayList();
        Optional<String> availablePermit = node.getAvailablePermit();
        if (availablePermit.isPresent()&&!permits.contains(availablePermit.get())) {
            List<String> newPermits = new ArrayList<>(permits);
            newPermits.add(availablePermit.get());
            // TODO: Replace magic number with actual number
            State withPermit = new State(h, newPermits, node, this, cost+=10);
            states.addAll(withPermit.reachableStates());
        }
        for (Connection connection:node.getConnections()) {
            Optional<String> requiredPermit = connection.getRequiredPermit();
            if (requiredPermit.isEmpty()||permits.contains(requiredPermit.get())) {
                states.add(new State(h,permits, connection.getDestination(), this,cost+connection.getWeight()));
            }
        }
        return states;
    }
}
