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


/**
 * The SyndromRenderers paints the syndrom graph.
 */
public class SyndromRenderer<V, E> extends BasicRenderer<V, E> {

    private SphereRenderer sphaerenRenderer = new SphereRenderer();

    @Override
    public void render(RenderContext<V, E> renderContext, Layout<V, E> layout) {
        throw new UnsupportedOperationException();
    }

    /**
     * renders the sphere label
     * @param layout the layout
     * @param sphere the sphere to render
     */
    public void renderSphereLabel(Layout<V,E> layout, Sphere sphere) {
        throw new UnsupportedOperationException();
    }
}