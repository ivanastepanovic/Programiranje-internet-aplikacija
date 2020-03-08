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
@Table(name = "mesecnagodisnja")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mesecnagodisnja.findAll", query = "SELECT m FROM Mesecnagodisnja m")
    , @NamedQuery(name = "Mesecnagodisnja.findByIdMesGod", query = "SELECT m FROM Mesecnagodisnja m WHERE m.idMesGod = :idMesGod")
    , @NamedQuery(name = "Mesecnagodisnja.findByZaposlenje", query = "SELECT m FROM Mesecnagodisnja m WHERE m.zaposlenje = :zaposlenje")
    , @NamedQuery(name = "Mesecnagodisnja.findByCenaMesecne", query = "SELECT m FROM Mesecnagodisnja m WHERE m.cenaMesecne = :cenaMesecne")
    , @NamedQuery(name = "Mesecnagodisnja.findByCenaGodisnje", query = "SELECT m FROM Mesecnagodisnja m WHERE m.cenaGodisnje = :cenaGodisnje")})
public class Mesecnagodisnja implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMesGod")
    private Integer idMesGod;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "zaposlenje")
    private String zaposlenje;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cenaMesecne")
    private int cenaMesecne;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cenaGodisnje")
    private int cenaGodisnje;

    public Mesecnagodisnja() {
    }

    public Mesecnagodisnja(Integer idMesGod) {
        this.idMesGod = idMesGod;
    }

    public Mesecnagodisnja(Integer idMesGod, String zaposlenje, int cenaMesecne, int cenaGodisnje) {
        this.idMesGod = idMesGod;
        this.zaposlenje = zaposlenje;
        this.cenaMesecne = cenaMesecne;
        this.cenaGodisnje = cenaGodisnje;
    }

    public Integer getIdMesGod() {
        return idMesGod;
    }

    public void setIdMesGod(Integer idMesGod) {
        this.idMesGod = idMesGod;
    }

    public String getZaposlenje() {
        return zaposlenje;
    }

    public void setZaposlenje(String zaposlenje) {
        this.zaposlenje = zaposlenje;
    }

    public int getCenaMesecne() {
        return cenaMesecne;
    }

    public void setCenaMesecne(int cenaMesecne) {
        this.cenaMesecne = cenaMesecne;
    }

    public int getCenaGodisnje() {
        return cenaGodisnje;
    }

    public void setCenaGodisnje(int cenaGodisnje) {
        this.cenaGodisnje = cenaGodisnje;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMesGod != null ? idMesGod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mesecnagodisnja)) {
            return false;
        }
        Mesecnagodisnja other = (Mesecnagodisnja) object;
        if ((this.idMesGod == null && other.idMesGod != null) || (this.idMesGod != null && !this.idMesGod.equals(other.idMesGod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Mesecnagodisnja[ idMesGod=" + idMesGod + " ]";
    }
    
}
