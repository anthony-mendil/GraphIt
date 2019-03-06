package io;

import log_management.DatabaseManager;
import log_management.dao.PersonalEntityManagerFactory;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
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
    private static String simpleGraphGXL="";
    private static String simpleGraphJSON ="";
    private static DatabaseManager databaseManager;

    /**
     * javadocTODO
     * @throws IOException javadocTODO
     */
    @BeforeClass
    public static void prepareOnce() throws IOException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        PersonalEntityManagerFactory.setEntityManagerFactory(entityManagerFactory);
        databaseManager = DatabaseManager.getInstance();
        oofio = new OOFio();
        simpleGraph = Paths.get(System.getProperty("user.home"), ".graphitTest", "simpleGraph.oof").toFile();
        FileUtils.copyInputStreamToFile(OOFioTest.class.getResourceAsStream("/simpleGraph.oof"), simpleGraph);
        simpleGraphGXL = IOUtils.toString(OOFioTest.class.getResourceAsStream("/simpleGraphGXL.gxl"), StandardCharsets.UTF_8);
        simpleGraphJSON = IOUtils.toString(OOFioTest.class.getResourceAsStream("/simpleGraphJSON.json"), StandardCharsets.UTF_8);
    }

    /**
     * javadocTODO
     */
    @AfterClass
    public static void endAll(){
        PersonalEntityManagerFactory.getInstance().close();
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
                databaseManager.getGraphDao().gxlFromDatabase().trim().replaceAll("\\n|\\s",""),
                simpleGraphGXL.trim().replaceAll("\\n|\\s",""));
    }

    /**
     * javadocTODO
     *//*
    @Test
    public void testImportOOFJSON() {
        oofio.importOOF(simpleGraph);
        Assert.assertEquals(
                databaseManager.getLogDao().getAllString(),
                simpleGraphJSON);
    }*/

}
