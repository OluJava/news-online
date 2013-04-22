/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package web.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Khatmau_sr
 */
@Entity
@Table(name = "Feedback")
@NamedQueries({
    @NamedQuery(name = "Feedback.findAll", query = "SELECT f FROM Feedback f"),
    @NamedQuery(name = "Feedback.findByFeedbackId", query = "SELECT f FROM Feedback f WHERE f.feedbackId = :feedbackId"),
    @NamedQuery(name = "Feedback.findByName", query = "SELECT f FROM Feedback f WHERE f.name = :name"),
    @NamedQuery(name = "Feedback.findByEmail", query = "SELECT f FROM Feedback f WHERE f.email = :email"),
    @NamedQuery(name = "Feedback.findByQuestion", query = "SELECT f FROM Feedback f WHERE f.question = :question"),
    @NamedQuery(name = "Feedback.findByAnswer", query = "SELECT f FROM Feedback f WHERE f.answer = :answer"),
    @NamedQuery(name = "Feedback.findByPostedTime", query = "SELECT f FROM Feedback f WHERE f.postedTime = :postedTime"),
    @NamedQuery(name = "Feedback.findByStatus", query = "SELECT f FROM Feedback f WHERE f.status = :status")})
public class Feedback implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "FeedbackId")
    private Integer feedbackId;
    @Column(name = "Name")
    private String name;
    @Column(name = "Email")
    private String email;
    @Column(name = "Question")
    private String question;
    @Column(name = "Answer")
    private String answer;
    @Column(name = "PostedTime")
    private String postedTime;
    @Column(name = "Status")
    private Boolean status;
    @JoinColumn(name = "AnswerBy", referencedColumnName = "UserId")
    @ManyToOne
    private Users users;

    public Feedback() {
    }

    public Feedback(Integer feedbackId) {
	this.feedbackId = feedbackId;
    }

    public Integer getFeedbackId() {
	return feedbackId;
    }

    public void setFeedbackId(Integer feedbackId) {
	this.feedbackId = feedbackId;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getQuestion() {
	return question;
    }

    public void setQuestion(String question) {
	this.question = question;
    }

    public String getAnswer() {
	return answer;
    }

    public void setAnswer(String answer) {
	this.answer = answer;
    }

    public String getPostedTime() {
	return postedTime;
    }

    public void setPostedTime(String postedTime) {
	this.postedTime = postedTime;
    }

    public Boolean getStatus() {
	return status;
    }

    public void setStatus(Boolean status) {
	this.status = status;
    }

    public Users getUsers() {
	return users;
    }

    public void setUsers(Users users) {
	this.users = users;
    }

    @Override
    public int hashCode() {
	int hash = 0;
	hash += (feedbackId != null ? feedbackId.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof Feedback)) {
	    return false;
	}
	Feedback other = (Feedback) object;
	if ((this.feedbackId == null && other.feedbackId != null) || (this.feedbackId != null && !this.feedbackId.equals(other.feedbackId))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "web.entity.Feedback[feedbackId=" + feedbackId + "]";
    }

}
