package graph.visualization.control;

import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalSatelliteGraphMouse;
import edu.uci.ics.jung.visualization.control.SatelliteTranslatingGraphMousePlugin;

import java.awt.event.InputEvent;

public class SatelliteGraphMouse  extends ModalSatelliteGraphMouse implements
        ModalGraphMouse {

    public SatelliteGraphMouse(){
        super();
    }

    public void loadPlugins(){
        //pickingPlugin = new PickingGraphMousePlugin();
        //animatedPickingPlugin = new SatelliteAnimatedPickingGraphMousePlugin();
        translatingPlugin = new SatelliteTranslatingGraphMousePlugin(InputEvent.BUTTON1_MASK);
        //scalingPlugin = new SyndromSatelliteScalingGraphMousePlugin(new AbsoluteCrossoverScalingControl(), 0);
        //add(scalingPlugin);
        add(translatingPlugin);
       //setMode(Mode.TRANSFORMING);
    }
}
