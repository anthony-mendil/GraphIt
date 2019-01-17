package log_management;

import actions.LogEntryName;
import gui.Values;
import gui.properties.Language;
import log_management.parameters.activate_deactivate.*;
import log_management.parameters.add_remove.*;
import log_management.parameters.edit.*;
import log_management.parameters.move.*;
import log_management.tables.Log;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class LogToStringConverter {

    public static String convert(Log log) {
        Language language = Values.getInstance().getGuiLanguage();
        if (language == Language.GERMAN) {
            try {
                return "Id: " + log.getId() + convertLogEntryNameGerman(log.getLogEntryName()) +
                        "Typ der Aktion: " + parametersPrint(log.getParameters(), log.getLogEntryName()) + " Zeit: " + log.getTime().toString();
            } catch (IOException e) {
                throw new IllegalStateException();
            }
        }
        else if (language == Language.ENGLISH) {
            try {
                return "Id: " + log.getId() + convertLogEntryNameGerman(log.getLogEntryName()) +
                        "Type of Action: " + parametersPrint(log.getParameters(), log.getLogEntryName()) + " Time: " + log.getTime().toString();
            } catch (IOException e) {
                throw new IllegalArgumentException();
            }
        }
        else {
            throw new IllegalStateException();
        }
    }

    private static String convertLogEntryNameGerman(LogEntryName logEntryName) {
        switch (logEntryName) {
            case ACTIVATE_HIGHLIGHT:
                return "Hervorhebung";
            case ACTIVATE_FADEOUT:
                return "Ausblenden";
            case ADD_ANCHOR_POINTS:
                return "Hinzufügen von Ankerpunkten";
            case ACTIVATE_ANCHOR_POINTS_FADEOUT:
                return "Ausblenden von Ankerpunkten";
            case ADD_EDGES:
                return "Entfernen von Realtionen rückgängig gemacht";
            case ADD_SPHERE:
                return "Hinzufügen einer Sphäre";
            case MOVE_SPHERE:
                return "Bewegen einer Sphäre";
            case ADD_VERTICES:
                return "Entfernen von Symptomen rüchgänig gemacht";
            case REMOVE_EDGES:
                return "Entfernen von Relationen";
            case MOVE_VERTICES:
                return "Bewegen von Symptomen";
            case REMOVE_SPHERE:
                return "Entfernen einer Sphäre";
            case EDIT_EDGES_TYPE:
                return "Änderung von Relationstypen";
            case REMOVE_VERTICES:
                return "Entfernen von Symptomen";
            case EDIT_EDGES_COLOR:
                return "Änderung der Farbe von Relationen";
            case EDIT_FONT_SPHERE:
                return "Änderung der Schriftart einer Sphäre";
            case EDIT_SPHERE_SIZE:
                return "Änderung der Größe einer Sphäre";
            case EDIT_EDGES_STROKE:
                return "Änderung der Linienart von Realtionen";
            case EDIT_SPHERE_COLOR:
                return "Änderung der Farbe einer Sphäre";
            case DEACTIVATE_FADEOUT:
                return "Einblenden";
            case EDIT_FONT_VERTICES:
                return "Änderung der Schriftart von Symptomen";
            case EDIT_VERTICES_FORM:
                return "Änderung der Form von Symptomen";
            case EDIT_VERTICES_SIZE:
                return "Änderung der Größe von Symptomen";
            case REMOVE_ANCHOR_POINTS:
                return "Entfernen von Ankerpunkten";
            case EDIT_SPHERE_FONT_SIZE:
                return "Änderung der Schriftgröße einer Sphäre";
            case EDIT_SPHERE_ANNOTATION:
                return "Änderung der Beschriftung einer Sphäre";
            case EDIT_VERTEX_ANNOTATION:
                return "Änderung der Beschriftung eines Symptoms";
            case EDIT_VERTICES_FONT_SIZE:
                return "Änderung der Schriftgröße von Symptomen";
            case EDIT_VERTICES_DRAW_COLOR:
                return "Änderung der Umrandungsfarbe von Symptomen";
            case EDIT_VERTICES_FILL_COLOR:
                return "Änderung der Füllfarbe von Symptomen";
            case DEACTIVATE_HIGHLIGHT:
                return "Deaktivieren der Hervorhebung";
            case DEACTIVATE_ANCHOR_POINTS_FADEOUT:
                return "Einblenden von Ankerpunkten";
            case EDIT_SPHERES_LAYOUT:
                return "Änderung des Layouts von Spären";
            case EDIT_VERTICES_LAYOUT:
                return "Änderung des Layouts von Syptomen";
            default: throw new IllegalArgumentException();

        }
    }

    private static String convertLogEntryNameEnglish(LogEntryName logEntryName) {
        switch (logEntryName) {
            case ACTIVATE_HIGHLIGHT:
                return "Highlighting";
            case ACTIVATE_FADEOUT:
                return "Fadeout";
            case ADD_ANCHOR_POINTS:
                return "Anchor points added";
            case ACTIVATE_ANCHOR_POINTS_FADEOUT:
                return "Anchor points fadeout";
            case ADD_EDGES:
                return "Undoing removing relations";
            case ADD_SPHERE:
                return "Sphere added";
            case MOVE_SPHERE:
                return "Sphere moved";
            case ADD_VERTICES:
                return "Undoing adding symptoms";
            case REMOVE_EDGES:
                return "Relations removed";
            case MOVE_VERTICES:
                return "Symptoms moved";
            case REMOVE_SPHERE:
                return "Sphere removed";
            case EDIT_EDGES_TYPE:
                return "Changed the type of relations";
            case REMOVE_VERTICES:
                return "Symptoms removed";
            case EDIT_EDGES_COLOR:
                return "Changed the colour of relations";
            case EDIT_FONT_SPHERE:
                return "Changed the font of a sphere";
            case EDIT_SPHERE_SIZE:
                return "Changed the size a sphere";
            case EDIT_EDGES_STROKE:
                return "Changed the ";
            case EDIT_SPHERE_COLOR:
                return "";
            case DEACTIVATE_FADEOUT:
                return "";
            case EDIT_FONT_VERTICES:
                return "";
            case EDIT_VERTICES_FORM:
                return "";
            case EDIT_VERTICES_SIZE:
                return "";
            case REMOVE_ANCHOR_POINTS:
                return "";
            case EDIT_SPHERE_FONT_SIZE:
                return "";
            case EDIT_SPHERE_ANNOTATION:
                return "";
            case EDIT_VERTEX_ANNOTATION:
                return "";
            case EDIT_VERTICES_FONT_SIZE:
                return "";
            case EDIT_VERTICES_DRAW_COLOR:
                return "";
            case EDIT_VERTICES_FILL_COLOR:
                return "";
            case DEACTIVATE_HIGHLIGHT:
                return "";
            case DEACTIVATE_ANCHOR_POINTS_FADEOUT:
                return "g";
            case EDIT_SPHERES_LAYOUT:
                return "";
            case EDIT_VERTICES_LAYOUT:
                return "";
            default: throw new IllegalArgumentException();

        }
    }

    private static String parametersPrint(String parameters, LogEntryName logEntryName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        switch (logEntryName) {
            case ACTIVATE_HIGHLIGHT:
                return objectMapper.readValue(parameters, ActivateDeactivateHighlightParam.class).toString();
            case ACTIVATE_FADEOUT:
                return objectMapper.readValue(parameters, ActivateDeactivateFadeoutParam.class).toString();
            case ADD_ANCHOR_POINTS:
                return objectMapper.readValue(parameters, AddRemoveAnchorPointsParam.class).toString();
            case ACTIVATE_ANCHOR_POINTS_FADEOUT:
                return objectMapper.readValue(parameters, ActivateDeactivateAnchorPointsFadeoutParam.class).toString();
            case ADD_EDGES:
                return objectMapper.readValue(parameters, AddRemoveEdgesParam.class).toString();
            case ADD_SPHERE:
                return objectMapper.readValue(parameters, AddRemoveSphereParam.class).toString();
            case EDIT_SPHERES_LAYOUT:
                return objectMapper.readValue(parameters, LayoutSpheresParam.class).toString();
            case MOVE_SPHERE:
                return objectMapper.readValue(parameters, MoveSphereParam.class).toString();
            case ADD_VERTICES:
                return objectMapper.readValue(parameters, AddRemoveVerticesParam.class).toString();
            case REMOVE_EDGES:
                return objectMapper.readValue(parameters, AddRemoveEdgesParam.class).toString();
            case MOVE_VERTICES:
                return objectMapper.readValue(parameters, MoveVerticesParam.class).toString();
            case REMOVE_SPHERE:
                return objectMapper.readValue(parameters, AddRemoveSphereParam.class).toString();
            case EDIT_EDGES_TYPE:
                return objectMapper.readValue(parameters, EditEdgesTypeParam.class).toString();
            case REMOVE_VERTICES:
                return objectMapper.readValue(parameters, ActivateDeactivateHighlightParam.class).toString();
            case EDIT_EDGES_COLOR:
                return objectMapper.readValue(parameters, ActivateDeactivateHighlightParam.class).toString();
            case EDIT_FONT_SPHERE:
                return objectMapper.readValue(parameters, ActivateDeactivateHighlightParam.class).toString();
            case EDIT_SPHERE_SIZE:
                return objectMapper.readValue(parameters, ActivateDeactivateHighlightParam.class).toString();
            case EDIT_EDGES_STROKE:
                return objectMapper.readValue(parameters, ActivateDeactivateHighlightParam.class).toString();
            case EDIT_SPHERE_COLOR:
                return objectMapper.readValue(parameters, ActivateDeactivateHighlightParam.class).toString();
            case DEACTIVATE_FADEOUT:
                return objectMapper.readValue(parameters, ActivateDeactivateHighlightParam.class).toString();
            case EDIT_FONT_VERTICES:
                return objectMapper.readValue(parameters, ActivateDeactivateHighlightParam.class).toString();
            case EDIT_VERTICES_FORM:
                return objectMapper.readValue(parameters, ActivateDeactivateHighlightParam.class).toString();
            case EDIT_VERTICES_SIZE:
                return objectMapper.readValue(parameters, ActivateDeactivateHighlightParam.class).toString();
            case REMOVE_ANCHOR_POINTS:
                return objectMapper.readValue(parameters, ActivateDeactivateHighlightParam.class).toString();
            case EDIT_SPHERE_FONT_SIZE:
                return objectMapper.readValue(parameters, ActivateDeactivateHighlightParam.class).toString();
            case EDIT_SPHERE_ANNOTATION:
                return objectMapper.readValue(parameters, ActivateDeactivateHighlightParam.class).toString();
            case EDIT_VERTEX_ANNOTATION:
                return objectMapper.readValue(parameters, ActivateDeactivateHighlightParam.class).toString();
            case EDIT_VERTICES_FONT_SIZE:
                return objectMapper.readValue(parameters, ActivateDeactivateHighlightParam.class).toString();
            case EDIT_VERTICES_DRAW_COLOR:
                return objectMapper.readValue(parameters, ActivateDeactivateHighlightParam.class).toString();
            case EDIT_VERTICES_FILL_COLOR:
                return objectMapper.readValue(parameters, ActivateDeactivateHighlightParam.class).toString();
            case DEACTIVATE_HIGHLIGHT:
                return objectMapper.readValue(parameters, ActivateDeactivateHighlightParam.class).toString();
            case DEACTIVATE_ANCHOR_POINTS_FADEOUT:
                return objectMapper.readValue(parameters, ActivateDeactivateHighlightParam.class).toString();
            case EDIT_VERTICES_LAYOUT:
                return objectMapper.readValue(parameters, ActivateDeactivateHighlightParam.class).toString();
            default: throw new IllegalArgumentException();
        }
    }
}
