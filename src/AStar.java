import java.util.*;

public class AStar {
    public static Optional<State> aStar(Node start, Node goal) {
        State initialState = new State(new ArrayList<>(), start, null, 0);
        PriorityQueue<State> frontier = new PriorityQueue<>();
        Set<State> reached = new HashSet<>();
        frontier.add(initialState);
        reached.add(initialState);
        while (!frontier.isEmpty()) {
            State state = frontier.poll();
            if (state.getNode().equals(goal)) {
                return Optional.of(state);
            }
            for (State childState:state.reachableStates()) {
                if (!reached.contains(childState)) {
                    reached.add(childState);
                    frontier.add(childState);
                }
            }
        }
        return Optional.empty();
    }

}
