package graph.graph;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import graph.algorithmen.predicates.*;
import graph.visualization.renderers.EdgeRenderer;
import graph.visualization.renderers.SyndromRenderer;
import graph.visualization.transformer.edge.*;
import graph.visualization.transformer.sphere.*;
import graph.visualization.transformer.vertex.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

/**
 * Syndrom combines all graph elements. A 'graph' needs a specific internal graph state, a layout and a
 * visualisation viewer.
 * Visual properties of the spheres, vertices and edges are set via transformers. These are assigned to the
 * RenderContext of the visualisation viewer here.
 */
@Data
public class Syndrom {
    /**
     * template rules for the graph/ layout
     */
    private Template template;
    /**
     * the visualisation viewer of syndrom. It contains the layout and graph.
     */
    @Setter(AccessLevel.NONE)
    private static VisualizationViewer<Vertex, Edge> vv;

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
     * Defines a functor that performs a predicates test on vertices for filtering the vertices for a sphere.
     */
    private VertexSpherePredicate<Vertex, Edge> vertexSpherePredicate;

    /**
     * Defines a functor that performs a predicates test on vertices for filtering the vertex for visibility.
     */
    private VertexIsVisiblePredicate<Vertex, Edge> vertexIsVisiblePredicate;

    /**
     * Defines a functor that performs a predicates test on edges for filtering the edge for visibility.
     */
    private EdgeIsVisiblePredicate<Vertex, Edge> edgeIsVisiblePredicate;

    /**
     * Defines a functor that performs a predicates test on spheres for filtering the sphere for visibility.
     */
    private SphereIsVisiblePredicate<Sphere> sphereIsVisiblePredicate;
    /**
     * Defines a functor that transform a edge into its arrow draw color. The input edge left unchanged. Its extracting
     * the arrow draw color of an edge.
     */
    private EdgeArrowDrawPaintTransformer arrowDrawPaint;

    /**
     * Defines a functor that transform a edge into its edge arrow fill color. The input edge left unchanged. Its
     * extracting the arrow fill color of an edge.
     */
    private EdgeArrowFillPaintTransformer arrowFillPaint;

    /**
     * Defines a functor that transform a edge into its edge arrow type. The input edge left unchanged. Its
     * extracting the edge arrow type.
     */
    private EdgeArrowTransformer edgeArrow;

    /**
     * Defines a functor that transform a edge into its edge draw color. The input edge left unchanged. Its
     * extracting the draw color of an edge.
     */
    private EdgeDrawPaintTransformer edgeDrawPaint;

    /**
     * Defines a functor that transform a edge into its edge arrow fill color. The input edge left unchanged. Its
     * extracting the arrow fill color of an edge.
     */
    private EdgeFillPaintTransformer edgeFillPaint;

    /**
     * Defines a functor that transform a edge into its stroke type. The input edge left unchanged. Its
     * extracting the stroke type of an edge.
     */
    private EdgeStrokeTransformer edgeStroke;

    /**
     * Defines a functor that transform a sphere into its draw color. The input sphere left unchanged. Its
     * extracting the draw color of a sphere.
     */
    private SphereDrawPaintTransformer sphereDrawPaint;

    /**
     * Defines a functor that transform a sphere into its fill color. The input sphere left unchanged. Its
     * extracting the fill color of a sphere.
     */
    private SphereFillPaintTransformer sphereFillPaint;

    /**
     * Defines a functor that transform a sphere into its annotation font. The input sphere left unchanged. Its
     * extracting the font annotation of a sphere.
     */
    private SphereFontTransformer sphereFont;

    /**
     *  Defines a functor that transform a sphere into its annotation. The input sphere left unchanged. Its
     * extracting the annotation of a sphere.
     */
    private SphereLabelTransformer sphereLabel;

    /**
     * Defines a functor that transform a sphere into its shape. The input sphere left unchanged. Its
     * extracting the shape of a sphere.
     */
    private SphereShapeTransformer sphereShape;

    /**
     * Defines a functor that transform a vertex into its draw color. The input vertex left unchanged. Its
     * extracting the draw color of a vertex.
     */
    private VertexDrawPaintTransformer vertexDrawPaint;

    /**
     * Defines a functor that transform a vertex into its fill color. The input vertex left unchanged. Its
     * extracting the fill color of a vertex.
     */
    private VertexFillPaintTransformer vertexFillPaint;

    /**
     * Defines a functor that transform a vertex into its annotation font. The input vertex left unchanged. Its
     * extracting the annotation font of a vertex.
     */
    private VertexFontTransformer vertexFont;

    /**
     * Defines a functor that transform a vertex into its annotation. The input vertex left unchanged. Its
     * extracting the annotation of a vertex.
     */
    private VertexLabelTransformer vertexLabel;

    /**
     * Defines a functor that transform a vertex into highlight color. The input vertex left unchanged. Its
     * extracting the highlight color of a vertex.
     */
    private VertexPaintHighlightTransformer vertexPaintHighlight;

    /**
     * Defines a functor that transform a vertex into its highlight stroke. The input vertex left unchanged. Its
     * extracting the highlight stroke of a vertex.
     */
    private VertexStrokeHighlightTransformer vertexStrokeHighlight;

    /**
     * Defines a functor that transform a vertex into its shape. The input vertex left unchanged. Its
     * extracting the shape of a vertex.
     */
    private VertexShapeTransformer vertexShape;

    /**
     * Defines a functor that transform a vertex into its stroke. The input vertex left unchanged. Its
     * extracting the stroke of a vertex.
     */
    private VertexStrokeTransformer vertexStroke;

    /**
     * Defines a functor that transform a vertex into tool tip. The input vertex left unchanged. Its
     * extracting the tool tip of a vertex.
     */
    private VertexToolTipTransformer vertexTooltip;

    /**
     * a syndrom instance.
     */
    private static Syndrom syndrom = null;

    /**
     * the renderer of the syndrom graph
     */
    private SyndromRenderer syndromRenderer;

    /**
     * Syndrom is supposed to be an singleton instance.
     * @return the syndrom instance
     */
    public static Syndrom getInstance(){
        if (syndrom == null){
            syndrom = new Syndrom();
        }
        return syndrom;
    }
}
