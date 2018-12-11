package actions.add;

import actions.LogAction;
import graph.graph.Vertex;
import log_management.LogEntryName;
import log_management.parameters.activate.ActivateAnchorPointsFadeoutParam;
import log_management.parameters.add.AddAnchorPointParam;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Collection;

public class AddAnchorLogPoint extends LogAction {
    /**
     * Constructor for the action in the case only one anchor-point shall be added.
     * @param pVertex
     * The vertex which hosts the anchor-point.
     */
    public AddAnchorLogPoint(Vertex pVertex){
        super(LogEntryName.ADD_ANCHOR_POINT);
    }

    /**
     * Constructor for the action in case several anchor-points shall be added.
     * @param pVertices
     * The vertices which hosts the anchor-points.
     */
    public AddAnchorLogPoint(Collection<Vertex> pVertices){
        super(LogEntryName.ADD_ANCHOR_POINT);
    }

    /**
     * Constructor which will be used to realize the undo-method of RemoveAnchorPointAction.
     * @param pActivateAnchorPointsFadeoutParam
     * The used parameters.
     */
    public AddAnchorLogPoint(AddAnchorPointParam pActivateAnchorPointsFadeoutParam){
        super(LogEntryName.ADD_ANCHOR_POINT);
    }
    /**
     * An anchor-point will be added.
     */
    @Override
    public void action() {
        throw new NotImplementedException();
    }


    /**
     * An already added Anchor-Point will be removed.
     */
    @Override
    public void undo() {
        throw new NotImplementedException();
    }
}
