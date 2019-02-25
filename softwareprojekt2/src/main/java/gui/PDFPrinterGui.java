package gui;

import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.io.InputStream;

public class PDFPrinterGui {

    private static Logger logger = Logger.getLogger(PDFPrinterGui.class);

    /**
     *
     */
    public PDFPrinterGui(InputStream pInputStream) {
        print(pInputStream);
    }

    public void print(InputStream pInputStream) {
        try {
            PDDocument pdDocument = PDDocument.load(pInputStream);
            PrinterJob printerJob = PrinterJob.getPrinterJob();
            printerJob.setJobName("GraphIt-Graph");
            printerJob.setPageable(new PDFPageable(pdDocument));
            boolean printSucceeds = printerJob.printDialog();
            if (printSucceeds) {
                printerJob.print();
            }
        } catch (IOException | PrinterException e) {
            logger.error(e.toString());
        }
    }
}