/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package web.bean;

import java.util.List;
import javax.ejb.Local;
import web.entity.Category;
import web.entity.Users;

/**
 *
 * @author Khatmau_sr
 */
@Local
public interface UserSBLocal {

    Users getUserById(int userId);
     boolean insert(String username, String password, String image, String fullname, String birthday, boolean gender, String address, String phone, String email, String question, String answer, String roles, int categoryid, boolean activesms, boolean status);

    boolean update(int userid, String image, String fullname, String birthday, boolean gender, String address, String phone, String email,String question,String answer, int categoryid, boolean activesms);

    Users getUser(int userId);

    boolean delete(int userId);

    Users login(String username, String password);

    boolean changePassword(int userId, String newpassword);

    String getLastLogin(int userId);

    void Block(int userId, boolean flag);

    void setLastLogin(String username);

    Category getCategoryById(int id);

    List<Category> getCategories();
    List<Category> getSubCategories(String parentCategoryId);

    Users getUserByName(String username);

    boolean checkusername(String username);

    boolean checkemail(String email);
    public List<Users> getUsers(String role, boolean status);
}
