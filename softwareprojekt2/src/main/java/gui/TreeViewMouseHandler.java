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
     * TODO
     */
    private final TreeView<Object> treeView;

    /**
     * TODO
     */
    private final HelperFunctions helper;

    /**
     * TODO
     * @param pC
     * @param pHelper
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
        if (Values.getInstance().getMode() != FunctionMode.ANALYSE) {
            if (e.getButton() == MouseButton.SECONDARY) {
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
            } else if (treeView.getContextMenu() != null) {
                treeView.getContextMenu().hide();
            }
        }
    }
}
