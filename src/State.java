import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class State implements Comparable<State>{
    public State(List<String> permits, Node node, State parent, int cost) {
        this.permits = permits;
        this.node = node;
        this.parent = parent;
        this.cost = cost;
    }


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
            State withPermit = new State(newPermits, node, this, cost+=10);
            states.addAll(withPermit.reachableStates());
        }
        for (Connection connection:node.getConnections()) {
            Optional<String> requiredPermit = connection.getRequiredPermit();
            if (requiredPermit.isEmpty()||permits.contains(requiredPermit.get())) {
                states.add(new State(permits, connection.getDestination(), this,cost+connection.getWeight()));
            }
        }
        return states;
    }

    @Override
    public int compareTo(State o) {
        return getTotalCost().compareTo(o.getTotalCost());
    }

    public Integer getTotalCost() {
        return cost+node.getH();
    }

    public Node getNode() {
        return node;
    }

    public State getParent() {
        return parent;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this==o) {
            return true;
        }
        if (o==null || getClass()!=o.getClass()) {
            return false;
        }
        State state = (State) o;
        return permits.equals(state.permits) && node.equals(state.node);
    }

    @Override
    public int hashCode() {
        return Objects.hash(permits, node);
    }
}
