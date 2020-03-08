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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ivana
 */
@Entity
@Table(name = "medjugradskalin")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Medjugradskalin.findAll", query = "SELECT m FROM Medjugradskalin m")
    , @NamedQuery(name = "Medjugradskalin.findByIdLinija", query = "SELECT m FROM Medjugradskalin m WHERE m.idLinija = :idLinija")
    , @NamedQuery(name = "Medjugradskalin.findByDatumPolaska", query = "SELECT m FROM Medjugradskalin m WHERE m.datumPolaska = :datumPolaska")
    , @NamedQuery(name = "Medjugradskalin.findByVremePolaska", query = "SELECT m FROM Medjugradskalin m WHERE m.vremePolaska = :vremePolaska")
    , @NamedQuery(name = "Medjugradskalin.findByDatumDolaska", query = "SELECT m FROM Medjugradskalin m WHERE m.datumDolaska = :datumDolaska")
    , @NamedQuery(name = "Medjugradskalin.findByVremeDolaska", query = "SELECT m FROM Medjugradskalin m WHERE m.vremeDolaska = :vremeDolaska")
    , @NamedQuery(name = "Medjugradskalin.findByIdAutobus", query = "SELECT m FROM Medjugradskalin m WHERE m.idAutobus = :idAutobus")
    , @NamedQuery(name = "Medjugradskalin.findByIdPrevoznik", query = "SELECT m FROM Medjugradskalin m WHERE m.idPrevoznik = :idPrevoznik")
    , @NamedQuery(name = "Medjugradskalin.findByBrSlobodnihMesta", query = "SELECT m FROM Medjugradskalin m WHERE m.brSlobodnihMesta = :brSlobodnihMesta")})
public class Medjugradskalin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idLinija")
    private Integer idLinija;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datumPolaska")
    @Temporal(TemporalType.DATE)
    private Date datumPolaska;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vremePolaska")
    @Temporal(TemporalType.TIME)
    private Date vremePolaska;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datumDolaska")
    @Temporal(TemporalType.DATE)
    private Date datumDolaska;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vremeDolaska")
    @Temporal(TemporalType.TIME)
    private Date vremeDolaska;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idAutobus")
    private int idAutobus;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idPrevoznik")
    private int idPrevoznik;
    @Basic(optional = false)
    @NotNull
    @Column(name = "brSlobodnihMesta")
    private int brSlobodnihMesta;
    @JoinColumn(name = "idLinija", referencedColumnName = "idLinija", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Linija linija;

    public Medjugradskalin() {
    }

    public Medjugradskalin(Integer idLinija) {
        this.idLinija = idLinija;
    }

    public Medjugradskalin(Integer idLinija, Date datumPolaska, Date vremePolaska, Date datumDolaska, Date vremeDolaska, int idAutobus, int idPrevoznik, int brSlobodnihMesta) {
        this.idLinija = idLinija;
        this.datumPolaska = datumPolaska;
        this.vremePolaska = vremePolaska;
        this.datumDolaska = datumDolaska;
        this.vremeDolaska = vremeDolaska;
        this.idAutobus = idAutobus;
        this.idPrevoznik = idPrevoznik;
        this.brSlobodnihMesta = brSlobodnihMesta;
    }

    public Integer getIdLinija() {
        return idLinija;
    }

    public void setIdLinija(Integer idLinija) {
        this.idLinija = idLinija;
    }

    public Date getDatumPolaska() {
        return datumPolaska;
    }

    public void setDatumPolaska(Date datumPolaska) {
        this.datumPolaska = datumPolaska;
    }

    public Date getVremePolaska() {
        return vremePolaska;
    }

    public void setVremePolaska(Date vremePolaska) {
        this.vremePolaska = vremePolaska;
    }

    public Date getDatumDolaska() {
        return datumDolaska;
    }

    public void setDatumDolaska(Date datumDolaska) {
        this.datumDolaska = datumDolaska;
    }

    public Date getVremeDolaska() {
        return vremeDolaska;
    }

    public void setVremeDolaska(Date vremeDolaska) {
        this.vremeDolaska = vremeDolaska;
    }

    public int getIdAutobus() {
        return idAutobus;
    }

    public void setIdAutobus(int idAutobus) {
        this.idAutobus = idAutobus;
    }

    public int getIdPrevoznik() {
        return idPrevoznik;
    }

    public void setIdPrevoznik(int idPrevoznik) {
        this.idPrevoznik = idPrevoznik;
    }

    public int getBrSlobodnihMesta() {
        return brSlobodnihMesta;
    }

    public void setBrSlobodnihMesta(int brSlobodnihMesta) {
        this.brSlobodnihMesta = brSlobodnihMesta;
    }

    public Linija getLinija() {
        return linija;
    }

    public void setLinija(Linija linija) {
        this.linija = linija;
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
        if (!(object instanceof Medjugradskalin)) {
            return false;
        }
        Medjugradskalin other = (Medjugradskalin) object;
        if ((this.idLinija == null && other.idLinija != null) || (this.idLinija != null && !this.idLinija.equals(other.idLinija))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Medjugradskalin[ idLinija=" + idLinija + " ]";
    }
    
}
