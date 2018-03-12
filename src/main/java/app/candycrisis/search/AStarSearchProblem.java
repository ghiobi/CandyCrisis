package app.candycrisis.search;

import app.candycrisis.search.functions.*;

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

    private S initalState;

    public AStarSearchProblem(S initalState) {
        this.openQueue = new PriorityQueue<>();
        this.closedMap = new HashMap<>();

        this.initalState = initalState;
    }

    public AStarSearchProblem useHeuristicFunction(HeuristicFunction<S> heuristicFn) {
        this.heuristicFn = heuristicFn;
        return this;
    }

    public AStarSearchProblem useCostFunction(CostFunction<S, A> costFn) {
        this.costFn = costFn;
        return this;
    }

    public AStarSearchProblem useActionFunction(ActionFunction<S, A> actionFn) {
        this.actionFn = actionFn;
        return this;
    }

    public AStarSearchProblem useActionStateTransitionFunction(ActionStateTransitionFunction<S, A> actionStateTransitionFn) {
        this.actionStateTransitionFn = actionStateTransitionFn;
        return this;
    }

    public SearchResult search(GoalFunction<S, A> goal) {
        int estimation = this.heuristicFn.estimate(this.initalState);
        this.openQueue.add(new NodeState<>(this.initalState, null, 0, estimation, null));

        while (!this.openQueue.isEmpty()) {
            NodeState<S, A> current = this.openQueue.poll();

            if (goal.reached(current)) {
                return new SearchResult(current);
            }

            for (A action: this.actionFn.actionsFor(current.getState())) {
                S successorState = this.actionStateTransitionFn.apply(current.getState(), action);

                if (this.closedMap.containsKey(successorState.toString())) {
//                    NodeState<S, A> oldNode = this.closedMap.get(successorState.toString());
//
//                    if (oldNode.getTransitionCost() < current.getTransitionCost() + this.costFn.evaluate(successorState, action)) {
//
//                    }
                } else {
                    int successorCost = current.getTransitionCost() + this.costFn.evaluate(successorState, action);
                    int successorHeuristic = this.heuristicFn.estimate(successorState);

                    NodeState<S, A> node = new NodeState<>(successorState, action, successorCost, successorHeuristic, current);
                    this.openQueue.add(node);

                    this.closedMap.put(current.getState().toString(), current);
                }
            }
        }

        return null;
    }

    public class SearchResult {

        private NodeState<S, A> node;

        public SearchResult(NodeState<S, A> node) {
            this.node = node;
        }

        public List<A> solution() {
            NodeState<S, A> current = this.node;
            List<A> actions = new LinkedList<A>();

            while (!current.isRoot()) {
                actions.add(current.getAction());

                current = current.getParent();
            }

            return actions;
        }

    }

}
