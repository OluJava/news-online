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
import web.entity.Category;

/**
 *
 * @author Khatmau_sr
 */
@Stateless
public class CategorySB implements CategorySBLocal {
    @PersistenceContext(unitName = "news-online-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
	em.persist(object);
    }

    public List<Category> getParentCategories() {
	return em.createQuery("SELECT c FROM Category c WHERE c.parent = 'None'").getResultList();
    }

    public List<Category> getSubCategories(String parentCategoryId) {
	Query q = em.createNamedQuery("Category.findByParent");
	q.setParameter("parent", parentCategoryId);
	return q.getResultList();
    }

    public Category getCategoryById(int id) {
	Query q = em.createNamedQuery("Category.findByCategoryId");
	q.setParameter("categoryId", id);
	return (Category)q.getResultList().get(0);
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
 
}
