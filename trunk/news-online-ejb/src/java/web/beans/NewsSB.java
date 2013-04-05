/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package web.beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import web.Entity.News;

/**
 *
 * @author Nguyen Duy
 */
@Stateless
public class NewsSB implements NewsSBLocal {
    @PersistenceContext(unitName = "news-online-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    public List<News> getNewsByStatus(boolean status) {
        Query q = em.createQuery("SELECT n FROM News n WHERE n.status = :status");
        q.setParameter("status", status);
        return q.getResultList();
    }

    public List<News> getNewsByTitle(String title) {
        Query q = em.createQuery("SELECT n FROM News n WHERE n.title = :title");
        q.setParameter("title", "%"+title+"%");
        return q.getResultList();
    }

    public List<News> getNewsByAuthor(String author) {
        Query q = em.createQuery("SELECT n FROM News n WHERE n.author = :author");
        q.setParameter("author", "%"+author+"%");
        return q.getResultList();
    }

    public List<News> getNewsByTag(String tag) {
        List<News> list = new ArrayList<News>();// List that will be return
        List<News> listAll = em.createNamedQuery("News.findAll").getResultList();// Get all the news
        Set<String> tagsSearch = new HashSet<String>(Arrays.asList(tag.split(";")));
        for(News news : listAll)
        {
            Set<String> tagsOfNews = new HashSet<String>(Arrays.asList(news.getTags().split(";")));
            if(tagsOfNews.retainAll(tagsSearch))
            {
                list.add(news);
            }
        }
        return list;
    }

    public void insert(News n) {
        em.persist(n);
    }

    public void update(News n) {
        em.merge(n);
    }

    public void delete(News n) {
        em.remove(em.merge(n));
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
 
}
