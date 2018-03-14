package app.candycrisis.search;

public class NodeState<S, A> implements Comparable<NodeState<S, A>> {

    private NodeState<S, A> parent;

    private S state;

    private A action;

    private int transitionCost;

    private int heuristicCost;

    public NodeState(S state) {
        this(state, null, 0, 0, null);
    }

    public NodeState(S state, A action, int transitionCost, int heuristicCost, NodeState<S, A> parent) {
        this.state = state;
        this.action = action;
        this.transitionCost = transitionCost;
        this.heuristicCost = heuristicCost;
        this.parent = parent;
    }

    public void setTransitionCost(int transitionCost) {
        this.transitionCost = transitionCost;
    }

    public void setHeuristicCost(int heuristicCost) {
        this.heuristicCost = heuristicCost;
    }

    public int getTransitionCost() {
        return this.transitionCost;
    }

    public int getHeuristicCost() {
        return this.heuristicCost;
    }

    public int getTotalCost() {
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
        return Integer.compare(this.getTotalCost(), o.getTotalCost());
    }

}
