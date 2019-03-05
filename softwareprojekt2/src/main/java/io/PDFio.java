package io;

import edu.uci.ics.jung.visualization.Layer;
import edu.uci.ics.jung.visualization.VisualizationImageServer;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.renderers.DefaultVertexLabelRenderer;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import edu.uci.ics.jung.visualization.transform.MutableAffineTransformer;
import graph.graph.Edge;
import graph.graph.Syndrom;
import graph.graph.Vertex;
import graph.visualization.renderers.EdgeRenderer;
import graph.visualization.renderers.SyndromRenderer;
import graph.visualization.renderers.VertexLabelRenderer;
import graph.visualization.transformer.edge.EdgeArrowFillPaintTransformer;
import graph.visualization.transformer.edge.EdgeArrowTransformer;
import graph.visualization.transformer.edge.EdgeFillPaintTransformer;
import graph.visualization.transformer.edge.EdgeStrokeTransformer;
import graph.visualization.transformer.vertex.*;
import gui.PDFPrinterGui;
import org.apache.log4j.Logger;
import org.freehep.graphics2d.VectorGraphics;
import org.freehep.graphicsio.pdf.PDFGraphics2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.*;
import java.util.Properties;

import static org.freehep.graphicsio.PageConstants.A4;
import static org.freehep.graphicsio.PageConstants.LANDSCAPE;
import static org.freehep.graphicsio.pdf.PDFGraphics2D.ORIENTATION;
import static org.freehep.graphicsio.pdf.PDFGraphics2D.PAGE_SIZE;

/**
 * The PDF exporter (export/print)
 */
public class PDFio {

    /**
     * The VisualizationViewer object of the current graph.
     */
    private VisualizationViewer<Vertex, Edge> vv;

    private static Logger logger = Logger.getLogger(PDFio.class);

    /**
     * Constructs a new PDFio object.
     *
     * @param pVv The VisualizationViewer object of the current graph.
     */
    public PDFio(VisualizationViewer<Vertex, Edge> pVv) {
        vv = pVv;
    }

    /**
     * creates a visualizationImageServer for visualise the current graph and prints its graphic into a PDFGraphics2D
     * and creates a pdf document out of it
     *
     * @param pOutputStream  the  file OutputStream to export the file to
     */
    private void createPDF(OutputStream pOutputStream) {
        VisualizationImageServer<Vertex, Edge> vis = new VisualizationImageServer<>(vv.getGraphLayout(), vv.getGraphLayout().getSize());
        vis.setBackground(Color.WHITE);
        vis.setRenderer(new SyndromRenderer<>());
        AffineTransform modelLayoutTransform =
                new AffineTransform(vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.VIEW).getTransform());

        vis.getRenderContext().getMultiLayerTransformer().setTransformer(Layer.VIEW, new MutableAffineTransformer(modelLayoutTransform));

        vis.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.VIEW).scale(
                Syndrom.getInstance().getVv().getRenderContext().getMultiLayerTransformer().getTransformer(Layer.VIEW).getScaleX(),
                Syndrom.getInstance().getVv().getRenderContext().getMultiLayerTransformer().getTransformer(Layer.VIEW).getScaleY(),
                vv.getCenter()
        );

        vis.getRenderContext().setVertexFillPaintTransformer(new VertexFillPaintTransformer<>());
        vis.getRenderContext().setVertexFontTransformer(new VertexFontTransformer<>());
        vis.getRenderContext().setVertexDrawPaintTransformer(new VertexDrawPaintTransformer<>());
        vis.getRenderContext().setVertexLabelTransformer(new VertexLabelTransformer<>());
        vis.getRenderContext().setVertexShapeTransformer(new VertexShapeTransformer<>());
        vis.getRenderContext().setVertexLabelRenderer(new DefaultVertexLabelRenderer(Color.black));

        vis.getRenderContext().setEdgeDrawPaintTransformer(new EdgeFillPaintTransformer<>());
        vis.getRenderContext().setEdgeArrowTransformer(new EdgeArrowTransformer<>(5, 10, 10, 0));
        vis.getRenderContext().setEdgeStrokeTransformer(new EdgeStrokeTransformer<>(vv));

        vis.getRenderContext().setArrowFillPaintTransformer(new EdgeArrowFillPaintTransformer<>());
        vis.getRenderContext().setArrowDrawPaintTransformer(new EdgeArrowFillPaintTransformer<>());


        vis.getRenderer().getVertexLabelRenderer().setPosition(Renderer.VertexLabel.Position.CNTR);

        EdgeRenderer<Vertex, Edge> edgeRenderer = new EdgeRenderer<>();
        vis.getRenderer().setEdgeRenderer(edgeRenderer);

        VertexLabelRenderer<Vertex, Edge> vertexLabelRenderer = new VertexLabelRenderer<>();
        vis.getRenderer().setVertexLabelRenderer(vertexLabelRenderer);


        VectorGraphics vectorGraphics;
        Dimension graphicsDimension = new Dimension((int) Syndrom.getInstance().getVv().getBounds().getWidth(), (int) Syndrom.getInstance().getVv().getBounds().getHeight());

        vectorGraphics = new PDFGraphics2D(pOutputStream, graphicsDimension);
        Properties properties = new Properties();
        properties.setProperty(ORIENTATION, LANDSCAPE);
        properties.setProperty(PAGE_SIZE, A4);
        vectorGraphics.setProperties(properties);
        vectorGraphics.startExport();
        vis.print(vectorGraphics);
        vectorGraphics.endExport();
    }

    public void exportPDF(File pFile) {
        try {
            createPDF(new FileOutputStream(pFile));
        } catch (FileNotFoundException e) {
            logger.error(e.toString());
        }
    }

    /**
     * Starts the dialog to export the current graph visualization as PDF.
     */
    public void printPDF() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        createPDF(byteArrayOutputStream);
        byte[] pdfBytes = byteArrayOutputStream.toByteArray();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(pdfBytes);
        new PDFPrinterGui(byteArrayInputStream);
    }
}
