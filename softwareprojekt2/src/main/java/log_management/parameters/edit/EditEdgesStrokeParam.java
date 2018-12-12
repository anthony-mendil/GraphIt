package log_management.parameters.edit;

import graph.graph.StrokeType;
import javafx.util.Pair;
import log_management.parameters.Param;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class EditEdgesStrokeParam extends Param implements Serializable {

    Map<Integer, List<Pair<Integer, String>>> edges;
    private Map<Integer, StrokeType> oldStrokes;
    private StrokeType newStroke;

    public EditEdgesStrokeParam(Map<Integer, List<Pair<Integer, String>>> edges, Map<Integer, StrokeType> oldStrokes,
                                StrokeType newStroke) {
        this.edges = edges;
        this.oldStrokes = oldStrokes;
        this.newStroke = newStroke;
    }

    @Override
    public String convertToJson() {
        throw new UnsupportedOperationException();
    }
}
