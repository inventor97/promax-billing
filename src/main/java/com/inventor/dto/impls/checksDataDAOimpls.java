package com.inventor.dto.impls;

import com.inventor.dto.interfaces.checks;
import com.inventor.entities.ChecksDataEntity;
import com.inventor.utils.HibernateUtil;
import com.inventor.utils.dateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        isActiveSession();
        Set<String> list = new HashSet<>(getSession()
                .createCriteria(ChecksDataEntity.class)
                .setProjection(Projections
                        .property( "name"))
                .list());
        getSession().getTransaction().commit();
        return new ArrayList<>(list);
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
    public List<ChecksDataEntity> getListBy(String month, String sub, String teach, Date date, boolean cash, boolean card, boolean byMonth) {
        isActiveSession();
        Criteria criteria = getSession().createCriteria(ChecksDataEntity.class);
        if (!month.equals("Barchasi")) {
            criteria.add(Restrictions.ilike("payedMonth", month + ",", MatchMode.ANYWHERE));
        }

        if (!sub.equals("Barchasi")) {
            criteria.add(Restrictions.ilike("subjects", sub + ",", MatchMode.ANYWHERE));
        }
        if (!teach.equals("Barchasi")) {
            criteria.add(Restrictions.ilike("teachers", teach + ",", MatchMode.ANYWHERE));
        }
        if (cash) {
            criteria.add(Restrictions.eq("paymentType", true));
        }
        if (card) {
            criteria.add(Restrictions.eq("paymentType", false));
        }
        if (!byMonth) {
            criteria.add(Restrictions.eq("dateCrated", date));
        } else {
            criteria.add(Restrictions.between("dateCrated",dateUtils.getFirstDayMonth(date), dateUtils.getLastDayMonth(date)));
        }
        List<ChecksDataEntity> ls = new ArrayList<>(criteria.list());
        getSession().getTransaction().commit();
        return ls;
    }

    @Override
    public List<ChecksDataEntity> getByName(String name) {
        isActiveSession();
        List<ChecksDataEntity> ls = new ArrayList<>(getSession()
                .createCriteria(ChecksDataEntity.class)
                .add(Restrictions.eq("name", name)).list());
        getSession().getTransaction().commit();
        return ls;
    }
}
