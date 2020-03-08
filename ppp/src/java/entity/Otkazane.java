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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ivana
 */
@Entity
@Table(name = "otkazane")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Otkazane.findAll", query = "SELECT o FROM Otkazane o")
    , @NamedQuery(name = "Otkazane.findByIdOtkaz", query = "SELECT o FROM Otkazane o WHERE o.idOtkaz = :idOtkaz")
    , @NamedQuery(name = "Otkazane.findByDatumOd", query = "SELECT o FROM Otkazane o WHERE o.datumOd = :datumOd")
    , @NamedQuery(name = "Otkazane.findByDatumDo", query = "SELECT o FROM Otkazane o WHERE o.datumDo = :datumDo")})
public class Otkazane implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idOtkaz")
    private Integer idOtkaz;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datumOd")
    @Temporal(TemporalType.DATE)
    private Date datumOd;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datumDo")
    @Temporal(TemporalType.DATE)
    private Date datumDo;
    @JoinColumn(name = "idLinija", referencedColumnName = "idLinija")
    @ManyToOne(optional = false)
    private Linija idLinija;

    public Otkazane() {
    }

    public Otkazane(Integer idOtkaz) {
        this.idOtkaz = idOtkaz;
    }

    public Otkazane(Integer idOtkaz, Date datumOd, Date datumDo) {
        this.idOtkaz = idOtkaz;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
    }

    public Integer getIdOtkaz() {
        return idOtkaz;
    }

    public void setIdOtkaz(Integer idOtkaz) {
        this.idOtkaz = idOtkaz;
    }

    public Date getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(Date datumOd) {
        this.datumOd = datumOd;
    }

    public Date getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(Date datumDo) {
        this.datumDo = datumDo;
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
        hash += (idOtkaz != null ? idOtkaz.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Otkazane)) {
            return false;
        }
        Otkazane other = (Otkazane) object;
        if ((this.idOtkaz == null && other.idOtkaz != null) || (this.idOtkaz != null && !this.idOtkaz.equals(other.idOtkaz))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Otkazane[ idOtkaz=" + idOtkaz + " ]";
    }
    
}
