/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package web.beans;

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

    List<News> getNewsByTitle(String title);

    List<News> getNewsByAuthor(String author);

    List<News> getNewsByTag(String tag);

    void insert(News n);

    void update(News n);

    void delete(News n);

}
