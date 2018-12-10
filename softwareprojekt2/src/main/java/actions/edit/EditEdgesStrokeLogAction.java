package actions.edit;

import actions.LogAction;
import javafx.scene.shape.StrokeType;
import log_management.LogDatabaseManager;
import log_management.LogEntryName;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import log_management.parameters.edit.EditEdgesSizeParam;

/*
    ([shift+]linksclick, bereich markieren) Kanten markieren -> GUI-Button Kantendicke
    ([shift+]linksclick, bereich markieren) Kanten markieren -> rechtsclick auf Kante -> Drop-Down-Menü -> Kantendicke
        -> neuer Drop-Down-Menü
 */
public class EditEdgesStrokeLogAction extends LogAction {
    /**
     *
     */
    public EditEdgesStrokeLogAction(graph.graph.StrokeType strokeType) {
        super(LogEntryName.EDIT_EDGES_SIZE);
        throw new NotImplementedException();
    }

    @Override
    public void action() {
        throw new NotImplementedException();
    }

    @Override
    public void undo() {
        throw new NotImplementedException();
    }

    @Override
    public void createParameter() {
        throw new NotImplementedException();
    }
}
