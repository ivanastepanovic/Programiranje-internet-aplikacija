/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pck;

import java.sql.Blob;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Ivana
 */
public class Gradska {
    private String pocetnoStajaliste;
    private String krajnjeStajaliste;
    private int brojLinije;
    private String redVoznje;
    private String vozaci;

    public String getVozaci() {
        return vozaci;
    }

    public void setVozaci(String vozaci) {
        this.vozaci = vozaci;
    }
    
    
    
    public String getRedVoznje() {
        return redVoznje;
    }

    public void setRedVoznje(String redVoznje) {
        this.redVoznje = redVoznje;
    }
   
    
    

    public String getPocetnoStajaliste() {
        return pocetnoStajaliste;
    }

    public void setPocetnoStajaliste(String pocetnoStajaliste) {
        this.pocetnoStajaliste = pocetnoStajaliste;
    }

    public String getKrajnjeStajaliste() {
        return krajnjeStajaliste;
    }

    public void setKrajnjeStajaliste(String krajnjeStajaliste) {
        this.krajnjeStajaliste = krajnjeStajaliste;
    }

    public int getBrojLinije() {
        return brojLinije;
    }

    public void setBrojLinije(int brojLinije) {
        this.brojLinije = brojLinije;
    }
    
    
}
