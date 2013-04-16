/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package web.bean;

import java.util.List;
import javax.ejb.Local;
import web.entity.News;

/**
 *
 * @author Khatmau_sr
 */
@Local
public interface NewsSBLocal {

    List<News> getNewsByStatus(boolean status);

    void insert(News news);

    void update(News news);

    void delete(News news);

    List<News> getNewsToDay();
    
}
