/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pck;

import entity.Korisnik;
import entity.Mesecnagodisnja;
import entity.Mesecnakarta;
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
@Named (value="regisMesecne")
@SessionScoped
public class regisMesecneController implements Serializable {
    @Inject
    KorisnikController kor;
    
    private MesGod selectedMG;
    private boolean zahtevM= false;
    private boolean zahtevG=false;
    private int id;
    String tip="";
    private String msg1=" ", msg2= " ";
    
    
    private boolean zahtevPoslat= false;
   

    public boolean isZahtevPoslat() {
        return zahtevPoslat;
    }

    public void setZahtevPoslat(boolean zahtevPoslat) {
        this.zahtevPoslat = zahtevPoslat;
    }
    
    public String status(){
        
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Query q= s.createQuery("from Korisnik where username= '" + kor.getUsername() + "'");
        Korisnik k=  (Korisnik) q.list().get(0);        
        s.getTransaction().commit();
        s.close();
        
        String status=" ";
        s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        q= s.createQuery("from Mesecnakarta where idKorisnik= " + k.getIdKorisnik());
        List<Mesecnakarta> l= q.list();        
        s.getTransaction().commit();
        s.close();
        for (Mesecnakarta kartica: l){
            if (kartica.getTip().equals("mesečna")){
                String str;
                int odobreno= kartica.getOdobreno();
                if (odobreno==0) str="na čekanju";
                else if (odobreno==1) str= "ODOBRENO";
                else str="odbijeno";
                status= status+ "Status zahteva za kupovinu mesečne karte za " + (kartica.getDatumKupovine().getMonth()+1) + 
                        ". mesec: " + str + "\n";
            }
            if (kartica.getTip().equals("godišnja")){
                String str;
                int odobreno= kartica.getOdobreno();
                if (odobreno==0) str="na čekanju";
                else if (odobreno==1) str= "ODOBRENO";
                else str="odbijeno";
                status= status + "Status zahteva za kupovinu godišnje karte za " + (kartica.getDatumKupovine().getYear()+1900) + 
                        ". godinu: " + str + "\n";
            }
        }
        return status;
    }
      
    public void showGrowl() {       
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg1, msg1));
    }
      
    
    public void kupi(){
        tip= selectedMG.getTip();
        int idKorisnik= selectedMG.getIdKorisnik();
        int cena= selectedMG.getCena();
        Date datum= new Date();
        id= idKorisnik;
        
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Query q= s.createQuery("from Korisnik where idKorisnik= " + selectedMG.getIdKorisnik());
        Korisnik kor= (Korisnik) q.list().get(0);        
        s.getTransaction().commit();
        s.close();
        
        s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        q= s.createQuery("from Mesecnakarta where idKorisnik= " + selectedMG.getIdKorisnik());
        List<Mesecnakarta> lista= q.list();       
        s.getTransaction().commit();
        s.close();
        
        for (Mesecnakarta mes: lista){
          
            if (tip.equals("mesečna") && (mes.getTip().equals("godišnja")) && (mes.getDatumKupovine().getMonth()== new Date().getMonth())){
                msg1="Neuspešna kupovina!";
                msg2="Već ste podneli zahtev za kupovinu mesečne karte za ovaj mesec.";
                showGrowl();
                return;
            }
            if (tip.equals("godišnja") && (mes.getTip().equals("godišnja")) && (mes.getDatumKupovine().getYear()== new Date().getYear()) ){
                msg1="Neuspešna kupovina!";
                msg2="Već ste podneli zahtev za kupovinu karte za ovu godinu.";
                showGrowl();
                return;
            }
        }
        
        
        
        Mesecnakarta m= new Mesecnakarta();
        m.setCena(cena);
        m.setDatumKupovine(datum);
        m.setIdKorisnik(kor);
        m.setOdobreno((short)0);
        m.setTip(tip);
        
        s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.save(m);
        s.getTransaction().commit();
        s.close();
        
        setZahtevPoslat(true);
        msg1="Uspešno ste podneli zahtev za kupovinom!";
        msg2="Potrebno je da sačekate odobrenje.";
        showGrowl();
    }
    
    public List<MesGod> mesecne(){
       List<MesGod> l= new ArrayList<MesGod>();
       MesGod mg1= new MesGod(), mg2= new MesGod();
     
       Session s = HibernateUtil.getSessionFactory().openSession();
       s.beginTransaction();
       Query q = s.createQuery("from Korisnik where username= '" + kor.getUsername() + "'" );
       Korisnik korisnik = (Korisnik) q.list().get(0);
       s.getTransaction().commit();
       s.close();
       mg1.setIdKorisnik(korisnik.getIdKorisnik());
       mg2.setIdKorisnik(korisnik.getIdKorisnik());
       mg1.setTip ("mesečna");
       mg2.setTip("godišnja");
      
       String zaposlenje=korisnik.getZaposlenje();
       
       s = HibernateUtil.getSessionFactory().openSession();
       s.beginTransaction();
       q = s.createQuery("from Mesecnagodisnja where zaposlenje= '" + zaposlenje + "'" );
       Mesecnagodisnja m = (Mesecnagodisnja) q.list().get(0);
       s.getTransaction().commit();
       s.close();
       
       mg1.setCena(m.getCenaMesecne());
       mg2.setCena(m.getCenaGodisnje());    
       mg1.setIdTip(m.getIdMesGod());
       mg2.setIdTip(m.getIdMesGod());
       
      
          
       l.add(mg1); l.add(mg2);
       return l;
    }

    public MesGod getSelectedMG() {
        return selectedMG;
    }

    public void setSelectedMG(MesGod selectedMG) {
        this.selectedMG = selectedMG;
    }
    
    
}
