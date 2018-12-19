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
import java.util.Map;


/**
 * The syndrom graph. Its extending the directed sparse graph with spheres and anchor points.
 */
public class SyndromGraph<V, E> extends DirectedSparseGraph<V, E> {
    /**
     * List of spheres.
     */
    private List<Sphere> spheren;

    /**
     * A counter for all objects.
     */
    private int counterObject;

    /**
     * The object factory for syndrom.
     */
    private final GraphObjectsFactory graphObjectsFactory;

    /**
     * Creates a new syndrom graph.
     */
    public SyndromGraph() {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns a list of all spheres from the syndrom graph.
     *
     * @return A list of all spheres.
     */
    public List<Sphere> getSpheres() {
        throw new UnsupportedOperationException();
    }


    /**
     * Assigns a vertex to a sphere.
     *
     * @param pSphere The sphere to assign to.
     * @param pVertex The vertex.
     */
    public void addVertexToSphere(Sphere pSphere, V pVertex) {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes a vertex from a sphere.
     *
     * @param pSphere The sphere.
     * @param pVertex The vertex to remove from the sphere.
     */
    public void removeVertexFromSphaere(Sphere pSphere, V pVertex) {
        throw new UnsupportedOperationException();
    }

    /**
     * Adds a vertex to the syndrom graphs and assigns it to a sphere.
     *
     * @param pVertex The vertex to add.
     * @param pSphere The sphere to assign to.
     * @return True if the vertex was added to the graph, false if not.
     */
    public boolean addVertex(V pVertex, Sphere pSphere) {
        throw new UnsupportedOperationException();
    }

    /**
     * Adds a new sphere to the graph.
     *
     * @return True if the sphere was added to the graph, false if not.
     */
    public boolean addSphere() {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes a sphere from the graph.
     *
     * @param pSphere The sphere to remove.
     */
    public void removeSphaere(Sphere pSphere) {
        throw new UnsupportedOperationException();
    }

    /**
     * Method for filtering the graph on the criteria.
     * @param edgeType The edge type to filter for.
     * @return A filtered list with spheres.
     */
    public List<Sphere> getFilteredEdgeType(EdgeType edgeType) {
        throw new UnsupportedOperationException();
    }

    /**
     * Filters the vertices/spheres annotation for a regular expression.
     * @param regularExpression The regular expression to filter for.
     * @return A filtered list with spheres.
     */
    public List<Sphere> getFilteredEdgeType(String regularExpression) {
        throw new UnsupportedOperationException();
    }

    /**
     * Filters the vertices for the attribute isVisible=false.
     * @return A filtered list with spheres.
     */
    public List<Sphere> getFilteredEdgeType() {
        throw new UnsupportedOperationException();
    }

    /**
     * A map mapping from the dimensions to its values.
     * @return The dimensions.
     */
    public Map<String, Integer> dimensions(){
        throw new UnsupportedOperationException();
    }

    /**
     * Updates a sphere in the graph.
     * @return True if update was successful, false if not.
     */
    public boolean updateSphere(){
            throw new UnsupportedOperationException();
    }

    /**
     * Updates an edge in the graph.
     * @return True if update was successful, false if not.
     */
    public boolean updateEdge(){
        throw new UnsupportedOperationException();
    }

    /**
     * Updates a vertex in the graph.
     * @return True if update was successful, false if not.
     */
    public boolean updateVertex(){
        throw new UnsupportedOperationException();
    }
}
