package graph.visualization.control;

import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.SatelliteScalingGraphMousePlugin;
import edu.uci.ics.jung.visualization.control.SatelliteVisualizationViewer;
import edu.uci.ics.jung.visualization.control.ScalingControl;
import gui.Values;

import java.awt.event.MouseWheelEvent;

public class SyndromSatelliteScalingGraphMousePlugin extends SatelliteScalingGraphMousePlugin {
    private Values values;

    public SyndromSatelliteScalingGraphMousePlugin(ScalingControl scaler, int modifiers) {
        super(scaler, modifiers);
        values = Values.getInstance();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        boolean accepted = checkModifiers(e);
        int scale = values.getScale();
        int min = values.getMinScale();
        int max = values.getMaxScale();
        int scaleNew;

        if (accepted) {
            VisualizationViewer vv = (VisualizationViewer) e.getSource();

            if (vv instanceof SatelliteVisualizationViewer) {
                VisualizationViewer vvMaster =
                        ((SatelliteVisualizationViewer) vv).getMaster();

                int amount = e.getWheelRotation();

                if (amount > 0 && (scale + 10) < max) {
                    scaleNew = scale + 10;
                    scaler.scale(vvMaster, (float) scaleNew / 100, vvMaster.getCenter());
                    values.setScale(scaleNew);

                } else if (amount < 0 && (scale - 10) > min) {
                    scaleNew = scale - 10;

                    scaler.scale(vvMaster, (float) scaleNew / 100, vvMaster.getCenter());
                    values.setScale(scaleNew);
                }
            }
            vv.repaint();

        }
    }
}
