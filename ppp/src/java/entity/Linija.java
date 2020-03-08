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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ivana
 */
@Entity
@Table(name = "linija")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Linija.findAll", query = "SELECT l FROM Linija l")
    , @NamedQuery(name = "Linija.findByIdLinija", query = "SELECT l FROM Linija l WHERE l.idLinija = :idLinija")})
public class Linija implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idLinija")
    private Integer idLinija;
    @ManyToMany(mappedBy = "linijaCollection")
    private Collection<Stanica> stanicaCollection;
    @JoinTable(name = "vozi", joinColumns = {
        @JoinColumn(name = "idLinija", referencedColumnName = "idLinija")}, inverseJoinColumns = {
        @JoinColumn(name = "idVozac", referencedColumnName = "idVozac")})
    @ManyToMany
    private Collection<Vozac> vozacCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLinija")
    private Collection<Otkazane> otkazaneCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "linija")
    private Gradskalin gradskalin;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "linija")
    private Medjugradskalin medjugradskalin;
    @JoinColumn(name = "dolazakStanica", referencedColumnName = "idStanica")
    @ManyToOne(optional = false)
    private Stanica dolazakStanica;
    @JoinColumn(name = "polazakStanica", referencedColumnName = "idStanica")
    @ManyToOne(optional = false)
    private Stanica polazakStanica;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLinija")
    private Collection<Rezervacijakarata> rezervacijakarataCollection;

    public Linija() {
    }

    public Linija(Integer idLinija) {
        this.idLinija = idLinija;
    }

    public Integer getIdLinija() {
        return idLinija;
    }

    public void setIdLinija(Integer idLinija) {
        this.idLinija = idLinija;
    }

    @XmlTransient
    public Collection<Stanica> getStanicaCollection() {
        return stanicaCollection;
    }

    public void setStanicaCollection(Collection<Stanica> stanicaCollection) {
        this.stanicaCollection = stanicaCollection;
    }

    @XmlTransient
    public Collection<Vozac> getVozacCollection() {
        return vozacCollection;
    }

    public void setVozacCollection(Collection<Vozac> vozacCollection) {
        this.vozacCollection = vozacCollection;
    }

    @XmlTransient
    public Collection<Otkazane> getOtkazaneCollection() {
        return otkazaneCollection;
    }

    public void setOtkazaneCollection(Collection<Otkazane> otkazaneCollection) {
        this.otkazaneCollection = otkazaneCollection;
    }

    public Gradskalin getGradskalin() {
        return gradskalin;
    }

    public void setGradskalin(Gradskalin gradskalin) {
        this.gradskalin = gradskalin;
    }

    public Medjugradskalin getMedjugradskalin() {
        return medjugradskalin;
    }

    public void setMedjugradskalin(Medjugradskalin medjugradskalin) {
        this.medjugradskalin = medjugradskalin;
    }

    public Stanica getDolazakStanica() {
        return dolazakStanica;
    }

    public void setDolazakStanica(Stanica dolazakStanica) {
        this.dolazakStanica = dolazakStanica;
    }

    public Stanica getPolazakStanica() {
        return polazakStanica;
    }

    public void setPolazakStanica(Stanica polazakStanica) {
        this.polazakStanica = polazakStanica;
    }

    @XmlTransient
    public Collection<Rezervacijakarata> getRezervacijakarataCollection() {
        return rezervacijakarataCollection;
    }

    public void setRezervacijakarataCollection(Collection<Rezervacijakarata> rezervacijakarataCollection) {
        this.rezervacijakarataCollection = rezervacijakarataCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLinija != null ? idLinija.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Linija)) {
            return false;
        }
        Linija other = (Linija) object;
        if ((this.idLinija == null && other.idLinija != null) || (this.idLinija != null && !this.idLinija.equals(other.idLinija))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Linija[ idLinija=" + idLinija + " ]";
    }
    
}
