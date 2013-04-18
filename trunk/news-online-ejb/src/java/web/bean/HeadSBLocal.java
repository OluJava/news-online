/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package web.bean;

import java.util.List;
import javax.ejb.Local;
import web.entity.HeadNews;

/**
 *
 * @author Khatmau_sr
 */
@Local
public interface HeadSBLocal {

    List<HeadNews> getHeads();
    
}
