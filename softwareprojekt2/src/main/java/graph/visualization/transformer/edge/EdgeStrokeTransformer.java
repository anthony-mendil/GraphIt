package graph.visualization.transformer.edge;

import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.picking.PickedState;
import graph.graph.Edge;
import graph.graph.StrokeType;
import graph.graph.Vertex;
import org.apache.commons.collections15.Transformer;

import java.awt.*;

/**
 * Defines a functor that transform an edge into its stroke type. The input edge is left unchanged.
 * Its extracting the stroke type of an edge.
 *
 * @author Nina Unterberg
 *
 * @param <E> The edge type.
 */
public class EdgeStrokeTransformer<E> implements Transformer<E, Stroke> {

    /**
     * Sets the dotting size of the dotted stroke.
     */
    private float[] dotting = {1.0f, 4.0f};
    /**
     * The unique visualization viewer.
     */
    private VisualizationViewer<Vertex, Edge> vv;

    /**
     * Transforms the stroke of the edge, if its picked, the stroke is bigger
     * the current visualisation viewer.
     *
     * @param vv the visualisation viewer
     */
    public EdgeStrokeTransformer(VisualizationViewer<Vertex, Edge> vv) {
        this.vv = vv;
    }

    @Override
    public Stroke transform(E e) {
        Edge edge = (Edge) e;
        Stroke stroke;

        PickedState<Edge> vertexPickedState = vv.getPickedEdgeState();
        if (vertexPickedState.isPicked(edge)) {
            Stroke basic = new BasicStroke(4);
            Stroke dotted = new BasicStroke(4.0f,
                    BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0f, dotting, 0f);
            Stroke dashed = new BasicStroke(4.0f,
                    BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0f, RenderContext.dashing, 0f);

            if (edge.getStroke() == StrokeType.BASIC || edge.getStroke() == StrokeType.BASIC_WEIGHT) {
                stroke = basic;
            } else if (edge.getStroke() == StrokeType.DOTTED_WEIGHT || edge.getStroke() == StrokeType.DOTTED) {
                stroke = dotted;
            } else {
                stroke = dashed;
            }
        } else {
            switch (edge.getStroke()) {
                case BASIC:
                    stroke = new BasicStroke(1);
                    break;
                case BASIC_WEIGHT:
                    stroke = new BasicStroke(3);
                    break;
                case DASHED:
                    stroke = RenderContext.DASHED;
                    break;
                case DASHED_WEIGHT:
                    stroke = new BasicStroke(3.0f,
                            BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0f, RenderContext.dashing, 0f);
                    break;
                case DOTTED:
                    stroke = RenderContext.DOTTED;
                    break;
                case DOTTED_WEIGHT:
                    stroke = new BasicStroke(3.0f,
                            BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0f, dotting, 0f);
                    break;
                default:
                    stroke = new BasicStroke(1);
                    break;
            }
        }

        return stroke;
    }
}
