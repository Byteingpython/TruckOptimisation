import java.util.*;

public class AStar {
    public static Optional<State> aStar(Node start, Node goal) {
        State initialState = new State(new ArrayList<>(), start, null, 0);
        PriorityQueue<State> frontier = new PriorityQueue<>();
        HashMap<State, State> reached = new HashMap<>();
        frontier.add(initialState);
        reached.put(initialState, initialState);
        while (!frontier.isEmpty()) {
            State state = frontier.poll();
            if (state.getNode().equals(goal)) {
                return Optional.of(state);
            }
            for (State childState:state.reachableStates()) {
                if (!reached.containsKey(childState)||childState.getCost()<reached.get(childState).getCost()) {
                    reached.put(childState, childState);
                    frontier.add(childState);
                }
            }
        }
        return Optional.empty();
    }

}
