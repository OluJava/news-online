/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package web.bean;

import javax.ejb.Local;
import web.entity.Users;

/**
 *
 * @author Khatmau_sr
 */
@Local
public interface UserSBLocal {

    Users getUserById(int userId);

}
