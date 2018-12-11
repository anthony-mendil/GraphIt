package actions.export_graph;

import edu.uci.ics.jung.algorithms.layout.Layout;
import graph.graph.Edge;
import graph.graph.Vertex;

public class GXLHandler {


    /**
     * Constructs a new GXLHandler
     */
    public GXLHandler(){
    }

    /**
     * Creates a GXL instance of the current existing Graph as String
     *
     * @param pLayout The layout representation of the current graph
     *
     * @return The current GXL instance as String
     */
    public String createGXLString(Layout<Vertex,Edge> pLayout){
        throw new UnsupportedOperationException();
    }

    /**
     * Opens a dialog to save the the GXL representation to a specific location
     *
     * @param pName The name of the Syndrom and the default filename
     */
    public void exportGXL(Layout<Vertex,Edge> pLayout, String pName){
        //createGXL(pLayout) to File with pName as default filename
    }

    /**
     * Opens a dialog to import a GXL file from a specific location to a layout representation of the current graph
     *
     * @return The layout representation of the graph
     */
    public Layout<Vertex, Edge> importGXL(){
        throw new UnsupportedOperationException();
    }

}
