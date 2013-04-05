/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package web.Entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Khatmau_sr
 */
@Entity
@Table(name = "SectionType")
@NamedQueries({
    @NamedQuery(name = "SectionType.findAll", query = "SELECT s FROM SectionType s"),
    @NamedQuery(name = "SectionType.findBySectionTypeId", query = "SELECT s FROM SectionType s WHERE s.sectionTypeId = :sectionTypeId"),
    @NamedQuery(name = "SectionType.findBySectionTypeName", query = "SELECT s FROM SectionType s WHERE s.sectionTypeName = :sectionTypeName")})
public class SectionType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "SectionTypeId")
    private Short sectionTypeId;
    @Column(name = "SectionTypeName")
    private String sectionTypeName;
    @OneToOne(mappedBy = "sectionType")
    private Section section;

    public SectionType() {
    }

    public SectionType(Short sectionTypeId) {
        this.sectionTypeId = sectionTypeId;
    }

    public Short getSectionTypeId() {
        return sectionTypeId;
    }

    public void setSectionTypeId(Short sectionTypeId) {
        this.sectionTypeId = sectionTypeId;
    }

    public String getSectionTypeName() {
        return sectionTypeName;
    }

    public void setSectionTypeName(String sectionTypeName) {
        this.sectionTypeName = sectionTypeName;
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
        hash += (sectionTypeId != null ? sectionTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SectionType)) {
            return false;
        }
        SectionType other = (SectionType) object;
        if ((this.sectionTypeId == null && other.sectionTypeId != null) || (this.sectionTypeId != null && !this.sectionTypeId.equals(other.sectionTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "web.Entity.SectionType[sectionTypeId=" + sectionTypeId + "]";
    }

}
