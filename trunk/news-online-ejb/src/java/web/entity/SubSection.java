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
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Khatmau_sr
 */
@Entity
@Table(name = "Sub_Section")
@NamedQueries({
    @NamedQuery(name = "SubSection.findAll", query = "SELECT s FROM SubSection s"),
    @NamedQuery(name = "SubSection.findBySubSectionId", query = "SELECT s FROM SubSection s WHERE s.subSectionId = :subSectionId"),
    @NamedQuery(name = "SubSection.findByTitle", query = "SELECT s FROM SubSection s WHERE s.title = :title"),
    @NamedQuery(name = "SubSection.findByDescription", query = "SELECT s FROM SubSection s WHERE s.description = :description")})
public class SubSection implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Sub_SectionId")
    private Integer subSectionId;
    @Column(name = "Title")
    private String title;
    @Column(name = "Description")
    private String description;
    @JoinColumn(name = "Sub_SectionId", referencedColumnName = "SectionId", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Section section;
    @JoinColumn(name = "Mas_SectionId", referencedColumnName = "Mas_SectionId")
    @ManyToOne
    private MasSection masSection;

    public SubSection() {
    }

    public SubSection(Integer subSectionId) {
        this.subSectionId = subSectionId;
    }

    public Integer getSubSectionId() {
        return subSectionId;
    }

    public void setSubSectionId(Integer subSectionId) {
        this.subSectionId = subSectionId;
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

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public MasSection getMasSection() {
        return masSection;
    }

    public void setMasSection(MasSection masSection) {
        this.masSection = masSection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (subSectionId != null ? subSectionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SubSection)) {
            return false;
        }
        SubSection other = (SubSection) object;
        if ((this.subSectionId == null && other.subSectionId != null) || (this.subSectionId != null && !this.subSectionId.equals(other.subSectionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "web.entity.SubSection[subSectionId=" + subSectionId + "]";
    }

}
