package util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.spi.PersistenceProvider;

/**
 * Clasa singleton care va returna EntityManagerFactory
 */
public class PersistenceUtil {
    public static EntityManagerFactory getFactory() {
        return Persistence.createEntityManagerFactory("chestionarPU");
    }

}
