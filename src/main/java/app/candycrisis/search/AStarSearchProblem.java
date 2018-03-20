package app.candycrisis.search;

import app.candycrisis.search.functions.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class AStarSearchProblem<S, A> {

    private HeuristicFunction<S> heuristicFn;

    private CostFunction<S, A> costFn;

    private ActionFunction<S, A> actionFn;

    private ActionStateTransitionFunction<S, A> actionStateTransitionFn;

    private PriorityQueue<NodeState<S, A>> openQueue;

    private HashMap<String, NodeState<S, A>> openMap;

    private HashMap<String, NodeState<S, A>> closedMap;

    private int iterations = 0;

    /**
     * Initializes an AStar search problem with an initial state.
     *
     * @param initalState a state
     */
    public AStarSearchProblem(S initalState) {
        this.openQueue = new PriorityQueue<>(100000);
        this.closedMap = new HashMap<>(100000);
        this.openMap = new HashMap<>(100000);

        NodeState<S, A> node = new NodeState<>(initalState);
        this.openQueue.add(node);
        this.openMap.put(node.getState().toString(), node);
    }

    /**
     * Closure for getting the estimation of a state.
     *
     * @param heuristicFn the function
     * @return AStarSearchProblem
     */
    public AStarSearchProblem<S, A> useHeuristicFunction(HeuristicFunction<S> heuristicFn) {
        this.heuristicFn = heuristicFn;
        return this;
    }

    /**
     * Closure for getting the cost of transitioning to the next step.
     *
     * @param costFn the function
     * @return AStarSearchProblem
     */
    public AStarSearchProblem<S, A> useCostFunction(CostFunction<S, A> costFn) {
        this.costFn = costFn;
        return this;
    }

    /**
     * Closure for getting a list of actions.
     *
     * @param actionFn the function
     * @return AStarSearchProblem
     */
    public AStarSearchProblem<S, A> useActionFunction(ActionFunction<S, A> actionFn) {
        this.actionFn = actionFn;
        return this;
    }

    /**
     * Closure for applying the action to a state.
     *
     * @param actionStateTransitionFn the function
     * @return AStarSearchProblem
     */
    public AStarSearchProblem<S, A> useActionStateTransitionFunction(ActionStateTransitionFunction<S, A> actionStateTransitionFn) {
        this.actionStateTransitionFn = actionStateTransitionFn;
        return this;
    }

    /**
     * Performs the A* search algorithm.
     *
     * @param goal the goal function
     * @return returns the SearchResult object containing the end node.
     */
    public SearchResult search(GoalFunction<S, A> goal) {
        NodeState<S, A> current = null;
        NodeState<S, A> successor = null;

        while (!this.openQueue.isEmpty()) {
            current = this.openQueue.poll();

            while (!this.openMap.containsKey(current.getState().toString())) {
                current = this.openQueue.poll();
            }

            this.openMap.remove(current.getState().toString());

            if (goal.reached(current)) {
                break;
            }

            for (A action: this.actionFn.actionsFor(current.getState())) {
                S successorState = this.actionStateTransitionFn.apply(current.getState(), action);

                double successorCost = current.getTransitionCost() + this.costFn.evaluate(successorState, action);
                double successorHeuristic = this.heuristicFn.estimate(successorState);

                NodeState<S, A> node = new NodeState<>(successorState, action, successorCost, successorHeuristic, current);

                if (goal.reached(node)) {
                    return new SearchResult(node, iterations);
                }

                successor = this.openMap.get(node.getState().toString());
                if (successor != null &&
                        successor.getTotalCost() < node.getTotalCost()) {
                    continue;
                }

                successor = this.closedMap.get(node.getState().toString());
                if (successor != null &&
                        successor.getTotalCost() < node.getTotalCost()) {
                    continue;
                }

                this.openQueue.add(node);
                this.openMap.put(node.getState().toString(), node);
            }

            iterations++;
            this.closedMap.put(current.getState().toString(), current);
        }

        return new SearchResult(current, iterations);
    }

    public class SearchResult {

        private NodeState<S, A> node;

        private int iterations;

        /**
         * Initializes a search result object contain methods for generating solutions.
         *
         * @param node the end goal node
         */
        private SearchResult(NodeState<S, A> node, int iterations) {
            this.node = node;
            this.iterations = iterations;
        }

        /**
         * Returns the solution action path.
         *
         * @return List<A> actions.
         */
        public List<A> solution() {
            NodeState<S, A> current = this.node;
            List<A> actions = new LinkedList<A>();

            while (!current.isRoot()) {
                actions.add(current.getAction());

                current = current.getParent();
            }

            Collections.reverse(actions);
            return actions;
        }

        public int iterations() {
            return this.iterations;
        }

    }

}
