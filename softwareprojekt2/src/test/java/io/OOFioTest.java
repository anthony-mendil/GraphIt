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
    private static File simpleGraph;
    private static File exportedSimpleGraph;
    private static String anyOOF;
    private static String simpleGraphGXL="";
    private static String simpleGraphJSON ="";
    private static DatabaseManager databaseManager;

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
        simpleGraph = Paths.get(".graphitTest", "simpleGraph.oof").toFile();
        FileUtils.copyInputStreamToFile(OOFioTest.class.getResourceAsStream("/simpleGraph.oof"), simpleGraph);
        exportedSimpleGraph=Paths.get(".graphitTest", "ExportSimpleGraph.oof").toFile();
        simpleGraphGXL = IOUtils.toString(OOFioTest.class.getResourceAsStream("/simpleGraphGXL.gxl"), StandardCharsets.UTF_8);
        simpleGraphJSON = IOUtils.toString(OOFioTest.class.getResourceAsStream("/simpleGraphJSON.json"), StandardCharsets.UTF_8);
        anyOOF=IOUtils.toString(OOFioTest.class.getResourceAsStream("/simpleGraph.oof"), StandardCharsets.UTF_8);
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

    private void resetDB() throws IOException {
        databaseManager.getGraphDao().delete(-1);
        PersonalEntityManagerFactory.getInstance().close();
        FileUtils.deleteDirectory(new File(".graphitTest"));
        prepareOnce();
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
    public void testImportOOFGXL() {
        oofio.importOOF(simpleGraph);
        Assert.assertEquals(
                simpleGraphGXL.trim().replaceAll("\\n|\\s",""),
                databaseManager.getGraphDao().gxlFromDatabase().trim().replaceAll("\\n|\\s",""));
    }

    /**
     * javadocTODO
     */
    @Test
    public void testImportOOFJSON() {
        oofio.importOOF(simpleGraph);
        Assert.assertEquals(
                simpleGraphJSON.trim().replaceAll("\\\\n|\\\\r|\\d",""),
                databaseManager.getLogDao().getAllString().replaceAll("\\d",""));
    }

    /**
     * javadocTODO
     */
    @Test
    public void testExportOOFGXL(){
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
    public void testExportOOFJSON() throws IOException {
        resetDB();
        oofio.importOOF(simpleGraph);
        oofio.exportAsOOF(exportedSimpleGraph);
        Assert.assertEquals(
                simpleGraphJSON.trim().replaceAll("\\\\n|\\\\r|\\d","").replaceAll("\\d",""),
                oofio.jsonFromOOF(FileHandler.fileToString(exportedSimpleGraph)).replaceAll("\\d",""));
    }
}
