package com.inventor.dao.impls;

import com.inventor.dao.interfaces.teachers;
import com.inventor.entities.TeachersEntity;
import com.inventor.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.criteria.internal.expression.function.AggregationFunction;

import java.util.ArrayList;
import java.util.List;

public class teacherDAOImpls extends abstractUA<TeachersEntity> implements teachers {

    private static teacherDAOImpls tDAOImpl;
    private SessionFactory sessionFactory = null;

    public teacherDAOImpls() {
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

    public static teacherDAOImpls getInstance() {
        if (tDAOImpl == null) {
            tDAOImpl = new teacherDAOImpls();
        }
        return tDAOImpl;
    }

    @Override
    public List<TeachersEntity> getAll() {
        isActiveSession();
        List<TeachersEntity> list = new ArrayList<>(getSession().createCriteria(TeachersEntity.class).list());
        getSession().getTransaction().commit();
        return list;
    }

    @Override
    public TeachersEntity get(long id) {
        isActiveSession();
        TeachersEntity obj = getSession().get(TeachersEntity.class, (int) id);
        getSession().getTransaction().commit();
        return obj;
    }

    @Override
    public boolean remove(long obj) {
        isActiveSession();
        TeachersEntity var = getSession().load(TeachersEntity.class, (int) obj);
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
                .createCriteria(TeachersEntity.class)
                .setProjection(Projections
                        .property( "name"))
                .list());
        getSession().getTransaction().commit();
        return list;
    }

    @Override
    public int getId(String name) {
        isActiveSession();
        int id = (int) getSession().createCriteria(TeachersEntity.class)
                .add(Restrictions.eq("name", name)).uniqueResult();
        getSession().getTransaction().commit();
        return id;
    }


    @Override
    public List<TeachersEntity> getTeachersList() {
        return null;
    }

    @Override
    public long getTeachersCountOnSubject(int subjetId) {
        String id = String.valueOf(subjetId);
        isActiveSession();
        long count  = (long) getSession()
                .createCriteria(TeachersEntity.class)
                .add(Restrictions
                        .like("subjectId", id, MatchMode.ANYWHERE))
                .setProjection(Projections
                        .rowCount()).uniqueResult();
        return count;
    }
}
