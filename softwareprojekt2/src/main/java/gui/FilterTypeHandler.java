package gui;

import actions.analyse.FilterGraphAction;
import graph.graph.EdgeArrowType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class FilterTypeHandler implements EventHandler<ActionEvent> {
    private final EdgeArrowType type;
    private final Controller c;

    public FilterTypeHandler(Controller pC,EdgeArrowType type) {
        c = pC;
        this.type = type;
    }

    @Override
    public void handle(ActionEvent evt) {
        FilterGraphAction filterGraphAction = new FilterGraphAction(type, c.getTreeViewArrowType().isSelected());
        filterGraphAction.action();
    }
}
