package graph.visualization;

import edu.uci.ics.jung.visualization.VisualizationServer;
import edu.uci.ics.jung.visualization.VisualizationViewer;

import java.awt.*;

/**
 * The grid, which will be showed if the user zooms into the graph, for a better overview.
 */
public class ViewGrid implements VisualizationServer.Paintable {
    /**
     * The visualization viewer for the zoomed state.
     */
    private VisualizationViewer master;
    /**
     * The visualization viewer in general.
     */
    private VisualizationViewer vv;

    /**
     * Makes the grid visible for the user.
     * @param pVv The visualization viewer containing the zoom context.
     * @param pMaster  The visualization viewer to show in zoom context.
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
