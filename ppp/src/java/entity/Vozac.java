/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ivana
 */
@Entity
@Table(name = "vozac")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vozac.findAll", query = "SELECT v FROM Vozac v")
    , @NamedQuery(name = "Vozac.findByIdVozac", query = "SELECT v FROM Vozac v WHERE v.idVozac = :idVozac")
    , @NamedQuery(name = "Vozac.findByIme", query = "SELECT v FROM Vozac v WHERE v.ime = :ime")
    , @NamedQuery(name = "Vozac.findByPrezime", query = "SELECT v FROM Vozac v WHERE v.prezime = :prezime")
    , @NamedQuery(name = "Vozac.findByDatumRodjenja", query = "SELECT v FROM Vozac v WHERE v.datumRodjenja = :datumRodjenja")
    , @NamedQuery(name = "Vozac.findByGodinaPocetka", query = "SELECT v FROM Vozac v WHERE v.godinaPocetka = :godinaPocetka")})
public class Vozac implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idVozac")
    private Integer idVozac;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ime")
    private String ime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "prezime")
    private String prezime;
    @Column(name = "datumRodjenja")
    @Temporal(TemporalType.DATE)
    private Date datumRodjenja;
    @Basic(optional = false)
    @NotNull
    @Column(name = "godinaPocetka")
    private int godinaPocetka;
    @ManyToMany(mappedBy = "vozacCollection")
    private Collection<Linija> linijaCollection;

    public Vozac() {
    }

    public Vozac(Integer idVozac) {
        this.idVozac = idVozac;
    }

    public Vozac(Integer idVozac, String ime, String prezime, int godinaPocetka) {
        this.idVozac = idVozac;
        this.ime = ime;
        this.prezime = prezime;
        this.godinaPocetka = godinaPocetka;
    }

    public Integer getIdVozac() {
        return idVozac;
    }

    public void setIdVozac(Integer idVozac) {
        this.idVozac = idVozac;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public int getGodinaPocetka() {
        return godinaPocetka;
    }

    public void setGodinaPocetka(int godinaPocetka) {
        this.godinaPocetka = godinaPocetka;
    }

    @XmlTransient
    public Collection<Linija> getLinijaCollection() {
        return linijaCollection;
    }

    public void setLinijaCollection(Collection<Linija> linijaCollection) {
        this.linijaCollection = linijaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVozac != null ? idVozac.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vozac)) {
            return false;
        }
        Vozac other = (Vozac) object;
        if ((this.idVozac == null && other.idVozac != null) || (this.idVozac != null && !this.idVozac.equals(other.idVozac))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Vozac[ idVozac=" + idVozac + " ]";
    }
    
}
