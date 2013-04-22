/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package web.bean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import web.entity.Users;

/**
 *
 * @author Khatmau_sr
 */
@Stateless
public class UserSB implements UserSBLocal {
    @PersistenceContext(unitName = "news-online-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
	em.persist(object);
    }

    public Users getUserById(int userId) {
	Query q = em.createNamedQuery("Users.findByUserId");
	q.setParameter("userId", userId);
	return (Users)q.getResultList().get(0);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

}
