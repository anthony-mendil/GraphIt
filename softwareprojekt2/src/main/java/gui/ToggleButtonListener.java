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
     * The controller that contains most of the gui elements and functions.
     */
    private Controller c;
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

    public ToggleButtonListener(Controller pC, ToggleButton pToggleButton) {
        toggleButton = pToggleButton;
        c = pC;
        handSelector = c.getHandSelector();
        addSphere = c.getAddSphere();
        addVertex = c.getAddVertex();
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
            if (toggleButton.getId().equals("handSelector")) {
                addSphere.setSelected(false);
                addVertex.setSelected(false);
            } else if (toggleButton.getId().equals("addSphere")) {
                handSelector.setSelected(false);
                addVertex.setSelected(false);
            } else if (toggleButton.getId().equals("addVertex")) {
                handSelector.setSelected(false);
                addSphere.setSelected(false);
            }
        } else {
            if (toggleButton.getId().equals("handSelector")) {
                if (!addSphere.isSelected() && !addVertex.isSelected()) {
                    toggleButton.setSelected(true);
                }
            } else if (toggleButton.getId().equals("addSphere")) {
                if (!handSelector.isSelected() && !addVertex.isSelected()) {
                    toggleButton.setSelected(true);
                }
            } else if (toggleButton.getId().equals("addVertex") && !handSelector.isSelected() && !addSphere.isSelected()) {
                toggleButton.setSelected(true);
            }
        }
    }
}
