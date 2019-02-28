package gui;

import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.File;

public class UserGuidePaneController {
    @FXML
    private WebView webview;
    private WebEngine webEngine;
    private File htmlFile=new File("");

    public void initContent(){
        webEngine= webview.getEngine();
        webEngine.loadContent("<html><h1>Hello</h1><h2>Hello</h2></html>");
    }
}
