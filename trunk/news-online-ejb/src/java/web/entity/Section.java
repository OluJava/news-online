/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package web.Entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "Section")
@NamedQueries({
    @NamedQuery(name = "Section.findAll", query = "SELECT s FROM Section s"),
    @NamedQuery(name = "Section.findBySectionId", query = "SELECT s FROM Section s WHERE s.sectionId = :sectionId")})
public class Section implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "SectionId")
    private Integer sectionId;
    @JoinColumn(name = "SectionTypeId", referencedColumnName = "SectionTypeId")
    @OneToOne
    private SectionType sectionType;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "section")
    private SubSection subSection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "section")
    private MasSection masSection;
    @OneToMany(mappedBy = "section")
    private Collection<News> newsCollection;

    public Section() {
    }

    public Section(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public SectionType getSectionType() {
        return sectionType;
    }

    public void setSectionType(SectionType sectionType) {
        this.sectionType = sectionType;
    }

    public SubSection getSubSection() {
        return subSection;
    }

    public void setSubSection(SubSection subSection) {
        this.subSection = subSection;
    }

    public MasSection getMasSection() {
        return masSection;
    }

    public void setMasSection(MasSection masSection) {
        this.masSection = masSection;
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
        hash += (sectionId != null ? sectionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Section)) {
            return false;
        }
        Section other = (Section) object;
        if ((this.sectionId == null && other.sectionId != null) || (this.sectionId != null && !this.sectionId.equals(other.sectionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "web.Entity.Section[sectionId=" + sectionId + "]";
    }

}
