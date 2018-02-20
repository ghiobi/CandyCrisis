package app.candycrisis.utils.search;

import app.candycrisis.utils.search.functions.*;

import java.util.LinkedList;
import java.util.List;

public class AStarSearchProblem<S, A> {

    private HeuristicFunction<S> heuristicFn;

    private CostFunction<S, A> costFn;

    private ActionFunction<S, A> actionFn;

    private ActionStateTransitionFunction<S, A> actionStateTransitionFn;

    private NodeState<S, A> root;

    public AStarSearchProblem(S initalState) {
        this.root = new NodeState<>(initalState, null);
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
        GoalFunction goalFunction = goal;

        NodeState<S, A> current = this.root;

        while (!goal.reached(current)) {
            break;
        }

        return new SearchResult(current);
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
