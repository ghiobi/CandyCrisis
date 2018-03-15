package app.candycrisis.search;

public class NodeState<S, A> implements Comparable<NodeState<S, A>> {

    private NodeState<S, A> parent;

    private S state;

    private A action;

    private double transitionCost;

    private double heuristicCost;

    public NodeState(S state) {
        this(state, null, 0, 0, null);
    }

    public NodeState(S state, A action, double transitionCost, double heuristicCost, NodeState<S, A> parent) {
        this.state = state;
        this.action = action;
        this.transitionCost = transitionCost;
        this.heuristicCost = heuristicCost;
        this.parent = parent;
    }

    public void setTransitionCost(double transitionCost) {
        this.transitionCost = transitionCost;
    }

    public void setHeuristicCost(double heuristicCost) {
        this.heuristicCost = heuristicCost;
    }

    public double getTransitionCost() {
        return this.transitionCost;
    }

    public double getHeuristicCost() {
        return this.heuristicCost;
    }

    public double getTotalCost() {
        return this.transitionCost + this.heuristicCost;
    }

    public A getAction() {
        return this.action;
    }

    public S getState() {
        return this.state;
    }

    public NodeState<S, A> getParent() {
        return this.parent;
    }

    public boolean isRoot() {
        return this.parent == null;
    }

    @Override
    public int compareTo(NodeState<S, A> o) {
        return Double.compare(this.getTotalCost(), o.getTotalCost());
    }

}
