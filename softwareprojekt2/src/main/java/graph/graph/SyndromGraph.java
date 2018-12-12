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

import java.util.List;


/**
 * The syndrom graph. Its extending the directed sparse graph with spheres and anchor points.
 */
public class SyndromGraph<V, E> extends DirectedSparseGraph<V, E> {
    /**
     * the name of the graph
     */
    private String graphName;
    /**
     * List of spheres
     */
    private List<Sphere> spheren;

    /**
     * a counter for all objects
     */
    private int counterObject;

    /**
     * Creates a new syndrom graph.
     */
    public SyndromGraph() {
        super();
    }

    /**
     * Returns a list of all spheres from the syndrom graph.
     *
     * @return a list of all spheres
     */
    public List<Sphere> getSpheres() {
        throw new UnsupportedOperationException();
    }


    /**
     * Assigns a vertex to a sphere.
     *
     * @param pSphere the sphere to assign to
     * @param pVertex the vertex
     */
    public void addVertexToSphere(Sphere pSphere, V pVertex) {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes a vertex from a sphere.
     *
     * @param pSphere the sphere
     * @param pVertex the vertex to remove from the sphere
     */
    public void removeVertexFromSphaere(Sphere pSphere, V pVertex) {
        throw new UnsupportedOperationException();
    }

    /**
     * Adds a vertex to the syndrom graphs and assigns it to a sphere.
     *
     * @param pVertex the vertex to add
     * @param pSphere the sphere to assign to
     * @return true if the vertex was added to the graph, false if not
     */
    public boolean addVertex(V pVertex, Sphere pSphere) {
        throw new UnsupportedOperationException();
    }

    /**
     * Adds a new sphere to the graph.
     *
     * @return true if the sphere was added to the graph, false if not
     */
    public boolean addSphere() {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes a sphere from the graph.
     *
     * @param pSphere the sphere to remove
     */
    public void removeSphaere(Sphere pSphere) {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates a new vertex.
     *
     * @return the new created vertex
     */
    public Vertex createVertex() {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates a new sphere.
     *
     * @return the new created sphere
     */
    public Sphere createSphere() {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates a new edge.
     *
     * @return the new created edge
     */
    public Edge createEdge() {
        throw new UnsupportedOperationException();
    }
}
