/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pck;

import entity.Gradskalin;
import entity.Linija;
import entity.Stanica;
import entity.Vozac;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Ivana
 */
@Named(value = "regGrad")
@SessionScoped

public class regisGradController implements Serializable {

    private String pocetnoStajaliste;
    private String krajnjeStajaliste;
    private int brojLinije;
    private String stanicaZaPretragu; //unosi korisnik

    private Gradska selectedGradska;

    private List<Gradska> gradske;
    private List<Gradska> filteredGradske;

    public List<Gradska> sveGradske() {

        List<Gradska> l = new ArrayList<Gradska>();

        List<Gradskalin> gradske = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query q = session.createQuery("from Gradskalin");
        gradske = q.list();
        session.getTransaction().commit();
        session.close();

        List<Stanica> stanice = null;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        q = session.createQuery("from Stanica");
        stanice = q.list();
        session.getTransaction().commit();
        session.close();

        for (Gradskalin g : gradske) {
            Gradska x = new Gradska();
            x.setBrojLinije(g.getBrojLinije());
            x.setRedVoznje(g.getRedVoznje());

            String nazivPocetne = " ", nazivKrajnje = " ";

            List<Linija> linije = null;
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            q = session.createQuery("from Linija");
            linije = q.list();
            session.getTransaction().commit();

            for (Linija lin : linije) {
                if (lin.getIdLinija() == g.getIdLinija()) {
                    nazivPocetne = lin.getPolazakStanica().getNazivStan();
                    nazivKrajnje = lin.getDolazakStanica().getNazivStan();
                    
                    x.setPocetnoStajaliste(nazivPocetne);
                    x.setKrajnjeStajaliste(nazivKrajnje);

                    String vozaci = "";
                    for (Vozac v : lin.getVozacCollection()) {
                        vozaci = vozaci + v.getIme() + " " + v.getPrezime() + "; ";
                    }
                    x.setVozaci(vozaci);

                    break;
                }
            }
            
            l.add(x);
            session.close();
        }
        return l;
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

    public Gradska getSelectedGradska() {
        return selectedGradska;
    }

    public void setSelectedGradska(Gradska selectedGradska) {
        this.selectedGradska = selectedGradska;
    }

    public List<Gradska> getFilteredGradske() {
        return filteredGradske;
    }

    public void setFilteredGradske(List<Gradska> filteredGradske) {
        this.filteredGradske = filteredGradske;
    }

    public String getStanicaZaPretragu() {
        return stanicaZaPretragu;
    }

    public void setStanicaZaPretragu(String stanicaZaPretragu) {
        this.stanicaZaPretragu = stanicaZaPretragu;
    }

}
