package graph.action;

import graph.model.AbstractGraph;

/**
 *
 * @author Administrator
 */
public class DijkstraAction implements IGraphAction {

    private double result;

    private AbstractGraph graph;

    private int srcIndex;
    private int destIndex;

    public DijkstraAction(AbstractGraph graph, int srcIndex, int destIndex) {
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
