package io;

import log_management.DatabaseManager;
import log_management.dao.PersonalEntityManagerFactory;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class OOFioTest {

    private void initDatabase(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        PersonalEntityManagerFactory.setEntityManagerFactory(entityManagerFactory);
    }

    @Test
    public void testEmptyDatabase(){
        initDatabase();
        Assert.assertTrue(DatabaseManager.getInstance().databaseEmpty());
        PersonalEntityManagerFactory.getInstance().close();
    }

    @Test
    public void testImportOOF(){
        
    }
}
