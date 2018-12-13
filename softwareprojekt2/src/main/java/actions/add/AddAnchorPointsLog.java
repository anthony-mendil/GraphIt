package actions.add;

import actions.LogAction;
import log_management.LogEntryName;
import log_management.parameters.add_remove.AddRemoveAnchorPointsParam;


import java.awt.geom.Point2D;

public class AddAnchorPointsLog extends LogAction {
	 /**
     * Constructor in the case few anchor-points shall be added.
     * get all picked Edges through pick support
     */
    public AddAnchorPointsLog(){
    	super(LogEntryName.ADD_ANCHOR_POINTS);
        throw new UnsupportedOperationException();
    }
    /**
     * Constructor in the case only one anchor-point shall be added.
     *
     * @param pPoint2D The position of the vertex which hosts the anchor-point.
     */
    public AddAnchorPointsLog(Point2D pPoint2D) {
        super(LogEntryName.ADD_ANCHOR_POINTS);
        throw new UnsupportedOperationException();
    }

    /**
     * Adds an anchor-point to the edge.
     * Constructor which will be used to realize the undo-method of RemoveAnchorPointsAction.
     *
     * @param pAddRemoveAnchorPointsParam The parameters containing all anchor points to add.
     */
    public AddAnchorPointsLog(AddRemoveAnchorPointsParam pAddRemoveAnchorPointsParam) {
        super(LogEntryName.ADD_ANCHOR_POINTS);
        throw new UnsupportedOperationException();
    }


    /**
     * Single/several anchor-points will be added.
     */
    @Override
    public void action() {
        throw new UnsupportedOperationException();
    }

    /**
     * Undoes the addition of a single/multiple existing anchor-points.
     */
    @Override
    public void undo() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void createParameter() {
        throw new UnsupportedOperationException();
    }
}
