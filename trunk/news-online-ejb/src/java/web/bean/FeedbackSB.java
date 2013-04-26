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
import web.entity.Feedback;

/**
 *
 * @author Khatmau_sr
 */
@Stateless
public class FeedbackSB implements FeedbackSBLocal {
    @PersistenceContext(unitName = "news-online-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
	em.persist(object);
    }

    public List<Feedback> getFeedbacksByStatus(boolean status) {
	Query q = em.createQuery("SELECT f FROM Feedback f WHERE f.status = :status ORDER BY f.postedTime DESC");
	q.setParameter("status", status);
	return q.getResultList();
    }

    public Feedback getFeedbackById(int id) {
	Query q = em.createNamedQuery("Feedback.findByFeedbackId");
	q.setParameter("feedbackId", id);
	return (Feedback)q.getResultList().get(0);
    }

    public void insert(Feedback obj) {
	em.persist(obj);
    }

    public void update(Feedback obj) {
	em.merge(obj);
    }

    public void delete(Feedback obj) {
	em.remove(em.merge(obj));
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
 
}
