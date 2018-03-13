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

    private HashMap<String, NodeState<S, A>> closedMap;

    /**
     * Initializes an AStar search problem with an initial state.
     *
     * @param initalState a state
     */
    public AStarSearchProblem(S initalState) {
        this.openQueue = new PriorityQueue<>(100000);
        this.closedMap = new HashMap<>(100000);

        this.openQueue.add(new NodeState<>(initalState));
    }

    /**
     * Closure for getting the estimation of a state.
     *
     * @param heuristicFn the function
     * @return AStarSearchProblem
     */
    public AStarSearchProblem useHeuristicFunction(HeuristicFunction<S> heuristicFn) {
        this.heuristicFn = heuristicFn;
        return this;
    }

    /**
     * Closure for getting the cost of transitioning to the next step.
     *
     * @param costFn the function
     * @return AStarSearchProblem
     */
    public AStarSearchProblem useCostFunction(CostFunction<S, A> costFn) {
        this.costFn = costFn;
        return this;
    }

    /**
     * Closure for getting a list of actions.
     *
     * @param actionFn the function
     * @return AStarSearchProblem
     */
    public AStarSearchProblem useActionFunction(ActionFunction<S, A> actionFn) {
        this.actionFn = actionFn;
        return this;
    }

    /**
     * Closure for applying the action to a state.
     *
     * @param actionStateTransitionFn the function
     * @return AStarSearchProblem
     */
    public AStarSearchProblem useActionStateTransitionFunction(ActionStateTransitionFunction<S, A> actionStateTransitionFn) {
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

        while (!this.openQueue.isEmpty()) {
            current = this.openQueue.poll();

            if (goal.reached(current)) {
                break;
            }

            for (A action: this.actionFn.actionsFor(current.getState())) {
                S successorState = this.actionStateTransitionFn.apply(current.getState(), action);

                if (!this.closedMap.containsKey(successorState.toString())) {
                    int successorCost = current.getTransitionCost() + this.costFn.evaluate(successorState, action);
                    int successorHeuristic = this.heuristicFn.estimate(successorState);

                    NodeState<S, A> node = new NodeState<>(successorState, action, successorCost, successorHeuristic, current);
                    this.openQueue.add(node);

                    this.closedMap.put(current.getState().toString(), current);
                }
            }
        }

        return new SearchResult(current);
    }

    public class SearchResult {

        private NodeState<S, A> node;

        /**
         * Initializes a search result object contain methods for generating solutions.
         *
         * @param node the end goal node
         */
        private SearchResult(NodeState<S, A> node) {
            this.node = node;
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

    }

}
