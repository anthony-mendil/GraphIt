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
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.apache.commons.collections15.Factory;

import java.util.*;


/**
 * TODO
 */
public class SyndromGraph<V, E> extends DirectedSparseGraph<V, E> {
    private transient Map<Sphere, ArrayList<V>> sphaeren;
    @Getter @NonNull
    private EdgeArrowType arrowType;
    @Getter @Setter
    private String name;
    private int i;

    /**
     * TODO
     */
    public SyndromGraph() {
        super();
    }

    /**
     * TODO
     *
     * @return TODO
     */
    public Set<Sphere> getSphaeren() { throw new UnsupportedOperationException(); }

    /**
     * TODO
     *
     * @param pSphere TODO
     * @return TODO
     */
    public List getSphaereWithVertices(Sphere pSphere) {
        throw new UnsupportedOperationException();
    }

    /**
     * TODO
     *
     * @param pSphere TODO
     * @param pVertex TODO
     */
    public void addVertexToSphaere(Sphere pSphere, V pVertex) {
        throw new UnsupportedOperationException();
    }

    /**
     * TODO
     *
     * @param pSphere TODO
     * @param pVertex TODO
     */
    public void removeVertexFromSphaere(Sphere pSphere, V pVertex) {
        throw new UnsupportedOperationException();
    }

    /**
     * TODO
     *
     * @param pVertex TODO
     * @param pSphere TODO
     * @return TODO
     */
    public boolean addVertex(V pVertex, Sphere pSphere) {
        throw new UnsupportedOperationException();
    }

    /**
     * TODO
     *
     */
    public void addSphaere() {
        throw new UnsupportedOperationException();
    }

    /**
     * TODO
     *
     */
    public void removeSphaere() {
        throw new UnsupportedOperationException();
    }

    // TODO: anchor points

    public Vertex create(){
        return new Vertex(i++);
    }

}
