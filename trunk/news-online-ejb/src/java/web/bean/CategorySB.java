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
	Query q = em.createQuery("SELECT c FROM Category c WHERE c.parent = 'None' AND c.status = :status ORDER BY c.title");
	q.setParameter("status", Boolean.TRUE);
	return q.getResultList();
    }

    public List<Category> getSubCategories(String parentCategoryId) {
	Query q = em.createQuery("SELECT c FROM Category c WHERE c.parent = :parent AND c.status = :status ORDER BY c.title");
	q.setParameter("parent", parentCategoryId);
	q.setParameter("status", Boolean.TRUE);
	return q.getResultList();
    }

    public Category getCategoryById(int id) {
	Query q = em.createNamedQuery("Category.findByCategoryId");
	q.setParameter("categoryId", id);
	return (Category)q.getResultList().get(0);
    }

    public List<Category> getCategoryByStatus(boolean status) {
	Query q = em.createQuery("SELECT c FROM Category c WHERE c.status = :status ORDER BY c.title");
	q.setParameter("status", status);
	return q.getResultList();
    }

    public boolean checkInsert(String title) {
	Query q = em.createNamedQuery("Category.findByTitle");
	q.setParameter("title", title);
	return q.getResultList().isEmpty();
    }

    public boolean checkUpdate(int cateId, String title) {
	Query q = em.createQuery("SELECT c FROM Category c WHERE c.categoryId = :categoryId AND c.title <> :title");
	q.setParameter("categoryId", cateId);
	q.setParameter("title", title);
	return q.getResultList().isEmpty();
    }

    public void insert(Category cate) {
	em.persist(cate);
    }

    public void update(Category cate) {
	em.merge(cate);
    }

    public void delete(Category cate) {
	em.remove(em.merge(cate));
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

}
