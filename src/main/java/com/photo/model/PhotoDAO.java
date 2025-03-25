package com.photo.model;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.util.HibernateUtil;

public class PhotoDAO implements PhotoDAOInterface {

    private SessionFactory factory;

    public PhotoDAO() {
        factory = HibernateUtil.getSessionFactory();
    }

    @Override
    public void insert(PhotoVO photoVO) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            photoVO.setUpdateTime(new java.sql.Timestamp(System.currentTimeMillis()));
            session.persist(photoVO);
            tx.commit();
        }
    }

    @Override
    public void update(PhotoVO photoVO) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            photoVO.setUpdateTime(new java.sql.Timestamp(System.currentTimeMillis()));
            session.merge(photoVO);
            tx.commit();
        }
    }

    @Override
    public void delete(Integer photoId) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            PhotoVO photoVO = session.get(PhotoVO.class, photoId);
            if (photoVO != null) {
                session.remove(photoVO);
            }
            tx.commit();
        }
    }

    @Override
    public PhotoVO findByPrimaryKey(Integer photoId) {
        try (Session session = factory.openSession()) {
            return session.get(PhotoVO.class, photoId);
        }
    }

    @Override
    public List<PhotoVO> getAll() {
        try (Session session = factory.openSession()) {
            Query<PhotoVO> query = session.createQuery("from PhotoVO", PhotoVO.class);
            return query.getResultList();
        }
    }
}