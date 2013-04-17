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
	Query q = em.createQuery("SELECT n FROM News n WHERE n.postedDate = :postedDate");
	q.setParameter("postedDate", getDate());
	return q.getResultList();
    }

    public News getNewsById(int newsId) {
	Query q = em.createNamedQuery("News.findByNewsId");
	q.setParameter("newsId", newsId);
	return (News)q.getResultList().get(0);
    }

    private long getDate(){
	Calendar cal = Calendar.getInstance(); // locale-specific
	Date date = new Date();
	cal.setTime(date);
	cal.set(Calendar.HOUR_OF_DAY, 0);
	cal.set(Calendar.MINUTE, 0);
	cal.set(Calendar.SECOND, 0);
	cal.set(Calendar.MILLISECOND, 0);
	return cal.getTimeInMillis();
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
 
}
