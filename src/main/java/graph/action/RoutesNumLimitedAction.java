package graph.action;

import graph.model.IGraph;

/**
 *
 * @author Daisy Wu
 */
public class RoutesNumLimitedAction implements IGraphAction {

    private IGraph graph;
    private int srcIndex;
    private int destIndex;
    private double distance;
    private int result;

    public RoutesNumLimitedAction(IGraph graph, int srcIndex, int destIndex, double distance) {
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
