/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package web.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Khatmau_sr
 */
@Entity
@Table(name = "Comment")
@NamedQueries({
    @NamedQuery(name = "Comment.findAll", query = "SELECT c FROM Comment c"),
    @NamedQuery(name = "Comment.findByCommentId", query = "SELECT c FROM Comment c WHERE c.commentId = :commentId"),
    @NamedQuery(name = "Comment.findByContent", query = "SELECT c FROM Comment c WHERE c.content = :content"),
    @NamedQuery(name = "Comment.findByPostedDate", query = "SELECT c FROM Comment c WHERE c.postedDate = :postedDate"),
    @NamedQuery(name = "Comment.findByLikes", query = "SELECT c FROM Comment c WHERE c.likes = :likes"),
    @NamedQuery(name = "Comment.findBySpams", query = "SELECT c FROM Comment c WHERE c.spams = :spams"),
    @NamedQuery(name = "Comment.findByStatus", query = "SELECT c FROM Comment c WHERE c.status = :status")})
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CommentId")
    private Integer commentId;
    @Column(name = "Content")
    private String content;
    @Column(name = "PostedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date postedDate;
    @Column(name = "Likes")
    private Integer likes;
    @Column(name = "Spams")
    private Integer spams;
    @Column(name = "Status")
    private Boolean status;
    @JoinColumn(name = "UserId", referencedColumnName = "UserId")
    @ManyToOne
    private Users users;
    @JoinColumn(name = "NewsId", referencedColumnName = "NewsId")
    @ManyToOne
    private News news;

    public Comment() {
    }

    public Comment(Integer commentId) {
	this.commentId = commentId;
    }

    public Integer getCommentId() {
	return commentId;
    }

    public void setCommentId(Integer commentId) {
	this.commentId = commentId;
    }

    public String getContent() {
	return content;
    }

    public void setContent(String content) {
	this.content = content;
    }

    public Date getPostedDate() {
	return postedDate;
    }

    public void setPostedDate(Date postedDate) {
	this.postedDate = postedDate;
    }

    public Integer getLikes() {
	return likes;
    }

    public void setLikes(Integer likes) {
	this.likes = likes;
    }

    public Integer getSpams() {
	return spams;
    }

    public void setSpams(Integer spams) {
	this.spams = spams;
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

    public News getNews() {
	return news;
    }

    public void setNews(News news) {
	this.news = news;
    }

    @Override
    public int hashCode() {
	int hash = 0;
	hash += (commentId != null ? commentId.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof Comment)) {
	    return false;
	}
	Comment other = (Comment) object;
	if ((this.commentId == null && other.commentId != null) || (this.commentId != null && !this.commentId.equals(other.commentId))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "web.entity.Comment[commentId=" + commentId + "]";
    }

}
