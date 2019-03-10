package gui;

import graph.graph.FunctionMode;
import graph.visualization.control.HelperFunctions;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * Initializes the context menu and selection of the overview.
 */
public class TreeViewMouseHandler implements EventHandler<MouseEvent> {

    /**
     * The treeView of the right side of the GUI.
     */
    private final TreeView<Object> treeView;

    /**
     * Helper-functions for the treeView.
     */
    private final HelperFunctions helper;

    /**
     * Constructor for the treeView-Handler.
     *
     * @param pC      The unique controller.
     * @param pHelper Helper-functions for the treeView.
     */
    TreeViewMouseHandler(Controller pC, HelperFunctions pHelper) {
        treeView = pC.getTreeView();
        helper = pHelper;
    }

    /**
     * Creates the context menu or the selects a gui element in the graph.
     *
     * @param e The mouse event.
     */
    @Override
    public void handle(MouseEvent e) {
        if (Values.getInstance().getMode() != FunctionMode.ANALYSE && e.getButton() == MouseButton.SECONDARY) {
            Node node = e.getPickResult().getIntersectedNode();
            if (node instanceof Text || (node instanceof TreeCell && ((TreeCell) node).getText() != null)) {
                TreeItem<Object> selected = treeView.getSelectionModel().getSelectedItem();
                Object val = selected.getValue();

                ContextMenu contextMenu = helper.openContextMenu(val);
                if (contextMenu != null) {
                    treeView.setContextMenu(contextMenu);
                    contextMenu.show(treeView, e.getScreenX(), e.getScreenY());
                }
            } else {
                treeView.setContextMenu(null);
            }

        }
    }
}
