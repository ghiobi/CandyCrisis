package app.candycrisis.utils.search.functions;

public interface ActionStateTransitionFunction<S, A> {

    public S apply(S state, A action);

}
