package gui;

import actions.analyse.FilterGraphAction;
import graph.graph.Syndrom;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;

/**
 * Listens to the selected checkbox and calls the action associated with it.
 * The Listener gets used by checkboxes for setting the allowed arrow types in the template options and for
 * filtering the graph by regular expressions and arrow types.
 */
public class TemplateCheckBoxListener implements ChangeListener<Boolean> {
    /**
     * The checkbox that the listener is assigned to.
     */
    private CheckBox checkBox;
    /**
     * The checkbox for filtering the graph by arrowtypes.
     */
    private CheckBox treeViewArrowType;
    /**
     * The checkbox for filtering the graph by regular expressions.
     */
    private CheckBox regularExpressionBox;
    /**
     * The controller that contains most of the gui elements and functions.
     */
    private Controller c;

    /**
     * Checkbox for the template.
     * @param pCheckBox The checkbox.
     * @param c         The unique controller.
     */
    TemplateCheckBoxListener(CheckBox pCheckBox, Controller c) {
        checkBox = pCheckBox;
        this.c = c;
        treeViewArrowType = c.getTreeViewArrowType();
        regularExpressionBox = c.getRegularExpressionBox();
    }

    /**
     * Select/Deselect the regular expression checkbox and if the checkbox is selected, it will call the
     * filter graph action with the text as argument from the associated textfield.
     *
     * @param newValue The boolean value for selecting and deselecting the checkbox.
     */
    private void regularExpressionBoxChecked(boolean newValue) {
        regularExpressionBox.setSelected(newValue);
        if (newValue) {
            treeViewArrowType.setSelected(false);
        }
        FilterGraphAction filterGraphAction = new FilterGraphAction(c.getRegularExpressionField().getText(), newValue);
        filterGraphAction.action();
    }

    /**
     * Select/Deselect the arrow type checkbox and if the checkbox is selected, it will call the
     * filter graph action with the selected menu item as argument.
     *
     * @param newValue The boolean value for selecting and deselecting the checkbox.
     */
    private void treeViewArrowTypeBoxChecked(boolean newValue) {
        treeViewArrowType.setSelected(newValue);
        if (newValue) {
            regularExpressionBox.setSelected(false);
        }

        FilterGraphAction filterGraphAction = new FilterGraphAction(c.getFilterEdgeArrowType(), newValue);
        filterGraphAction.action();
    }

    /**
     * When the checkbox is selected, the function will determine which checkbox it is and will call the right functions
     * accordingly to it.
     *
     * @param observable Is the checkbox selected or not.
     * @param oldValue   Was it selected before or not.
     * @param newValue   Is it selected now or not.
     */
    @Override
    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        switch (checkBox.getId()) {
            case "reinforcedBox":
                Syndrom.getInstance().getTemplate().setReinforcedEdgesAllowed(newValue);
                break;
            case "neutralBox":
                Syndrom.getInstance().getTemplate().setNeutralEdgesAllowed(newValue);
                break;
            case "extenuatingBox":
                Syndrom.getInstance().getTemplate().setExtenuatingEdgesAllowed(newValue);
                break;
            case "treeViewArrowType":
                treeViewArrowTypeBoxChecked(newValue);
                break;
            case "regularExpressionBox":
                regularExpressionBoxChecked(newValue);
                break;
            default:
                throw new IllegalArgumentException();
        }
    }
}
