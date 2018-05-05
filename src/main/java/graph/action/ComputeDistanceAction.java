package graph.action;

import graph.model.IGraph;

/**
 *
 * @author Daisy Wu
 */
public class ComputeDistanceAction implements IGraphAction {

    private IGraph graph;

    private int[] indexs;

    private double result;

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public ComputeDistanceAction(IGraph graph, int[] indexs) {
        this.graph = graph;
        this.indexs = indexs;
    }

    @Override
    public void execute() {
        result = graph.computeDistance(indexs);
    }

}
