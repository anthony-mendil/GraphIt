package graph.graph;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import org.codehaus.jackson.annotate.JsonValue;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A vertex of the syndrom graph. A vertex is always assigned to a sphere. Vertices have a unique location and are
 * connected through edges. All attributes of a vertex can be changed, just the id is unique and final.
 */
@Data
public class Vertex {
    /**
     * The unique id of a vertex.
     */
    @NonNull
    private final int id;

    /**
     * The fill color of a vertex.
     */
    @NonNull
    private Paint fillPaint;

    /**
     * The coordinate of a vertex. A vertex have to be placed within a sphere and where no other vertex is located.
     */
    @NonNull
    @Getter
    private Point2D coordinates;

    /**
     * The shape of a vertex.
     */
    @NonNull
    private VertexShapeType shape;

    /**
     * The annotation of a vertex.
     */
    @NonNull
    private Map<String, String> annotation;

    /**
     * The draw color of a vertex.
     */
    @NonNull
    private Paint drawPaint;

    /**
     * The edges with a reinforcing relation with its position on the vertex.
     */
    @NonNull
    private Map<ScopePoint, Point2D> vertexArrowReinforced;

    /**
     * The edges with a neutral relation with its position on the vertex.
     */
    @NonNull
    private Map<ScopePoint, Point2D> vertexArrowNeutral;

    /**
     * The edges with a extenuating relation with its position on the vertex.
     */
    @NonNull
    private Map<ScopePoint, Point2D> vertexArrowExtenuating;

    /**
     * The size of a vertex.
     */
    @NonNull
    private int size;

    /**
     * Defines whether an edge is visible or not.
     */
    @NonNull
    private boolean isVisible;

    /**
     * The font of the annotation.
     */
    @NonNull
    private String font;

    /**
     * The font size of the annotation.
     */
    @NonNull
    private int fontSize;

    /**
     * Creates a new vertex.
     * @param id The identification of the vertex.
     * @param fillPaint The inner colour of the vertex.
     * @param coordinates The cooridnates of the vertex.
     * @param shape The shape of the vertex.
     * @param annotation The annotation of the vertex.
     * @param drawPaint The colour of the boundary.
     * @param size The size of the vertex.
     * @param font The font of the annotation of the vertex.
     * @param fontSize The font size of the annotation of the vertex.
     */

    public Vertex(int id, Paint fillPaint, Point2D coordinates, VertexShapeType shape, Map<String, String>
            annotation, Paint drawPaint, int size, String font, int fontSize ){
        this.id = id;
        this.fillPaint = fillPaint;
        this.coordinates = coordinates;
        this.shape = shape;
        this.drawPaint = drawPaint;
        this.size = size;
        this.annotation = annotation;
        this.font = font;
        this.fontSize = fontSize;
        vertexArrowExtenuating = new LinkedHashMap<>();
        vertexArrowNeutral = new LinkedHashMap<>();
        vertexArrowReinforced = new LinkedHashMap<>();
        isVisible = true;
    }

    /**
     * Checks whether two vertices are the same.
     * @param v the target vertex.
     * @return judgement, whether the vertices are equal.
     */
    public boolean equals(Vertex v){
        return this.id == v.id;
    }
    @Override
    @JsonValue
    public String toString(){
        return Integer.toString(id);
    }

}
