/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pck;

import entity.Korisnik;
import entity.Medjugradskalin;
import entity.Mesecnagodisnja;
import entity.Mesecnakarta;

import entity.Rezervacijakarata;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Ivana
 */
@Named(value = "adminZah")
@SessionScoped

public class adminZahteviController implements Serializable {
    private Rezervacija selectedRezervacija;
    private Korisnik selectedKorisnik;
    private MesGod selectedKupovina;

    public MesGod getSelectedKupovina() {
        return selectedKupovina;
    }

    public void setSelectedKupovina(MesGod selectedKupovina) {
        this.selectedKupovina = selectedKupovina;
    }
    
    public void prihvatiKupovinu(){
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Query q= s.createQuery("from Mesecnakarta where idKorisnik=" + selectedKupovina.getIdKorisnik() +
                " and cena=" + selectedKupovina.getCena());
        Mesecnakarta mes= (Mesecnakarta) q.list().get(0);
        s.getTransaction().commit();
        mes.setOdobreno((short)1);
        
        s.beginTransaction();
        s.update(mes);
        s.getTransaction().commit();
        s.close();
        showGrowl("Kupovina karte je prihvaćena!", " ");

    }
    
    public void odbijKupovinu(){
        
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Query q= s.createQuery("from Mesecnakarta where idKorisnik=" + selectedKupovina.getIdKorisnik() +
                " and cena=" + selectedKupovina.getCena());
        Mesecnakarta mes= (Mesecnakarta) q.list().get(0);
        s.getTransaction().commit();
        mes.setOdobreno((short)2);
        
        s.beginTransaction();
        s.update(mes);
        s.getTransaction().commit();
        s.close();
        showGrowl("Kupovina karte je odbijena!", " ");

    }
    
    public void prihvatiKorisnika(){
        selectedKorisnik.setPrihvacen((short)1);
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.update(selectedKorisnik);
        s.getTransaction().commit();
        s.close();
        showGrowl("Korisnik je prihvaćen", " ");
    }
    
    public void odbijKorisnika(){
        selectedKorisnik.setPrihvacen((short)2);
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.update(selectedKorisnik);
        s.getTransaction().commit();
        s.close();
        showGrowl("Korisnik je odbijen", " ");
    }
    
    public List<Korisnik> korisnici(){
        List<Korisnik> l;
         Session s = HibernateUtil.getSessionFactory().openSession();
         s.beginTransaction();
         Query q = s.createQuery("from Korisnik where prihvacen= 0 ");
         l=q.list();
         s.getTransaction().commit();
         s.close();
         return l;    
    }

    public void showGrowl(String msg1, String msg2) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg1, msg2));
    }

    
    public void prihvatiRezervaciju() {
        Medjugradskalin m = selectedRezervacija.getMedjugradskaLin();
        int slobodno = m.getBrSlobodnihMesta();
        if (slobodno >= selectedRezervacija.getBrKarata()) {
 
            int idRezervacija = selectedRezervacija.getIdRezervacija();
            Session s = HibernateUtil.getSessionFactory().openSession();
            s.beginTransaction();
            Query q = s.createQuery("from Rezervacijakarata where idRezervacijaKarata= " + idRezervacija);
            Rezervacijakarata rez = (Rezervacijakarata) q.list().get(0);
            s.getTransaction().commit();
            rez.setOdobreno((short) 1);
            s.beginTransaction();
            s.update(rez);
            s.getTransaction().commit();
            s.close();
            m.setBrSlobodnihMesta(slobodno- selectedRezervacija.getBrKarata());
            s= HibernateUtil.getSessionFactory().openSession();
            s.beginTransaction();
            s.update(m);
            s.getTransaction().commit();
            s.close();
        }
        showGrowl("Rezervacija je prihvaćena", " ");
    }

    public void odbijRezervaciju() {
        int idRezervacija = selectedRezervacija.getIdRezervacija();
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Query q = s.createQuery("from Rezervacijakarata where idRezervacijaKarata= " + idRezervacija);
        Rezervacijakarata rez = (Rezervacijakarata) q.list().get(0);
        s.getTransaction().commit();

        rez.setOdobreno((short) 2);
        s.beginTransaction();
        s.update(rez);
        s.getTransaction().commit();
        s.close();
        showGrowl("Rezervacija je odbijena", " ");
    }


    public List<Rezervacija> rezervacije() {
        List<Rezervacija> l = new ArrayList<Rezervacija>();

        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Query q = s.createQuery("from Rezervacijakarata where odobreno= 0");
        List<Rezervacijakarata> rezervacije = q.list();
        s.getTransaction().commit();
        s.close();

        for (Rezervacijakarata r : rezervacije) {

            Rezervacija rez = new Rezervacija();
            rez.setBrKarata(r.getBrojKarata());
            rez.setKorisnik(r.getIdKorisnik());
            rez.setLinija(r.getIdLinija());
            rez.setIdRezervacija(r.getIdRezervacijaKarata());

            s = HibernateUtil.getSessionFactory().openSession();
            s.beginTransaction();
            q = s.createQuery("from Medjugradskalin where idLinija= " + r.getIdLinija().getIdLinija());
            Medjugradskalin m = (Medjugradskalin) q.list().get(0);
            s.getTransaction().commit();
            s.close();
            rez.setMedjugradskaLin(m);
            l.add(rez);
        }

        return l;
    }


    public Rezervacija getSelectedRezervacija() {
        return selectedRezervacija;
    }

    public void setSelectedRezervacija(Rezervacija selectedRezervacija) {
        this.selectedRezervacija = selectedRezervacija;
    }

    public Korisnik getSelectedKorisnik() {
        return selectedKorisnik;
    }

    public void setSelectedKorisnik(Korisnik selectedKorisnik) {
        this.selectedKorisnik = selectedKorisnik;
    }
    
    public List<MesGod> mesecne(){
       List<MesGod> l= new ArrayList<MesGod>();
     
       Session s = HibernateUtil.getSessionFactory().openSession();
       s.beginTransaction();
       Query q = s.createQuery("from Mesecnakarta where odobreno= 0" );
       List<Mesecnakarta> zahtevi = q.list();
       s.getTransaction().commit();
       s.close();
       
       for (Mesecnakarta kartica: zahtevi){
           MesGod mg= new MesGod();
           mg.setIdKorisnik(kartica.getIdKorisnik().getIdKorisnik());
           mg.setTip(kartica.getTip());
           mg.setCena(kartica.getCena());
           l.add(mg);
       }
       return l;
    }


    
}
