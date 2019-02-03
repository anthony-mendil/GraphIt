package log_management.dao;

import javax.persistence.EntityManager;

public class PersonalEntityManager {

    /**
     * Manages the access to the database.
     */
    private static EntityManager entityManager;

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
