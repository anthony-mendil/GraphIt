package log_management.parameters.edit;

import graph.graph.Vertex;
import graph.graph.VertexShapeType;
import gui.Values;
import gui.properties.Language;
import log_management.parameters.EnumNameCreator;
import log_management.parameters.Param;
import log_management.parameters.SyndromObjectPrinter;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Parameter object of the action EditVerticesFormLogAction.
 */
@Data
public class EditVerticesFormParam extends Param implements Serializable {
    @Getter
    private List<Vertex> oldVertices;

    @Getter
    private List<VertexShapeType> oldShapeTypes;

    @Getter
    private List<Vertex> newVertices;

    @Getter
    private List<VertexShapeType> newShapeTypes;

    /**
     * Creates a vertices object of its own class.
     * @param pOldVertices The vertices containing their old shape/annotation.
     * @param pNewVertices The vertices containing their new shape/annotation.
     */
    public EditVerticesFormParam(Map<Vertex,VertexShapeType> pOldVertices, Map<Vertex,VertexShapeType> pNewVertices) {
        oldVertices = new ArrayList<>();
        oldShapeTypes = new ArrayList<>();
        newVertices = new ArrayList<>();
        newShapeTypes = new ArrayList<>();

        pOldVertices.forEach((v, s) -> {
            oldVertices.add(v);
            oldShapeTypes.add(s);
        });
        pNewVertices.forEach((v, s) -> {
            newVertices.add(v);
            newShapeTypes.add(s);
        });
    }

    @Override
    public String prettyPrint() {
        Language language = Values.getInstance().getGuiLanguage();
        String information = "";
        if (language == Language.ENGLISH) {
            information += "Symptoms changed: ";
            for (int i = 0; i < oldVertices.size(); i++) {
                information += SyndromObjectPrinter.vertexPrintEnglish(oldVertices.get(i)) + ". "
                        + "Old shape: "
                        + EnumNameCreator.vertexShapeTypeTranslator(oldShapeTypes.get(i), language)
                        + ", new shape: "
                        + EnumNameCreator.vertexShapeTypeTranslator(newShapeTypes.get(i), language) + ". ";
            }
        } else {
            information += "Veränderte Symptome: ";
            for (int i = 0; i < oldVertices.size(); i++) {
                information += SyndromObjectPrinter.vertexPrintGerman(oldVertices.get(i)) + ". "
                        + "Alte Form: "
                        + EnumNameCreator.vertexShapeTypeTranslator(oldShapeTypes.get(i), language)
                        + ", neue Form: "
                        + EnumNameCreator.vertexShapeTypeTranslator(newShapeTypes.get(i), language) + ". ";
            }
        }
        return information;
    }

    public Map<Vertex,VertexShapeType> getOldVertices() {
        Map<Vertex, VertexShapeType> map = new HashMap<>();
        for (int i = 0; i < oldVertices.size(); i++) {
            map.put(oldVertices.get(i), oldShapeTypes.get(i));
        }
        return map;
    }

    public Map<Vertex,VertexShapeType> getNewVertices() {
        Map<Vertex, VertexShapeType> map = new HashMap<>();
        for (int i = 0; i <newVertices.size(); i++) {
            map.put(newVertices.get(i), newShapeTypes.get(i));
        }
        return map;
    }
}
