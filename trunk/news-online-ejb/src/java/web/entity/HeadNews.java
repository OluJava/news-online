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
@Table(name = "HeadNews", catalog = "NewsOnline", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "HeadNews.findAll", query = "SELECT h FROM HeadNews h"),
    @NamedQuery(name = "HeadNews.findById", query = "SELECT h FROM HeadNews h WHERE h.id = :id"),
    @NamedQuery(name = "HeadNews.findByImages", query = "SELECT h FROM HeadNews h WHERE h.images = :images")})
public class HeadNews implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id", nullable = false)
    private Integer id;
    @Column(name = "Images", length = 255)
    private String images;
    @JoinColumn(name = "NewsId", referencedColumnName = "NewsId")
    @ManyToOne
    private News news;

    public HeadNews() {
    }

    public HeadNews(Integer id) {
	this.id = id;
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getImages() {
	return images;
    }

    public void setImages(String images) {
	this.images = images;
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
	hash += (id != null ? id.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof HeadNews)) {
	    return false;
	}
	HeadNews other = (HeadNews) object;
	if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "web.entity.HeadNews[id=" + id + "]";
    }

}
