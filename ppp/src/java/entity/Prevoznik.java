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
import javax.persistence.Lob;
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
@Table(name = "prevoznik")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prevoznik.findAll", query = "SELECT p FROM Prevoznik p")
    , @NamedQuery(name = "Prevoznik.findByIdPrevoznik", query = "SELECT p FROM Prevoznik p WHERE p.idPrevoznik = :idPrevoznik")
    , @NamedQuery(name = "Prevoznik.findByNaziv", query = "SELECT p FROM Prevoznik p WHERE p.naziv = :naziv")
    , @NamedQuery(name = "Prevoznik.findByAdresa", query = "SELECT p FROM Prevoznik p WHERE p.adresa = :adresa")
    , @NamedQuery(name = "Prevoznik.findByTelefon", query = "SELECT p FROM Prevoznik p WHERE p.telefon = :telefon")})
public class Prevoznik implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPrevoznik")
    private Integer idPrevoznik;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "naziv")
    private String naziv;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "adresa")
    private String adresa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "telefon")
    private String telefon;
    @Lob
    @Column(name = "logo")
    private byte[] logo;

    public Prevoznik() {
    }

    public Prevoznik(Integer idPrevoznik) {
        this.idPrevoznik = idPrevoznik;
    }

    public Prevoznik(Integer idPrevoznik, String naziv, String adresa, String telefon) {
        this.idPrevoznik = idPrevoznik;
        this.naziv = naziv;
        this.adresa = adresa;
        this.telefon = telefon;
    }

    public Integer getIdPrevoznik() {
        return idPrevoznik;
    }

    public void setIdPrevoznik(Integer idPrevoznik) {
        this.idPrevoznik = idPrevoznik;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPrevoznik != null ? idPrevoznik.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prevoznik)) {
            return false;
        }
        Prevoznik other = (Prevoznik) object;
        if ((this.idPrevoznik == null && other.idPrevoznik != null) || (this.idPrevoznik != null && !this.idPrevoznik.equals(other.idPrevoznik))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Prevoznik[ idPrevoznik=" + idPrevoznik + " ]";
    }
    
}
