/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package web.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Khatmau_sr
 */
@Entity
@Table(name = "Users")
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByUserId", query = "SELECT u FROM Users u WHERE u.userId = :userId"),
    @NamedQuery(name = "Users.findByUsername", query = "SELECT u FROM Users u WHERE u.username = :username"),
    @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password"),
    @NamedQuery(name = "Users.findByImage", query = "SELECT u FROM Users u WHERE u.image = :image"),
    @NamedQuery(name = "Users.findByFullName", query = "SELECT u FROM Users u WHERE u.fullName = :fullName"),
    @NamedQuery(name = "Users.findByBirthday", query = "SELECT u FROM Users u WHERE u.birthday = :birthday"),
    @NamedQuery(name = "Users.findByGender", query = "SELECT u FROM Users u WHERE u.gender = :gender"),
    @NamedQuery(name = "Users.findByAddress", query = "SELECT u FROM Users u WHERE u.address = :address"),
    @NamedQuery(name = "Users.findByPhone", query = "SELECT u FROM Users u WHERE u.phone = :phone"),
    @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email"),
    @NamedQuery(name = "Users.findByQuestion", query = "SELECT u FROM Users u WHERE u.question = :question"),
    @NamedQuery(name = "Users.findByAnswer", query = "SELECT u FROM Users u WHERE u.answer = :answer"),
    @NamedQuery(name = "Users.findByRoles", query = "SELECT u FROM Users u WHERE u.roles = :roles"),
    @NamedQuery(name = "Users.findByLastLogin", query = "SELECT u FROM Users u WHERE u.lastLogin = :lastLogin"),
    @NamedQuery(name = "Users.findByActiveSMS", query = "SELECT u FROM Users u WHERE u.activeSMS = :activeSMS"),
    @NamedQuery(name = "Users.findByStatus", query = "SELECT u FROM Users u WHERE u.status = :status")})
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "UserId")
    private Integer userId;
    @Column(name = "Username")
    private String username;
    @Column(name = "Password")
    private String password;
    @Column(name = "Image")
    private String image;
    @Column(name = "FullName")
    private String fullName;
    @Column(name = "Birthday")
    private String birthday;
    @Column(name = "Gender")
    private Boolean gender;
    @Column(name = "Address")
    private String address;
    @Column(name = "Phone")
    private String phone;
    @Column(name = "Email")
    private String email;
    @Column(name = "Question")
    private String question;
    @Column(name = "Answer")
    private String answer;
    @Column(name = "Roles")
    private String roles;
    @Column(name = "LastLogin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;
    @Column(name = "ActiveSMS")
    private Boolean activeSMS;
    @Column(name = "Status")
    private Boolean status;
    @JoinColumn(name = "Category", referencedColumnName = "CategoryId")
    @ManyToOne
    private Category category;
    @OneToMany(mappedBy = "users")
    private Collection<Comment> commentCollection;
    @OneToMany(mappedBy = "users")
    private Collection<Feedback> feedbackCollection;
    @OneToMany(mappedBy = "users")
    private Collection<News> newsCollection;

    public Users() {
    }

    public Users(Integer userId) {
	this.userId = userId;
    }

    public Integer getUserId() {
	return userId;
    }

    public void setUserId(Integer userId) {
	this.userId = userId;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getImage() {
	return image;
    }

    public void setImage(String image) {
	this.image = image;
    }

    public String getFullName() {
	return fullName;
    }

    public void setFullName(String fullName) {
	this.fullName = fullName;
    }

    public String getBirthday() {
	return birthday;
    }

    public void setBirthday(String birthday) {
	this.birthday = birthday;
    }

    public Boolean getGender() {
	return gender;
    }

    public void setGender(Boolean gender) {
	this.gender = gender;
    }

    public String getAddress() {
	return address;
    }

    public void setAddress(String address) {
	this.address = address;
    }

    public String getPhone() {
	return phone;
    }

    public void setPhone(String phone) {
	this.phone = phone;
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

    public String getRoles() {
	return roles;
    }

    public void setRoles(String roles) {
	this.roles = roles;
    }

    public Date getLastLogin() {
	return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
	this.lastLogin = lastLogin;
    }

    public Boolean getActiveSMS() {
	return activeSMS;
    }

    public void setActiveSMS(Boolean activeSMS) {
	this.activeSMS = activeSMS;
    }

    public Boolean getStatus() {
	return status;
    }

    public void setStatus(Boolean status) {
	this.status = status;
    }

    public Category getCategory() {
	return category;
    }

    public void setCategory(Category category) {
	this.category = category;
    }

    public Collection<Comment> getCommentCollection() {
	return commentCollection;
    }

    public void setCommentCollection(Collection<Comment> commentCollection) {
	this.commentCollection = commentCollection;
    }

    public Collection<Feedback> getFeedbackCollection() {
	return feedbackCollection;
    }

    public void setFeedbackCollection(Collection<Feedback> feedbackCollection) {
	this.feedbackCollection = feedbackCollection;
    }

    public Collection<News> getNewsCollection() {
	return newsCollection;
    }

    public void setNewsCollection(Collection<News> newsCollection) {
	this.newsCollection = newsCollection;
    }

    @Override
    public int hashCode() {
	int hash = 0;
	hash += (userId != null ? userId.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof Users)) {
	    return false;
	}
	Users other = (Users) object;
	if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "web.entity.Users[userId=" + userId + "]";
    }

}
