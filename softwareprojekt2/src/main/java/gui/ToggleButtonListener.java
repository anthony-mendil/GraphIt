package gui;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ToggleButton;

/**
 * Listens to the selected toggle buttons and handles that only one toggle button can be selected.
 * Also at least one toggle button must be active.
 */
public class ToggleButtonListener implements ChangeListener<Boolean> {
    /**
     * The toggle button that the listener is assigned to.
     */
    private ToggleButton toggleButton;
    /**
     * The toggle button for the selecting action.
     */
    private ToggleButton handSelector;
    /**
     * The toggle button for the add sphere action.
     */
    private ToggleButton addSphere;
    /**
     * The toggle button for the add vertex action.
     */
    private ToggleButton addVertex;

    ToggleButtonListener(Controller pC, ToggleButton pToggleButton) {
        toggleButton = pToggleButton;
        handSelector = pC.getHandSelector();
        addSphere = pC.getAddSphere();
        addVertex = pC.getAddVertex();
    }

    /**
     * Determines the current toggle button and turns off all the other toggle buttons accordingly to the current one.
     *
     * @param observable Is the toggle button selected or not.
     * @param oldValue   Was it selected before or not.
     * @param newValue   Is it selected now or not.
     */
    @Override
    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        if (newValue) {
            switch (toggleButton.getId()) {
                case "handSelector":
                    addSphere.setSelected(false);
                    addVertex.setSelected(false);
                    break;
                case "addSphere":
                    handSelector.setSelected(false);
                    addVertex.setSelected(false);
                    break;
                case "addVertex":
                    handSelector.setSelected(false);
                    addSphere.setSelected(false);
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        } else {
            changeOnNotSelected();
        }
    }

    /**
     * javadocTODO
     */
    private void changeOnNotSelected(){
        switch (toggleButton.getId()) {
            case "handSelector":
                if (!addSphere.isSelected() && !addVertex.isSelected()) {
                    toggleButton.setSelected(true);
                }
                break;
            case "addSphere":
                if (!handSelector.isSelected() && !addVertex.isSelected()) {
                    toggleButton.setSelected(true);
                }
                break;
            case "addVertex":
                if(!handSelector.isSelected() && !addSphere.isSelected()){
                    toggleButton.setSelected(true);
                }
                break;
            default:
                throw new IllegalArgumentException();
        }
    }
}
