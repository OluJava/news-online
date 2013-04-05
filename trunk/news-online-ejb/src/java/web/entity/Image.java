/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package web.Entity;

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
@Table(name = "Image")
@NamedQueries({
    @NamedQuery(name = "Image.findAll", query = "SELECT i FROM Image i"),
    @NamedQuery(name = "Image.findByImageId", query = "SELECT i FROM Image i WHERE i.imageId = :imageId"),
    @NamedQuery(name = "Image.findByTitle", query = "SELECT i FROM Image i WHERE i.title = :title"),
    @NamedQuery(name = "Image.findByPath", query = "SELECT i FROM Image i WHERE i.path = :path")})
public class Image implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ImageId")
    private Integer imageId;
    @Column(name = "Title")
    private String title;
    @Column(name = "Path")
    private String path;
    @OneToMany(mappedBy = "image")
    private Collection<News> newsCollection;

    public Image() {
    }

    public Image(Integer imageId) {
        this.imageId = imageId;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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
        hash += (imageId != null ? imageId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Image)) {
            return false;
        }
        Image other = (Image) object;
        if ((this.imageId == null && other.imageId != null) || (this.imageId != null && !this.imageId.equals(other.imageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "web.Entity.Image[imageId=" + imageId + "]";
    }

}
