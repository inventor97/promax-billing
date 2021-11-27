package com.inventor.dao.impls;

import com.inventor.dao.interfaces.checks;
import com.inventor.entities.ChecksDataEntity;
import com.inventor.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class checksDataDAOimpls extends abstractUA<ChecksDataEntity> implements checks {

    private static checksDataDAOimpls checksDAOImpls;
    private SessionFactory sessionFactory = null;

    public checksDataDAOimpls() {
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

    public static checksDataDAOimpls getInstance() {
        if (checksDAOImpls == null) {
            checksDAOImpls = new checksDataDAOimpls();
        }
        return checksDAOImpls;
    }

    @Override
    public List<ChecksDataEntity> getAll() {
        isActiveSession();
        List<ChecksDataEntity> list = new ArrayList<>(getSession().createCriteria(ChecksDataEntity.class).list());
        getSession().getTransaction().commit();
        return list;
    }

    @Override
    public ChecksDataEntity get(long id) {
        isActiveSession();
        ChecksDataEntity obj = getSession().get(ChecksDataEntity.class, id);
        getSession().getTransaction().commit();
        return obj;
    }

    @Override
    public boolean remove(long obj) {
        isActiveSession();
        ChecksDataEntity var = getSession().load(ChecksDataEntity.class, (int) obj);
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
                .createCriteria(ChecksDataEntity.class)
                .setProjection(Projections
                        .property( "name"))
                .list());
        getSession().getTransaction().commit();
        return list;
    }

    @Override
    public int getId(String name) {
        isActiveSession();
        int id = (int) getSession().createCriteria(ChecksDataEntity.class)
                .add(Restrictions.eq("name", name)).uniqueResult();
        getSession().getTransaction().commit();

        return id;
    }

    public int getMaxId() {
        isActiveSession();
        int maxId = (int) getSession().createCriteria(ChecksDataEntity.class)
                .setProjection(Projections
                        .max("id")).uniqueResult();
        return maxId;
    }

    @Override
    public List<ChecksDataEntity> getListByDate(Date date) {
        isActiveSession();
        List<ChecksDataEntity> ls = new ArrayList<>(getSession().createCriteria(ChecksDataEntity.class)
                .add(Restrictions
                        .eq("dateCrated", date)).list());
        getSession().getTransaction().commit();
        return ls;
    }

    @Override
    public List<ChecksDataEntity> getListBySubject(String sub) {
        isActiveSession();
        List<ChecksDataEntity> ls = new ArrayList<>(getSession()
                .createCriteria(ChecksDataEntity.class).add(Restrictions.ilike("subjects", sub + ",")).list());
        getSession().getTransaction().commit();
        return ls;
    }

    @Override
    public List<ChecksDataEntity> getListByTeacher(String teacher) {
        isActiveSession();
        List<ChecksDataEntity> ls = new ArrayList<>(getSession()
                .createCriteria(ChecksDataEntity.class)
                .add(Restrictions.ilike("teachers", teacher+",")).list());
        getSession().getTransaction().commit();
        return ls;
    }

    @Override
    public List<ChecksDataEntity> getListByMonth(String month) {
        isActiveSession();
        List<ChecksDataEntity> ls = new ArrayList<>(getSession()
                .createCriteria(ChecksDataEntity.class).add(Restrictions.ilike("payedMonth", month)).list());
        getSession().getTransaction().commit();
        return ls;
    }
}
