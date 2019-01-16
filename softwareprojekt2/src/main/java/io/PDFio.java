package io;

import edu.uci.ics.jung.visualization.VisualizationViewer;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import org.freehep.graphics2d.VectorGraphics;
import org.freehep.graphicsio.pdf.PDFGraphics2D;

import javax.print.PrintService;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static org.freehep.graphicsio.PageConstants.*;

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
     * @param pFile The destination File
     */
    public PDFio(VisualizationViewer pVv){
        vv=pVv;
    }

    /**
     * Starts the dialog to export the current graph visualization as PDF.
     */
    public void exportPDF(File pFile) {
        file=pFile;
        try {
            VectorGraphics vectorGraphics = new PDFGraphics2D(file, vv);
            Properties properties = new Properties();
            properties.setProperty(ORIENTATION,LANDSCAPE);
            properties.setProperty(PAGE_SIZE,A4);
            vectorGraphics.setProperties(properties);
            vectorGraphics.startExport();
            vv.print(vectorGraphics);
            vectorGraphics.endExport();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts the dialog to export the current graph visualization as PDF.
     */
    public void printPDF() {
        exportPDF(new File("/tmp.pdf"));
        PDDocument pdDocument= new PDDocument();
        try {
            pdDocument =PDDocument.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        printerJob.setPageable(new PDFPageable(pdDocument));
        try {
            printerJob.print();
        } catch (PrinterException e) {
            e.printStackTrace();
        }
    }
}
