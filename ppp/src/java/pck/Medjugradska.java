/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pck;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ivana
 */
public class Medjugradska implements Serializable {

    private String nazivPrevoznika;
    private Date datumPolaska;
    private Date vremePolaska;
    private String stanice;
    
    private String mestoPolaska;
    private String mestoDolaska;
    
    private String vozaci;
    
    private String autobusMarka;
    private String autobusModel;
    private int autobusBrojSedista;
    
    
    private int idAutobus;
    private int idLinija;
    
    private int brojKarata;
    private String odobreno;
    
    public boolean prosla(){
        Date danas= new Date();
        if (danas.after(datumPolaska)) return true;
        else return false;
    }

    public int getBrojKarata() {
        return brojKarata;
    }

    public void setBrojKarata(int brojKarata) {
        this.brojKarata = brojKarata;
    }

    public String getOdobreno() {
        return odobreno;
    }

    public void setOdobreno(String odobreno) {
        this.odobreno = odobreno;
    }
    
    

    public int getIdLinija() {
        return idLinija;
    }

    public void setIdLinija(int idLinija) {
        this.idLinija = idLinija;
    }

  
    

    public int getIdAutobus() {
        return idAutobus;
    }

    public void setIdAutobus(int idAutobus) {
        this.idAutobus = idAutobus;
    }
    
    

    
    

    public String getAutobusMarka() {
        return autobusMarka;
    }

    public void setAutobusMarka(String autobusMarka) {
        this.autobusMarka = autobusMarka;
    }

    public String getAutobusModel() {
        return autobusModel;
    }

    public void setAutobusModel(String autobusModel) {
        this.autobusModel = autobusModel;
    }

    public int getAutobusBrojSedista() {
        return autobusBrojSedista;
    }

    public void setAutobusBrojSedista(int autobusBrojSedista) {
        this.autobusBrojSedista = autobusBrojSedista;
    }
    
    

    public String getVozaci() {
        return vozaci;
    }

    public void setVozaci(String vozaci) {
        this.vozaci = vozaci;
    }
    
    

    public String getMestoPolaska() {
        return mestoPolaska;
    }

    public void setMestoPolaska(String mestoPolaska) {
        this.mestoPolaska = mestoPolaska;
    }

    public String getMestoDolaska() {
        return mestoDolaska;
    }

    public void setMestoDolaska(String mestoDolaska) {
        this.mestoDolaska = mestoDolaska;
    }
    
    

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

    public String getStanice() {
        return stanice;
    }

    public void setStanice(String stanice) {
        this.stanice = stanice;
    }

}
