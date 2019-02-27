package gui;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;

public class ToggleButtonListener implements ChangeListener<Boolean> {
    private ToggleButton toggleButton;
    private Controller c;
    private ToggleButton handSelector;
    private ToggleButton addSphere;
    private ToggleButton addVertex;

    public ToggleButtonListener(Controller pC, ToggleButton pToggleButton){
        toggleButton = pToggleButton;
        c = pC;
        handSelector = c.getHandSelector();
        addSphere = c.getAddSphere();
        addVertex = c.getAddVertex();
    }

    @Override
    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        if(newValue){
            if(toggleButton.getId().equals("handSelector")){
                addSphere.setSelected(false);
                addVertex.setSelected(false);
            }else if(toggleButton.getId().equals("addSphere")){
                handSelector.setSelected(false);
                addVertex.setSelected(false);
            }else if(toggleButton.getId().equals("addVertex")){
                handSelector.setSelected(false);
                addSphere.setSelected(false);
            }
        }
    }
}
