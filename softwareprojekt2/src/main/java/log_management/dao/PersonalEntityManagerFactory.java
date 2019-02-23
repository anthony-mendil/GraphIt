package log_management.dao;

import javax.persistence.EntityManagerFactory;

public class PersonalEntityManagerFactory {

    /**
     * Manages the access to the database.
     */
    private static EntityManagerFactory entityManagerFactory;

    /**
     * Returns the personal Entity Manager used to access the database.
     * @return The personal Entity Manager.
     */
    public static EntityManagerFactory getInstance() {
        return entityManagerFactory;
    }

    public static void setEntityManagerFactory(EntityManagerFactory initialEntityManagerFactory) {
        entityManagerFactory = initialEntityManagerFactory;
    }
}
