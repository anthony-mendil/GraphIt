package graph.visualization.control;

import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalSatelliteGraphMouse;
import edu.uci.ics.jung.visualization.control.SatelliteTranslatingGraphMousePlugin;

import java.awt.event.InputEvent;

public class SatelliteGraphMouse extends ModalSatelliteGraphMouse implements
        ModalGraphMouse {

    /**
     * the satellite graph mouse (zoom)
     */
    public SatelliteGraphMouse() {
        super();
    }

    @Override
    public void loadPlugins() {
        translatingPlugin = new SatelliteTranslatingGraphMousePlugin(InputEvent.BUTTON1_MASK);
        add(translatingPlugin);
    }
}
