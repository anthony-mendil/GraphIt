package gui;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ToggleButton;

/**
 * Listens to the selected toggle buttons and handles that only one toggle button can be selected.
 * Also at least one toggle button must be active.
 *
 * @author Jacky Philipp Mach
 */
public class ArrowTypeToggleButtonListener implements ChangeListener<Boolean> {
    /**
     * The toggle button that the listener is assigned to.
     */
    private ToggleButton toggleButton;
    /**
     * The toggle button for the change to reinforcement arrow action.
     */
    private ToggleButton edgeArrowReinforcedToggle;
    /**
     * The toggle button for the change to extenuating arrow action.
     */
    private ToggleButton edgeArrowExtenuatingToggle;
    /**
     * The toggle button for the change to unknown arrow action.
     */
    private ToggleButton edgeArrowNeutralToggle;

    /**
     * Constructor for the toggle button in the controller.
     *
     * @param pC            The unique controller.
     * @param pToggleButton The toggle-button.
     */
    ArrowTypeToggleButtonListener(Controller pC, ToggleButton pToggleButton) {
        toggleButton = pToggleButton;
        edgeArrowReinforcedToggle = pC.getEdgeArrowReinforcedToggle();
        edgeArrowExtenuatingToggle = pC.getEdgeArrowExtenuatingToggle();
        edgeArrowNeutralToggle = pC.getEdgeArrowNeutralToggle();
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
                case "edgeArrowReinforcedToggle":
                    edgeArrowExtenuatingToggle.setSelected(false);
                    edgeArrowNeutralToggle.setSelected(false);
                    break;
                case "edgeArrowExtenuatingToggle":
                    edgeArrowReinforcedToggle.setSelected(false);
                    edgeArrowNeutralToggle.setSelected(false);
                    break;
                case "edgeArrowNeutralToggle":
                    edgeArrowReinforcedToggle.setSelected(false);
                    edgeArrowExtenuatingToggle.setSelected(false);
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        } else {
            changeOnNotSelected();
        }
    }

    /**
     * Implements the behaviour that at least one toggle button must be active.
     */
    private void changeOnNotSelected() {
        switch (toggleButton.getId()) {
            case "edgeArrowReinforcedToggle":
                if (!edgeArrowExtenuatingToggle.isSelected() && !edgeArrowNeutralToggle.isSelected()) {
                    toggleButton.setSelected(true);
                }
                break;
            case "edgeArrowExtenuatingToggle":
                if (!edgeArrowReinforcedToggle.isSelected() && !edgeArrowNeutralToggle.isSelected()) {
                    toggleButton.setSelected(true);
                }
                break;
            case "edgeArrowNeutralToggle":
                if (!edgeArrowReinforcedToggle.isSelected() && !edgeArrowExtenuatingToggle.isSelected()) {
                    toggleButton.setSelected(true);
                }
                break;
            default:
                throw new IllegalArgumentException();
        }
    }
}
