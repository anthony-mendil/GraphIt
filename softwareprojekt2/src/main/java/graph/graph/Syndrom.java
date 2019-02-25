package graph.graph;

import edu.uci.ics.jung.algorithms.layout.AggregateLayout;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.visualization.*;
import edu.uci.ics.jung.visualization.control.AbsoluteCrossoverScalingControl;
import edu.uci.ics.jung.visualization.control.PluggableGraphMouse;
import edu.uci.ics.jung.visualization.control.SatelliteVisualizationViewer;
import edu.uci.ics.jung.visualization.control.TranslatingGraphMousePlugin;
import edu.uci.ics.jung.visualization.renderers.DefaultVertexLabelRenderer;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import edu.uci.ics.jung.visualization.transform.MutableAffineTransformer;
import graph.visualization.SyndromVisualisationViewer;
import graph.visualization.control.*;
import graph.visualization.picking.SyndromPickSupport;
import graph.visualization.renderers.EdgeRenderer;
import graph.visualization.renderers.SyndromRenderer;
import graph.visualization.renderers.VertexLabelRenderer;
import graph.visualization.transformer.edge.EdgeArrowFillPaintTransformer;
import graph.visualization.transformer.edge.EdgeArrowTransformer;
import graph.visualization.transformer.edge.EdgeFillPaintTransformer;
import graph.visualization.transformer.edge.EdgeStrokeTransformer;
import graph.visualization.transformer.vertex.*;
import gui.Values;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.awt.*;
import java.awt.event.InputEvent;
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

    private boolean templateIsSet = false;

    /**
     * Satellite view for zoom context.
     */
    private SatelliteVisualizationViewer<Vertex, Edge> vv2;


    /**
     * For adding/removing graph mouse plugins.
     */
    private PluggableGraphMouse pluggable;

    /**
     * The name of the graph.
     */
    private String graphName;

    private AggregateLayout<Vertex, Edge> layout;

    /**
     * The values set by the gui.
     */
    @Setter(AccessLevel.NONE)
    private final Values values;

    private static Syndrom instance;

    private AbsoluteCrossoverScalingControl scalingControl;

    private int scale;

    private SpherePickingPlugin spherePickingPlugin = new SpherePickingPlugin();
    private VertexPickingPlugin vertexPickingPlugin = new VertexPickingPlugin();
    private EdgePickingPlugin edgePickingPlugin = new EdgePickingPlugin();
    private GeneralPickingPlugin generalPickingPlugin = new GeneralPickingPlugin();
    private TranslatingGraphMousePlugin translatingPlugin = new TranslatingGraphMousePlugin(InputEvent.BUTTON1_MASK);


    /**
     * The constructor, initialising all attributes.
     */
    @SuppressWarnings("unchecked")
    private Syndrom() {
        values = Values.getInstance();
        pluggable = new PluggableGraphMouse();
        pluggable.add(spherePickingPlugin);
        pluggable.add(vertexPickingPlugin);
        pluggable.add(edgePickingPlugin);
        pluggable.add(generalPickingPlugin);
    }

    public void setPluggableModeEdit(){
        pluggable.remove(translatingPlugin);
    }

    public void setPluggableModeAnalyse(){
        pluggable.add(translatingPlugin);
    }

    public static Syndrom getInstance() {
        if (instance == null) {
            instance = new Syndrom();
        }
        return instance;
    }

    private void setVisualisationViewer(SyndromVisualisationViewer<Vertex, Edge> vv) {
        SyndromPickSupport<Vertex, Edge> pickSupport = new SyndromPickSupport<>(vv);
        vv.setBackground(Color.WHITE);
        vv.setRenderer(new SyndromRenderer<>());
        vv.getRenderContext().setPickSupport(pickSupport);
        vv.setGraphMouse(pluggable);
        setRenderer(vv);
        vv.getRenderContext().setVertexShapeTransformer(new VertexShapeTransformer<>());
        vv.getRenderContext().setVertexStrokeTransformer(new VertexStrokeTransformer<>(vv));
        this.vv = vv;
    }

    private void setVisualisationViewer2(SatelliteVisualizationViewer<Vertex, Edge> vv2) {
        AbsoluteCrossoverScalingControl vv2Scaler = new AbsoluteCrossoverScalingControl();
        scalingControl = vv2Scaler;
        vv2.scaleToLayout(vv2Scaler);
        vv2.setRenderer(new SyndromRenderer<>());
        setRenderer(vv2);
        vv2.getRenderContext().setVertexShapeTransformer(new VertexShapeVV2Transformer<>());
        vv2.setGraphMouse(new SatelliteGraphMouse());
        this.vv2 = vv2;
    }

    private void setRenderer(VisualizationViewer<Vertex, Edge> vv) {
        vv.getRenderContext().setVertexFillPaintTransformer(new VertexFillPaintTransformer<>());
        vv.getRenderContext().setVertexFontTransformer(new VertexFontTransformer<>());
        vv.getRenderContext().setVertexDrawPaintTransformer(new VertexDrawPaintTransformer<>());
        vv.getRenderContext().setVertexLabelTransformer(new VertexLabelTransformer<>());

        vv.getRenderContext().setVertexLabelRenderer(new DefaultVertexLabelRenderer(Color.black));
        vv.getRenderer().getVertexLabelRenderer().setPosition(Renderer.VertexLabel.Position.CNTR);

        vv.getRenderContext().setEdgeDrawPaintTransformer(new EdgeFillPaintTransformer<>());
        vv.getRenderContext().setEdgeArrowTransformer(new EdgeArrowTransformer<>(5, 10, 10, 0));
        vv.getRenderContext().setEdgeStrokeTransformer(new EdgeStrokeTransformer<>(vv));

        vv.getRenderContext().setArrowFillPaintTransformer(new EdgeArrowFillPaintTransformer<>());
        vv.getRenderContext().setArrowDrawPaintTransformer(new EdgeArrowFillPaintTransformer<>());

        vv.getGraphLayout().setGraph(new SyndromGraph<>());
        EdgeRenderer<Vertex, Edge> edgeRenderer = new EdgeRenderer<>();
        VertexLabelRenderer<Vertex, Edge> vertexLabelRenderer = new VertexLabelRenderer<>();
        vv.getRenderer().setVertexLabelRenderer(vertexLabelRenderer);
        vv.getRenderer().setEdgeRenderer(edgeRenderer);
    }

    public void scale(int value) {
        scale = value;
        scalingControl.scale(vv, (float) value / 100, vv.getCenter());

        if (value <= 100) {
            AffineTransform modelLayoutTransform =
                    new AffineTransform(vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.VIEW).getTransform());
            vv2.getRenderContext().getMultiLayerTransformer().setTransformer(Layer.LAYOUT, new MutableAffineTransformer(modelLayoutTransform));
        }
    }


    public void generateNew() {
        SyndromGraph<Vertex, Edge> graph = new SyndromGraph<>();
        layout = new AggregateLayout<>(new StaticLayout<>(graph));
        final VisualizationModel<Vertex, Edge> visualizationModel =
                new DefaultVisualizationModel<>(layout, values.getDefaultLayoutVVSize());
        vv = new SyndromVisualisationViewer<>(visualizationModel, values
                .getDefaultLayoutVVSize());
        vv.setGraphLayout(layout);
        setVisualisationViewer(vv);
        vv2 = new SatelliteVisualizationViewer<>(vv, new Dimension(260, 195));
        setVisualisationViewer2(vv2);
    }
}
