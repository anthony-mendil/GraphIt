package log_management.parameters.edit;


import graph.graph.Vertex;
import javafx.util.Pair;
import log_management.parameters.Param;
import lombok.Getter;
import java.util.Map;

/**
 * Parameter object of the action EditFontLogAction.
 */
public class EditFontVerticesParam extends Param {
    /**
     * The set of vertices and their old font.
     */
    @Getter
    private Map<Vertex, Pair<String,String>> verticesOldFont;
    /**
     * The new font-type.
     */
    @Getter
    private String newFont;
    // Q:Does the font change everywhere or is for example a list of vertex id's needed?
    // A:Unfortunately no :( . But I am not actually sure about it, just pretending.

    /**
     * Creates a parameterobject of its own class.
     * @param pVerticesOldFont The vertices and their old font.
     * @param pNewFont The new font-type.
     */
    public EditFontVerticesParam(Map<Vertex, Pair<String,String>> pVerticesOldFont, String pNewFont) {
        this.verticesOldFont = pVerticesOldFont;
        this.newFont = pNewFont;
    }
}
