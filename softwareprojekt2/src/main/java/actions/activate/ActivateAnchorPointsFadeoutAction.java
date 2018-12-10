package actions.activate;

import actions.LogAction;
import graph.graph.Vertex;
import log_management.LogEntryName;
import log_management.parameters.activate.ActivateAnchorPointsFadeoutParam;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.geom.Point2D;
import java.util.Collection;

/*
    linksclick Elemente markieren -> GUI Button Ankerpunkte FadeOut
    linksclick Elemente markieren -> rechtsclick irgendwo -> Drop-Down-Menü -> Ankerpunkte FadeOut
 */
/**
 * All existing anchor-points fadeout and are no longer visible for the user.
 */
public class ActivateAnchorPointsFadeoutAction extends LogAction {

    /**
     * Constructor in case all/several anchor-points shall fadeout.
     */
    public ActivateAnchorPointsFadeoutAction() {
        super(LogEntryName.ACTIVATE_ANCHOR_POINTS_FADEOUT);
    }

    /**
     * Constructor for the action in the case only one anchor-point shall fade out.
     * @param pPoint2D
     * The position of the vertex which hosts the anchor-point.
     */
    public ActivateAnchorPointsFadeoutAction(Point2D pPoint2D){
       super(LogEntryName.ACTIVATE_ANCHOR_POINTS_FADEOUT);
    }

    /**
     * Constructor which will be used to realize the undo-method of DeactivateAnchorFadeout.
     * @param pActivateAnchorPointsFadeoutParam
     */
    public ActivateAnchorPointsFadeoutAction(ActivateAnchorPointsFadeoutParam pActivateAnchorPointsFadeoutParam){
        super(LogEntryName.ACTIVATE_ANCHOR_POINTS_FADEOUT);
    }
   
    @Override
    public void action() {
        throw new NotImplementedException();
    }

    @Override
    public void undo() {
        throw new NotImplementedException();
    }
}
