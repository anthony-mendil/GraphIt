package io;

import log_management.DatabaseManager;
import log_management.dao.PersonalEntityManagerFactory;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.BasicConfigurator;
import org.junit.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

public class OOFioTest {

    private static OOFio oofio;
    private static DatabaseManager databaseManager;
    private static String anyOOF;
    private static File simpleGraph;
    private static File exportedSimpleGraph;
    private static String simpleGraphGXL="";
    private static String simpleGraphJSON ="";

    private static File bigGraph;
    private static File exportedBigGraph;
    private static String bigGraphGXL="";
    private static String bigGraphJSON ="";

    /**
     * javadocTODO
     * @throws IOException javadocTODO
     */
    @BeforeClass
    public static void prepareOnce() throws IOException {
        BasicConfigurator.configure();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        PersonalEntityManagerFactory.setEntityManagerFactory(entityManagerFactory);
        databaseManager = DatabaseManager.getInstance();
        oofio = new OOFio();
        anyOOF=IOUtils.toString(OOFioTest.class.getResourceAsStream("/simpleGraph.oof"), StandardCharsets.UTF_8);

        simpleGraph = Paths.get(".graphitTest", "simpleGraph.oof").toFile();
        FileUtils.copyInputStreamToFile(OOFioTest.class.getResourceAsStream("/simpleGraph.oof"), simpleGraph);
        exportedSimpleGraph=Paths.get(".graphitTest", "ExportSimpleGraph.oof").toFile();
        simpleGraphGXL = IOUtils.toString(OOFioTest.class.getResourceAsStream("/simpleGraphGXL.gxl"), StandardCharsets.UTF_8);
        simpleGraphJSON = IOUtils.toString(OOFioTest.class.getResourceAsStream("/simpleGraphJSON.json"), StandardCharsets.UTF_8);

        bigGraph = Paths.get(".graphitTest", "bigGraph.oof").toFile();
        FileUtils.copyInputStreamToFile(OOFioTest.class.getResourceAsStream("/bigGraph.oof"), bigGraph);
        exportedBigGraph=Paths.get(".graphitTest", "ExportBigGraph.oof").toFile();
        bigGraphGXL = IOUtils.toString(OOFioTest.class.getResourceAsStream("/bigGraphGXL.gxl"), StandardCharsets.UTF_8);
        bigGraphJSON = IOUtils.toString(OOFioTest.class.getResourceAsStream("/bigGraphJSON.json"), StandardCharsets.UTF_8);
    }

    /**
     * javadocTODO
     */
    @AfterClass
    public static void endAll() throws IOException {
        PersonalEntityManagerFactory.getInstance().close();
        FileUtils.deleteDirectory(new File(".graphitTest"));
    }

    /**
     * javadocTODO
     */
    @After
    public void end(){
        databaseManager.getGraphDao().delete(-1);
    }


    /**
     * javadocTODO
     */
    @Test
    public void testCreateOOF() throws IOException {
        Assert.assertEquals(anyOOF, oofio.createOOF(oofio.gxlFromOOF(anyOOF),oofio.jsonFromOOF(anyOOF)));
    }

    /**
     * javadocTODO
     */
    @Test
    public void testEmptyDatabase() {
        Assert.assertTrue(databaseManager.databaseEmpty());
    }

    /**
     * javadocTODO
     */
    @Test
    public void testImportOOFGXLSimple() {
        oofio.importOOF(simpleGraph);
        Assert.assertEquals(
                simpleGraphGXL.trim().replaceAll("\\n|\\s",""),
                databaseManager.getGraphDao().gxlFromDatabase().trim().replaceAll("\\n|\\s",""));
    }

    /**
     * javadocTODO
     */
    @Test
    public void testImportOOFJSONSimple() {
        oofio.importOOF(simpleGraph);
        Assert.assertEquals(
                simpleGraphJSON.trim().replaceAll("\\\\n|\\\\r|\\d",""),
                databaseManager.getLogDao().getAllString().replaceAll("\\d",""));
    }

    /**
     * javadocTODO
     */
    @Test
    public void testExportOOFGXLSimple(){
        oofio.importOOF(simpleGraph);
        oofio.exportAsOOF(exportedSimpleGraph);
        Assert.assertEquals(
                simpleGraphGXL.trim().replaceAll("\\n|\\s",""),
                oofio.gxlFromOOF(FileHandler.fileToString(exportedSimpleGraph)).trim().replaceAll("\\n|\\s",""));
    }

    /**
     * javadocTODO
     */
    @Test
    public void testExportOOFJSONSimple() {
        oofio.importOOF(simpleGraph);
        oofio.exportAsOOF(exportedSimpleGraph);
        Assert.assertEquals(
                simpleGraphJSON.trim().replaceAll("\\\\n|\\\\r|\\d","").replaceAll("\\d",""),
                oofio.jsonFromOOF(FileHandler.fileToString(exportedSimpleGraph)).replaceAll("\\d",""));
    }

    /**
     * javadocTODO
     *//*
    @Test
    public void testImportOOFGXLBig() {
        oofio.importOOF(bigGraph);
        Assert.assertEquals(
                bigGraphGXL.trim().replaceAll("\\n|\\s",""),
                databaseManager.getGraphDao().gxlFromDatabase().trim().replaceAll("\\n|\\s",""));
    }*/

    /**
     * javadocTODO
     *//*
    @Test
    public void testImportOOFJSONBig() {
        oofio.importOOF(bigGraph);
        Assert.assertEquals(
                bigGraphJSON.trim().replaceAll("\\\\n|\\\\r|\\d",""),
                databaseManager.getLogDao().getAllString().replaceAll("\\d",""));
    }*/

    /**
     * javadocTODO
     *//*
    @Test
    public void testExportOOFGXLBig(){
        oofio.importOOF(bigGraph);
        oofio.exportAsOOF(exportedBigGraph);
        Assert.assertEquals(
                bigGraphGXL.trim().replaceAll("\\n|\\s",""),
                oofio.gxlFromOOF(FileHandler.fileToString(exportedBigGraph)).trim().replaceAll("\\n|\\s",""));
    }*/

    /**
     * javadocTODO
     *//*
    @Test
    public void testExportOOFJSONBig() {
        oofio.importOOF(bigGraph);
        oofio.exportAsOOF(exportedBigGraph);
        Assert.assertEquals(
                bigGraphJSON.trim().replaceAll("\\\\n|\\\\r|\\d","").replaceAll("\\d",""),
                oofio.jsonFromOOF(FileHandler.fileToString(exportedBigGraph)).replaceAll("\\d",""));
    }*/
}
