package io;

import edu.uci.ics.jung.visualization.VisualizationViewer;

import java.io.FileInputStream;

public class IODialog {

    /**
     * Constructs a new IODialog
     */
    public IODialog(){

    }

    /**
     * Starts a new dialog to import a file of type {@code pType}
     *
     * @param pType The type of the File that gets imported: {@code .gxl} or {@code .oof}
     * @return The file content as String
     */
    protected String inputDialog(String pType){
        throw new UnsupportedOperationException();
    }

    /**
     * Starts a new dialog to export a file with {@code pData} as content
     *
     * @param pData The Data as String that gets exported
     */
    protected void outputDialog(String pData){
        throw new UnsupportedOperationException();
    }

    /**
     * Starts a new dialog to export a PDF of the graph
     *
     * @param pPDF The PDF that gets exported
     */
    protected void outputDialog(FileInputStream pPDF){
        throw new UnsupportedOperationException();
    }

    /**
     * Starts a new dialog to print a PDF file of the graph
     *
     * @param pPDF The PDF that gets printed
     */
    protected void printDialog(FileInputStream pPDF){
        throw new UnsupportedOperationException();
    }
}
