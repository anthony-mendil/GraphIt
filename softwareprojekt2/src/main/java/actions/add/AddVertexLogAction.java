package actions.add;

import actions.GraphAction;
import actions.LogAction;
import edu.uci.ics.jung.algorithms.layout.Layout;
import graph.graph.Sphere;
import graph.graph.SyndromGraph;
import log_management.LogEntryName;
import log_management.parameters.add.AddVertexParam;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.geom.Point2D;

/*
    GUI Button Symptom hinzufügen
    rechtsclick auf Sphäre -> Drop-Down-Menü -> Symptom hinzufügen
 */
/**
 * The action adds a vertex to the graph. A vertex is always bound to a sphere.
 */
public class AddVertexLogAction extends LogAction {
    private Point2D e;
    private SyndromGraph graph;
    private Sphere sphere;

    /**
     * Constructor in case the user clicks on a blank space to create a vertex.
     * @param point the coordinates where the vertex should be added
     */
    public AddVertexLogAction(Point2D point) {
        super(LogEntryName.ADD_VERTEX);
        throw new NotImplementedException();
    }
    /**
     * Constructor which will be used to realize the undo-method of RemoveVertexLogAction.
     * @param pAddVertxParam
     * The used parameters.
     */
    public AddVertexLogAction(AddVertexParam pAddVertxParam) {
        super(LogEntryName.ADD_VERTEX);
        throw new NotImplementedException();
    }


    @Override
    @SuppressWarnings("unchecked")
    public void action() {
        throw new NotImplementedException();
    }

    @Override
    public void undo() {
        throw new NotImplementedException();
    }

}