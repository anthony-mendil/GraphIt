package gui;

import actions.analyse.FilterGraphAction;
import graph.graph.EdgeArrowType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.LinkedList;
import java.util.List;

/**
 * Handles the event when a menuitem was selected from the filter arrowtype options in the overview and filters
 * accordingly of the selected arrowtype, if the associated checkbox is selected.
 *
 * @author Jacky Philipp Mach
 */
public class FilterTypeHandler implements EventHandler<ActionEvent> {
    /**
     * The selected arrowtype.
     */
    private final LinkedList<EdgeArrowType> type;
    /**
     * The controller that contains most of the gui elements and functions.
     */
    private final Controller c;

    /**
     * Constructor for the filter-type-handler.
     * @param pC    The unique controller.
     * @param type  The edge-arrow-type.
     */
    FilterTypeHandler(Controller pC, EdgeArrowType type) {
        c = pC;
        LinkedList<EdgeArrowType> edgeTypes = new LinkedList<>();
        edgeTypes.add(type);
        this.type = edgeTypes;
    }

    /**
     * The event gets called when a menuitem was selected in the arrowtype menubutton in the overview.
     * When the associated checkbox is selected, this function will call the filter action with the selected
     * arrowtype.
     */
    @Override
    public void handle(ActionEvent evt) {
        //FilterGraphAction filterGraphAction = new FilterGraphAction(type, c.getTreeViewArrowType().isSelected());
        //filterGraphAction.action();
    }
}
