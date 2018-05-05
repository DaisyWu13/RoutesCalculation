package graph.action;

import graph.model.IGraph;

/**
 *
 * @author Administrator
 */
public class DijkstraAction implements IGraphAction {

    private double result;

    private IGraph graph;

    private int srcIndex;
    private int destIndex;

    public DijkstraAction(IGraph graph, int srcIndex, int destIndex) {
        this.graph = graph;
        this.srcIndex = srcIndex;
        this.destIndex = destIndex;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    @Override
    public void execute() {
        result = graph.dijkstra(srcIndex, destIndex);
    }

}
