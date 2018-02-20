package app.candycrisis.utils.search;

import java.util.List;

public class NodeState<S, A> {

    private NodeState parent;

    private List<NodeState> children;

    private S state;

    private A action;

    private double transitionCost;

    private double estimationCost;

    public NodeState(S state, A action) {
        this.state = state;
        this.action = action;
        this.transitionCost = 0;
        this.estimationCost = 0;
    }

    public void setTransitionCost(double transitionCost) {
        this.transitionCost = transitionCost;
    }

    public void setEstimationCost(double estimationCost) {
        this.estimationCost = estimationCost;
    }

    public double getTotalCost() {
        return this.transitionCost + this.estimationCost;
    }

    public A getAction() {
        return this.action;
    }

    public S getState() {
        return this.state;
    }

    public void addChildren(NodeState state) {
        this.children.add(state);
    }

    public List<NodeState> getChildren() {
        return this.children;
    }

    public NodeState getParent() {
        return this.parent;
    }

    public boolean isRoot() {
        return this.parent == null;
    }

}
