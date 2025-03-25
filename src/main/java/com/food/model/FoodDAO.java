package com.food.model;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.util.HibernateUtil;

public class FoodDAO {

    public void insert(FoodVO foodVO) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(foodVO);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public FoodVO findByPrimaryKey(Integer foodId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(FoodVO.class, foodId);
        }
    }

    public void update(FoodVO foodVO) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(foodVO);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public void delete(Integer foodId) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            FoodVO foodVO = session.get(FoodVO.class, foodId);
            if (foodVO != null) session.delete(foodVO);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public List<FoodVO> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM FoodVO", FoodVO.class).list();
        }
    }
}
