import edu.uci.ics.jung.algorithms.layout.FRLayout2;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.visualization.VisualizationServer;
import edu.uci.ics.jung.visualization.VisualizationViewer;

import java.awt.*;

public class JavaDocTestClass {

    /***
     * Main
     * @param args
     */
    public static void main (String args[]) {
        System.out.println("HelloWorld");
        DirectedSparseGraph g = new DirectedSparseGraph();
    }

    public DirectedSparseGraph v (){
        DirectedSparseGraph g = new DirectedSparseGraph();
        return g;
    }
}
