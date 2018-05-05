package graph.action;

import graph.model.IGraph;

/**
 *
 * @author Daisy Wu
 */
public class RoutesNumAction implements IGraphAction {

    private IGraph graph;
    private int srcIndex;
    private int destIndex;
    private int level;
    private boolean total;
    private int result;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public RoutesNumAction(IGraph graph, int srcIndex, int destIndex, int level, boolean total) {
        this.graph = graph;
        this.srcIndex = srcIndex;
        this.destIndex = destIndex;
        this.level = level;
        this.total = total;
    }

    @Override
    public void execute() {
        result = graph.routesNum(srcIndex, destIndex, level, total);
    }

}
