package com.it.entity;

import java.util.List;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

/**
 * A data access object (DAO) providing persistence and search support for
 * Msphoto entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.it.entity.Msphoto
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class MsphotoDAO {
	private static final Logger log = LoggerFactory.getLogger(MsphotoDAO.class);
	// property constants
	public static final String MPADDRESS = "mpaddress";
	public static final String DID = "did";

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	protected void initDao() {
		// do nothing
	}

	public void save(Msphoto transientInstance) {
		log.debug("saving Msphoto instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Msphoto persistentInstance) {
		log.debug("deleting Msphoto instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Msphoto findById(java.lang.Integer id) {
		log.debug("getting Msphoto instance with id: " + id);
		try {
			Msphoto instance = (Msphoto) getCurrentSession().get("com.it.entity.Msphoto", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Msphoto instance) {
		log.debug("finding Msphoto instance by example");
		try {
			List results = getCurrentSession().createCriteria("com.it.entity.Msphoto").add(Example.create(instance))
					.list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Msphoto instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Msphoto as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByMpaddress(Object mpaddress) {
		return findByProperty(MPADDRESS, mpaddress);
	}

	public List findByDid(Object did) {
		return findByProperty(DID, did);
	}

	public List findAll() {
		log.debug("finding all Msphoto instances");
		try {
			String queryString = "from Msphoto";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Msphoto merge(Msphoto detachedInstance) {
		log.debug("merging Msphoto instance");
		try {
			Msphoto result = (Msphoto) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Msphoto instance) {
		log.debug("attaching dirty Msphoto instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Msphoto instance) {
		log.debug("attaching clean Msphoto instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MsphotoDAO getFromApplicationContext(ApplicationContext ctx) {
		return (MsphotoDAO) ctx.getBean("MsphotoDAO");
	}
}