package app.candycrisis.search.functions;

public interface HeuristicFunction<S> {

    public double estimate(S state);

}
