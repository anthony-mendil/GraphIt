package log_management.dao;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Singleton
public class PersonalEntityManager {

    private static EntityManager entityManager;

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

    private PersonalEntityManager() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        entityManager = entityManagerFactory.createEntityManager();
    }
}
