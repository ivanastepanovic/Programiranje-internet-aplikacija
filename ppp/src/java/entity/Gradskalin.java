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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ivana
 */
@Entity
@Table(name = "gradskalin")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gradskalin.findAll", query = "SELECT g FROM Gradskalin g")
    , @NamedQuery(name = "Gradskalin.findByIdLinija", query = "SELECT g FROM Gradskalin g WHERE g.idLinija = :idLinija")
    , @NamedQuery(name = "Gradskalin.findByBrojLinije", query = "SELECT g FROM Gradskalin g WHERE g.brojLinije = :brojLinije")
    , @NamedQuery(name = "Gradskalin.findByRedVoznje", query = "SELECT g FROM Gradskalin g WHERE g.redVoznje = :redVoznje")})
public class Gradskalin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idLinija")
    private Integer idLinija;
    @Basic(optional = false)
    @NotNull
    @Column(name = "brojLinije")
    private int brojLinije;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "redVoznje")
    private String redVoznje;
    @JoinColumn(name = "idLinija", referencedColumnName = "idLinija", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Linija linija;

    public Gradskalin() {
    }

    public Gradskalin(Integer idLinija) {
        this.idLinija = idLinija;
    }

    public Gradskalin(Integer idLinija, int brojLinije, String redVoznje) {
        this.idLinija = idLinija;
        this.brojLinije = brojLinije;
        this.redVoznje = redVoznje;
    }

    public Integer getIdLinija() {
        return idLinija;
    }

    public void setIdLinija(Integer idLinija) {
        this.idLinija = idLinija;
    }

    public int getBrojLinije() {
        return brojLinije;
    }

    public void setBrojLinije(int brojLinije) {
        this.brojLinije = brojLinije;
    }

    public String getRedVoznje() {
        return redVoznje;
    }

    public void setRedVoznje(String redVoznje) {
        this.redVoznje = redVoznje;
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
        if (!(object instanceof Gradskalin)) {
            return false;
        }
        Gradskalin other = (Gradskalin) object;
        if ((this.idLinija == null && other.idLinija != null) || (this.idLinija != null && !this.idLinija.equals(other.idLinija))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Gradskalin[ idLinija=" + idLinija + " ]";
    }
    
}
