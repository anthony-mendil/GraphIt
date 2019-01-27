package graph.visualization.renderers;/*
 * Copyright (c) 2003, the JUNG Project and the Regents of the University of
 * California All rights reserved.
 *
 * This software is open-source under the BSD license; see either "license.txt"
 * or http://jung.sourceforge.net/license.txt for a description.
 */

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.picking.PickedState;
import edu.uci.ics.jung.visualization.renderers.BasicRenderer;
import graph.graph.*;
import graph.visualization.transformer.sphere.SphereShapeTransformer;

import java.awt.*;
import java.util.*;

/**
 * The SyndromRenderer paints the syndrom graph.
 */
public class SyndromRenderer<V, E> extends BasicRenderer<V, E> {
    /**
     * The sphere renderer responsible for the rendering of the spheres.
     */
    private SphereRenderer sphaerenRenderer = new SphereRenderer();

    private SphereShapeTransformer<Sphere> sphereShapeTransformer = new SphereShapeTransformer<>();

    /**
     * Basic render function implemented in JUNG.
     *
     * @param renderContext The renderContext of the graph defined in JUNG.
     * @param layout        The given layout defined in JUNG.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void render(RenderContext<V, E> renderContext, Layout<V, E> layout) {
        SyndromGraph<V, E> g = (SyndromGraph<V, E>) layout.getGraph();
        PickedState<Sphere> pickedState = Syndrom.getInstance().getVv().getPickedSphereState();
        Collection<E> renderEdges = new ArrayList<>();
        Sphere sp = null;
        boolean overlapped = isOverlapped(pickedState, g);

        for (V v : g.getVertices()){
            graph.graph.Vertex vertex = (graph.graph.Vertex) v;
            vertex.setVertexArrowExtenuating(new EnumMap<>(ScopePoint.class));
            vertex.setVertexArrowNeutral(new EnumMap<>(ScopePoint.class));
            vertex.setVertexArrowReinforced(new EnumMap<>(ScopePoint.class));
        }

        // paints all spheres
        renderSpheres((ArrayList<Sphere>) g.getSpheres(), renderContext);

        // if a sphere overlaps another one all incoming and outgoing edges get detected
        if (overlapped) {
            sp = pickedState.getPicked().iterator().next();
            Collection<V> included = (Collection<V>) sp.getVertices();
            Collection<E> incoming = new ArrayList<>();
            Collection<E> outgoing = new ArrayList<>();
            for (V v : included) {
                incoming.addAll(g.getInEdges(v));
                outgoing.addAll(g.getOutEdges(v));
            }

            for (E e : layout.getGraph().getEdges()) {
                if (!incoming.contains(e) && !outgoing.contains(e)) {
                    renderEdges.add(e);
                }
            }

            for (E e : renderEdges) {
                renderEdge(e, renderContext, layout);
            }
        } else {
            // if not sphere overlaps another one all edges get rendered
            for (E e : layout.getGraph().getEdges()) {
                renderEdge(e, renderContext, layout);
            }
        }

        // all vertices get rendered
        renderVerticesWithPicked(g, renderContext, layout);

        // if a sphere overlaps another one, the sphere is painted with all its vertices and all edges
        // between the spheres vertices
        if (overlapped) {
            renderObjectsSphere(sp, g, renderContext, layout);
        }
    }

    @SuppressWarnings("unchecked")
    private void renderObjectsSphere(Sphere sp, SyndromGraph<V, E> g, RenderContext<V, E> renderContext, Layout<V, E> layout) {
        sphaerenRenderer.paintSphere(renderContext, sp);
        Collection<V> collection = (Collection<V>) sp.getVertices();
        for (E e : layout.getGraph().getEdges()) {
            Collection<V> incident = g.getEndpoints(e);
            if (collection.containsAll(incident)) {
                renderEdge(
                        renderContext,
                        layout,
                        e);
                renderEdgeLabel(
                        renderContext,
                        layout,
                        e);
            }
        }
        renderVertices(collection, renderContext, layout);
    }

    @SuppressWarnings("unchecked")
    private void renderVerticesWithPicked(SyndromGraph<V, E> g, RenderContext<V, E> renderContext, Layout<V, E> layout){
        PickedState<graph.graph.Vertex> picked = Syndrom.getInstance().getVv().getPickedVertexState();
        Collection<V> vertices = g.getVertices();
        Collection<V> pick = (Collection<V>) picked.getPicked();
        ArrayList<V> render = new ArrayList<>();
        for (V v: vertices){
            if (!pick.contains(v)){
                render.add(v);
            }
        }

        renderVertices(render, renderContext, layout);
        renderVertices(pick, renderContext, layout);
    }

    private void renderVertices(Collection<V> vertices, RenderContext<V, E> renderContext, Layout<V, E> layout) {
        try {
            for (V v : vertices) {
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

    private void renderEdge(E e, RenderContext<V, E> renderContext, Layout<V, E> layout) {
        try {
            renderEdge(
                    renderContext,
                    layout,
                    e);
            renderEdgeLabel(
                    renderContext,
                    layout,
                    e);
        } catch (ConcurrentModificationException cme) {
            renderContext.getScreenDevice().repaint();
        }
    }

    private void renderSpheres(ArrayList<Sphere> spheres, RenderContext<V, E> renderContext) {
        try {
            for (Sphere sphere : spheres) {
                sphaerenRenderer.paintSphere(renderContext, sphere);
            }
        } catch (ConcurrentModificationException cme) {
            renderContext.getScreenDevice().repaint();
        }
    }

    private boolean isOverlapped(PickedState<Sphere> pickedState, SyndromGraph<V, E> g){
        boolean overlapped = false;
        for (Sphere s : pickedState.getPicked()) {
            Shape sShape = sphereShapeTransformer.transform(s);
            for (Sphere sphere : g.getSpheres()) {
                if (!s.equals(sphere)) {
                    Shape sphereShape = sphereShapeTransformer.transform(sphere);
                    if (sphereShape.intersects(sShape.getBounds())) {
                        overlapped = true;
                    }
                }
            }
        }
        return overlapped;
    }
}