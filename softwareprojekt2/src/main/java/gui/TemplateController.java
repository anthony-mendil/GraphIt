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

import java.lang.reflect.Array;
import java.util.ArrayList;
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

        ArrayList<String> name = new ArrayList<>();

        for(Sphere sphere : spheres){
            name.add(sphere.getAnnotation().get("de"));
        }

        ObservableList<String> test = FXCollections.observableArrayList(name);
        /*
        ObservableList sizes =
                FXCollections.observableArrayList(
                        "Sphären",
                        "8",
                        "9",
                        "10",
                        "11",
                        "12",
                        new Separator(),
                        "Symptome",
                        "14",
                        "18",
                        "24",
                        "30",
                        "36",
                        new Separator(),
                        "Kanten",
                        "48",
                        "60",
                        "72",
                        "96"
                    );
        */
        templateListView.setItems(test);

    }
}
