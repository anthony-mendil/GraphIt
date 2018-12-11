package actions.export_graph;

import edu.uci.ics.jung.algorithms.layout.Layout;
import graph.graph.Edge;
import graph.graph.Vertex;

public class GXLHandler {

    private Layout<Vertex, Edge> layout;
    private String name;

    /**
     *
     *
     * @param pLayout
     * @param pName
     */
    public GXLHandler(Layout<Vertex, Edge> pLayout, String pName){
        layout=pLayout;
        name=pName;
    }

    /**
     *
     */
    public void exportGXL(){

    }

    /**
     *
     */
    public void importGXL(){

    }

}
