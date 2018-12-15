package log_management.parameters.edit;

import graph.graph.Edge;
import graph.graph.Vertex;
import javafx.util.Pair;
import log_management.parameters.Param;
import lombok.Getter;

import java.io.Serializable;
import java.util.Map;

/**
 * Parameterobject for the Action EditFontSizeVerticesLogAction.
 */
public class EditFontSizeVerticesParam extends Param {
    /**
     * The set of the vertices, their annotation and their old sizes.
     */
    @Getter
    Map<Vertex,Pair<String,Integer>> verticesOldSize;
    /**
     * The new fontsize.
     */
    @Getter
    private int newFontSize;
    //Q: Does the font size change everywhere or is for example a list of vertex id's needed?
    //A: I think, that every fontsize of all vertices should be independent from each other.
    //   Therefore there must be a map or something kinda like this...

    /**
     * Creates a parameterobject of its own class.
     * @param pVerticesOldSize The vertices and their old font-size.
     * @param pNewFontSize The new font-size.
     */
    public EditFontSizeVerticesParam(Map<Vertex,Pair<String,Integer>> pVerticesOldSize, int pNewFontSize) {
        this.verticesOldSize = pVerticesOldSize;
        this.newFontSize = pNewFontSize;
    }
}
