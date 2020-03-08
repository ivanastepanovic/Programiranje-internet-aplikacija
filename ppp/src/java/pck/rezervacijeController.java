/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pck;

import entity.Korisnik;
import entity.Linija;
import entity.Medjugradskalin;
import entity.Prevoznik;
import entity.Rezervacijakarata;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
@Named(value = "rez")
@SessionScoped

public class rezervacijeController implements Serializable {

    private int idKorisnik;
    private int idLinija;

    private String msg1 = "", msg2 = "";

    Medjugradska selectedRez;
    

    public void showGrowl() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg1, msg2));
    }

    @Inject
    KorisnikController korisnik;

    public void otkazi() {
        Date datumPolaska = selectedRez.getDatumPolaska();
        Date danas = new Date();
        Date vremePolaska = selectedRez.getVremePolaska();
        danas.setHours(danas.getHours() - 2);
        if (datumPolaska.before(danas)
                || (datumPolaska.equals(danas) && vremePolaska.getHours() > (danas.getHours()))) {
            msg1 = "Nije moguće otkazati rezervaciju!";
            msg1 = "Otkazivanje nije dozvoljeno sat vremena pred polazak.";
        } else {
            msg1 = "Uspešno ste otkazali rezervaciju.";

            Rezervacijakarata rezervacija = null;

            List<Rezervacijakarata> rezervacije = null;
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query q = session.createQuery("from Rezervacijakarata where idKorisnik= " + idKorisnik + "and idLinija= "
                    + selectedRez.getIdLinija());
            rezervacije = q.list();
            session.getTransaction().commit();
            session.close();
            rezervacija = rezervacije.get(0);

            if (rezervacija.getOdobreno()==1) {
                //uvecam broj slobodnih sedista za taj broj ukoliko je bila odobrena
                int b= rezervacija.getBrojKarata();
                Session s = HibernateUtil.getSessionFactory().openSession();
                s.beginTransaction();
                q= s.createQuery("from Medjugradskalin where idLinija=" + rezervacija.getIdLinija());
                Medjugradskalin medju= (Medjugradskalin) q.list().get(0);
                s.getTransaction().commit();
                medju.setBrSlobodnihMesta(medju.getBrSlobodnihMesta()+ b);
                s.update(medju);
                s.getTransaction().commit();
                
                s.getTransaction().commit();
                s.close();

                
            }

            Session s = HibernateUtil.getSessionFactory().openSession();
            s.beginTransaction();
            s.delete(rezervacija);
            s.getTransaction().commit();
            s.close();

        }
        showGrowl();
    }

    public List<Medjugradska> rezervacije() {
        int admin= korisnik.getUsername().equals("admin") ? 1: 0;
        List<Medjugradska> l = new ArrayList<Medjugradska>();

        List<Korisnik> korisnici = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        Query q = session.createQuery("from Korisnik where username= '" + korisnik.getUsername() + "'");
        korisnici = q.list();
        session.getTransaction().commit();
        session.close();
        idKorisnik = korisnici.get(0).getIdKorisnik();

        List<Rezervacijakarata> rezervacije = null;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String userQuery="from Rezervacijakarata where idKorisnik=" + idKorisnik;
        q= session.createQuery(userQuery);
        rezervacije = q.list();
        session.getTransaction().commit();
        session.close();

        List<Linija> linije = null;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        q = session.createQuery("from Linija");
        linije = q.list();
        session.getTransaction().commit();
        session.close();

        List<Medjugradskalin> medjugradske = null;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        q = session.createQuery("from Medjugradskalin");
        medjugradske = q.list();
        session.getTransaction().commit();
        session.close();

        List<Prevoznik> prevoznici = null;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        q = session.createQuery("from Prevoznik");
        prevoznici = q.list();
        session.getTransaction().commit();
        session.close();

        for (Rezervacijakarata r : rezervacije) {
            Medjugradska x = new Medjugradska();
            x.setBrojKarata(r.getBrojKarata());
            String odobreno = (r.getOdobreno()==1) ? "odobreno" : "na čekanju";
            x.setOdobreno(odobreno);

            for (Medjugradskalin m : medjugradske) {

                if (r.getIdLinija().getIdLinija() == m.getIdLinija()) {
                    x.setDatumPolaska(m.getDatumPolaska());
                    x.setVremePolaska(m.getVremePolaska());

                    m.getIdPrevoznik();
                    for (Prevoznik p : prevoznici) {
                        if (p.getIdPrevoznik() == m.getIdPrevoznik()) {
                            x.setNazivPrevoznika(p.getNaziv());
                            break;
                        }
                    }
                    break;
                }

            }

            for (Linija lin : linije) {

                if (lin.getIdLinija() == r.getIdLinija().getIdLinija()) { //OBJEKAT Linija su 
                    x.setMestoPolaska(lin.getPolazakStanica().getNazivStan());
                    x.setMestoDolaska(lin.getDolazakStanica().getNazivStan());
                    l.add(x);

                    break;
                }
            }
        }

        return l;
    }

    public Medjugradska getSelectedRez() {
        return selectedRez;
    }

    public void setSelectedRez(Medjugradska selectedRez) {
        this.selectedRez = selectedRez;
    }

}
