package app.candycrisis.search.functions;

import app.candycrisis.search.NodeState;

public interface GoalFunction<S, A> {

    public boolean reached(NodeState<S, A> state);

}
