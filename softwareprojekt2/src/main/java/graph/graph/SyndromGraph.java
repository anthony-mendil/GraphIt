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
import edu.uci.ics.jung.graph.util.EdgeType;

import java.util.List;


/**
 * The syndrom graph. Its extending the directed sparse graph with spheres and anchor points.
 */
public class SyndromGraph<V, E> extends DirectedSparseGraph<V, E> {
    /**
     * List of spheres
     */
    private List<Sphere> spheren;

    /**
     * a counter for all objects
     */
    private int counterObject;

    /**
     *
     */
    private final int id;

    /**
     * the object factory for syndrom
     */
    private final GraphObjectsFactory pGraphObjectsFactory;

    /**
     * Creates a new syndrom graph.
     */
    public SyndromGraph(int id) {
        super();
        throw new UnsupportedOperationException();
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
     * methode for filter the graph on the criteria.
     * @param edgeType the edge type to filter for
     * @return a filtered list with spheres
     */
    public List<Sphere> getFilteredEdgeType(EdgeType edgeType) {
        throw new UnsupportedOperationException();
    }

    /**
     * filters the vertices/ spheres annotation for a regular expression
     * @param regularExpression the regular expression to filter for
     * @return a filtered list with spheres
     */
    public List<Sphere> getFilteredEdgeType(String regularExpression) {
        throw new UnsupportedOperationException();
    }

    /**
     * filters the vertices for the attribute isVisible=false
     * @return a filtered list with spheres
     */
    public List<Sphere> getFilteredEdgeType() {
        throw new UnsupportedOperationException();
    }
}
