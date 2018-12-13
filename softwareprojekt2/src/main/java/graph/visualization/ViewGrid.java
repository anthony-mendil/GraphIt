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
    VisualizationViewer master;
    /**
     *
     */
    VisualizationViewer vv;

    /**
     * @param vv the visualisation view containing zoom context
     * @param master  the visualisation view to show in zoom context
     */
    public ViewGrid(VisualizationViewer vv, VisualizationViewer master) {

    }
    @Override
    public void paint(Graphics g) {

    }

    @Override
    public boolean useTransform() {
        return false;
    }
}
