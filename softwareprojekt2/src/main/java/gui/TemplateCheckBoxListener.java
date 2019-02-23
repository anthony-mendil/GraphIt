package gui;

import actions.analyse.FilterGraphAction;
import graph.graph.Syndrom;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import org.apache.log4j.Logger;

public class TemplateCheckBoxListener implements ChangeListener<Boolean> {
    private static Logger logger = Logger.getLogger(TemplateCheckBoxListener.class);
    private CheckBox checkBox;
    private CheckBox showFadedOutObjects;
    private CheckBox treeViewArrowType;
    private CheckBox regularExpressionBox;
    private Controller c;

    TemplateCheckBoxListener(CheckBox pCheckBox, Controller c) {
        checkBox = pCheckBox;
        this.c = c;
        showFadedOutObjects = c.getShowFadedOutObjects();
        treeViewArrowType = c.getTreeViewArrowType();
        regularExpressionBox = c.getRegularExpressionBox();
    }

    private void showFadedOutObjectsBoxChecked(boolean newValue) {
        showFadedOutObjects.setSelected(newValue);
        if (newValue) {
            treeViewArrowType.setSelected(false);
            regularExpressionBox.setSelected(false);
        }
        FilterGraphAction filterGraphAction = new FilterGraphAction(newValue);
        filterGraphAction.action();
    }

    private void regularExpressionBoxChecked(boolean newValue) {
        regularExpressionBox.setSelected(newValue);
        if (newValue) {
            showFadedOutObjects.setSelected(false);
            treeViewArrowType.setSelected(false);
        }
        FilterGraphAction filterGraphAction = new FilterGraphAction(c.getRegularExpressionField().getText(), newValue);
        filterGraphAction.action();
    }

    private void treeViewArrowTypeBoxChecked(boolean newValue) {
        treeViewArrowType.setSelected(newValue);
        if (newValue) {
            showFadedOutObjects.setSelected(false);
            regularExpressionBox.setSelected(false);
        }

        FilterGraphAction filterGraphAction = new FilterGraphAction(c.getFilterEdgeArrowType(), newValue);
        filterGraphAction.action();
    }

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
            case "showFadedOutObjects":
                showFadedOutObjectsBoxChecked(newValue);
                break;
            case "regularExpressionBox":
                regularExpressionBoxChecked(newValue);
                break;
            default:
                throw new IllegalArgumentException();
        }
    }
}
