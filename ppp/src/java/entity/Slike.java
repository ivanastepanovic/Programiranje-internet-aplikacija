/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ivana
 */
@Entity
@Table(name = "slike")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Slike.findAll", query = "SELECT s FROM Slike s")
    , @NamedQuery(name = "Slike.findByIdSlika", query = "SELECT s FROM Slike s WHERE s.idSlika = :idSlika")
    , @NamedQuery(name = "Slike.findByIdAutobus", query = "SELECT s FROM Slike s WHERE s.idAutobus = :idAutobus")
    , @NamedQuery(name = "Slike.findByNazivSlike", query = "SELECT s FROM Slike s WHERE s.nazivSlike = :nazivSlike")})
public class Slike implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSlika")
    private Integer idSlika;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idAutobus")
    private int idAutobus;
    @Size(max = 30)
    @Column(name = "nazivSlike")
    private String nazivSlike;

    public Slike() {
    }

    public Slike(Integer idSlika) {
        this.idSlika = idSlika;
    }

    public Slike(Integer idSlika, int idAutobus) {
        this.idSlika = idSlika;
        this.idAutobus = idAutobus;
    }

    public Integer getIdSlika() {
        return idSlika;
    }

    public void setIdSlika(Integer idSlika) {
        this.idSlika = idSlika;
    }

    public int getIdAutobus() {
        return idAutobus;
    }

    public void setIdAutobus(int idAutobus) {
        this.idAutobus = idAutobus;
    }

    public String getNazivSlike() {
        return nazivSlike;
    }

    public void setNazivSlike(String nazivSlike) {
        this.nazivSlike = nazivSlike;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSlika != null ? idSlika.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Slike)) {
            return false;
        }
        Slike other = (Slike) object;
        if ((this.idSlika == null && other.idSlika != null) || (this.idSlika != null && !this.idSlika.equals(other.idSlika))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Slike[ idSlika=" + idSlika + " ]";
    }
    
}
