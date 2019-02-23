package graph.graph;/*
 * Created on Mar 26, 2007
 *
 * Copyright (c) 2007, the JUNG Project and the Regents of the University
 * of California
 * All rights reserved.
 *
 * This software is open-source under the BSD license; see either
 * "license.txt" or
 * http://jung.sourceforge.net/license.txt for a description.
 */

import edu.uci.ics.jung.graph.DirectedSparseGraph;
import graph.visualization.SyndromVisualisationViewer;
import graph.visualization.picking.SyndromPickSupport;
import lombok.Data;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;


/**
 * The syndrom graph. Its extending the directed sparse graph with spheres and anchor points.
 */
@Data
public class SyndromGraph<V, E> extends DirectedSparseGraph<V, E> {
    /**
     * List of spheres.
     */
    private transient List<Sphere> spheres = new ArrayList<>();

    /**
     * The object factory for syndrom.
     */
    private final transient GraphObjectsFactory graphObjectsFactory;

    /**
     * Creates a new syndrom graph.
     */
    public SyndromGraph() {
        graphObjectsFactory = new GraphObjectsFactory();
    }



    /**
     * Assigns a vertex to a sphere.
     *
     * @param pSphere The sphere to assign to.
     * @param pVertex The vertex.
     */
    private boolean addVertexToSphere(Sphere pSphere, Vertex pVertex) {
        return pSphere.getVertices().add(pVertex);
    }

    @SuppressWarnings("unchecked")
    public void addEdge(V v1, V v2){
        Edge edge = graphObjectsFactory.createEdge();
        addEdge((E)edge, v1, v2);
    }

    /**
     * Adds an edge to the graph, if it already existed once in the past.
     *
     * @param edge The existing edge.
     * @param v1 The source vertex.
     * @param v2 The sink vertex.
     */
    @SuppressWarnings("Unchecked")
    public void addEdgeExisting(Edge edge, V v1, V v2){
        addEdge((E)edge, v1, v2);
    }

    /**
     * Adds a vertex to the syndrom graphs and assigns it to a sphere if it wasn't existing in the past.
     *
     * @param pSphere The sphere to assign to.
     * @return True if the vertex was added to the graph, false if not.
     */
    public Vertex addVertex(Point2D pos, Sphere pSphere) {
        Vertex vertex = graphObjectsFactory.createVertex(pos);
        addVertex((V) vertex);
        addVertexToSphere(pSphere, vertex);
        return vertex;
    }

    /**
     * Adds a vertex to the syndromgraphs and assigns it to the sphere if was existing in the past.
     */
    public Vertex addVertexExisting(Vertex vertex){
        SyndromVisualisationViewer vv = Syndrom.getInstance().getVv();
        SyndromPickSupport<Vertex, Edge> pickSupport = (SyndromPickSupport) vv.getPickSupport();
        Sphere sp = pickSupport.getSphere(vertex.getCoordinates().getX(), vertex.getCoordinates().getY());
        addVertex((V) vertex);
        addVertexToSphere(sp, vertex);
        return vertex;
    }

    /**
     * Adds a new sphere to the graph.
     *
     * @return True if the sphere was added to the graph, false if not.
     * @param pos The point where the sphere gets placed
     */
    public boolean addSphere(Point2D pos) {
        Sphere sphere = graphObjectsFactory.createSphere(pos);
        return spheres.add(sphere);
    }

    /**
     * Removes a sphere from the graph.
     *
     * @param pSphere The sphere to remove.
     */
    public void removeSphere(Sphere pSphere) {
        spheres.remove(pSphere);
    }


}
