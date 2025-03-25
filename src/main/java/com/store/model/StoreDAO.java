package com.store.model;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.util.HibernateUtil;

public class StoreDAO {

    private SessionFactory factory;

    public StoreDAO() {
        factory = HibernateUtil.getSessionFactory();
    }

    public void insert(StoreVO storeVO) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(storeVO);
            tx.commit();
        }
    }

    public void update(StoreVO storeVO) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.update(storeVO);
            tx.commit();
        }
    }

    public void delete(Integer storeId) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            StoreVO store = session.get(StoreVO.class, storeId);
            if (store != null) {
                session.remove(store);
            }
            tx.commit();
        }
    }

    public StoreVO findByPrimaryKey(Integer storeId) {
        try (Session session = factory.openSession()) {
            return session.get(StoreVO.class, storeId);
        }
    }

    public List<StoreVO> getAll() {
        try (Session session = factory.openSession()) {
            Query<StoreVO> query = session.createQuery("from StoreVO", StoreVO.class);
            return query.getResultList();
        }
    }
}