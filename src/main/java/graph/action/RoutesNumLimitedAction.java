package graph.action;

import graph.model.AbstractGraph;

/**
 *
 * @author Daisy Wu
 */
public class RoutesNumLimitedAction implements IGraphAction {

    private AbstractGraph graph;
    private int srcIndex;
    private int destIndex;
    private double distance;
    private int result;

    public RoutesNumLimitedAction(AbstractGraph graph, int srcIndex, int destIndex, double distance) {
        this.graph = graph;
        this.srcIndex = srcIndex;
        this.destIndex = destIndex;
        this.distance = distance;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public void execute() {
        result = graph.routesNumLimitedByDistance(srcIndex, destIndex, distance);
    }

}
