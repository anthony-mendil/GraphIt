package log_management.parameters.edit;

import javafx.util.Pair;
import log_management.parameters.Param;
import lombok.Getter;

import java.awt.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Parameterobject of the action EditVerticesFormLogAction.
 */
public class EditVerticesFormParam extends Param{
    /**
     * The set of vertices and their old shapes.
     */
    @Getter
    Map<Integer, Pair<String,Shape>> verticesShape;
    /**
     * The new shape of the vertices.
     */
    @Getter
    private Shape newShape;

    /**
     * Creates a parameterobject of its own class.
     * @param pVerticesShape The vertices and their old shape.
     * @param pNewShape The new shape.
     */
    public EditVerticesFormParam(Map<Integer, Pair<String,Shape>> pVerticesShape, Shape pNewShape) {
        this.verticesShape = pVerticesShape;
        this.newShape = pNewShape;
    }

    @Override
    public String convertToJson() {
        throw new UnsupportedOperationException();
    }
}
