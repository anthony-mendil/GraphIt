package log_management.parameters.edit;

import graph.graph.Edge;
import graph.graph.Vertex;
import javafx.util.Pair;
import log_management.parameters.Param;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Parameter object for the Action EditFontSizeVerticesLogAction.
 */
public class EditFontSizeVerticesParam extends Param {
    /**
     * The set of the vertices and their old font-size.
     */
    @Getter
    Map<Vertex,Integer> oldVertices;
    /**
     * The set of the vertices and their new font-size.
     */
    @Getter
    Map<Vertex,Integer> newVertices;
    //Q: Does the font size change everywhere or is for example a list of vertex id's needed?
    //A: I think, that every font size of all vertices should be independent from each other.
    //   Therefore there must be a map or something kinda like this...

    /**
     * Creates a parameter object of its own class.
     * @param pOldVertices The vertices containing their old font-size.
     * @param pNewVertices The vertices containing their new font-size.
     */
    public EditFontSizeVerticesParam(Map<Vertex,Integer> pOldVertices, Map<Vertex,Integer> pNewVertices) {
        this.oldVertices = pOldVertices;
        this.newVertices = pNewVertices;
    }
    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }
}
