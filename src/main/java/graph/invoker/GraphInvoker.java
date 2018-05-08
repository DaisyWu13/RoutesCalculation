package graph.invoker;

import graph.action.IGraphAction;

/**
 *
 * @author Daisy Wu
 */
public class GraphInvoker {

    private IGraphAction action;

    public GraphInvoker(IGraphAction action) {
        this.action = action;
    }

    public void runAction() {
        action.execute();
    }

}
