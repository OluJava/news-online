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
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Khatmau_sr
 */
@Entity
@Table(name = "Mas_Section")
@NamedQueries({
    @NamedQuery(name = "MasSection.findAll", query = "SELECT m FROM MasSection m"),
    @NamedQuery(name = "MasSection.findByMasSectionId", query = "SELECT m FROM MasSection m WHERE m.masSectionId = :masSectionId"),
    @NamedQuery(name = "MasSection.findByTitle", query = "SELECT m FROM MasSection m WHERE m.title = :title"),
    @NamedQuery(name = "MasSection.findByDescription", query = "SELECT m FROM MasSection m WHERE m.description = :description")})
public class MasSection implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Mas_SectionId")
    private Integer masSectionId;
    @Column(name = "Title")
    private String title;
    @Column(name = "Description")
    private String description;
    @OneToMany(mappedBy = "masSection")
    private Collection<SubSection> subSectionCollection;
    @JoinColumn(name = "Mas_SectionId", referencedColumnName = "SectionId", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Section section;

    public MasSection() {
    }

    public MasSection(Integer masSectionId) {
        this.masSectionId = masSectionId;
    }

    public Integer getMasSectionId() {
        return masSectionId;
    }

    public void setMasSectionId(Integer masSectionId) {
        this.masSectionId = masSectionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<SubSection> getSubSectionCollection() {
        return subSectionCollection;
    }

    public void setSubSectionCollection(Collection<SubSection> subSectionCollection) {
        this.subSectionCollection = subSectionCollection;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (masSectionId != null ? masSectionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MasSection)) {
            return false;
        }
        MasSection other = (MasSection) object;
        if ((this.masSectionId == null && other.masSectionId != null) || (this.masSectionId != null && !this.masSectionId.equals(other.masSectionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "web.Entity.MasSection[masSectionId=" + masSectionId + "]";
    }

}
