package log_management;

import actions.LogEntryName;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gui.Values;
import gui.properties.Language;
import gui.properties.LoadLanguage;
import javafx.util.Pair;
import log_management.json_deserializers.PairDeserializer;
import log_management.json_deserializers.Point2DDeserializer;
import log_management.json_serializers.PairSerializer;
import log_management.json_serializers.Point2DSerializer;
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
import java.time.format.DateTimeFormatter;

public class LogToStringConverter {

    private int incrementer = 1;
    private LoadLanguage lang = LoadLanguage.getInstance();

    public void resetIncrementer(){
        incrementer = 1;
    }

    public String convert(Log log) {
        Language language = Values.getInstance().getGuiLanguage();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if (language == Language.GERMAN) {
            try {
                return incrementer++ + "\n" + convertLogEntryName(log.getLogEntryName()) +
                        "\n" + parametersPrint(log.getParameters(), log.getLogEntryName()) + "\n" + log.getTime().format(formatter);
            } catch (IllegalArgumentException e) {
                throw new IllegalStateException();
            }
        }
        else if (language == Language.ENGLISH) {
            try {
                return incrementer++ + "\n" + convertLogEntryName(log.getLogEntryName()) +
                        "\n" + parametersPrint(log.getParameters(), log.getLogEntryName()) + "\n" + log.getTime().format(formatter);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException();
            }
        }
        else {
            throw new IllegalStateException();
        }
    }

    public String convertForTextFile(Log log) {
        Language language = Values.getInstance().getGuiLanguage();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if (language == Language.GERMAN) {
            try {
                return "Nummer des Eintrags: " + incrementer++ + "\n" + "  Typ der Aktion: " + convertLogEntryName(log.getLogEntryName()) +
                        "\n" + "  Informationen: " + parametersPrint(log.getParameters(), log.getLogEntryName()) + "\n" + "  Zeit: " + log.getTime().format(formatter) + "\n";
            } catch (IllegalArgumentException e) {
                throw new IllegalStateException();
            }
        }
        else if (language == Language.ENGLISH) {
            try {
                return "Log number: " + incrementer++ + "\n" + "  Type of Action: " + convertLogEntryName(log.getLogEntryName()) +
                        "\n" + "  Information: " + parametersPrint(log.getParameters(), log.getLogEntryName()) + "\n" + "  Time: " + log.getTime().format(formatter) + "\n";
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException();
            }
        }
        else {
            throw new IllegalStateException();
        }
    }

    private String convertLogEntryName(LogEntryName logEntryName) {
        switch (logEntryName) {
            case ACTIVATE_HIGHLIGHT:
                return lang.loadLanguagesKey("ACTIVATE_HIGHLIGHT");
            case ACTIVATE_FADEOUT:
                return lang.loadLanguagesKey("ACTIVATE_FADEOUT");
            case ADD_ANCHOR_POINTS:
                return lang.loadLanguagesKey("ADD_ANCHOR_POINTS");
            case ACTIVATE_ANCHOR_POINTS_FADEOUT:
                return lang.loadLanguagesKey("ACTIVATE_ANCHOR_POINTS_FADEOUT");
            case ADD_EDGES:
                return lang.loadLanguagesKey("ADD_EDGES");
            case ADD_SPHERE:
                return lang.loadLanguagesKey("ADD_SPHERE");
            case MOVE_SPHERE:
                return lang.loadLanguagesKey("MOVE_SPHERE");
            case ADD_VERTICES:
                return lang.loadLanguagesKey("ADD_VERTICES");
            case REMOVE_EDGES:
                return lang.loadLanguagesKey("REMOVE_EDGES");
            case MOVE_VERTICES:
                return lang.loadLanguagesKey("MOVE_VERTICES");
            case REMOVE_SPHERE:
                return lang.loadLanguagesKey("REMOVE_SPHERE");
            case EDIT_EDGES_TYPE:
                return lang.loadLanguagesKey("EDIT_EDGES_TYPE");
            case REMOVE_VERTICES:
                return lang.loadLanguagesKey("REMOVE_VERTICES");
            case EDIT_EDGES_COLOR:
                return lang.loadLanguagesKey("EDIT_EDGES_COLOR");
            case EDIT_FONT_SPHERE:
                return lang.loadLanguagesKey("EDIT_FONT_SPHERE");
            case EDIT_SPHERE_SIZE:
                return lang.loadLanguagesKey("EDIT_SPHERE_SIZE");
            case EDIT_EDGES_STROKE:
                return lang.loadLanguagesKey("EDIT_EDGES_STROKE");
            case EDIT_SPHERE_COLOR:
                return lang.loadLanguagesKey("EDIT_SPHERE_COLOR");
            case DEACTIVATE_FADEOUT:
                return lang.loadLanguagesKey("DEACTIVATE_FADEOUT");
            case EDIT_FONT_VERTICES:
                return lang.loadLanguagesKey("EDIT_FONT_VERTICES");
            case EDIT_VERTICES_FORM:
                return lang.loadLanguagesKey("EDIT_VERTICES_FORM");
            case EDIT_VERTICES_SIZE:
                return lang.loadLanguagesKey("EDIT_VERTICES_SIZE");
            case REMOVE_ANCHOR_POINTS:
                return lang.loadLanguagesKey("REMOVE_ANCHOR_POINTS");
            case EDIT_SPHERE_FONT_SIZE:
                return lang.loadLanguagesKey("EDIT_SPHERE_FONT_SIZE");
            case EDIT_SPHERE_ANNOTATION:
                return lang.loadLanguagesKey("EDIT_SPHERE_ANNOTATION");
            case EDIT_VERTEX_ANNOTATION:
                return lang.loadLanguagesKey("EDIT_VERTEX_ANNOTATION");
            case EDIT_VERTICES_FONT_SIZE:
                return lang.loadLanguagesKey("EDIT_VERTICES_FONT_SIZE");
            case EDIT_VERTICES_DRAW_COLOR:
                return lang.loadLanguagesKey("EDIT_VERTICES_DRAW_COLOR");
            case EDIT_VERTICES_FILL_COLOR:
                return lang.loadLanguagesKey("EDIT_VERTICES_FILL_COLOR");
            case DEACTIVATE_HIGHLIGHT:
                return lang.loadLanguagesKey("DEACTIVATE_HIGHLIGHT");
            case DEACTIVATE_ANCHOR_POINTS_FADEOUT:
                return lang.loadLanguagesKey("DEACTIVATE_ANCHOR_POINTS_FADEOUT");
            case EDIT_SPHERES_LAYOUT:
                return lang.loadLanguagesKey("EDIT_SPHERES_LAYOUT");
            case EDIT_VERTICES_LAYOUT:
                return lang.loadLanguagesKey("EDIT_VERTICES_LAYOUT");
            default:
                throw new IllegalArgumentException();
        }
    }

    private String parametersPrint(String parameters, LogEntryName logEntryName) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Point2D.class, new Point2DSerializer());
        gsonBuilder.registerTypeAdapter(Point2D.class, new Point2DDeserializer());
        gsonBuilder.registerTypeAdapter(Pair.class, new PairSerializer());
        gsonBuilder.registerTypeAdapter(Pair.class, new PairDeserializer());
        Gson gson = gsonBuilder.create();

        switch (logEntryName) {
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
            case EDIT_FONT_VERTICES:
                return gson.fromJson(parameters, EditFontVerticesParam.class).prettyPrint();
            case EDIT_VERTICES_FORM:
                return gson.fromJson(parameters, EditVerticesFormParam.class).prettyPrint();
            case EDIT_VERTICES_SIZE:
                return gson.fromJson(parameters, EditVerticesSizeParam.class).prettyPrint();
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
            case EDIT_VERTICES_LAYOUT:
                return gson.fromJson(parameters, LayoutVerticesParam.class).prettyPrint();
            default: throw new IllegalArgumentException();
        }
    }
}
