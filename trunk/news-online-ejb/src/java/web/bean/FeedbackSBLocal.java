/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package web.bean;

import java.util.List;
import javax.ejb.Local;
import web.entity.Feedback;

/**
 *
 * @author Khatmau_sr
 */
@Local
public interface FeedbackSBLocal {

    List<Feedback> getFeedbacksByStatus(boolean status);

    void insert(Feedback obj);

    void update(Feedback obj);

    void delete(Feedback obj);

    Feedback getFeedbackById(int id);
    
}
