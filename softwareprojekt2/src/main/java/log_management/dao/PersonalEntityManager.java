package log_management.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersonalEntityManager {

    /**
     * Manages the access to the database.
     */
    private static EntityManager entityManager;

    /**
     * returns the personal Entity Manager used to access the database
     * @return the personal Entity Manager
     */
    public static EntityManager getInstance() {
        if (entityManager != null) {
            return entityManager;
        }
        else {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
            entityManager = entityManagerFactory.createEntityManager();
            return entityManager;
        }
    }
}
