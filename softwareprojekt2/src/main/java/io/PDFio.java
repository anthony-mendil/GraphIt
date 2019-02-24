package io;

import edu.uci.ics.jung.visualization.Layer;
import edu.uci.ics.jung.visualization.VisualizationImageServer;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.renderers.DefaultVertexLabelRenderer;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import edu.uci.ics.jung.visualization.transform.MutableAffineTransformer;
import graph.graph.*;
import graph.visualization.renderers.EdgeRenderer;
import graph.visualization.renderers.SyndromRenderer;
import graph.visualization.renderers.VertexLabelRenderer;
import graph.visualization.transformer.edge.EdgeArrowFillPaintTransformer;
import graph.visualization.transformer.edge.EdgeArrowTransformer;
import graph.visualization.transformer.edge.EdgeFillPaintTransformer;
import graph.visualization.transformer.edge.EdgeStrokeTransformer;
import graph.visualization.transformer.vertex.*;
import gui.Values;
import javafx.geometry.Point2D;
import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import org.freehep.graphics2d.VectorGraphics;
import org.freehep.graphicsio.pdf.PDFGraphics2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
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
    private VisualizationViewer vv;

    private static Logger logger = Logger.getLogger(PDFio.class);

    /**
     * Constructs a new PDFio object.
     *
     * @param pVv The VisualizationViewer object of the current graph.
     */
    public PDFio(VisualizationViewer pVv) {
        vv = pVv;
    }


    private Point2D getMinPoint() {
        SyndromGraph<Vertex, Edge> graph =  (SyndromGraph<Vertex, Edge>) Syndrom.getInstance().getVv().getGraphLayout().getGraph();
        List<Sphere> spheres = graph.getSpheres();
        if (spheres.isEmpty()) {
            return new Point2D(0, 0);
        }
        Point2D point = new Point2D(spheres.get(0).getCoordinates().getX(), spheres.get(0).getCoordinates().getY());
        for (Sphere sph : spheres) {
            //check x
            if (sph.getCoordinates().getX() < point.getX()) {
                point = new Point2D((float) sph.getCoordinates().getX(), point.getY());
            }
            //check y
            if (sph.getCoordinates().getY() < point.getY()) {
                point = new Point2D(point.getX(), (float) sph.getCoordinates().getY());
            }
        }
        return point;
    }

    private Point2D getMaxPoint() {
        SyndromGraph<Vertex, Edge> graph =  (SyndromGraph<Vertex, Edge>) Syndrom.getInstance().getVv().getGraphLayout().getGraph();
        List<Sphere> spheres = graph.getSpheres();
        Point2D p = new Point2D((float) Values.getInstance().getDefaultLayoutVVSize().getWidth(), (float) Values.getInstance().getDefaultLayoutVVSize().getHeight());
        if (spheres.isEmpty()) {
            System.out.println("empty");
            return new Point2D(p.getX(), p.getY());
        }
        Point2D point = new Point2D(spheres.get(0).getCoordinates().getX() + spheres.get(0).getWidth(), spheres.get(0).getCoordinates().getY()+spheres.get(0).getHeight());
        for (Sphere sph : spheres) {
            //check x
            if (sph.getCoordinates().getX() > point.getX()) {
                point = new Point2D((float) sph.getCoordinates().getX() + sph.getWidth(), point.getY());
            }
            //check y
            if (sph.getCoordinates().getY() > point.getY()) {
                point = new Point2D(point.getX(), (float) sph.getCoordinates().getY() + sph.getHeight());
            }
        }
        return point;
    }


    /**
     * Calculates the dimension of the graph spanned by the spheres
     *
     * @return the dimension of the graph
     */
    protected Dimension getGraphDimension() {
        List<Sphere> spheres = Syndrom.getInstance().getGraph().getSpheres();
        Dimension dimension = new Dimension(0, 0);
        for (Sphere sph : spheres) {
            //check x
            double x = sph.getCoordinates().getX() + sph.getWidth();
            if (x > dimension.getWidth()) {
                dimension.setSize(x, dimension.getHeight());
            }
            //check y
            double y = sph.getCoordinates().getY() + sph.getHeight();
            if (y > dimension.getHeight()) {
                dimension.setSize(dimension.getWidth(), y);
            }
        }
        return dimension;
    }

    /**
     * Starts the dialog to export the current graph visualization as PDF.
     */
    public void exportPDF(File pFile) {

        VisualizationImageServer<Vertex, Edge> vis = new VisualizationImageServer<>(vv.getGraphLayout(), vv.getGraphLayout().getSize());
        vis.setBackground(Color.WHITE);
        vis.setRenderer(new SyndromRenderer<>());
        /* vis.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.VIEW).scale(
                Syndrom.getInstance().getVv().getRenderContext().getMultiLayerTransformer().getTransformer(Layer.VIEW).getScaleX(),
                Syndrom.getInstance().getVv().getRenderContext().getMultiLayerTransformer().getTransformer(Layer.VIEW).getScaleY(),
                vv.getCenter()
        );*/

       /* AffineTransform view = vis.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.VIEW).getTransform();
        Point2D min = getMinPoint();
        System.out.println("min point: "+min);*/

        /*Point2D min = getMinPoint();
        vis.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.VIEW).setTranslate(min.getX(), min.getY());*/


        /*System.out.println("maxPoint: "+getMaxPoint());
        System.out.println("minPoint: "+getMinPoint());
        Point2D min = getMinPoint();
        Point2D max = getMaxPoint();

        java.awt.geom.Point2D middle = new java.awt.geom.Point2D.Double((min.getX() + max.getX())/2, (min.getY() + max.getY())/2 );



        System.out.println("middle: "+middle);

        double distanceX = 2000.0 / Math.abs(min.getX() - max.getX()) ;
        double distanceY = 1500.0 / Math.abs(min.getY() - max.getY()) ;

        double scale = (distanceX < distanceY) ? distanceX : distanceY;
        scale = scale - 0.01;
        System.out.println("scale: "+scale);

        if (distanceX > distanceY) {
            System.out.println("stanceX");
        } else {
            System.out.println("distanceY");
        }

        //middle = vis.getRenderContext().getMultiLayerTransformer().transform(Layer.LAYOUT, middle);
*/


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
        try {
            vectorGraphics = new PDFGraphics2D(pFile, graphicsDimension);
            Properties properties = new Properties();
            properties.setProperty(ORIENTATION, LANDSCAPE);
            properties.setProperty(PAGE_SIZE, A4);
            vectorGraphics.setProperties(properties);
            vectorGraphics.startExport();
            vis.print(vectorGraphics);
            vectorGraphics.endExport();
        } catch (FileNotFoundException e) {
            pFile.deleteOnExit();
            logger.error(e.toString());
        }
    }

    /**
     * Starts the dialog to export the current graph visualization as PDF.
     */
    public void printPDF() {
        File file = null;
        try {
            file=Files.createTempFile("graphit", null).toFile();
            exportPDF(file);

            PDDocument pdDocument = PDDocument.load(file);

            PrinterJob printerJob = PrinterJob.getPrinterJob();
            printerJob.setJobName(file.getName());
            printerJob.setPageable(new PDFPageable(pdDocument));
            boolean printSucceeds=printerJob.printDialog();
            if (printSucceeds) {
                    printerJob.print();
            }
        } catch (IOException | PrinterException e) {
            logger.error(e.toString());
        }finally {
            if(file!=null){
                try {
                    Files.delete(file.toPath());
                } catch (IOException e) {
                    //ignored
                }
            }
        }

    }
}
