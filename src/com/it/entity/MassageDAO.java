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
 * A data access object (DAO) providing persistence and search support for
 * Massage entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.it.entity.Massage
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class MassageDAO {
	private static final Logger log = LoggerFactory.getLogger(MassageDAO.class);
	// property constants
	public static final String MTEXT = "mtext";
	public static final String MTITLE = "mtitle";
	public static final String MLIKE = "mlike";
	public static final String MTIME = "mtime";
	public static final String YH = "yh";
	public static final String LONGITUDE = "longitude";
	public static final String LATITUDE = "latitude";
	public static final String MCOMMENT = "mcomment";

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

	public void save(Massage transientInstance) {
		log.debug("saving Massage instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Massage persistentInstance) {
		log.debug("deleting Massage instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Massage findById(java.lang.Integer id) {
		log.debug("getting Massage instance with id: " + id);
		try {
			Massage instance = (Massage) getCurrentSession().get("com.it.entity.Massage", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Massage instance) {
		log.debug("finding Massage instance by example");
		try {
			List results = getCurrentSession().createCriteria("com.it.entity.Massage").add(Example.create(instance))
					.list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Massage instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Massage as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByMtext(Object mtext) {
		return findByProperty(MTEXT, mtext);
	}

	public List findByMtitle(Object mtitle) {
		return findByProperty(MTITLE, mtitle);
	}

	public List findByMlike(Object mlike) {
		return findByProperty(MLIKE, mlike);
	}

	public List findByMtime(Object mtime) {
		return findByProperty(MTIME, mtime);
	}

	public List findByYh(Object yh) {
		return findByProperty(YH, yh);
	}

	public List findByLongitude(Object longitude) {
		return findByProperty(LONGITUDE, longitude);
	}

	public List findByLatitude(Object latitude) {
		return findByProperty(LATITUDE, latitude);
	}

	public List findByMcomment(Object mcomment) {
		return findByProperty(MCOMMENT, mcomment);
	}

	public List findAll() {
		log.debug("finding all Massage instances");
		try {
			String queryString = "from Massage";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public List findExtendMassage(){
		log.debug("finding all Massage instances");
		try {
			String queryString = "from Massage";
			Query queryObject = getCurrentSession().createQuery(queryString);
			List massageList=queryObject.list();
			Massage[] massages=new Massage[massageList.size()];
			@SuppressWarnings("unchecked")
			Massage[]list=(Massage[]) massageList.toArray(massages);
			return creatExtendMassage(list);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	@SuppressWarnings("unchecked")
	public List creatExtendMassage(Massage[]list){
		Massage massage;
		User user;
		ArrayList<Msphoto> msList;
		ArrayList<ExtendMassage> listExtend=new ArrayList<ExtendMassage>();
		for(int n=0;n<list.length;n++){
			massage=list[n];
			ExtendMassage extendMassage=changeMassage(massage);
			String queryString1="from User u where u.id="+massage.getYh();
			Query queryObject1 = getCurrentSession().createQuery(queryString1);
			user=(User) queryObject1.list().get(0);
			String queryString2="from Msphoto m where m.did="+massage.getId();
			Query queryObject2 = getCurrentSession().createQuery(queryString2);
			msList=(ArrayList<Msphoto>) queryObject2.list();
			if(user!=null){
				ArrayList<String> photos1=new ArrayList<String>();
				ArrayList<String> photos2=new ArrayList<String>();
				extendMassage.setIcon(user.getUpicture());
				extendMassage.setName(user.getUname());
				
				for(int t=0;t<msList.size();t++){
					photos1.add(msList.get(t).getMpaddress());
					photos2.add(msList.get(t).getMpaddress());
				}
				ArrayList<ArrayList<String>> photos=new ArrayList<ArrayList<String>>();
				photos.add(photos1);
				photos.add(photos2);
				extendMassage.setPhotos(photos);
				listExtend.add(extendMassage);
			}
		}
		return listExtend;
	}
	public ExtendMassage changeMassage(Massage massage){
		ExtendMassage extendMassage=new ExtendMassage();
//		extendMassage.setTitle(massage.getTitle());
		extendMassage.setTime(massage.getMtime());
		extendMassage.setText(massage.getMtext());
		extendMassage.setLike_count(massage.getMlike());
		extendMassage.setComment_count(massage.getMcomment());
		return extendMassage;
	}

	public Massage merge(Massage detachedInstance) {
		log.debug("merging Massage instance");
		try {
			Massage result = (Massage) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Massage instance) {
		log.debug("attaching dirty Massage instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Massage instance) {
		log.debug("attaching clean Massage instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MassageDAO getFromApplicationContext(ApplicationContext ctx) {
		return (MassageDAO) ctx.getBean("MassageDAO");
	}
}