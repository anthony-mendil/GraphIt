package actions.other;

import actions.GraphAction;
import graph.graph.Edge;
import graph.graph.FunctionMode;
import graph.graph.Vertex;
import graph.visualization.SyndromVisualisationViewer;
import gui.Values;

/**
 * Changes the mode either to "Ersteller"/"Bearbeiter"/ "Auswerter".
 */
public class ChangeGraphLanguageAction extends GraphAction {

    @Override
    public void action() {
        syndrom.getVv().repaint();
        syndrom.getVv2().repaint();
    }

    @Override
    public void undo() {
        //
    }
}
