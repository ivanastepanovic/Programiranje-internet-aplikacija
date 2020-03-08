/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import pck.HibernateUtil;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Ivana
 */
@Entity
public class NajskorijeMedjugradske implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    
    @Column (name="nazivPrevoznika")
    @Size(min = 1, max = 50)
    private String nazivPrevoznika;
    
    
    @Column (name="datumPolaska")
    @Temporal(TemporalType.DATE)
    private Date datumPolaska;
    
    
    @Column (name="vremePolaska")
    @Temporal(TemporalType.DATE)
    private Date vremePolaska;
    
   

    public String getNazivPrevoznika() {
        return nazivPrevoznika;
    }

    public void setNazivPrevoznika(String nazivPrevoznika) {
        this.nazivPrevoznika = nazivPrevoznika;
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

    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NajskorijeMedjugradske)) {
            return false;
        }
        NajskorijeMedjugradske other = (NajskorijeMedjugradske) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.najskorijeMedjugradske[ id=" + id + " ]";
    }
    
}
