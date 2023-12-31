package graph.visualization.control;

import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalSatelliteGraphMouse;
import edu.uci.ics.jung.visualization.control.SatelliteTranslatingGraphMousePlugin;

/**
 * The specific mouse
 *
 * @author Nina Unterberg
 */
public class SatelliteGraphMouse extends ModalSatelliteGraphMouse implements
        ModalGraphMouse {

    /**
     * The satellite graph mouse (zoom).
     */
    public SatelliteGraphMouse() {
        super();
    }

    /**
     * InputEvent.BUTTON1_MASK is deprecated, but if you replace it with BUTTON1_DOWN_MASK it will not work anymore.
     * so work around: remove BUTTON1_DOWN_MASK and input the integer values it stands for.
     */
    @Override
    public void loadPlugins() {
        translatingPlugin = new SatelliteTranslatingGraphMousePlugin(1 << 4);
        add(translatingPlugin);
    }
}
