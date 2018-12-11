package actions.export_graph;

import actions.GraphAction;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/*
    Aktionsleiste -> Datei.. -> Exportieren als.. -> PDF
 */
public class ExportPdfAction extends GraphAction {

    /**
     * Actionhandling for exporting the graph as PDF
     */
    public ExportPdfAction(){

    }

    /**
     * Executes the defined behavior of the action.
     */
    @Override
    public void action() {
        throw new NotImplementedException();
    }

    /**
     * Reverts the action. The internal state of the graph is the same as before the action was executed.
     */
    @Override
    public void undo() {
        throw new NotImplementedException();
    }
}
