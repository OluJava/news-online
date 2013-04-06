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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Khatmau_sr
 */
@Entity
@Table(name = "BlockMessageUser")
@NamedQueries({
    @NamedQuery(name = "BlockMessageUser.findAll", query = "SELECT b FROM BlockMessageUser b"),
    @NamedQuery(name = "BlockMessageUser.findByUserId", query = "SELECT b FROM BlockMessageUser b WHERE b.userId = :userId")})
public class BlockMessageUser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "UserId")
    private Integer userId;
    @Lob
    @Column(name = "Idblocked")
    private String idblocked;
    @JoinColumn(name = "UserId", referencedColumnName = "UserId", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private User user;

    public BlockMessageUser() {
    }

    public BlockMessageUser(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getIdblocked() {
        return idblocked;
    }

    public void setIdblocked(String idblocked) {
        this.idblocked = idblocked;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BlockMessageUser)) {
            return false;
        }
        BlockMessageUser other = (BlockMessageUser) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "web.entity.BlockMessageUser[userId=" + userId + "]";
    }

}
