package log_management.parameters.add_remove;

import graph.graph.Vertex;
import log_management.parameters.Param;
import lombok.Getter;


/**
 * Parameter object for the action AddVertexLogAction.
 */
public class AddRemoveVertexParam extends Param{
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
     * Creates an parameter object of its own class.
     * @param pVertex The new vertex.
     * @param pSphereId The sphere-Identity
     * @param pSphereAnnotation The annotation of the sphere.
     */
    public AddRemoveVertexParam(Vertex pVertex, int pSphereId, String pSphereAnnotation) {
        this.vertex = pVertex;
        this.sphereId = pSphereId;
        this.sphereAnnotation = pSphereAnnotation;
    }
}
