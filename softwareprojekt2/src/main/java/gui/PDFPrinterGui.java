package gui;

import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.io.InputStream;

/**
 * Class for showing the print dialog.
 */
public class PDFPrinterGui {

    /**
     * TODO
     */
    private static Logger logger = Logger.getLogger(PDFPrinterGui.class);

    /**
     * Constructor for the PDFPrinterGui with the inputStream to print.
     *
     * @param pInputStream The inputStream to print.
     */
    public PDFPrinterGui(InputStream pInputStream) {
        print(pInputStream);
    }

    /**
     * Prints the passed InputStream.
     *
     * @param pInputStream The inputStream to print.
     */
    private void print(InputStream pInputStream) {
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