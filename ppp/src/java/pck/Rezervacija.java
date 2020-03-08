/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pck;

import entity.Korisnik;
import entity.Linija;
import entity.Medjugradskalin;

/**
 *
 * @author Ivana
 */
public class Rezervacija {
    private int idRezervacija;
    private Korisnik korisnik;
    private Linija linija;
    private int odobreno;
    private int brKarata;
    private Medjugradskalin medjugradskaLin;

    public int getIdRezervacija() {
        return idRezervacija;
    }

    public void setIdRezervacija(int idRezervacija) {
        this.idRezervacija = idRezervacija;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public Linija getLinija() {
        return linija;
    }

    public void setLinija(Linija linija) {
        this.linija = linija;
    }

    public int getOdobreno() {
        return odobreno;
    }

    public void setOdobreno(int odobreno) {
        this.odobreno = odobreno;
    }

    public int getBrKarata() {
        return brKarata;
    }

    public void setBrKarata(int brKarata) {
        this.brKarata = brKarata;
    }

    public Medjugradskalin getMedjugradskaLin() {
        return medjugradskaLin;
    }

    public void setMedjugradskaLin(Medjugradskalin medjugradskaLin) {
        this.medjugradskaLin = medjugradskaLin;
    }
    
    
    
}
