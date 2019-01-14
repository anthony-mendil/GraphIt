package graph.visualization.renderers;/*
 * Copyright (c) 2003, the JUNG Project and the Regents of the University of
 * California All rights reserved.
 *
 * This software is open-source under the BSD license; see either "license.txt"
 * or http://jung.sourceforge.net/license.txt for a description.
 */

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.renderers.BasicRenderer;
import graph.graph.Sphere;
import graph.graph.SyndromGraph;

import java.util.ConcurrentModificationException;

/**
 * The SyndromRenderer paints the syndrom graph.
 */
public class SyndromRenderer<V, E> extends BasicRenderer<V, E> {
    /**
     * The sphere renderer responsible for the rendering of the spheres.
     */
    private SphereRenderer sphaerenRenderer = new SphereRenderer();
    /**
     * The edge renderer responsible for the edges of the graph.
     */
    private EdgeRenderer edgeRenderer = new EdgeRenderer();

    /**
     * Basic render function implemented in JUNG.
     * @param renderContext The renderContext of the graph defined in JUNG.
     * @param layout The given layout defined in JUNG.
     */
    @Override
    public void render(RenderContext<V, E> renderContext, Layout<V, E> layout) {
        SyndromGraph<Integer, String> g = (SyndromGraph<Integer, String>) layout.getGraph();

        try {
            for (Sphere s : g.getSpheres()) {
                sphaerenRenderer.paintSphere(renderContext, s);


            }
        } catch (ConcurrentModificationException cme) {
            renderContext.getScreenDevice().repaint();
        }

        try {
            for (E e : layout.getGraph().getEdges()) {
                renderEdge(
                        renderContext,
                        layout,
                        e);
                renderEdgeLabel(
                        renderContext,
                        layout,
                        e);
            }
        } catch (ConcurrentModificationException cme) {
            renderContext.getScreenDevice().repaint();
        }

        try {
            for (V v : layout.getGraph().getVertices()) {

                renderVertex(
                        renderContext,
                        layout,
                        v);
                renderVertexLabel(
                        renderContext,
                        layout,
                        v);
            }
        } catch (ConcurrentModificationException cme) {
            renderContext.getScreenDevice().repaint();
        }
    }

    /**
     * Renders the sphere label.
     * @param layout The layout.
     * @param sphere The sphere to render.
     */
    public void renderSphereLabel(Layout<V,E> layout, Sphere sphere) {
        throw new UnsupportedOperationException();
    }
}