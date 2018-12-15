package log_management.parameters.move;


import javafx.util.Pair;
import log_management.parameters.Param;
import lombok.Getter;

import java.util.Map;

/**
 * parameter object for LayoutGraphLogAction, changes the position of the vertices
 */
public class LayoutParam extends Param {
    /**
     * the vertex ids with vertex annotation and new position
     */
    @Getter
    private Map<Integer, Pair<String, Integer>> vertices;
    /**
     * The old annotation of the vertex.
     */
    @Getter
    private String oldAnnotation;
    /**
     * The new annotation of the vertex.
     */
    @Getter
    private String newAnnotation;

    /**
     * Creates a parameter object of its own class.
     *
     * @param vertices the vertex ids with vertex annotation and new position
     */
    public LayoutParam(Map<Integer, Pair<String, Integer>> vertices) {
        this.vertices = vertices;
    }

}
