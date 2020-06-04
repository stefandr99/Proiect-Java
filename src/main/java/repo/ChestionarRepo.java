package repo;

import entity.Chestionar;
import util.PersistenceUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class ChestionarRepo {
    @PersistenceContext
    EntityManager entityManager;
    /**
     * constructor unde se intializeaza entityManager cu ajutorul clasei singleton PersistenceUtil
     */
    public ChestionarRepo() {
        entityManager = PersistenceUtil.getFactory().createEntityManager();
    }

    public Chestionar findById(int id) {
        return entityManager.find(Chestionar.class, id);
    }
}
