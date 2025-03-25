package com.foodType.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.util.HibernateUtil;

public class FoodTypeDAO {

    private SessionFactory factory;

    public FoodTypeDAO() {
        factory = HibernateUtil.getSessionFactory();
    }

    public void insert(FoodTypeVO foodTypeVO) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(foodTypeVO);
            tx.commit();
        }
    }

    public void update(FoodTypeVO foodTypeVO) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.update(foodTypeVO);
            tx.commit();
        }
    }

    public FoodTypeVO findByPrimaryKey(Integer foodTypeId) {
        try (Session session = factory.openSession()) {
            return session.get(FoodTypeVO.class, foodTypeId);
        }
    }

    public List<FoodTypeVO> getAll() {
        try (Session session = factory.openSession()) {
            Query<FoodTypeVO> query = session.createQuery("from FoodTypeVO", FoodTypeVO.class);
            return query.getResultList();
        }
    }

    public void delete(Integer foodTypeId) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            FoodTypeVO vo = session.get(FoodTypeVO.class, foodTypeId);
            if (vo != null) session.remove(vo);
            tx.commit();
        }
    }
}