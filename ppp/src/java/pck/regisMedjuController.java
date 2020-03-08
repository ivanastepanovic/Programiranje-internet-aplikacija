/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pck;

import entity.Autobus;
import entity.Korisnik;
import entity.Linija;
import entity.Medjugradskalin;
import entity.Prevoznik;
import entity.Rezervacijakarata;
import entity.Slike;
import entity.Stanica;
import entity.Vozac;
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
@Named(value = "regisMedj")
@SessionScoped

public class regisMedjuController implements Serializable {

    //private List<Medjugradska> medjugradske;
    private List<Medjugradska> filteredMedjugradske;
    private Medjugradska selectedMedjugradska;
    private String stanicaZaPretragu;

    private int brojKarata;
    private String karteMessage = "";

    public void showGrowl() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Uspešno ste podneli zahtev za rezervaciju!",
                "Više detalja možete videti u odeljku \"karte\""));
        rezervisi();
    }

    @Inject
    KorisnikController korisnik;

    public void rezervisi() {

        List<Korisnik> korisnici = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query q = session.createQuery("from Korisnik where username= '" + korisnik.getUsername() + "'");
        korisnici = q.list();
        session.getTransaction().commit();
        session.close();

        List<Linija> linije = null;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        q = session.createQuery("from Linija where idLinija= " + selectedMedjugradska.getIdLinija());
        linije = q.list();
        session.getTransaction().commit();
        session.close();

        Rezervacijakarata r = new Rezervacijakarata();
        r.setIdKorisnik(korisnici.get(0));
        r.setIdLinija(linije.get(0));
        r.setBrojKarata(brojKarata);
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        int i = (int) s.save(r);
        s.getTransaction().commit();
        s.close();
        karteMessage = "Zahtev za rezervacijom karata je uspešno poslat! \n Stanje rezervacije možete videti u \"karte\" sekciji.";

    }

    public List<Slike> getImages() {

        List<Slike> slike = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query q = session.createQuery("from Slike where idAutobus=" + selectedMedjugradska.getIdAutobus());
        slike = q.list();
        session.getTransaction().commit();
        session.close();

        return slike;
    }

    public List<Medjugradska> getMedjugradske() {
        Date danas= new Date();

        List<Medjugradska> l = new ArrayList<Medjugradska>();

        List<Stanica> stanice = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query q = session.createQuery("from Stanica");
        stanice = q.list();
        session.getTransaction().commit();
        session.close();

        List<Prevoznik> prevoznici = null;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        q = session.createQuery("from Prevoznik");
        prevoznici = q.list();
        session.getTransaction().commit();
        session.close();

        List<Autobus> autobusi = null;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        q = session.createQuery("from Autobus");
        autobusi = q.list();
        session.getTransaction().commit();
        session.close();

        String nazivPocetne = " ", nazivKrajnje = " ";
        List<Linija> linije = null;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        q = session.createQuery("from Linija");
        linije = q.list();
        session.getTransaction().commit();

        List<Medjugradskalin> medjugradske = null;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        q = session.createQuery("from Medjugradskalin");
        medjugradske = q.list();
        session.getTransaction().commit();

        for (Medjugradskalin m : medjugradske) {
            Medjugradska x = new Medjugradska();
            x.setDatumPolaska(m.getDatumPolaska());
            x.setVremePolaska(m.getVremePolaska());
            x.setIdLinija(m.getIdLinija());

            for (Prevoznik p : prevoznici) {
                if (m.getIdPrevoznik() == p.getIdPrevoznik()) {
                    x.setNazivPrevoznika(p.getNaziv());
                    break;
                }
            }

            for (Autobus a : autobusi) {
                if (m.getIdAutobus() == a.getIdAutobus()) {
                    x.setIdAutobus(m.getIdAutobus());

                    x.setAutobusBrojSedista(a.getBrSedista());
                    x.setAutobusMarka(a.getMarka());
                    x.setAutobusModel(a.getModel());

                    break;
                }
            }

            for (Linija lin : linije) {
                if (lin.getIdLinija() == m.getIdLinija()) {
                    nazivPocetne = lin.getPolazakStanica().getNazivStan();
                    nazivKrajnje = lin.getDolazakStanica().getNazivStan();

                    String vozaci = "";
                    for (Vozac v : lin.getVozacCollection()) {
                        vozaci = vozaci + v.getIme() + " " + v.getPrezime() + "; ";
                    }
                    x.setVozaci(vozaci);

                    String stan = "";
                    for (Stanica s : lin.getStanicaCollection()) {
                        stan = stan + s.getNazivStan() + "; ";
                    }
                    x.setStanice(stan);

                    for (Stanica s : lin.getStanicaCollection()) {
                        if (stanicaZaPretragu == null || stanicaZaPretragu.isEmpty() || s.getNazivStan().equals(stanicaZaPretragu)) {
                            x.setMestoPolaska(nazivPocetne);
                            x.setMestoDolaska(nazivKrajnje);
                            
                            if (danas.before(x.getDatumPolaska())) l.add(x); // da se ne bi prikazivale prosle
                            break; 
                        }
                    }

                    break;
                }
            }

        }
        if (session != null) {
            session.close();
        }

        return l;
    }

    public void setStanicaZaPretragu(String naziv) {
        stanicaZaPretragu = naziv;
    }

    public List<Medjugradska> getFilteredMedjugradske() {
        return filteredMedjugradske;
    }

    public void setFilteredMedjugradske(List<Medjugradska> filteredMedjugradske) {
        this.filteredMedjugradske = filteredMedjugradske;
    }

    public Medjugradska getSelectedMedjugradska() {
        return selectedMedjugradska;
    }

    public void setSelectedMedjugradska(Medjugradska selectedMedjugradska) {
        this.selectedMedjugradska = selectedMedjugradska;
    }

    public String getStanicaZaPretragu() {
        return stanicaZaPretragu;
    }

    public int getBrojKarata() {
        return brojKarata;
    }

    public void setBrojKarata(int brojKarata) {
        this.brojKarata = brojKarata;
    }

    public String getKarteMessage() {
        return karteMessage;
    }

}
