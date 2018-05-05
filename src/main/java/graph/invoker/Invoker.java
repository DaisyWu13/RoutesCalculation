package graph.invoker;

import graph.action.IGraphAction;

/**
 *
 * @author Daisy Wu
 */
public class Invoker {

    private IGraphAction action;

    public Invoker(IGraphAction action) {
        this.action = action;
    }

    public void runAction() {
        action.execute();
    }

}
