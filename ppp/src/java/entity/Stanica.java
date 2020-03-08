/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ivana
 */
@Entity
@Table(name = "stanica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stanica.findAll", query = "SELECT s FROM Stanica s")
    , @NamedQuery(name = "Stanica.findByIdStanica", query = "SELECT s FROM Stanica s WHERE s.idStanica = :idStanica")
    , @NamedQuery(name = "Stanica.findByNazivStan", query = "SELECT s FROM Stanica s WHERE s.nazivStan = :nazivStan")})
public class Stanica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idStanica")
    private Integer idStanica;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nazivStan")
    private String nazivStan;
    @JoinTable(name = "medjustanice", joinColumns = {
        @JoinColumn(name = "idStanica", referencedColumnName = "idStanica")}, inverseJoinColumns = {
        @JoinColumn(name = "idLinija", referencedColumnName = "idLinija")})
    @ManyToMany
    private Collection<Linija> linijaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dolazakStanica")
    private Collection<Linija> linijaCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "polazakStanica")
    private Collection<Linija> linijaCollection2;

    public Stanica() {
    }

    public Stanica(Integer idStanica) {
        this.idStanica = idStanica;
    }

    public Stanica(Integer idStanica, String nazivStan) {
        this.idStanica = idStanica;
        this.nazivStan = nazivStan;
    }

    public Integer getIdStanica() {
        return idStanica;
    }

    public void setIdStanica(Integer idStanica) {
        this.idStanica = idStanica;
    }

    public String getNazivStan() {
        return nazivStan;
    }

    public void setNazivStan(String nazivStan) {
        this.nazivStan = nazivStan;
    }

    @XmlTransient
    public Collection<Linija> getLinijaCollection() {
        return linijaCollection;
    }

    public void setLinijaCollection(Collection<Linija> linijaCollection) {
        this.linijaCollection = linijaCollection;
    }

    @XmlTransient
    public Collection<Linija> getLinijaCollection1() {
        return linijaCollection1;
    }

    public void setLinijaCollection1(Collection<Linija> linijaCollection1) {
        this.linijaCollection1 = linijaCollection1;
    }

    @XmlTransient
    public Collection<Linija> getLinijaCollection2() {
        return linijaCollection2;
    }

    public void setLinijaCollection2(Collection<Linija> linijaCollection2) {
        this.linijaCollection2 = linijaCollection2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idStanica != null ? idStanica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stanica)) {
            return false;
        }
        Stanica other = (Stanica) object;
        if ((this.idStanica == null && other.idStanica != null) || (this.idStanica != null && !this.idStanica.equals(other.idStanica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Stanica[ idStanica=" + idStanica + " ]";
    }
    
}
