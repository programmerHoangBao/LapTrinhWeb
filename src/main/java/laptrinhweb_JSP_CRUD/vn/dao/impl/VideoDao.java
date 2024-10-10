package laptrinhweb_JSP_CRUD.vn.dao.impl;

import java.util.List;

import laptrinhweb_JSP_CRUD.vn.configs.JPAConfig;
import laptrinhweb_JSP_CRUD.vn.dao.IVideoDao;
import laptrinhweb_JSP_CRUD.vn.entity.Video;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class VideoDao implements IVideoDao{

	@Override
	public int count() {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "SELECT count(c) FROM Video c";
		Query query = enma.createQuery(jpql);
		return ((Long) query.getSingleResult()).intValue();
	}

	@Override
	public List<Video> findAll(int page, int pagesize) {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Video> query = enma.createNamedQuery("Video.findAll", Video.class);
		query.setFirstResult(page * pagesize);
		query.setMaxResults(pagesize);
		return query.getResultList();
	}

	@Override
	public List<Video> findByVideoTitle(String videoTitle) {
		EntityManager enma = JPAConfig.getEntityManager();
		String jpql = "SELECT c FROM Video c WHERE c.title like :catname";
		TypedQuery<Video> query = enma.createQuery(jpql, Video.class);
		query.setParameter("catename", "%" + videoTitle + "%");
		return query.getResultList();
	}

	@Override
	public List<Video> findAll() {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Video> query = enma.createNamedQuery("Video.findAll", Video.class);
		return query.getResultList();
	}

	@Override
	public Video findById(String videoid) {
		EntityManager enma = JPAConfig.getEntityManager();
		Video video = enma.find(Video.class, videoid);
		return video;
	}

	@Override
	public void delete(String videoid) throws Exception {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			// enma.remove(category);
			Video video = enma.find(Video.class, videoid);
			if (video != null) {
				enma.remove(video);
			} else {
				throw new Exception("Không tìm thấy");
			}
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public void update(Video video) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.merge(video);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public void insert(Video video) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(video);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public List<Video> findByIdCategory(int cateid) {
		EntityManager enma = JPAConfig.getEntityManager();
        String jpql = "SELECT v FROM Video v WHERE v.category.categoryid = :cateid";
        TypedQuery<Video> query= enma.createQuery(jpql, Video.class);
        query.setParameter("cateid", cateid);
        return query.getResultList();
	}

}
