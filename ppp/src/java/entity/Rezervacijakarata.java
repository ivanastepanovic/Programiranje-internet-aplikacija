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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ivana
 */
@Entity
@Table(name = "rezervacijakarata")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rezervacijakarata.findAll", query = "SELECT r FROM Rezervacijakarata r")
    , @NamedQuery(name = "Rezervacijakarata.findByIdRezervacijaKarata", query = "SELECT r FROM Rezervacijakarata r WHERE r.idRezervacijaKarata = :idRezervacijaKarata")
    , @NamedQuery(name = "Rezervacijakarata.findByOdobreno", query = "SELECT r FROM Rezervacijakarata r WHERE r.odobreno = :odobreno")
    , @NamedQuery(name = "Rezervacijakarata.findByBrojKarata", query = "SELECT r FROM Rezervacijakarata r WHERE r.brojKarata = :brojKarata")})
public class Rezervacijakarata implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idRezervacijaKarata")
    private Integer idRezervacijaKarata;
    @Basic(optional = false)
    @NotNull
    @Column(name = "odobreno")
    private short odobreno;
    @Basic(optional = false)
    @NotNull
    @Column(name = "brojKarata")
    private int brojKarata;
    @JoinColumn(name = "idKorisnik", referencedColumnName = "idKorisnik")
    @ManyToOne(optional = false)
    private Korisnik idKorisnik;
    @JoinColumn(name = "idLinija", referencedColumnName = "idLinija")
    @ManyToOne(optional = false)
    private Linija idLinija;

    public Rezervacijakarata() {
    }

    public Rezervacijakarata(Integer idRezervacijaKarata) {
        this.idRezervacijaKarata = idRezervacijaKarata;
    }

    public Rezervacijakarata(Integer idRezervacijaKarata, short odobreno, int brojKarata) {
        this.idRezervacijaKarata = idRezervacijaKarata;
        this.odobreno = odobreno;
        this.brojKarata = brojKarata;
    }

    public Integer getIdRezervacijaKarata() {
        return idRezervacijaKarata;
    }

    public void setIdRezervacijaKarata(Integer idRezervacijaKarata) {
        this.idRezervacijaKarata = idRezervacijaKarata;
    }

    public short getOdobreno() {
        return odobreno;
    }

    public void setOdobreno(short odobreno) {
        this.odobreno = odobreno;
    }

    public int getBrojKarata() {
        return brojKarata;
    }

    public void setBrojKarata(int brojKarata) {
        this.brojKarata = brojKarata;
    }

    public Korisnik getIdKorisnik() {
        return idKorisnik;
    }

    public void setIdKorisnik(Korisnik idKorisnik) {
        this.idKorisnik = idKorisnik;
    }

    public Linija getIdLinija() {
        return idLinija;
    }

    public void setIdLinija(Linija idLinija) {
        this.idLinija = idLinija;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRezervacijaKarata != null ? idRezervacijaKarata.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rezervacijakarata)) {
            return false;
        }
        Rezervacijakarata other = (Rezervacijakarata) object;
        if ((this.idRezervacijaKarata == null && other.idRezervacijaKarata != null) || (this.idRezervacijaKarata != null && !this.idRezervacijaKarata.equals(other.idRezervacijaKarata))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Rezervacijakarata[ idRezervacijaKarata=" + idRezervacijaKarata + " ]";
    }
    
}
