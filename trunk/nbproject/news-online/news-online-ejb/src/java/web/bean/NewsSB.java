/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package web.bean;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

    public List<News> getNewsByStatus(boolean status) {
	Query q = em.createNamedQuery("News.findByStatus");
	q.setParameter("status", status);
	return q.getResultList();
    }

    public List<News> getNewsToDay() {
	return em.createQuery("SELECT n FROM News n WHERE CAST(n.postedDate AS DATE) = CAST(GETDATE() AS DATE)").getResultList();
    }

    public void insert(News news) {
	em.persist(news);
    }

    public void update(News news) {
	em.merge(news);
    }

    public void delete(News news) {
	em.remove(em.merge(news));
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
 
}
