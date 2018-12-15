package log_management.parameters.edit;

import graph.graph.Edge;
import graph.graph.Vertex;
import javafx.util.Pair;
import log_management.parameters.Param;
import lombok.Getter;

import java.awt.*;
import java.util.Map;

/**
 * Parameter object of the action EditEdgesColorLogAction.
 */
public class EditEdgesColorParam extends Param{
    /**
     * The list of edges and their old color.
     */
    @Getter
    private Map<Edge,Pair<Pair<Vertex,Vertex>,Color>> edgesOldColor;
    /**
     * The new color the edges should have.
     */
    @Getter
    private Color newColor;

    /**
     * Creates an parameterobject of its own class.
     * @param pEdgesOldColor The list of edges and their old color.
     * @param pNewColor The new color.
     */
    public EditEdgesColorParam(Map<Edge,Pair<Pair<Vertex,Vertex>,Color>> pEdgesOldColor, Color pNewColor) {
        this.edgesOldColor = pEdgesOldColor;
        this.newColor = pNewColor;
    }

}
