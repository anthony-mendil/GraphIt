package graph.visualization.control;

import edu.uci.ics.jung.visualization.control.*;

import java.awt.event.InputEvent;

public class SatelliteGraphMouse  extends ModalSatelliteGraphMouse implements
        ModalGraphMouse {

    public SatelliteGraphMouse(){
        super();
    }

    public void loadPlugins(){
        System.out.println("yes");
        pickingPlugin = new PickingGraphMousePlugin();
        animatedPickingPlugin = new SatelliteAnimatedPickingGraphMousePlugin();
        translatingPlugin = new SatelliteTranslatingGraphMousePlugin(InputEvent.BUTTON1_MASK);
        rotatingPlugin = new SatelliteRotatingGraphMousePlugin();
        shearingPlugin = new SatelliteShearingGraphMousePlugin();
       // scalingPlugin = new SyndromSatelliteScalingGraphMousePlugin(new AbsoluteCrossoverScalingControl(), 0);
        add(scalingPlugin);
        setMode(Mode.TRANSFORMING);
    }
}
