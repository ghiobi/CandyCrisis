package app.candycrisis.utils.search.functions;

import app.candycrisis.utils.search.NodeState;

public interface GoalFunction<S, A> {

    public boolean reached(NodeState<S, A> state);

}
