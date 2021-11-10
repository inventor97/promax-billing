package com.inventor.dao.impls;

import com.inventor.dao.interfaces.cashers;
import com.inventor.entities.CashersEntity;
import com.inventor.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class cashersDAOImpls extends abstractUA<CashersEntity> implements cashers  {

    private static cashersDAOImpls cDAOImpls;
    private SessionFactory sessionFactory = null;

    public cashersDAOImpls() {
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

    public static cashersDAOImpls getInstance() {
        if (cDAOImpls == null) {
            cDAOImpls = new cashersDAOImpls();
        }
        return cDAOImpls;
    }

    @Override
    public List<CashersEntity> getAll() {
        isActiveSession();
        List<CashersEntity> list = new ArrayList<>(getSession().createCriteria(CashersEntity.class).list());
        getSession().getTransaction().commit();
        return list;
    }

    @Override
    public CashersEntity get(long id) {
        isActiveSession();
        CashersEntity obj = getSession().get(CashersEntity.class, id);
        getSession().getTransaction().commit();
        return obj;
    }

    @Override
    public boolean remove(long obj) {
        isActiveSession();
        CashersEntity var = getSession().load(CashersEntity.class, (int) obj);
        if (var != null) {
            getSession().delete(var);
            return true;
        }
        getSession().getTransaction().commit();
        return false;
    }

    @Override
    public List<String> getNames() {
        List<String> list = new ArrayList<>(getSession()
                .createCriteria(CashersEntity.class)
                .setProjection(Projections
                        .property("name"))
                .list());
        getSession().getTransaction().commit();
        return list;
    }

    @Override
    public int getId(String name) {
        isActiveSession();
        int id = (int) getSession().createCriteria(CashersEntity.class)
                .add(Restrictions.eq("name", name)).uniqueResult();
        getSession().getTransaction().commit();

        return id;
    }
}
