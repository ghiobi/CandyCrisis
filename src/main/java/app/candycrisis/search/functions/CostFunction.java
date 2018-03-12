package app.candycrisis.search.functions;

public interface CostFunction<S, A> {

    public int evaluate(S state, A action);

}
