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

//import static actions.LogEntryName.*;

public class LogToStringConverter {

    public static String convert(Log log) {
        Language language = Values.getInstance().getGuiLanguage();
        if (language == Language.GERMAN) {
            try {
                return "Id: " + log.getId() + convertLogEntryNameGerman(log.getLogEntryName()) +
                        parametersPrint(log.getParameters(), log.getLogEntryName()) + " Zeit: " + log.getTime().toString();
            } catch (IOException e) {
                throw new IllegalStateException();
            }
        }
        else if (language == Language.ENGLISH) {
            return "";
        }
        else {
            throw new IllegalStateException();
        }
    }

    private static String convertLogEntryNameGerman(LogEntryName logEntryName) {
        switch (logEntryName) {
            case ACTIVATE_HIGHLIGHT:
                return "";
            case ACTIVATE_FADEOUT:
                return "";
            case ADD_ANCHOR_POINTS:
                return "";
            case ACTIVATE_ANCHOR_POINTS_FADEOUT:
                return "";
            case ADD_EDGES:
                return "";
            case ADD_SPHERE:
                return "";
            case MOVE_SPHERE:
                return "";
            case ADD_VERTICES:
                return "";
            case REMOVE_EDGES:
                return "";
            case MOVE_VERTICES:
                return "";
            case REMOVE_SPHERE:
                return "";
            case EDIT_EDGES_TYPE:
                return "";
            case REMOVE_VERTICES:
                return "";
            case EDIT_EDGES_COLOR:
                return "";
            case EDIT_FONT_SPHERE:
                return "";
            case EDIT_SPHERE_SIZE:
                return "";
            case EDIT_EDGES_STROKE:
                return "";
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
                return "h";
            case EDIT_SPHERES_LAYOUT:
                return "";
            case EDIT_VERTICES_LAYOUT:
                return "";
            default: throw new IllegalArgumentException();

        }
    }

    private static String convertLogEntryNameEnglish(LogEntryName logEntryName) {
        switch (logEntryName) {
            case ACTIVATE_HIGHLIGHT:
                return "";
            case ACTIVATE_FADEOUT:
                return "";
            case ADD_ANCHOR_POINTS:
                return "";
            case ACTIVATE_ANCHOR_POINTS_FADEOUT:
                return "";
            case ADD_EDGES:
                return "";
            case ADD_SPHERE:
                return "";
            case MOVE_SPHERE:
                return "";
            case ADD_VERTICES:
                return "";
            case REMOVE_EDGES:
                return "";
            case MOVE_VERTICES:
                return "";
            case REMOVE_SPHERE:
                return "";
            case EDIT_EDGES_TYPE:
                return "";
            case REMOVE_VERTICES:
                return "";
            case EDIT_EDGES_COLOR:
                return "";
            case EDIT_FONT_SPHERE:
                return "";
            case EDIT_SPHERE_SIZE:
                return "";
            case EDIT_EDGES_STROKE:
                return "";
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
                return objectMapper.readValue(parameters, ActivateDeactivateHighlightParam.class).toString();
            case EDIT_EDGES_TYPE:
                return objectMapper.readValue(parameters, ActivateDeactivateHighlightParam.class).toString();
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
