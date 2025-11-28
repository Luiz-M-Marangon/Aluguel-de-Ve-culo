package com.mycompany.locadora.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.mycompany.locadora.service.HibernateUtil;
import java.util.List;

public class GenericDAO<T> {
    private final Class<T> type;

    public GenericDAO(Class<T> type) {
        this.type = type;
    }

    public void save(T entity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void update(T entity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void delete(T entity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public T findById(Object id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(type, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<T> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from " + type.getName(), type).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}