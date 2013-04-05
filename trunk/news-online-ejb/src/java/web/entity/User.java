/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package web.Entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "User")
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByUserId", query = "SELECT u FROM User u WHERE u.userId = :userId"),
    @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByFullName", query = "SELECT u FROM User u WHERE u.fullName = :fullName"),
    @NamedQuery(name = "User.findByBirthday", query = "SELECT u FROM User u WHERE u.birthday = :birthday"),
    @NamedQuery(name = "User.findByGender", query = "SELECT u FROM User u WHERE u.gender = :gender"),
    @NamedQuery(name = "User.findByAddress", query = "SELECT u FROM User u WHERE u.address = :address"),
    @NamedQuery(name = "User.findByPhone", query = "SELECT u FROM User u WHERE u.phone = :phone"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByRole", query = "SELECT u FROM User u WHERE u.role = :role"),
    @NamedQuery(name = "User.findByLastLogin", query = "SELECT u FROM User u WHERE u.lastLogin = :lastLogin"),
    @NamedQuery(name = "User.findByStatus", query = "SELECT u FROM User u WHERE u.status = :status")})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "UserId")
    private Integer userId;
    @Column(name = "Username")
    private String username;
    @Column(name = "Password")
    private String password;
    @Column(name = "FullName")
    private String fullName;
    @Column(name = "Birthday")
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthday;
    @Column(name = "Gender")
    private Boolean gender;
    @Column(name = "Address")
    private String address;
    @Column(name = "Phone")
    private String phone;
    @Column(name = "Email")
    private String email;
    @Column(name = "Role")
    private String role;
    @Column(name = "LastLogin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;
    @Column(name = "Status")
    private Boolean status;
    @OneToMany(mappedBy = "user")
    private Collection<Message> messageCollection;
    @OneToMany(mappedBy = "user1")
    private Collection<Message> messageCollection1;
    @OneToMany(mappedBy = "user")
    private Collection<Comment> commentCollection;
    @OneToMany(mappedBy = "user")
    private Collection<News> newsCollection;

    public User() {
    }

    public User(Integer userId) {
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Collection<Message> getMessageCollection() {
        return messageCollection;
    }

    public void setMessageCollection(Collection<Message> messageCollection) {
        this.messageCollection = messageCollection;
    }

    public Collection<Message> getMessageCollection1() {
        return messageCollection1;
    }

    public void setMessageCollection1(Collection<Message> messageCollection1) {
        this.messageCollection1 = messageCollection1;
    }

    public Collection<Comment> getCommentCollection() {
        return commentCollection;
    }

    public void setCommentCollection(Collection<Comment> commentCollection) {
        this.commentCollection = commentCollection;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "web.Entity.User[userId=" + userId + "]";
    }

}
