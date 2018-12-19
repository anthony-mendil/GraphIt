package log_management.parameters.edit;


import graph.graph.Vertex;
import javafx.util.Pair;
import log_management.parameters.Param;
import lombok.Data;
import lombok.Getter;

import java.util.List;
import java.util.Map;

/**
 * Parameter object of the action EditFontLogAction.
 */
@Data
public class EditFontVerticesParam extends Param {
    /**
     * The set of vertices containing their old font.
     */
    @Getter
    private Map<Vertex,String> oldVertices;
    /**
     * The set of vertices containing their new font.
     */
    @Getter
    private Map<Vertex,String> newVertices;

    // Q:Does the font change everywhere or is for example a list of vertex id's needed?
    // A:Unfortunately no :( . But I am not actually sure about it, just pretending.

    /**
     * Creates a parameter object of its own class.
     * @param pOldVertices The vertices containing their old font.
     * @param pNewVertices The vertices containing their new font.
     */
    public EditFontVerticesParam(Map<Vertex,String> pOldVertices, Map<Vertex,String> pNewVertices) {
        this.oldVertices = pOldVertices;
        this.newVertices = pNewVertices;
    }
    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }
}
