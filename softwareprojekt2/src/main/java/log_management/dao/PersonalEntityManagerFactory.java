package log_management.dao;

import javax.persistence.EntityManagerFactory;

/**
 * The entity manager factory used by everyone to create an entity manager.
 *
 * @author Anthony Mendil
 */
public class PersonalEntityManagerFactory {

    /**
     * Manages the access to the database.
     */
    private static EntityManagerFactory entityManagerFactory;

    /**
     * This constructor should not be called.
     */
    private PersonalEntityManagerFactory() {
        throw new IllegalStateException("utility class");
    }

    /**
     * Returns the personal Entity Manager used to access the database.
     *
     * @return The personal Entity Manager.
     */
    public static EntityManagerFactory getInstance() {
        return entityManagerFactory;
    }

    /**
     * Sets the entity manager factory.
     *
     * @param initialEntityManagerFactory The entity manager factory.
     */
    public static void setEntityManagerFactory(EntityManagerFactory initialEntityManagerFactory) {
        entityManagerFactory = initialEntityManagerFactory;
    }
}
