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
@Table(name = "News", catalog = "NewsOnline", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "News.findAll", query = "SELECT n FROM News n"),
    @NamedQuery(name = "News.findByNewsId", query = "SELECT n FROM News n WHERE n.newsId = :newsId"),
    @NamedQuery(name = "News.findByTitle", query = "SELECT n FROM News n WHERE n.title = :title"),
    @NamedQuery(name = "News.findByImage", query = "SELECT n FROM News n WHERE n.image = :image"),
    @NamedQuery(name = "News.findByDescription", query = "SELECT n FROM News n WHERE n.description = :description"),
    @NamedQuery(name = "News.findByContent", query = "SELECT n FROM News n WHERE n.content = :content"),
    @NamedQuery(name = "News.findByAuthor", query = "SELECT n FROM News n WHERE n.author = :author"),
    @NamedQuery(name = "News.findByTags", query = "SELECT n FROM News n WHERE n.tags = :tags"),
    @NamedQuery(name = "News.findByPostedDate", query = "SELECT n FROM News n WHERE n.postedDate = :postedDate"),
    @NamedQuery(name = "News.findByEditedDate", query = "SELECT n FROM News n WHERE n.editedDate = :editedDate"),
    @NamedQuery(name = "News.findByViewed", query = "SELECT n FROM News n WHERE n.viewed = :viewed"),
    @NamedQuery(name = "News.findByStatus", query = "SELECT n FROM News n WHERE n.status = :status")})
public class News implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NewsId", nullable = false)
    private Integer newsId;
    @Column(name = "Title", length = 50)
    private String title;
    @Column(name = "Image", length = 100)
    private String image;
    @Column(name = "Description", length = 150)
    private String description;
    @Column(name = "Content", length = 1073741823)
    private String content;
    @Column(name = "Author", length = 30)
    private String author;
    @Column(name = "Tags", length = 50)
    private String tags;
    @Column(name = "PostedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date postedDate;
    @Column(name = "EditedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date editedDate;
    @Column(name = "Viewed")
    private Integer viewed;
    @Column(name = "Status")
    private Boolean status;
    @OneToMany(mappedBy = "news")
    private Collection<Comment> commentCollection;
    @OneToMany(mappedBy = "news")
    private Collection<HeadNews> headNewsCollection;
    @JoinColumn(name = "Uploader", referencedColumnName = "UserId")
    @ManyToOne
    private Users users;
    @JoinColumn(name = "Category", referencedColumnName = "CategoryId")
    @ManyToOne
    private Category category;

    public News() {
    }

    public News(Integer newsId) {
	this.newsId = newsId;
    }

    public Integer getNewsId() {
	return newsId;
    }

    public void setNewsId(Integer newsId) {
	this.newsId = newsId;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public String getImage() {
	return image;
    }

    public void setImage(String image) {
	this.image = image;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public String getContent() {
	return content;
    }

    public void setContent(String content) {
	this.content = content;
    }

    public String getAuthor() {
	return author;
    }

    public void setAuthor(String author) {
	this.author = author;
    }

    public String getTags() {
	return tags;
    }

    public void setTags(String tags) {
	this.tags = tags;
    }

    public Date getPostedDate() {
	return postedDate;
    }

    public void setPostedDate(Date postedDate) {
	this.postedDate = postedDate;
    }

    public Date getEditedDate() {
	return editedDate;
    }

    public void setEditedDate(Date editedDate) {
	this.editedDate = editedDate;
    }

    public Integer getViewed() {
	return viewed;
    }

    public void setViewed(Integer viewed) {
	this.viewed = viewed;
    }

    public Boolean getStatus() {
	return status;
    }

    public void setStatus(Boolean status) {
	this.status = status;
    }

    public Collection<Comment> getCommentCollection() {
	return commentCollection;
    }

    public void setCommentCollection(Collection<Comment> commentCollection) {
	this.commentCollection = commentCollection;
    }

    public Collection<HeadNews> getHeadNewsCollection() {
	return headNewsCollection;
    }

    public void setHeadNewsCollection(Collection<HeadNews> headNewsCollection) {
	this.headNewsCollection = headNewsCollection;
    }

    public Users getUsers() {
	return users;
    }

    public void setUsers(Users users) {
	this.users = users;
    }

    public Category getCategory() {
	return category;
    }

    public void setCategory(Category category) {
	this.category = category;
    }

    @Override
    public int hashCode() {
	int hash = 0;
	hash += (newsId != null ? newsId.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof News)) {
	    return false;
	}
	News other = (News) object;
	if ((this.newsId == null && other.newsId != null) || (this.newsId != null && !this.newsId.equals(other.newsId))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "web.entity.News[newsId=" + newsId + "]";
    }

}
