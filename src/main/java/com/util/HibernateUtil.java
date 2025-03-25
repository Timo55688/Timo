package com.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.food.model.FoodVO;
import com.photo.model.PhotoVO;
import com.store.model.StoreVO;
import com.foodType.model.FoodTypeVO;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration cfg = new Configuration().configure();

            // 加入所有 Hibernate 實體類
            cfg.addAnnotatedClass(FoodVO.class);
            cfg.addAnnotatedClass(PhotoVO.class);
            cfg.addAnnotatedClass(StoreVO.class);
            cfg.addAnnotatedClass(FoodTypeVO.class);

            sessionFactory = cfg.buildSessionFactory();

        } catch (Throwable ex) {
            System.err.println("初始化 SessionFactory 失敗: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}
