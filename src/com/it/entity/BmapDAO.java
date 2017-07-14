package com.it.entity;

import java.util.ArrayList;
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
 * A data access object (DAO) providing persistence and search support for Bmap
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.it.entity.Bmap
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class BmapDAO {
	private static final Logger log = LoggerFactory.getLogger(BmapDAO.class);
	// property constants
	public static final String LONGITUDE = "longitude";
	public static final String LATITUDE = "latitude";
	public static final String PMPHOTO = "pmphoto";
	public static final String PTITLE = "ptitle";
	public static final String PDESCRIBE = "pdescribe";
	public static final String YH = "yh";
	public static final String PTIME = "ptime";

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

	public void save(Bmap transientInstance) {
		log.debug("saving Bmap instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Bmap persistentInstance) {
		log.debug("deleting Bmap instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Bmap findById(java.lang.Integer id) {
		log.debug("getting Bmap instance with id: " + id);
		try {
			Bmap instance = (Bmap) getCurrentSession().get("com.it.entity.Bmap", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Bmap instance) {
		log.debug("finding Bmap instance by example");
		try {
			List results = getCurrentSession().createCriteria("com.it.entity.Bmap").add(Example.create(instance))
					.list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Bmap instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Bmap as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List findExtendMap(){
		Bmap bmap;
		List<Bmap>mapList=findAll();
		Bmap[] maps=new Bmap[mapList.size()];
		Bmap[]list=(Bmap[]) mapList.toArray(maps);
		ArrayList<ExtendMap> listExtend=new ArrayList<ExtendMap>();
		for(int n=0;n<list.length;n++){
			bmap=list[n];
			ExtendMap extMap=new ExtendMap();
			extMap.setImage(bmap.getPmphoto());
			extMap.setTitle(bmap.getPtitle());
			extMap.setSubtitle(bmap.getPdescribe());
			extMap.setLatitude(bmap.getLatitude());
			extMap.setLongitude(bmap.getLongitude());
			listExtend.add(extMap);
		}
		return listExtend;
	}
	public List findByLongitude(Object longitude) {
		return findByProperty(LONGITUDE, longitude);
	}

	public List findByLatitude(Object latitude) {
		return findByProperty(LATITUDE, latitude);
	}

	public List findByPmphoto(Object pmphoto) {
		return findByProperty(PMPHOTO, pmphoto);
	}

	public List findByPtitle(Object ptitle) {
		return findByProperty(PTITLE, ptitle);
	}

	public List findByPdescribe(Object pdescribe) {
		return findByProperty(PDESCRIBE, pdescribe);
	}

	public List findByYh(Object yh) {
		return findByProperty(YH, yh);
	}

	public List findByPtime(Object ptime) {
		return findByProperty(PTIME, ptime);
	}

	public List findAll() {
		log.debug("finding all Bmap instances");
		try {
			String queryString = "from Bmap";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Bmap merge(Bmap detachedInstance) {
		log.debug("merging Bmap instance");
		try {
			Bmap result = (Bmap) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Bmap instance) {
		log.debug("attaching dirty Bmap instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Bmap instance) {
		log.debug("attaching clean Bmap instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BmapDAO getFromApplicationContext(ApplicationContext ctx) {
		return (BmapDAO) ctx.getBean("BmapDAO");
	}
}