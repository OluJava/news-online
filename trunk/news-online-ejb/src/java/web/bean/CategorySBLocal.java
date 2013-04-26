/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package web.bean;

import java.util.List;
import javax.ejb.Local;
import web.entity.Category;

/**
 *
 * @author Khatmau_sr
 */
@Local
public interface CategorySBLocal {

    List<Category> getParentCategories();

    List<Category> getSubCategories(String parentCategoryId);

    Category getCategoryById(int id);

    List<Category> getCategoryByStatus(boolean status);

    boolean checkInsert(String title);

    boolean checkUpdate(int cateId, String title);

    void insert(Category cate);

    void update(Category cate);

    void delete(Category cate);

}
