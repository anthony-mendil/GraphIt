package log_management.parameters.move;


import graph.graph.Vertex;
import gui.Values;
import gui.properties.Language;
import log_management.parameters.Param;
import lombok.Data;
import lombok.Getter;

import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.Map;

/**
 * Parameter object for LayoutSphereGraphLogAction, changes the position of the vertices.
 */
@Data
public class LayoutVerticesParam extends Param implements Serializable {
    /**
     * The vertices containing their old position.
     */
    @Getter
    private Map<Vertex, Point2D> oldVertices;

    /**
     * Creates a vertices object of its own class.
     *
     * @param pOldPosition Map of vertices containing their old positions.
     */
    public LayoutVerticesParam(Map<Vertex, Point2D> pOldPosition) {
        this.oldVertices = pOldPosition;
    }
    @Override
    public String toString() {
        Language language = Values.getInstance().getGuiLanguage();
        if (language == Language.ENGLISH) {
            return "The symptoms were automatically positioned";
        } else {
            return "Die Symptome wurden automatisch angeordnet";
        }
    }
}
