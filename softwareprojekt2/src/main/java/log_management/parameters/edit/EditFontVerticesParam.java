package log_management.parameters.edit;


import graph.graph.Vertex;
import javafx.util.Pair;
import log_management.parameters.Param;
import lombok.Getter;

import java.util.List;
import java.util.Map;

/**
 * Parameter object of the action EditFontLogAction.
 */
public class EditFontVerticesParam extends Param {
    /**
     * The set of vertices containing their old font.
     */
    @Getter
    private List<Vertex> pVertices;
    /**
     * The new font-type.
     */
    @Getter
    private String newFont;
    // Q:Does the font change everywhere or is for example a list of vertex id's needed?
    // A:Unfortunately no :( . But I am not actually sure about it, just pretending.

    /**
     * Creates a parameter object of its own class.
     * @param pVertices The vertices containing their old font.
     * @param pNewFont The new font-type.
     */
    public EditFontVerticesParam(List<Vertex> pVertices, String pNewFont) {
        this.pVertices = pVertices;
        this.newFont = pNewFont;
    }
    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }
}
