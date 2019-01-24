package gui;

import graph.graph.Sphere;
import graph.graph.Syndrom;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TemplateController {

    @FXML
    private ListView templateListView;

    private Stage templateStage;

    private Syndrom syndrom;

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

    public void initialize(){
        loadListView();
    }

    public void loadListView(){
        templateListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        /*
        ArrayList<Sphere> spheres = new ArrayList<Sphere>(syndrom.getGraph().getSpheres());
        ArrayList<String> name = new ArrayList<>();
        for(Sphere sphere : spheres){
            name.add();
        }
        ObservableList<String> test = FXCollections.observableArrayList();
        */
        ObservableList<String> sizes =
                FXCollections.observableArrayList(
                        "8",
                        "9",
                        "10",
                        "11",
                        "12",
                        "14",
                        "18",
                        "24",
                        "30",
                        "36",
                        "48",
                        "60",
                        "72",
                        "96"
                    );

        templateListView.setItems(sizes);
    }
}
