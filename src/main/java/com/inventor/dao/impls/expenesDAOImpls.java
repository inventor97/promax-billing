package com.inventor.dao.impls;

import com.inventor.dao.interfaces.expensesDAO;
import com.inventor.entities.ChecksDataEntity;
import com.inventor.entities.ExpensesEntity;
import com.inventor.entities.TeachersEntity;
import com.inventor.utils.HibernateUtil;
import com.inventor.utils.dateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class expenesDAOImpls extends abstractUA<ExpensesEntity> implements expensesDAO {

    private static expenesDAOImpls tDAOImpl;
    private SessionFactory sessionFactory = null;

    public expenesDAOImpls() {
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

    public static expenesDAOImpls getInstance() {
        if (tDAOImpl == null) {
            tDAOImpl = new expenesDAOImpls();
        }
        return tDAOImpl;
    }

    @Override
    public List<ExpensesEntity> getAll() {
        isActiveSession();
        List<ExpensesEntity> list = new ArrayList<>(getSession().createCriteria(ExpensesEntity.class).list());
        getSession().getTransaction().commit();
        return list;
    }

    @Override
    public ExpensesEntity get(long id) {
        isActiveSession();
        ExpensesEntity obj = getSession().get(ExpensesEntity.class, (int) id);
        getSession().getTransaction().commit();
        return obj;
    }

    @Override
    public boolean remove(long obj) {
        isActiveSession();
        ExpensesEntity var = getSession().load(ExpensesEntity.class, (int) obj);
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
                .createCriteria(ExpensesEntity.class)
                .setProjection(Projections
                        .property( "name"))
                .list());
        getSession().getTransaction().commit();
        return list;
    }

    @Override
    public int getId(String name) {
        isActiveSession();
        int id = (int) getSession().createCriteria(ExpensesEntity.class)
                .add(Restrictions.eq("name", name)).uniqueResult();
        getSession().getTransaction().commit();
        return id;
    }

    @Override
    public List<ExpensesEntity> getByList(Date date, boolean byMonth) {
        isActiveSession();
        Criteria criteria = getSession().createCriteria(ChecksDataEntity.class);
        if (!byMonth) {
            criteria.add(Restrictions.eq("dateCrated", date));
        } else {
            criteria.add(Restrictions.between("dateCrated", dateUtils.getFirstDayMonth(date), dateUtils.getLastDayMonth(date)));
        }
        List<ExpensesEntity> ls = new ArrayList<>(criteria.list());
        getSession().getTransaction().commit();
        return ls;
    }
}
