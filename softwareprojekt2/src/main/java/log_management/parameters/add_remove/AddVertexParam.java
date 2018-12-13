package log_management.parameters.add;

import graph.graph.Vertex;
import log_management.parameters.Param;
import lombok.Getter;

import java.io.Serializable;

/**
 * Parameterobject for the action AddVertexLogAction.
 */
public class AddVertexParam extends Param{
    /**
     * The new vertex, which will be added to the graph.
     */
    @Getter
    private Vertex vertex;
    /**
     * The sphere in which the vertex will be part of.
     */
    @Getter
    private int sphereId;
    /**
     * The annotation of the sphere, which will be used in printing the logs.
     */
    @Getter
    private String sphereAnnotation;

    /**
     * Creates an parameterobject of its own class.
     * @param pVertex The new vertex.
     * @param pSphereId The sphere-Identity
     * @param pSphereAnnotation The annotation of the sphere.
     */
    public AddVertexParam(Vertex pVertex, int pSphereId, String pSphereAnnotation) {
        this.vertex = pVertex;
        this.sphereId = pSphereId;
        this.sphereAnnotation = pSphereAnnotation;
    }


    @Override
    public String convertToJson() {
        throw new UnsupportedOperationException();
    }
}
