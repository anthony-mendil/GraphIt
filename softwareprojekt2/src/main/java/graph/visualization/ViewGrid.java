package graph.visualization;

import edu.uci.ics.jung.visualization.VisualizationServer;
import edu.uci.ics.jung.visualization.VisualizationViewer;

import java.awt.*;

/**
 *
 */
public class ViewGrid implements VisualizationServer.Paintable {
    /**
     *
     */
    private VisualizationViewer master;
    /**
     *
     */
    private VisualizationViewer vv;

    /**
     * @param pVv the visualisation view containing zoom context
     * @param pMaster  the visualisation view to show in zoom context
     */
    public ViewGrid(VisualizationViewer pVv, VisualizationViewer pMaster) {

    }
    @Override
    public void paint(Graphics pG) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean useTransform() {
        return false;
    }
}
