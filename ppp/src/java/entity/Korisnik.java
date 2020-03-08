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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "korisnik")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Korisnik.findAll", query = "SELECT k FROM Korisnik k")
    , @NamedQuery(name = "Korisnik.findByIdKorisnik", query = "SELECT k FROM Korisnik k WHERE k.idKorisnik = :idKorisnik")
    , @NamedQuery(name = "Korisnik.findByUsername", query = "SELECT k FROM Korisnik k WHERE k.username = :username")
    , @NamedQuery(name = "Korisnik.findByPassword", query = "SELECT k FROM Korisnik k WHERE k.password = :password")
    , @NamedQuery(name = "Korisnik.findByIme", query = "SELECT k FROM Korisnik k WHERE k.ime = :ime")
    , @NamedQuery(name = "Korisnik.findByPrezime", query = "SELECT k FROM Korisnik k WHERE k.prezime = :prezime")
    , @NamedQuery(name = "Korisnik.findByGrad", query = "SELECT k FROM Korisnik k WHERE k.grad = :grad")
    , @NamedQuery(name = "Korisnik.findByOpstina", query = "SELECT k FROM Korisnik k WHERE k.opstina = :opstina")
    , @NamedQuery(name = "Korisnik.findByAdresa", query = "SELECT k FROM Korisnik k WHERE k.adresa = :adresa")
    , @NamedQuery(name = "Korisnik.findByDatumRodjenja", query = "SELECT k FROM Korisnik k WHERE k.datumRodjenja = :datumRodjenja")
    , @NamedQuery(name = "Korisnik.findByTelefon", query = "SELECT k FROM Korisnik k WHERE k.telefon = :telefon")
    , @NamedQuery(name = "Korisnik.findByZaposlenje", query = "SELECT k FROM Korisnik k WHERE k.zaposlenje = :zaposlenje")
    , @NamedQuery(name = "Korisnik.findByMail", query = "SELECT k FROM Korisnik k WHERE k.mail = :mail")
    , @NamedQuery(name = "Korisnik.findByPrihvacen", query = "SELECT k FROM Korisnik k WHERE k.prihvacen = :prihvacen")})
public class Korisnik implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idKorisnik")
    private Integer idKorisnik;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "ime")
    private String ime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "prezime")
    private String prezime;
    @Size(max = 30)
    @Column(name = "grad")
    private String grad;
    @Size(max = 30)
    @Column(name = "opstina")
    private String opstina;
    @Size(max = 50)
    @Column(name = "adresa")
    private String adresa;
    @Column(name = "datumRodjenja")
    @Temporal(TemporalType.DATE)
    private Date datumRodjenja;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "telefon")
    private String telefon;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "zaposlenje")
    private String zaposlenje;
    @Size(max = 30)
    @Column(name = "mail")
    private String mail;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prihvacen")
    private short prihvacen;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idKorisnik")
    private Collection<Mesecnakarta> mesecnakartaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idKorisnik")
    private Collection<Rezervacijakarata> rezervacijakarataCollection;

    public Korisnik() {
    }

    public Korisnik(Integer idKorisnik) {
        this.idKorisnik = idKorisnik;
    }

    public Korisnik(Integer idKorisnik, String username, String password, String ime, String prezime, String telefon, String zaposlenje, short prihvacen) {
        this.idKorisnik = idKorisnik;
        this.username = username;
        this.password = password;
        this.ime = ime;
        this.prezime = prezime;
        this.telefon = telefon;
        this.zaposlenje = zaposlenje;
        this.prihvacen = prihvacen;
    }

    public Integer getIdKorisnik() {
        return idKorisnik;
    }

    public void setIdKorisnik(Integer idKorisnik) {
        this.idKorisnik = idKorisnik;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getOpstina() {
        return opstina;
    }

    public void setOpstina(String opstina) {
        this.opstina = opstina;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getZaposlenje() {
        return zaposlenje;
    }

    public void setZaposlenje(String zaposlenje) {
        this.zaposlenje = zaposlenje;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public short getPrihvacen() {
        return prihvacen;
    }

    public void setPrihvacen(short prihvacen) {
        this.prihvacen = prihvacen;
    }

    @XmlTransient
    public Collection<Mesecnakarta> getMesecnakartaCollection() {
        return mesecnakartaCollection;
    }

    public void setMesecnakartaCollection(Collection<Mesecnakarta> mesecnakartaCollection) {
        this.mesecnakartaCollection = mesecnakartaCollection;
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
        hash += (idKorisnik != null ? idKorisnik.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Korisnik)) {
            return false;
        }
        Korisnik other = (Korisnik) object;
        if ((this.idKorisnik == null && other.idKorisnik != null) || (this.idKorisnik != null && !this.idKorisnik.equals(other.idKorisnik))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Korisnik[ idKorisnik=" + idKorisnik + " ]";
    }
    
}
