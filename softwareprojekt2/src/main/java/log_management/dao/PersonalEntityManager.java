package log_management.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersonalEntityManager {

    /**
     * Manages the access to the database.
     */
    private static EntityManager entityManager;

//    static {
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
//        entityManager = entityManagerFactory.createEntityManager();
//    }

    /**
     * Returns the personal Entity Manager used to access the database.
     * @return The personal Entity Manager.
     */
    public static EntityManager getInstance() {
        return entityManager;
    }

    public static void setEntityManager(EntityManager initialEntityManager) {
        entityManager = initialEntityManager;
    }
}
