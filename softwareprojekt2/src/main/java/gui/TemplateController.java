package gui;

import graph.graph.*;
import graph.visualization.SyndromVisualisationViewer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Separator;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Pair;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TemplateController {

    @FXML
    private ListView templateListView;

    private Stage templateStage;


    public TemplateController(Stage pStage){
        templateStage = pStage;
    }

    /**
     * Creates an RulesTemplateAction-object and executes the action with the action history.
     */
    public void rulesTemplate() {
        /*Template temp = new Template();

            temp.setMaxSphereCounter(Integer.parseInt(maxSphereField.getText()));
            temp.setMaxVertexCounter(Integer.parseInt(maxSymptomField.getText()));
            temp.setMaxEdgeCounter(Integer.parseInt(maxEdgesField.toString()));

        RulesTemplateAction rulesTemplateAction = new RulesTemplateAction(temp);*/
    }

    public void showTemplateWindow(){
        if(!templateStage.isShowing()){
            templateStage.show();
        }
    }

    public void closeTemplateWindow() {
        if (templateStage.isShowing()) {
            templateStage.hide();
        }
    }

    public void loadListView(){
        templateListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        SyndromVisualisationViewer<Vertex, Edge> vv = Syndrom.getInstance().getVv();
        SyndromGraph<Vertex, Edge> graph = (SyndromGraph<Vertex, Edge>) vv.getGraphLayout().getGraph();

        List<Sphere> spheres = graph.getSpheres();

        Collection<Vertex> vertices = graph.getVertices();

        Collection<Edge> edges = graph.getEdges();

        ArrayList<String> name = new ArrayList<>();

        if(!spheres.isEmpty()){
            name.add("---------------------------Sphären---------------------------");

            for(Sphere sphere : spheres){
                name.add(sphere.getAnnotation().get("de"));
            }
        }

        if(!vertices.isEmpty()){
            name.add("---------------------------Symptome------------------------");

            for(Vertex vertex : vertices){
                name.add(vertex.getAnnotation().get("de"));
            }
        }

        if(!edges.isEmpty()) {
            name.add("---------------------------Kanten----------------------------");

            for(Edge edge : edges){
                edu.uci.ics.jung.graph.util.Pair<Vertex> sourceTargetJung = vv.getGraphLayout().getGraph().getEndpoints(edge);
                name.add(sourceTargetJung.getFirst().getAnnotation().get("de") + " -> " + sourceTargetJung.getSecond().getAnnotation().get("de"));
            }
        }

        ObservableList<String> test = FXCollections.observableArrayList(name);
        templateListView.setItems(test);
    }
}
