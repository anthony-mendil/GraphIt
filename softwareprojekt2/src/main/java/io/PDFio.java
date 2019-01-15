package io;

import edu.uci.ics.jung.visualization.VisualizationViewer;
import javafx.print.PageOrientation;
import org.freehep.graphics2d.VectorGraphics;
import org.freehep.graphicsbase.util.export.ExportDialog;
import org.freehep.graphicsio.pdf.PDFGraphics2D;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
    public PDFio(VisualizationViewer pVv, File pFile){
        vv=pVv;
        file=pFile;
    }

    /**
     * Creates a PDF of the current graph visualization.
     *
     *  @return A FileInputStream of the PDF of the current graph.
     */
    protected File createPDF(){
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
        return file;
    }

    /**
     * Starts the dialog to export the current graph visualization as PDF.
     */
    public void exportPDF() {
        createPDF();
    }

    /**
     * Starts the dialog to export the current graph visualization as PDF.
     */
    public void printPDF() {
        throw new UnsupportedOperationException();
    }
}
