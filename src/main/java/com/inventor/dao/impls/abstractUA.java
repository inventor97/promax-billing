package com.inventor.dao.impls;

import com.inventor.dao.interfaces.abstractInterface;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public abstract class abstractUA<T> implements abstractInterface<T> {

    private SessionFactory sessionFactory = null;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    private void isActiveSession() {
        if (!getSession().getTransaction().isActive()) {
            getSession().beginTransaction();
        }
    }

    @Override
    public boolean add(T obj) {
        try {
            isActiveSession();
            getSession().persist(obj);
            getSession().getTransaction().commit();
            return true;
        } catch (RuntimeException e) {
            getSession().getTransaction().rollback();
            return false;
        }
    }

    @Override
    public boolean update(T obj) {
        try {
            isActiveSession();
            getSession().update(obj);
            getSession().getTransaction().commit();
            return true;
        } catch (RuntimeException e) {
            getSession().getTransaction().rollback();
            return false;
        }
    }
}
