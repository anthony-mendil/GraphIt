package gui;

import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.File;

public class UserGuidePaneController {
    @FXML
    private WebView webview;
    private File htmlFile=new File("");

    public void initContent(){
        WebEngine webEngine= webview.getEngine();
        webEngine.load("http://www.informatik.uni-bremen.de/st/Lehre/swpII_1819/");
    }
}
