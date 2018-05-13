package graph.action;

import graph.model.AbstractGraph;

/**
 *
 * @author Daisy Wu
 */
public class ComputeDistanceAction implements IGraphAction {

    private AbstractGraph graph;

    private int[] indexs;

    private double result;

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public ComputeDistanceAction(AbstractGraph graph, int[] indexs) {
        this.graph = graph;
        this.indexs = indexs;
    }

    @Override
    public void execute() {
        result = graph.computeDistance(indexs);
    }

}
