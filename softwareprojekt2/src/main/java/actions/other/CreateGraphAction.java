package actions.other;

import actions.GraphAction;
import graph.graph.SyndromGraph;

/**
 * Creates a new graph.
 */
public class CreateGraphAction extends GraphAction {
    private String graphName;

    /**
     * Constructor in case the user creates a new graph.
     * @param pGraphName
     */
    public CreateGraphAction(String pGraphName){
    }

    @Override
    @SuppressWarnings("unchecked")
    public void action() {
        SyndromGraph g = (SyndromGraph) getLayout().getGraph();
        // openInput
        String gotInput = "";
        g.setName(gotInput);
        getLayout().setGraph(g);
        setGraph(g);
    }

    @Override
    public void undo() {
        //
    }
}
