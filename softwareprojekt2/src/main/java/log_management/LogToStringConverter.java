package log_management;

import actions.LogEntryName;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gui.Values;
import gui.properties.Language;
import log_management.json_deserializers.Point2DDeserializer;
import log_management.json_serializers.Point2DSerializer;
import log_management.parameters.activate_deactivate.ActivateDeactivateAnchorPointsFadeoutParam;
import log_management.parameters.activate_deactivate.ActivateDeactivateFadeoutParam;
import log_management.parameters.activate_deactivate.ActivateDeactivateHighlightParam;
import log_management.parameters.add_remove.AddRemoveAnchorPointsParam;
import log_management.parameters.add_remove.AddRemoveEdgesParam;
import log_management.parameters.add_remove.AddRemoveSphereParam;
import log_management.parameters.add_remove.AddRemoveVerticesParam;
import log_management.parameters.edit.*;
import log_management.parameters.move.LayoutSpheresParam;
import log_management.parameters.move.LayoutVerticesParam;
import log_management.parameters.move.MoveSphereParam;
import log_management.parameters.move.MoveVerticesParam;
import log_management.tables.Log;

import java.awt.geom.Point2D;

public class LogToStringConverter {

    public static String convert(Log log) {
        Language language = Values.getInstance().getGuiLanguage();
        if (language == Language.GERMAN) {
            try {
                return "Nummer: " + log.getId() + ", Typ der Aktion: " + convertLogEntryNameGerman(log.getLogEntryName()) +
                        "\nParameter: " + parametersPrint(log.getParameters(), log.getLogEntryName()) + "\nZeit: " + log.getTime().toString();
            } catch (IllegalArgumentException e) {
                throw new IllegalStateException();
            }
        }
        else if (language == Language.ENGLISH) {
            try {
                return "Number: " + log.getId() + ", Type of Action: " + convertLogEntryNameEnglish(log.getLogEntryName()) +
                        "\nParameters: " + parametersPrint(log.getParameters(), log.getLogEntryName()) + "\nTime: " + log.getTime().toString();
            } catch (IllegalArgumentException e) {
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

    private static String convertLogEntryNameEnglish(LogEntryName logEntryName) throws IllegalArgumentException{
        switch (logEntryName) {
            case ACTIVATE_HIGHLIGHT:
                return "Highlighting";
            case ACTIVATE_FADEOUT:
                return "Fade out";
            case ADD_ANCHOR_POINTS:
                return "Anchor points added";
            case ACTIVATE_ANCHOR_POINTS_FADEOUT:
                return "Anchor points faded out";
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
                return "Changed the color of relations";
            case EDIT_FONT_SPHERE:
                return "Changed the font of a sphere";
            case EDIT_SPHERE_SIZE:
                return "Changed the size a sphere";
            case EDIT_EDGES_STROKE:
                return "Changed the line type of relations";
            case EDIT_SPHERE_COLOR:
                return "Changed the color of spheres";
            case DEACTIVATE_FADEOUT:
                return "Fade in";
            case EDIT_FONT_VERTICES:
                return "Changed the font of symptoms";
            case EDIT_VERTICES_FORM:
                return "Changed the form of symptoms";
            case EDIT_VERTICES_SIZE:
                return "Changed the size of symptoms";
            case REMOVE_ANCHOR_POINTS:
                return "Anchor points removed";
            case EDIT_SPHERE_FONT_SIZE:
                return "Changed font size of a sphere";
            case EDIT_SPHERE_ANNOTATION:
                return "Changed annotation of a sphere";
            case EDIT_VERTEX_ANNOTATION:
                return "Changed annotation of a symptom";
            case EDIT_VERTICES_FONT_SIZE:
                return "Changed font size of symptoms";
            case EDIT_VERTICES_DRAW_COLOR:
                return "Changed draw color of symptoms";
            case EDIT_VERTICES_FILL_COLOR:
                return "Changed fill color of symptoms";
            case DEACTIVATE_HIGHLIGHT:
                return "Turned of highlighting";
            case DEACTIVATE_ANCHOR_POINTS_FADEOUT:
                return "Anchor points faded in";
            case EDIT_SPHERES_LAYOUT:
                return "Changed Layout of a sphere";
            case EDIT_VERTICES_LAYOUT:
                return "Changed Layout of symptoms";
            default: throw new IllegalArgumentException();

        }
    }

    private static String parametersPrint(String parameters, LogEntryName logEntryName) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Point2D.class, new Point2DSerializer());
        gsonBuilder.registerTypeAdapter(Point2D.class, new Point2DDeserializer());
        Gson gson = gsonBuilder.create();

        switch (logEntryName) {
            case ACTIVATE_HIGHLIGHT:
                return gson.fromJson(parameters, ActivateDeactivateHighlightParam.class).prettyPrint();
            case ACTIVATE_FADEOUT:
                return gson.fromJson(parameters, ActivateDeactivateFadeoutParam.class).prettyPrint();
            case ADD_ANCHOR_POINTS:
                return gson.fromJson(parameters, AddRemoveAnchorPointsParam.class).prettyPrint();
            case ACTIVATE_ANCHOR_POINTS_FADEOUT:
                return gson.fromJson(parameters, ActivateDeactivateAnchorPointsFadeoutParam.class).prettyPrint();
            case ADD_EDGES:
                return gson.fromJson(parameters, AddRemoveEdgesParam.class).prettyPrint();
            case ADD_SPHERE:
                return gson.fromJson(parameters, AddRemoveSphereParam.class).prettyPrint();
            case EDIT_SPHERES_LAYOUT:
                return gson.fromJson(parameters, LayoutSpheresParam.class).prettyPrint();
            case MOVE_SPHERE:
                return gson.fromJson(parameters, MoveSphereParam.class).prettyPrint();
            case ADD_VERTICES:
                return gson.fromJson(parameters, AddRemoveVerticesParam.class).prettyPrint();
            case REMOVE_EDGES:
                return gson.fromJson(parameters, AddRemoveEdgesParam.class).prettyPrint();
            case MOVE_VERTICES:
                return gson.fromJson(parameters, MoveVerticesParam.class).prettyPrint();
            case REMOVE_SPHERE:
                return gson.fromJson(parameters, AddRemoveSphereParam.class).prettyPrint();
            case EDIT_EDGES_TYPE:
                return gson.fromJson(parameters, EditEdgesTypeParam.class).prettyPrint();
            case REMOVE_VERTICES:
                return gson.fromJson(parameters, AddRemoveVerticesParam.class).prettyPrint();
            case EDIT_EDGES_COLOR:
                return gson.fromJson(parameters, EditEdgesColorParam.class).prettyPrint();
            case EDIT_FONT_SPHERE:
                return gson.fromJson(parameters, EditFontSphereParam.class).prettyPrint();
            case EDIT_SPHERE_SIZE:
                return gson.fromJson(parameters, EditSphereSizeParam.class).prettyPrint();
            case EDIT_EDGES_STROKE:
                return gson.fromJson(parameters, EditEdgesStrokeParam.class).prettyPrint();
            case EDIT_SPHERE_COLOR:
                return gson.fromJson(parameters, EditSphereColorParam.class).prettyPrint();
            case DEACTIVATE_FADEOUT:
                return gson.fromJson(parameters, ActivateDeactivateFadeoutParam.class).prettyPrint();
            case EDIT_FONT_VERTICES:
                return gson.fromJson(parameters, EditFontVerticesParam.class).prettyPrint();
            case EDIT_VERTICES_FORM:
                return gson.fromJson(parameters, EditVerticesFormParam.class).prettyPrint();
            case EDIT_VERTICES_SIZE:
                return gson.fromJson(parameters, EditVerticesSizeParam.class).prettyPrint();
            case REMOVE_ANCHOR_POINTS:
                return gson.fromJson(parameters, AddRemoveAnchorPointsParam.class).prettyPrint();
            case EDIT_SPHERE_FONT_SIZE:
                return gson.fromJson(parameters, EditFontSizeSphereParam.class).prettyPrint();
            case EDIT_SPHERE_ANNOTATION:
                return gson.fromJson(parameters, EditSphereAnnotationParam.class).prettyPrint();
            case EDIT_VERTEX_ANNOTATION:
                return gson.fromJson(parameters, EditVertexAnnotationParam.class).prettyPrint();
            case EDIT_VERTICES_FONT_SIZE:
                return gson.fromJson(parameters, EditFontSizeVerticesParam.class).prettyPrint();
            case EDIT_VERTICES_DRAW_COLOR:
                return gson.fromJson(parameters, EditVerticesDrawColorParam.class).prettyPrint();
            case EDIT_VERTICES_FILL_COLOR:
                return gson.fromJson(parameters, EditVerticesFillColorParam.class).prettyPrint();
            case DEACTIVATE_HIGHLIGHT:
                return gson.fromJson(parameters, ActivateDeactivateHighlightParam.class).prettyPrint();
            case DEACTIVATE_ANCHOR_POINTS_FADEOUT:
                return gson.fromJson(parameters, ActivateDeactivateAnchorPointsFadeoutParam.class).prettyPrint();
            case EDIT_VERTICES_LAYOUT:
                return gson.fromJson(parameters, LayoutVerticesParam.class).prettyPrint();
            default: throw new IllegalArgumentException();
        }
    }
}
