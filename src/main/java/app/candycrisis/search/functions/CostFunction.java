package app.candycrisis.search.functions;

public interface CostFunction<S, A> {

    public double evaluate(S state, A action);

}
