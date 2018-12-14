package graph.graph;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.GraphZoomScrollPane;
import edu.uci.ics.jung.visualization.VisualizationServer;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.PluggableGraphMouse;
import edu.uci.ics.jung.visualization.control.SatelliteVisualizationViewer;
import graph.algorithmen.predicates.*;
import graph.visualization.renderers.SyndromRenderer;
import graph.visualization.transformer.edge.*;
import graph.visualization.transformer.sphere.*;
import graph.visualization.transformer.vertex.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.List;

/**
 * Syndrom combines all graph elements. A 'graph' needs a specific internal graph state, a layout and a visualisation
 * viewer. Visual properties of the spheres, vertices and edges are set via transformers. These are assigned to the
 * RenderContext of the visualisation viewer here.
 */
@Data
@Singleton
public class Syndrom {
    /**
     * the visualisation viewer of syndrom. It contains the layout and graph.
     */
    @Setter(AccessLevel.NONE)
    private final VisualizationViewer<Vertex, Edge> vv;
    /**
     * template rules for the graph/ layout
     */
    private Template template;
    /**
     * the layout of syndrom
     */
    @Setter(AccessLevel.NONE)
    private Layout<Vertex, Edge> layout;
    /**
     * the internal state of the syndrom-graph
     */
    @Setter(AccessLevel.NONE)
    private SyndromGraph<Vertex, Edge> graph;

    /**
     * the renderer of the syndrom graph
     */
    private SyndromRenderer syndromRenderer;

    /**
     * the current function mode
     */
    private FunctionMode mode;

    /**
     * Satellite view for zoom context
     */
    final SatelliteVisualizationViewer<Vertex,Edge> vv2;

    /**
     * zoom pane, containing the vv
     */
    private GraphZoomScrollPane gzsp;

    /**
     * for adding/ removing graph mouse plugins
     */
    private PluggableGraphMouse pluggable;

    /**
     * the view grid for zoom context
     */
    VisualizationServer.Paintable viewGrid;

    /**
     * the name of the graph
     */
    private String graphName;

    /**
     * the id
     */
    private int id;

    /**
     * the values set by the gui
     */
    @Setter(AccessLevel.NONE)
    @Inject
    private Values values;

    /**
     * the constructor, initialising all attributes
     */
    public Syndrom(){
        throw new UnsupportedOperationException();
    }
}
