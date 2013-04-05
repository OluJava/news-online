/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package web.Entity;

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
    @NamedQuery(name = "Comment.findByCommentContent", query = "SELECT c FROM Comment c WHERE c.commentContent = :commentContent"),
    @NamedQuery(name = "Comment.findByCommentSub", query = "SELECT c FROM Comment c WHERE c.commentSub = :commentSub"),
    @NamedQuery(name = "Comment.findByCommentDate", query = "SELECT c FROM Comment c WHERE c.commentDate = :commentDate"),
    @NamedQuery(name = "Comment.findByCommentSpam", query = "SELECT c FROM Comment c WHERE c.commentSpam = :commentSpam"),
    @NamedQuery(name = "Comment.findByCommentStatus", query = "SELECT c FROM Comment c WHERE c.commentStatus = :commentStatus")})
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CommentId")
    private Integer commentId;
    @Column(name = "CommentContent")
    private String commentContent;
    @Column(name = "CommentSub")
    private Integer commentSub;
    @Column(name = "CommentDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date commentDate;
    @Column(name = "CommentSpam")
    private Integer commentSpam;
    @Column(name = "CommentStatus")
    private Boolean commentStatus;
    @JoinColumn(name = "UserId", referencedColumnName = "UserId")
    @ManyToOne
    private User user;
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

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Integer getCommentSub() {
        return commentSub;
    }

    public void setCommentSub(Integer commentSub) {
        this.commentSub = commentSub;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public Integer getCommentSpam() {
        return commentSpam;
    }

    public void setCommentSpam(Integer commentSpam) {
        this.commentSpam = commentSpam;
    }

    public Boolean getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(Boolean commentStatus) {
        this.commentStatus = commentStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        return "web.Entity.Comment[commentId=" + commentId + "]";
    }

}
