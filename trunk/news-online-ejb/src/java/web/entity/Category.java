/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package web.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Khatmau_sr
 */
@Entity
@Table(name = "Category", catalog = "NewsOnline", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c"),
    @NamedQuery(name = "Category.findByCategoryId", query = "SELECT c FROM Category c WHERE c.categoryId = :categoryId"),
    @NamedQuery(name = "Category.findByTitle", query = "SELECT c FROM Category c WHERE c.title = :title"),
    @NamedQuery(name = "Category.findByParent", query = "SELECT c FROM Category c WHERE c.parent = :parent"),
    @NamedQuery(name = "Category.findByStatus", query = "SELECT c FROM Category c WHERE c.status = :status")})
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CategoryId", nullable = false)
    private Integer categoryId;
    @Column(name = "Title", length = 50)
    private String title;
    @Column(name = "Parent", length = 10)
    private String parent;
    @Column(name = "Status")
    private Boolean status;
    @OneToMany(mappedBy = "category")
    private Collection<Users> usersCollection;
    @OneToMany(mappedBy = "category")
    private Collection<News> newsCollection;

    public Category() {
    }

    public Category(Integer categoryId) {
	this.categoryId = categoryId;
    }

    public Integer getCategoryId() {
	return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
	this.categoryId = categoryId;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public String getParent() {
	return parent;
    }

    public void setParent(String parent) {
	this.parent = parent;
    }

    public Boolean getStatus() {
	return status;
    }

    public void setStatus(Boolean status) {
	this.status = status;
    }

    public Collection<Users> getUsersCollection() {
	return usersCollection;
    }

    public void setUsersCollection(Collection<Users> usersCollection) {
	this.usersCollection = usersCollection;
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
	hash += (categoryId != null ? categoryId.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof Category)) {
	    return false;
	}
	Category other = (Category) object;
	if ((this.categoryId == null && other.categoryId != null) || (this.categoryId != null && !this.categoryId.equals(other.categoryId))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "web.entity.Category[categoryId=" + categoryId + "]";
    }

}
