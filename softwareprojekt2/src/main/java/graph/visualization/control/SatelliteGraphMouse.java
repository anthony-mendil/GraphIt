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

    /**
     * InputEvent.BUTTON1_MASK is deprecated, but if you replace it with BUTTON1_DOWN_MASK it will not work anymore.
     */
    @Override
    @SuppressWarnings("deprecated")
    public void loadPlugins() {
        translatingPlugin = new SatelliteTranslatingGraphMousePlugin(InputEvent.BUTTON1_MASK);
        add(translatingPlugin);
    }
}
