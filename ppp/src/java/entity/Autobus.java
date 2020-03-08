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
@Table(name = "autobus")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Autobus.findAll", query = "SELECT a FROM Autobus a")
    , @NamedQuery(name = "Autobus.findByIdAutobus", query = "SELECT a FROM Autobus a WHERE a.idAutobus = :idAutobus")
    , @NamedQuery(name = "Autobus.findByMarka", query = "SELECT a FROM Autobus a WHERE a.marka = :marka")
    , @NamedQuery(name = "Autobus.findByModel", query = "SELECT a FROM Autobus a WHERE a.model = :model")
    , @NamedQuery(name = "Autobus.findByBrSedista", query = "SELECT a FROM Autobus a WHERE a.brSedista = :brSedista")})
public class Autobus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAutobus")
    private Integer idAutobus;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "marka")
    private String marka;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "model")
    private String model;
    @Basic(optional = false)
    @NotNull
    @Column(name = "brSedista")
    private int brSedista;

    public Autobus() {
    }

    public Autobus(Integer idAutobus) {
        this.idAutobus = idAutobus;
    }

    public Autobus(Integer idAutobus, String marka, String model, int brSedista) {
        this.idAutobus = idAutobus;
        this.marka = marka;
        this.model = model;
        this.brSedista = brSedista;
    }

    public Integer getIdAutobus() {
        return idAutobus;
    }

    public void setIdAutobus(Integer idAutobus) {
        this.idAutobus = idAutobus;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getBrSedista() {
        return brSedista;
    }

    public void setBrSedista(int brSedista) {
        this.brSedista = brSedista;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAutobus != null ? idAutobus.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Autobus)) {
            return false;
        }
        Autobus other = (Autobus) object;
        if ((this.idAutobus == null && other.idAutobus != null) || (this.idAutobus != null && !this.idAutobus.equals(other.idAutobus))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Autobus[ idAutobus=" + idAutobus + " ]";
    }
    
}
