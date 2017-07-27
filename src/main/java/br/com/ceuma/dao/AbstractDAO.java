package br.com.ceuma.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class AbstractDAO<T, PK extends Serializable> {

    @Autowired
    private SessionFactory sessionFactory;
    private final Class<T> clazz;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }


    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    protected T save(T entity) throws Exception {
        try {
            getSession().persist(entity);
            return entity;
        } catch (Throwable t) {
            throw new Exception(t.getMessage());
        }
    }

    protected T update(T entity) throws Exception {
        try {
            getSession().update(entity);
        } catch (Throwable t) {
            throw new Exception(t.getMessage());
        }
        return entity;
    }

    protected void delete(T entity) throws Exception {
        try {
            getSession().delete(entity);
        } catch (Throwable t) {
            throw new Exception(t.getMessage());
        }
    }

    protected Criteria createEntityCriteria() throws Exception {
        return getSession().createCriteria(clazz);
    }

    @SuppressWarnings("unchecked")
    protected List<T> findAll() throws Exception {
        return getSession().createCriteria(clazz).list();
    }

    @SuppressWarnings("unchecked")
    protected T findById(PK id) throws Exception {
        return (T) getSession().createCriteria(clazz)
                .add(Restrictions.eq("id", id)).uniqueResult();
    }


}
