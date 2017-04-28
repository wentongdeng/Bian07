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
 * A data access object (DAO) providing persistence and search support for Map
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.it.entity.Map
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class MapDAO {
	private static final Logger log = LoggerFactory.getLogger(MapDAO.class);
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

	public void save(Map transientInstance) {
		log.debug("saving Map instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Map persistentInstance) {
		log.debug("deleting Map instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Map findById(java.lang.Integer id) {
		log.debug("getting Map instance with id: " + id);
		try {
			Map instance = (Map) getCurrentSession().get("com.it.entity.Map", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Map instance) {
		log.debug("finding Map instance by example");
		try {
			List results = getCurrentSession().createCriteria("com.it.entity.Map").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Map instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Map as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
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
		log.debug("finding all Map instances");
		try {
			String queryString = "from Map";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findExtendMap(){
		@SuppressWarnings("unchecked")
		Map map;
		List<Map>mapList=findAll();
		Map[] maps=new Map[mapList.size()];
		Map[]list=(Map[]) mapList.toArray(maps);
		ArrayList<ExtendMap> listExtend=new ArrayList<ExtendMap>();
		for(int n=0;n<list.length;n++){
			map=list[n];
			ExtendMap extMap=new ExtendMap();
			extMap.setImage(map.getPmphoto());
			extMap.setTitle(map.getPtitle());
			extMap.setSubtitle(map.getPdescribe());
			extMap.setLatitude(map.getLatitude());
			extMap.setLongitude(map.getLongitude());
			listExtend.add(extMap);
		}
		return listExtend;
	}
	
	public Map merge(Map detachedInstance) {
		log.debug("merging Map instance");
		try {
			Map result = (Map) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Map instance) {
		log.debug("attaching dirty Map instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Map instance) {
		log.debug("attaching clean Map instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MapDAO getFromApplicationContext(ApplicationContext ctx) {
		return (MapDAO) ctx.getBean("MapDAO");
	}
}