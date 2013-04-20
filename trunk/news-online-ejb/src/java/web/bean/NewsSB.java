/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package web.bean;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import web.entity.News;

/**
 *
 * @author Khatmau_sr
 */
@Stateless
public class NewsSB implements NewsSBLocal {
    @PersistenceContext(unitName = "news-online-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
	em.persist(object);
    }

    public List<News> getHeadLines() {
	Query q = em.createQuery("SELECT n FROM News n WHERE n.postedDate = :postedDate AND n.status = :status");
	q.setParameter("postedDate", Calendar.getInstance(), TemporalType.DATE);
	q.setParameter("status", true);
	return q.getResultList();
    }

    public News getNewsById(int newsId) {
	Query q = em.createNamedQuery("News.findByNewsId");
	q.setParameter("newsId", newsId);
	return (News)q.getResultList().get(0);
    }

    public List<News> getPopularNews() {
	Query q = em.createQuery("SELECT n FROM News n WHERE n.postedDate > :postedDate AND n.status = :status ORDER BY n.viewed DESC");
	Calendar c = Calendar.getInstance();
	c.add(Calendar.DATE, -10);
	q.setParameter("postedDate", c, TemporalType.DATE);
	q.setParameter("status", true);
	return q.getResultList();
    }

    public List<News> getRecentNews() {
	Query q = em.createQuery("SELECT n FROM News n WHERE n.status = :status ORDER BY n.postedDate DESC");
	q.setParameter("status", true);
	return q.getResultList();
    }

    public List<News> getNewsByTitle(String title) {
	Query q = em.createQuery("SELECT n FROM News n WHERE n.status = :status AND n.title LIKE :title ORDER BY n.postedDate DESC");
	q.setParameter("status", true);
	q.setParameter("title", "%" + title + "%");
	return q.getResultList();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
 
}