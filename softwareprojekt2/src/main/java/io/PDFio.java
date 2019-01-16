package io;

import edu.uci.ics.jung.visualization.VisualizationImageServer;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import graph.graph.Edge;
import graph.graph.Sphere;
import graph.graph.Syndrom;
import graph.graph.Vertex;
import graph.visualization.renderers.SyndromRenderer;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import org.freehep.graphics2d.VectorGraphics;
import org.freehep.graphicsio.pdf.PDFGraphics2D;

import java.awt.*;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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


    /**
     * The File the pdf get's written into
     */
    private File file;

    /**
     * Constructs a new PDFio object.
     *
     * @param pVv The VisualizationViewer object of the current graph.
     */
    public PDFio(VisualizationViewer pVv){
        vv=pVv;
    }

    /**
     * Calculates the dimension of the graph spanned by the spheres
     *
     * @return the dimension of the graph
     */
    protected Dimension getGraphDimension(){
        List<Sphere> spheres=Syndrom.getInstance().getGraph().getSpheres();
        Dimension dimension=new Dimension(0,0);
        for(Sphere sph : spheres){
            //check x
            double x=sph.getCoordinates().getX()+sph.getWidth();
            if(x>dimension.getWidth()){
                dimension.setSize(x,dimension.getHeight());
            }
            //check y
            double y=sph.getCoordinates().getY()+sph.getHeight();
            if(y>dimension.getHeight()){
                dimension.setSize(dimension.getWidth(),y);
            }
        }
        return dimension;
    }

    /**
     * Starts the dialog to export the current graph visualization as PDF.
     */
    public void exportPDF(File pFile){
        file=pFile;
        VisualizationImageServer<Vertex, Edge> vis = new VisualizationImageServer<Vertex, Edge>(vv.getGraphLayout(), vv.getGraphLayout().getSize());
        vis.setBackground(Color.WHITE);
        vis.setRenderer(new SyndromRenderer<>());
        VectorGraphics vectorGraphics = null;
        try {
            vectorGraphics = new PDFGraphics2D(file, getGraphDimension());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Properties properties = new Properties();
        properties.setProperty(ORIENTATION,LANDSCAPE);
        properties.setProperty(PAGE_SIZE,A4);
        vectorGraphics.setProperties(properties);
        vectorGraphics.startExport();
        System.out.println(vectorGraphics.getClipBounds().toString());
        vis.print(vectorGraphics);
        vectorGraphics.endExport();
    }

    /**
     * Starts the dialog to export the current graph visualization as PDF.
     */
    public void printPDF() {
        exportPDF(new File("SyndromToPrint.pdf"));
        PDDocument pdDocument= new PDDocument();
        try {
            pdDocument =PDDocument.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        printerJob.setJobName(file.getName());
        printerJob.setPageable(new PDFPageable(pdDocument));
        try {
            printerJob.print();
        } catch (PrinterException e) {
            e.printStackTrace();
        }finally {
            file.delete();
        }
    }
}
