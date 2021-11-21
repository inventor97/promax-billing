package com.inventor.dao.impls;

import com.inventor.dao.interfaces.subject;
import com.inventor.entities.CashersEntity;
import com.inventor.entities.ChecksDataEntity;
import com.inventor.entities.SubjectsEntity;
import com.inventor.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class subjectDAOimpls extends abstractUA<SubjectsEntity> implements subject {

    private static subjectDAOimpls sDAOImpl;
    private SessionFactory sessionFactory = null;

    public subjectDAOimpls() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    private void isActiveSession() {
        if (!getSession().getTransaction().isActive()) {
            getSession().beginTransaction();
        }
    }

    public static subjectDAOimpls getInstance() {
        if (sDAOImpl == null) {
            sDAOImpl = new subjectDAOimpls();
        }
        return sDAOImpl;
    }

    @Override
    public List<SubjectsEntity> getAll() {
        isActiveSession();
        List<SubjectsEntity> list = new ArrayList<>(getSession().createCriteria(SubjectsEntity.class).list());
        getSession().getTransaction().commit();
        return list;
    }

    @Override
    public SubjectsEntity get(long id) {
        isActiveSession();
        SubjectsEntity obj = getSession().get(SubjectsEntity.class,(int) id);
        getSession().getTransaction().commit();
        return obj;
    }

    @Override
    public boolean remove(long obj) {
        isActiveSession();
        SubjectsEntity var = getSession().load(SubjectsEntity.class, (int) obj);
        if (var != null) {
            getSession().delete(var);
            return true;
        }
        getSession().getTransaction().commit();
        return false;
    }

    @Override
    public List<String> getNames() {
        isActiveSession();
        List<String> list = new ArrayList<>(getSession()
                .createCriteria(SubjectsEntity.class)
                .setProjection(Projections
                        .property( "name"))
                .list());
        getSession().getTransaction().commit();
        return list;
    }

    @Override
    public int getId(String name) {
        isActiveSession();
        int id = (int) getSession().createCriteria(SubjectsEntity.class)
                .add(Restrictions.eq("name", name))
                .setProjection(Projections
                        .property("id")).uniqueResult();
        getSession().getTransaction().commit();
        return id;
    }
}
