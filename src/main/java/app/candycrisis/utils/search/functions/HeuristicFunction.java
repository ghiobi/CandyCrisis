package app.candycrisis.utils.search.functions;

public interface HeuristicFunction<S> {

    public double estimate(S state);

}
