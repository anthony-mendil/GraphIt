package graph.graph;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import graph.visualization.predicate.EdgeArrowPredicate;
import graph.visualization.predicate.VertexPredicate;
import graph.visualization.transformer.edge.*;
import graph.visualization.transformer.sphere.*;
import graph.visualization.transformer.vertex.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

/**
 *
 */
@Data
public class Syndrom {
    @Setter(AccessLevel.NONE)
    private static VisualizationViewer<Vertex, Edge> vv;
    @Setter(AccessLevel.NONE)
    private Layout<Vertex, Edge> layout;
    @Setter(AccessLevel.NONE)
    private SyndromGraph<Vertex, Edge> graph;

    private EdgeArrowPredicate<Vertex, Edge> filterArrow;
    private VertexPredicate<Vertex, Edge> filterVertex;

    private EdgeArrowDrawPaintTransformer arrowDrawPaint;
    private EdgeArrowFillPaintTransformer arrowFillPaint;
    private EdgeArrowTransformer edgeArrow;
    private EdgeDrawPaintTransformer edgeDrawPaint;
    private EdgeFillPaintTransformer edgeFillPaint;
    private EdgeStrokeTransformer edgeStroke;

    private SphereDrawPaintTransformer sphereDrawPaint;
    private SphereFillPaintTransformer sphereFillPaint;
    private SphereFontTransformer sphereFont;
    private SphereLabelTransformer sphereLabel;
    private SphereShapeTransformer sphereShape;

    private VertexDrawPaintTransformer vertexDrawPaint;
    private VertexFillPaintTransformer vertexFillPaint;
    private VertexFontTransformer vertexFont;
    private VertexLabelTransformer vertexLabel;
    private VertexPaintHighlightTransformer vertexPaintHighlight;
    private VertexStrokeHighlightTransformer vertexStrokeHighlight;
    private VertexShapeTransformer vertexShape;
    private VertexStrokeTransformer vertexStroke;
    private VertexToolTipTransformer vertexTooltip;

    private Syndrom syndrom = null;

    public Syndrom getInstance(){
        if (syndrom == null){
            syndrom = new Syndrom();
        }
        return syndrom;
    }
}
