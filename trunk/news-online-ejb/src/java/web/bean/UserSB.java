/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import web.entity.Category;
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
        return (Users) q.getResultList().get(0);
    }

    public List<Users> getUsers(String role, boolean status) {
        if (role.equals("User")) {
            Query q = em.createQuery("SELECT u FROM Users u WHERE u.status = :status and u.roles = 'User'");
            q.setParameter("status", status);
            return q.getResultList();
        } else if (role.equals("Employee")) {
            Query q = em.createQuery("SELECT u FROM Users u WHERE u.status = :status and u.roles = 'Employee'");
            q.setParameter("status", status);
            return q.getResultList();
        } else {
            Query q = em.createQuery("SELECT u FROM Users u WHERE u.status = :status and u.roles <> 'Admin'");
            q.setParameter("status", status);
            return q.getResultList();
        }
    }

    public boolean insert(String username, String password, String image, String fullname, String birthday, boolean gender, String address, String phone, String email, String question, String answer, String roles, int categoryid, boolean activesms, boolean status) {
        boolean flag = false;
        try {
            Users u = new Users();
            u.setPassword(password);
            u.setUsername(username);
            u.setActiveSMS(activesms);
            u.setAddress(address);
            u.setBirthday(birthday);
            u.setCategory(getCategoryById(categoryid));
            u.setEmail(email);
            u.setFullName(fullname);
            u.setGender(gender);
            u.setImage(image);
            u.setPhone(phone);
            u.setRoles(roles);
            u.setStatus(status);
            u.setQuestion(question);
            u.setAnswer(answer);
            em.persist(u);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean update(int userid, String image, String fullname, String birthday, boolean gender, String address, String phone, String email,String question,String answer, int categoryid, boolean activesms) {
        boolean flag = false;
        try {
            Users u = new Users();
            u.setUserId(userid);
            u.setUsername(getUser(userid).getUsername());
            u.setPassword(getUser(userid).getPassword());
            u.setActiveSMS(activesms);
            u.setLastLogin(getUser(userid).getLastLogin());
            u.setStatus(getUser(userid).getStatus());
            u.setAddress(address);
            u.setBirthday(birthday);
            u.setCategory(getCategoryById(categoryid));
            u.setEmail(email);
            u.setFullName(fullname);
            u.setGender(gender);
            u.setImage(image);
            u.setPhone(phone);
            u.setRoles(getUser(userid).getRoles());
            u.setQuestion(question);
            u.setAnswer(answer);
            em.merge(u);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public Users getUser(int userId) {
        Users u = new Users();
        try {
            Query q = em.createQuery("SELECT u FROM Users u WHERE u.userId = :userId");
            q.setParameter("userId", userId);
            u = (Users) q.getResultList().get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }

    public boolean delete(int userId) {
        boolean flag = false;
        try {
            em.remove(em.merge(getUser(userId)));
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public Users login(String username, String password) {
        Users u = new Users();
        try {
            Query q = em.createQuery("SELECT u FROM Users u WHERE u.username = :username and u.password = :password");
            q.setParameter("username", username);
            q.setParameter("password", password);
            if (!q.getResultList().isEmpty()) {
                u = (Users) q.getSingleResult();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }

    public boolean changePassword(int userId, String newpassword) {
        boolean flag = false;
        try {
            Query q = em.createQuery("update Users set Password = :password where UserId = :userId");
            q.setParameter("password", newpassword);
            q.setParameter("userId", userId);
            q.executeUpdate();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public String getLastLogin(int userId) {
        if (getUser(userId).getLastLogin() == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String current = sdf.format(new Date());
        String last = sdf.format(getUser(userId).getLastLogin());
        String t1 = last.split(" ")[1];
        String t2 = current.split(" ")[1];
        if (last.split(" ")[0].equals(current.split(" ")[0])) {
            if (t1.split(":")[0].equals(t2.split(":")[0])) {
                if (t1.split(":")[1].equals(t2.split(":")[1])) {
                    if (t1.split(":")[2].equals(t2.split(":")[2])) {
                        return "0 Second ago";
                    } else {
                        return String.valueOf(Integer.parseInt(t2.split(":")[2]) - Integer.parseInt(t1.split(":")[2])) + " Second(s) ago";
                    }
                } else {
                    return String.valueOf(Integer.parseInt(t2.split(":")[1]) - Integer.parseInt(t1.split(":")[1])) + " Minute(s) ago";
                }
            } else if ((Integer.parseInt(t2.split(":")[0]) - Integer.parseInt(t1.split(":")[0])) == 1) {
                if (Integer.parseInt(t2.split(":")[1]) > Integer.parseInt(t1.split(":")[1])) {
                    return "1 Hour " + String.valueOf((Integer.parseInt(t2.split(":")[1]) - Integer.parseInt(t1.split(":")[1]))) + " Minute(s) ago";
                } else if (Integer.parseInt(t2.split(":")[1]) == Integer.parseInt(t1.split(":")[1])) {
                    return "1 Hour ago";
                } else {
                    return String.valueOf((60 + Integer.parseInt(t2.split(":")[1]) - Integer.parseInt(t1.split(":")[1]))) + " Minute(s) ago";
                }
            } else {
                if (Integer.parseInt(t2.split(":")[1]) > Integer.parseInt(t1.split(":")[1])) {
                    return String.valueOf(Integer.parseInt(t2.split(":")[0]) - Integer.parseInt(t1.split(":")[0])) + " Hours " + String.valueOf(Integer.parseInt(t2.split(":")[1]) - Integer.parseInt(t1.split(":")[1])) + " Minute(s) ago";
                } else if (Integer.parseInt(t2.split(":")[1]) < Integer.parseInt(t1.split(":")[1])) {
                    if ((Integer.parseInt(t2.split(":")[0]) - Integer.parseInt(t1.split(":")[0])) < 0) {
                        return String.valueOf(Integer.parseInt(t2.split(":")[0]) - Integer.parseInt(t1.split(":")[0]) + 11) + " Hours " + String.valueOf(60 + Integer.parseInt(t2.split(":")[1]) - Integer.parseInt(t1.split(":")[1])) + " Minute(s) ago";
                    } else {
                        return String.valueOf(Integer.parseInt(t2.split(":")[0]) - Integer.parseInt(t1.split(":")[0]) - 1) + " Hours " + String.valueOf(60 + Integer.parseInt(t2.split(":")[1]) - Integer.parseInt(t1.split(":")[1])) + " Minute(s) ago";
                    }
                } else {
                    return String.valueOf(Integer.parseInt(t2.split(":")[0]) - Integer.parseInt(t1.split(":")[0])) + " Hours ago";
                }
            }

        } else {
            return last.split(" ")[0].toString();
        }
    }

    public void Block(int userId, boolean flag) {
        try {
            Users user = getUser(userId);
            user.setStatus(flag);
            em.merge(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setLastLogin(String username) {
        try {
            Users u = getUserByName(username);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date d = new Date();
            u.setLastLogin(sdf.parse(sdf.format(d)));
            em.merge(u);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Category getCategoryById(int id) {
        return (Category) em.createQuery("SELECT c FROM Category c WHERE c.categoryId = :categoryId").setParameter("categoryId", id).getResultList().get(0);
    }

    public List<Category> getCategories() {
        List<Category> all = em.createNamedQuery("Category.findAll").getResultList();
        for(int i =0;i<all.size();i++)
        {
            if(!getSubCategories(all.get(i).getCategoryId().toString()).isEmpty())
            {
                all.remove(i);
            }
        }
        return all;
    }


    public List<Category> getSubCategories(String parentCategoryId) {
        Query q = em.createNamedQuery("Category.findByParent");
        q.setParameter("parent", parentCategoryId);
        return q.getResultList();
    }

    public Users getUserByName(String username) {
        return (Users) em.createNamedQuery("Users.findByUsername").setParameter("username", username).getResultList().get(0);
    }

    public boolean checkusername(String username) {
        boolean flag=false;
        try {
            List name = new ArrayList();
            name = em.createNamedQuery("Users.findByUsername").setParameter("username",username).getResultList();
            if(!name.isEmpty())
            {
                flag=true;
            }
        } catch (Exception e) {
        }
        return flag;
    }

    public boolean checkemail(String email) {
        boolean flag=false;
        try {
            List name = new ArrayList();
            name = em.createNamedQuery("Users.findByEmail").setParameter("email",email).getResultList();
            if(!name.isEmpty())
            {
                flag=true;
            }
        } catch (Exception e) {
        }
        return flag;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
