/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ivana
 */
@Entity
@Table(name = "mesecnakarta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mesecnakarta.findAll", query = "SELECT m FROM Mesecnakarta m")
    , @NamedQuery(name = "Mesecnakarta.findByIdMesecna", query = "SELECT m FROM Mesecnakarta m WHERE m.idMesecna = :idMesecna")
    , @NamedQuery(name = "Mesecnakarta.findByTip", query = "SELECT m FROM Mesecnakarta m WHERE m.tip = :tip")
    , @NamedQuery(name = "Mesecnakarta.findByCena", query = "SELECT m FROM Mesecnakarta m WHERE m.cena = :cena")
    , @NamedQuery(name = "Mesecnakarta.findByDatumKupovine", query = "SELECT m FROM Mesecnakarta m WHERE m.datumKupovine = :datumKupovine")
    , @NamedQuery(name = "Mesecnakarta.findByOdobreno", query = "SELECT m FROM Mesecnakarta m WHERE m.odobreno = :odobreno")})
public class Mesecnakarta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMesecna")
    private Integer idMesecna;
    @Size(max = 30)
    @Column(name = "tip")
    private String tip;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cena")
    private int cena;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datumKupovine")
    @Temporal(TemporalType.DATE)
    private Date datumKupovine;
    @Basic(optional = false)
    @NotNull
    @Column(name = "odobreno")
    private short odobreno;
    @JoinColumn(name = "idKorisnik", referencedColumnName = "idKorisnik")
    @ManyToOne(optional = false)
    private Korisnik idKorisnik;

    public Mesecnakarta() {
    }

    public Mesecnakarta(Integer idMesecna) {
        this.idMesecna = idMesecna;
    }

    public Mesecnakarta(Integer idMesecna, int cena, Date datumKupovine, short odobreno) {
        this.idMesecna = idMesecna;
        this.cena = cena;
        this.datumKupovine = datumKupovine;
        this.odobreno = odobreno;
    }

    public Integer getIdMesecna() {
        return idMesecna;
    }

    public void setIdMesecna(Integer idMesecna) {
        this.idMesecna = idMesecna;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public Date getDatumKupovine() {
        return datumKupovine;
    }

    public void setDatumKupovine(Date datumKupovine) {
        this.datumKupovine = datumKupovine;
    }

    public short getOdobreno() {
        return odobreno;
    }

    public void setOdobreno(short odobreno) {
        this.odobreno = odobreno;
    }

    public Korisnik getIdKorisnik() {
        return idKorisnik;
    }

    public void setIdKorisnik(Korisnik idKorisnik) {
        this.idKorisnik = idKorisnik;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMesecna != null ? idMesecna.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mesecnakarta)) {
            return false;
        }
        Mesecnakarta other = (Mesecnakarta) object;
        if ((this.idMesecna == null && other.idMesecna != null) || (this.idMesecna != null && !this.idMesecna.equals(other.idMesecna))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Mesecnakarta[ idMesecna=" + idMesecna + " ]";
    }
    
}
