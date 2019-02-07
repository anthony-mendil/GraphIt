package test.io;

import io.GXLio;
import net.sourceforge.gxl.*;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.persistence.criteria.CriteriaBuilder;
import java.awt.*;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;

public class GXLioTest {

    private GXLDocument doc = null;

    private static Logger logger = Logger.getLogger(GXLioTest.class);

    @Before
    public void prepare(){
        doc = new GXLDocument();
        GXLGraph syndrom = new GXLGraph("syndrom");
        GXLNode sphere0 = new GXLNode("0");
        java.awt.Color fillPaint = new java.awt.Color(255, 0, 0, 255);
        String paintDescription = getPaintDescription(fillPaint);
        sphere0.setAttr("color", new GXLString(paintDescription));
        GXLNode sphere1 = new GXLNode("1");
        sphere1.setAttr("width", new GXLString("25.0"));
        sphere1.setAttr("height", new GXLString("30.0"));
        GXLNode sphere2 = new GXLNode("2");

        Point2D coordinates = new java.awt.geom.Point2D.Double(250.0, 500.0);
        GXLNode vertex0 = new GXLNode("3");
        vertex0.setAttr("coordinates", new GXLString(coordinates.toString()));
        GXLNode vertex1 = new GXLNode("4");
        vertex1.setAttr("size", new GXLInt(15));
        GXLNode vertex2 = new GXLNode("5");
        GXLNode vertex3 = new GXLNode("6");
        GXLNode vertex4 = new GXLNode("7");
        GXLNode vertex5 = new GXLNode("8");
        GXLEdge edge0 = new GXLEdge("2", "3");
        GXLEdge edge1 = new GXLEdge("3", "5");
        GXLEdge edge2 = new GXLEdge("6", "8");
        GXLEdge edge3 = new GXLEdge("8", "6");
        GXLEdge edge4 = new GXLEdge("2", "7");
        GXLEdge edge5 = new GXLEdge("5", "4");
        syndrom.add(sphere0);
        syndrom.add(sphere1);
        syndrom.add(sphere2);
        syndrom.add(vertex0);
        syndrom.add(vertex1);
        syndrom.add(vertex2);
        syndrom.add(vertex3);
        syndrom.add(vertex4);
        syndrom.add(vertex5);
        syndrom.add(edge0);
        syndrom.add(edge1);
        syndrom.add(edge2);
        syndrom.add(edge3);
        syndrom.add(edge4);
        syndrom.add(edge5);
        doc.getDocumentElement().add(syndrom);
        try {
            doc.write(new File("GXLTest"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testElementNumber(){
        logger.info("Ich bin das GXLDokument: " + doc);
        int numberOfGraphs = doc.getDocumentElement().getGraphCount();
        Assert.assertEquals(1, numberOfGraphs);
        int numberOfElementsInTheGraph = doc.getElement("syndrom").getChildCount();
        Assert.assertEquals(15, numberOfElementsInTheGraph);
    }

    @Test
    public void testColor(){
        int numberOfGraphs = doc.getDocumentElement().getGraphCount();
        Assert.assertEquals(1, numberOfGraphs);
        GXLNode sphere0 = (GXLNode) doc.getElement("0");
        java.awt.Color expectedColor = new Color(255, 0, 0, 255);
        String sphereColorDescription = ((GXLString) sphere0.getAttr("color").getValue()).getValue();
        String[] colorArray = getNumberArrayFromString(sphereColorDescription);
        java.awt.Color sphereColor = new Color(Integer.parseInt(colorArray[0]), Integer.parseInt(colorArray[1]),
                Integer.parseInt(colorArray[2]), Integer.parseInt(colorArray[3]));
        logger.info("Beschreibung der erwarteten Farbe: " + getPaintDescription(expectedColor));
        logger.info("Beschreibung der Farbe der Sphäre: " + getPaintDescription(sphereColor));
        Assert.assertEquals(expectedColor, sphereColor);
    }



    @Test
    public void testCoordinates(){
        GXLNode vertex0 = (GXLNode) doc.getElement("3");
        String coordinatesDescription = ((GXLString) vertex0.getAttr("coordinates").getValue()).getValue();
        String[] coordinatesArray = getNumberArrayFromString(coordinatesDescription);
        Assert.assertEquals(250.0, Double.parseDouble(coordinatesArray[0]), 0.0);
        Assert.assertEquals(500.0, Double.parseDouble(coordinatesArray[1]), 0.0);
    }

    @Test
    public void testSize(){
        GXLNode sphere1 = (GXLNode) doc.getElement("1");
        GXLNode vertex1 = (GXLNode) doc.getElement("4");
        double sphereWidth = Double.parseDouble((((GXLString) sphere1.getAttr("width").getValue()).getValue()));
        double sphereheight = Double.parseDouble((((GXLString) sphere1.getAttr("height").getValue()).getValue()));
        Assert.assertEquals(25.0, sphereWidth, 0.0);
        Assert.assertEquals(30.0, sphereheight, 0.0);
        int vertexSize = (((GXLInt) vertex1.getAttr("size").getValue()).getIntValue());
        Assert.assertEquals(15, vertexSize);
    }



    /**
     * Forms a description of a color.
     *
     * @param color the color that need to be describted
     * @return the description of the color as a String
     */
    public String getPaintDescription(Color color) {
        return ("java.awt.Color[r=" + color.getRed() + ",g=" + color.getGreen()
                + ",b=" + color.getBlue() + ",a=" + color.getAlpha() + "]");
    }


    /**
     * Converts a String that contains an unknown amount of numbers into a String array.
     * Each entry of the array contains a number as String.
     * It is not generally clear of wich concrete type this number is.
     *
     * @param pWord a word containing an unknown amount of numbers.
     * @return the numbers as String contained in the String parameter as entries in the array.
     */
    public String[] getNumberArrayFromString(String pWord) {
        String word = pWord;
        String[] alphabet = {"2D", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        for (String s : alphabet) {
            word = word.replaceAll(s, "");
            word = word.replaceAll(s.toUpperCase(), "");
        }
        int numberOFDots = 0;
        while (word.charAt(numberOFDots) == '.') {
            numberOFDots++;
        }
        word = word.substring(numberOFDots);
        word = word.substring(1, word.length() - 1);
        if (word.contains("=")) {
            word = word.substring(1);
            word = word.replaceAll(",", "");
            return word.split("=");
        }
        word = word.trim();
        return word.split(",");
    }

}
