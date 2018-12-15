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
     * The list of the vertices
     */
    @Getter
    List<Vertex> pVertices;
    /**
     * The new font size.
     */
    @Getter
    private int newFontSize;
    //Q: Does the font size change everywhere or is for example a list of vertex id's needed?
    //A: I think, that every font size of all vertices should be independent from each other.
    //   Therefore there must be a map or something kinda like this...

    /**
     * Creates a parameter object of its own class.
     * @param pVertices The vertices containing their old font-size.
     * @param pNewFontSize The new font-size.
     */
    public EditFontSizeVerticesParam(List<Vertex> pVertices, int pNewFontSize) {
        this.pVertices = pVertices;
        this.newFontSize = pNewFontSize;
    }
    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }
}
