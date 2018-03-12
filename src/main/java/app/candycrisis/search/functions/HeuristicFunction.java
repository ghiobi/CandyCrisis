package app.candycrisis.search.functions;

public interface HeuristicFunction<S> {

    public int estimate(S state);

}
