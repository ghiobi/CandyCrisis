package app.candycrisis.utils.search.functions;

public interface ActionFunction<S, A> {

    public Iterable<A> actionsFor(S state);

}
