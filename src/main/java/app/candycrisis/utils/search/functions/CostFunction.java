package app.candycrisis.utils.search.functions;

public interface CostFunction<S, A> {

    public double evaluate(S state, A action);

}
