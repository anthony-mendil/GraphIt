package graph.graph;

import edu.uci.ics.jung.algorithms.layout.AggregateLayout;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.visualization.*;
import edu.uci.ics.jung.visualization.control.AbsoluteCrossoverScalingControl;
import edu.uci.ics.jung.visualization.control.PluggableGraphMouse;
import edu.uci.ics.jung.visualization.control.SatelliteVisualizationViewer;
import edu.uci.ics.jung.visualization.renderers.DefaultVertexLabelRenderer;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import edu.uci.ics.jung.visualization.transform.MutableAffineTransformer;
import graph.algorithmen.predicates.*;
import graph.visualization.SyndromVisualisationViewer;
import graph.visualization.control.*;
import graph.visualization.picking.SyndromPickSupport;
import graph.visualization.renderers.EdgeRenderer;
import graph.visualization.renderers.SyndromRenderer;
import graph.visualization.renderers.VertexLabelRenderer;
import graph.visualization.transformer.edge.*;
import graph.visualization.transformer.sphere.*;
import graph.visualization.transformer.vertex.*;
import gui.Values;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Syndrom combines all graph elements. A 'graph' needs a specific internal graph state, a layout and a visualization
 * viewer. Visual properties of the spheres, vertices and edges are set via transformers. These are assigned to the
 * RenderContext of the visualization viewer here.
 */
@Data
public class Syndrom {
    /**
     * The visualization viewer of syndrom. It contains the layout and graph.
     */
    private SyndromVisualisationViewer<Vertex, Edge> vv;
    /**
     * Template rules for the graph/layout.
     */
    private Template template;

    private boolean templateIsSet=false;

    /**
     * The layout of syndrom.
     */
    private AggregateLayout<Vertex, Edge> layout;
    /**
     * The internal state of the syndrom-graph.
     */
    private SyndromGraph<Vertex, Edge> graph;
    /**
     * Defines a functor that performs a predicates test on edges for filtering the edge types of the edges.
     */
    private EdgeArrowPredicate<Vertex, Edge> filterArrow;
    /**
     * Defines a functor that performs a predicates test on vertices for filtering the vertices annotation.
     */
    private VertexAnnotationPredicate<Vertex, Edge> vertexAnnotationPredicate;
    /**
     * Defines a functor that performs a predicates test on vertices for filtering the vertex for edge types.
     */
    private VertexEdgePredicate<Vertex, Edge> vertexEdgePredicate;
    /**
     * Defines a functor that performs a predicates test on vertices for filtering the vertex for visibility.
     */
    private VertexIsVisiblePredicate<Vertex, Edge> vertexIsVisiblePredicate;
    /**
     * Defines a functor that performs a predicates test on edges for filtering the edge for visibility.
     */
    private EdgeIsVisiblePredicate<Vertex, Edge> edgeIsVisiblePredicate;
    /**
     * Defines a functor that performs a predicates test where all predicates need to pass.
     */
    private CombinesPredicate<Vertex, Edge> combinesPredicate;
    /**
     * Defines a functor that transform an edge into its arrow draw color. The input edge is left unchanged.
     * Its extracting the arrow draw color of an edge.
     */
    private EdgeArrowFillPaintTransformer arrowDrawPaint;
    /**
     * Defines a functor that transform an edge into its edge arrow fill color. The input edge is left unchanged.
     * Its extracting the arrow fill color of an edge.
     */
    private EdgeArrowFillPaintAnchorTransformer arrowFillPaint;
    /**
     * Defines a functor that transform an edge into its edge arrow type. The input edge is left unchanged.
     * Its extracting the edge arrow type.
     */
    private EdgeArrowTransformer edgeArrow;
    /**
     * Defines a functor that transform an edge into its edge draw color. The input edge is left unchanged.
     * Its extracting the draw color of an edge.
     */
    private EdgeDrawPaintTransformer edgeDrawPaint;
    /**
     * Defines a functor that transform an edge into its edge arrow fill color. The input edge is left unchanged.
     * Its extracting the arrow fill color of an edge.
     */
    private EdgeFillPaintTransformer edgeFillPaint;
    /**
     * Defines a functor that transform a edge into its stroke type. The input edge is left unchanged.
     * Its extracting the stroke type of an edge.
     */
    private EdgeStrokeTransformer edgeStroke;
    /**
     * Defines a functor that transform a sphere into its draw color. The input sphere is left unchanged.
     * Its extracting the draw color of a sphere.
     */
    private SphereDrawPaintTransformer sphereDrawPaint;
    /**
     * Defines a functor that transform a sphere into its fill color. The input sphere is left unchanged.
     * Its extracting the fill color of a sphere.
     */
    private SphereFillPaintTransformer sphereFillPaint;
    /**
     * Defines a functor that transform a sphere into its annotation font. The input sphere is left unchanged.
     * Its extracting the font annotation of a sphere.
     */
    private SphereFontTransformer sphereFont;
    /**
     * Defines a functor that transform a sphere into its annotation. The input sphere is left unchanged.
     * Its extracting the annotation of a sphere.
     */
    private SphereLabelTransformer sphereLabel;
    /**
     * Defines a functor that transform a sphere into its shape. The input sphere is left unchanged.
     * Its extracting the shape of a sphere.
     */
    private SphereShapeTransformer sphereShape;
    /**
     * Defines a functor that transform a vertex into its draw color. The input vertex is left unchanged.
     * Its extracting the draw color of a vertex.
     */
    private VertexDrawPaintTransformer vertexDrawPaint;
    /**
     * Defines a functor that transform a vertex into its fill color. The input vertex is left unchanged.
     * Its extracting the fill color of a vertex.
     */
    private VertexFillPaintTransformer vertexFillPaint;
    /**
     * Defines a functor that transform a vertex into its annotation font. The input vertex is left unchanged.
     * Its extracting the annotation font of a vertex.
     */
    private VertexFontTransformer vertexFont;
    /**
     * Defines a functor that transform a vertex into its annotation. The input vertex is left unchanged.
     * Its extracting the annotation of a vertex.
     */
    private VertexLabelTransformer vertexLabel;
    /**
     * Defines a functor that transform a vertex into highlight color. The input vertex is left unchanged.
     * Its extracting the highlight color of a vertex.
     */
    private VertexPaintHighlightTransformer vertexPaintHighlight;
    /**
     * Defines a functor that transform a vertex into its highlight stroke. The input vertex is left unchanged.
     * Its extracting the highlight stroke of a vertex.
     */
    private VertexStrokeHighlightTransformer vertexStrokeHighlight;
    /**
     * Defines a functor that transform a vertex into its shape. The input vertex is left unchanged.
     * Its extracting the shape of a vertex.
     */
    private VertexShapeTransformer vertexShape;
    /**
     * Defines a functor that transform a vertex into its stroke. The input vertex is left unchanged.
     * Its extracting the stroke of a vertex.
     */
    private VertexStrokeTransformer vertexStroke;
    /**
     * Defines a functor that transform a vertex into tooltip. The input vertex is left unchanged.
     * Its extracting the tooltip of a vertex.
     */
    private VertexToolTipTransformer vertexTooltip;
    /**
     * The renderer of the syndrom graph.
     */
    private SyndromRenderer syndromRenderer;

    /**
     * Satellite view for zoom context.
     */
    private SatelliteVisualizationViewer<Vertex,Edge> vv2;

    /**
     * Zoom pane, containing the visualization viewer.
     */
    private GraphZoomScrollPane gzsp;

    /**
     * For adding/removing graph mouse plugins.
     */
    private PluggableGraphMouse pluggable;

    /**
     * The view grid for zoom context.
     */
    private VisualizationServer.Paintable viewGrid;

    /**
     * The name of the graph.
     */
    private String graphName;

    /**
     * The values set by the gui.
     */
    @Setter(AccessLevel.NONE)
    private final Values values;

    private static Syndrom instance;

    private SyndromPickSupport pickSupport;

    private AbsoluteCrossoverScalingControl scalingControl;

    private int scale;

    /**
     * The constructor, initialising all attributes.
     */
    @SuppressWarnings("unchecked")
    private Syndrom(){
        values = Values.getInstance();
        pluggable = new PluggableGraphMouse();
        pluggable.add(new SpherePickingPlugin());
        pluggable.add(new VertexPickingPlugin());
        pluggable.add(new EdgePickingPlugin());
        pluggable.add(new GeneralPickingPlugin());
    }

    public static Syndrom getInstance(){
        if (instance == null){
            instance = new Syndrom();
        }
        return instance;
    }

    public void setVisualisationViewer(SyndromVisualisationViewer<Vertex, Edge> vv){
        pickSupport = new SyndromPickSupport(vv);
        gzsp = new GraphZoomScrollPane(vv);
        vv.setBackground(Color.WHITE);
        vv.setRenderer(new SyndromRenderer<>());
        vv.getRenderContext().setPickSupport(pickSupport);
        vv.setGraphMouse(pluggable);

        setRenderer(vv);

        vv.getRenderContext().setVertexStrokeTransformer(new VertexStrokeTransformer<>(vv));
        this.vv = vv;
    }

    public void setVisualisationViewer2(SatelliteVisualizationViewer<Vertex, Edge> vv2){
        AbsoluteCrossoverScalingControl vv2Scaler = new AbsoluteCrossoverScalingControl();
        scalingControl = vv2Scaler;
        vv2.scaleToLayout(vv2Scaler);
        vv2.setRenderer(new SyndromRenderer<>());
        setRenderer(vv2);
        vv2.setGraphMouse(new SatelliteGraphMouse());
        this.vv2 = vv2;
    }

    private void setRenderer(VisualizationViewer<Vertex, Edge> vv){
        vv.getRenderContext().setVertexFillPaintTransformer(new VertexFillPaintTransformer<>());
        vv.getRenderContext().setVertexFontTransformer(new VertexFontTransformer<>());
        vv.getRenderContext().setVertexDrawPaintTransformer(new VertexDrawPaintTransformer<>());
        vv.getRenderContext().setVertexLabelTransformer(new VertexLabelTransformer<>());
        vv.getRenderContext().setVertexShapeTransformer(new VertexShapeTransformer<>());
        vv.getRenderContext().setVertexLabelRenderer(new DefaultVertexLabelRenderer(Color.black));
        vv.getRenderer().getVertexLabelRenderer().setPosition(Renderer.VertexLabel.Position.CNTR);

        vv.getRenderContext().setEdgeDrawPaintTransformer(new EdgeFillPaintTransformer<>());
        vv.getRenderContext().setEdgeArrowTransformer(new EdgeArrowTransformer<>(5, 10, 10, 0));
        vv.getRenderContext().setEdgeStrokeTransformer(new EdgeStrokeTransformer<Edge>(vv));

        vv.getRenderContext().setArrowFillPaintTransformer(new EdgeArrowFillPaintTransformer<>());
        vv.getRenderContext().setArrowDrawPaintTransformer(new EdgeArrowFillPaintTransformer<>());

        vv.getGraphLayout().setGraph(new SyndromGraph<>());
        EdgeRenderer<Vertex, Edge> edgeRenderer = new EdgeRenderer<>();
        VertexLabelRenderer<Vertex, Edge> vertexLabelRenderer = new VertexLabelRenderer<>();
        vv.getRenderer().setVertexLabelRenderer(vertexLabelRenderer);
        vv.getRenderer().setEdgeRenderer(edgeRenderer);
    }

    public void scale(int value){
        scale = value;
        scalingControl.scale(vv, (float) value / 100, vv.getCenter());

        if (value < 100){
            AffineTransform modelLayoutTransform =
                    new AffineTransform(vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.VIEW).getTransform());
            vv2.getRenderContext().getMultiLayerTransformer().setTransformer(Layer.LAYOUT, new MutableAffineTransformer(modelLayoutTransform));
        }
    }

    @SuppressWarnings("unchecked")
    public void generateNew(){
        graph = new SyndromGraph<>();

        layout = new AggregateLayout<>(new StaticLayout<Vertex, Edge>(graph));
        final VisualizationModel<Vertex, Edge> visualizationModel =
                new DefaultVisualizationModel(layout, values.getDefaultLayoutVVSize());
        vv = new SyndromVisualisationViewer<>(visualizationModel,values
                .getDefaultLayoutVVSize());
        vv.setGraphLayout(layout);
        setVisualisationViewer(vv);

        vv2 = new SatelliteVisualizationViewer(vv, new Dimension(260,195));

        setVisualisationViewer2(vv2);
    }
}
