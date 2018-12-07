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

import java.util.*;


/**
 * TODO
 */
public class SyndromGraph<V, E> extends DirectedSparseGraph<V, E> {
    private transient Map<Sphaere, ArrayList<V>> sphaeren;

    /**
     * TODO
     */
    SyndromGraph() {
        super();
    }

    /**
     * TODO
     */
    public Set<Sphaere> getSphaeren() {
        throw new UnsupportedOperationException();
    }

    /**
     * TODO
     */
    public List getSphaereWithVertices(Sphaere s) {
        throw new UnsupportedOperationException();
    }

    /**
     * TODO
     */
    public void addVertexToSphaere(Sphaere s, V vertex) {
        throw new UnsupportedOperationException();
    }

    /**
     * TODO
     */
    public void removeVertexFromSphaere(Sphaere s, V vertex) {
        throw new UnsupportedOperationException();
    }

    /**
     * TODO
     */
    public boolean addVertex(V vertex, Sphaere s) {
        throw new UnsupportedOperationException();
    }

    /**
     * TODO
     */
    public void addSphaere() {
        throw new UnsupportedOperationException();
    }

    /**
     * TODO
     */
    public void removeSphaere() {
        throw new UnsupportedOperationException();
    }

    // TODO: anchor points
}
