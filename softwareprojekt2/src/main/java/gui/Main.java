package gui;

import graph.graph.Syndrom;
import graph.graph.Values;
import javafx.stage.Stage;

public class Main {

    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
       // throw new UnsupportedOperationException();
        Values values = new Values();
        Syndrom syndrom = new Syndrom(values);
        Controller controller = new Controller(syndrom, values);


    }
}
