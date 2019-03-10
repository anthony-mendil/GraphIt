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

/**
 * The test class for oof testing.
 */
public class OOFioTest {

    /**
     * The OOFio that handles the import and export of the oof.
     */
    private static OOFio oofio;

    /**
     * The DatabaseManager that handles the database, which is used
     * to test the import and export of the oof.
     */
    private static DatabaseManager databaseManager;

    /**
     * An oof String that is used to test the oof structure.
     */
    private static String anyOOF;

    /**
     * An oof file that gets tested.
     */
    private static File simpleGraph;

    /**
     * A file that the oof gets exported to.
     */
    private static File exportedSimpleGraph;

    /**
     * A json String from an oof that is compared to the imported and exported oof.
     */
    private static String simpleGraphJSON ="";

    /**
     * An oof file that gets tested.
     */
    private static File bigGraph;

    /**
     * A file that the oof gets exported to.
     */
    private static File exportedBigGraph;

    /**
     * A json String from an oof that is compared to the imported and exported oof.
     */
    private static String bigGraphJSON ="";

    /**
     * Prepares the oof tests. It starts the database, and loads all documents used by the import and export test cases.
     * @throws IOException When not all test documents could be loaded.
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
        simpleGraphJSON = IOUtils.toString(OOFioTest.class.getResourceAsStream("/simpleGraphJSON.json"), StandardCharsets.UTF_8);

        bigGraph = Paths.get(".graphitTest", "bigGraph.oof").toFile();
        FileUtils.copyInputStreamToFile(OOFioTest.class.getResourceAsStream("/bigGraph.oof"), bigGraph);
        exportedBigGraph=Paths.get(".graphitTest", "ExportBigGraph.oof").toFile();
        bigGraphJSON = IOUtils.toString(OOFioTest.class.getResourceAsStream("/bigGraphJSON.json"), StandardCharsets.UTF_8);
    }

    /**
     * Closes the Database and deletes the test folder.
     */
    @AfterClass
    public static void endAll() throws IOException {
        PersonalEntityManagerFactory.getInstance().close();
        FileUtils.deleteDirectory(new File(".graphitTest"));
    }

    /**
     * Clears the database.
     */
    @After
    public void end(){
        databaseManager.getGraphDao().delete(-1);
    }


    /**
     * Tests the creation and the disassembling of oof Strings.
     */
    @Test
    public void testCreateOOF() {
        Assert.assertEquals(anyOOF, oofio.createOOF(oofio.gxlFromOOF(anyOOF),oofio.jsonFromOOF(anyOOF)));
    }

    /**
     * Tests if the Database is empty on startup.
     */
    @Test
    public void testEmptyDatabase() {
        Assert.assertTrue(databaseManager.databaseEmpty());
    }

    /**
     * Tests if the json of the oof gets imported correctly.
     */
    @Test
    public void testImportOOFJSONSimple() {
        oofio.importOOF(simpleGraph);
        Assert.assertEquals(
                simpleGraphJSON.trim().replaceAll("\\\\n|\\\\r|\\d",""),
                databaseManager.getLogDao().getAllString().replaceAll("\\d",""));
    }

    /**
     *      * Tests if the json of the oof gets exported correctly.
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
     * Tests if the json of the oof gets imported correctly.
     */
    @Test
    public void testImportOOFJSONBig() {
        oofio.importOOF(bigGraph);
        Assert.assertEquals(
                bigGraphJSON.trim().replaceAll("\\\\n|\\\\r|\\d",""),
                databaseManager.getLogDao().getAllString().replaceAll("\\d",""));
    }

    /**
     * Tests if the json of the oof gets exported correctly.
     */
    @Test
    public void testExportOOFJSONBig() {
        oofio.importOOF(bigGraph);
        oofio.exportAsOOF(exportedBigGraph);
        Assert.assertEquals(
                bigGraphJSON.trim().replaceAll("\\\\n|\\\\r|\\d","").replaceAll("\\d",""),
                oofio.jsonFromOOF(FileHandler.fileToString(exportedBigGraph)).replaceAll("\\d",""));
    }
}
