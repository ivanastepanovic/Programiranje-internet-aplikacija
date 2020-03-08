/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pck;


import entity.Linija;
import entity.Medjugradskalin;
import entity.Prevoznik;
import entity.Stanica;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;

import javax.inject.Named;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Ivana
 */
@Named(value = "gost")
@SessionScoped
public class GostController implements Serializable {

    private Date datumPolaska;
    private String vremeOd, vremeDo;
    private String prevoznik;
    private String mestoPolaska;
    private String mestoDolaska;
    
    public List<String> prevoznici(){
        List<String> prevoznici = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query q = session.createQuery("select p.naziv from Prevoznik p");
        prevoznici = q.list();
        session.getTransaction().commit();
        session.close();
        prevoznici.add(0, " ");
        return prevoznici;
    }
    
    //**************************************************************************
    //Pretraga linija
    
    public List<Medjugradska> popuniPretraga() {
    
        List<Medjugradska> l= new ArrayList<Medjugradska>();
 
        List<Medjugradskalin> medjugradske= null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query q;
        
        java.sql.Date d= new java.sql.Date(datumPolaska.getTime());
        String where="";
        if (!(vremeOd.equals(" "))){
            vremeOd= vremeOd.trim();
            Time t= new Time(Integer.parseInt(vremeOd), 0, 0);
            where= where + " and m.vremePolaska>= '" + t + "'";
        }
        if (!(vremeDo.equals(" "))){
            vremeDo= vremeDo.trim();
            Time t= new Time(Integer.parseInt(vremeDo), 0, 0);
            where= where + " and m.vremePolaska<= '" + t + "'";
        }
        
        //da dodam za mesto polaska, dolaska i prevoznike
              
            q = session.createQuery("from Medjugradskalin m where "
                    + " m.datumPolaska= ' " + d +"' " + where +  " order by m.datumPolaska");
        medjugradske = q.list();
        session.getTransaction().commit();
        session.close();
        
        //da ne prikazuje vise od 10 najskorijih polazaka
        int i=0;
        for (Medjugradskalin li : medjugradske){
            if (i>9) medjugradske.remove(i);
            else i++;  
        }
                
        List<Prevoznik> prevoznici = null;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        q = session.createQuery("from Prevoznik");
        prevoznici = q.list();
        session.getTransaction().commit();
        session.close();
              
        List<Stanica> stanice = null;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        q = session.createQuery("from Stanica");
        stanice = q.list();
        session.getTransaction().commit();
        session.close();
      
        for (Medjugradskalin m : medjugradske) {
            Medjugradska n= new Medjugradska();
            n.setDatumPolaska(m.getDatumPolaska());
            n.setVremePolaska(m.getVremePolaska());
              
            int idLinija= m.getIdLinija();
                
            List<Linija> linije = null;
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            q = session.createQuery("from Linija");
            linije = q.list();
            session.getTransaction().commit();
          
             boolean b_mestoPolaska= false;
                if (mestoPolaska.isEmpty() || mestoPolaska.equals(" ")) b_mestoPolaska=true;
             boolean b_mestoDolaska=false;
                if (mestoDolaska.isEmpty() || mestoDolaska.equals(" ")) b_mestoDolaska=true;
            
            for (Linija linija : linije){
                if (idLinija== linija.getIdLinija()) {
                    String st="";
                    for (Stanica s : linija.getStanicaCollection()){
                        st= st + s.getNazivStan() + "; ";
                    }
                n.setStanice(st);
                if (b_mestoPolaska== false)
                    if (st.contains(mestoPolaska)) b_mestoPolaska=true;
                
                if (b_mestoDolaska== false)
                    if (st.contains(mestoDolaska)) b_mestoDolaska=true;
                
                break;
                }
            }
            session.close();
            
           
         
            
            
            int id = m.getIdPrevoznik();
            for (Prevoznik p : prevoznici) {
                int p_id= p.getIdPrevoznik();
                if (p_id == id) {
                    n.setNazivPrevoznika(p.getNaziv());
                    if (prevoznik.isEmpty() || prevoznik.equals(" ") || prevoznik.equals(p.getNaziv()) )
                       if (b_mestoPolaska && b_mestoDolaska) l.add(n);
                    break;
                }
            }
            
        }
          
     return l;
    }

    
    //**************************************************************************

    
    
    //**************************************************************************
    //**************************************************************************
    
  
     public List<Medjugradska> popuniNajskorije(){
         
        List<Medjugradska> l= new ArrayList<Medjugradska>();
 
        List<Medjugradskalin> medjugradske= null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query q = session.createQuery("from Medjugradskalin m order by m.datumPolaska");
        medjugradske = q.list();
        session.getTransaction().commit();
        session.close();
        
        //da ne prikazuje vise od 10 najskorijih polazaka
        int i=0;
        for (Medjugradskalin li : medjugradske){
            if (i>9) medjugradske.remove(i);
            else i++;  
        }
        
        
        List<Prevoznik> prevoznici = null;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        q = session.createQuery("from Prevoznik");
        prevoznici = q.list();
        session.getTransaction().commit();
        session.close();
        
       
        List<Stanica> stanice = null;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        q = session.createQuery("from Stanica");
        stanice = q.list();
        session.getTransaction().commit();
        session.close();
        
        
        
        for (Medjugradskalin m : medjugradske) {
            Medjugradska n= new Medjugradska();
            n.setDatumPolaska(m.getDatumPolaska());
            n.setVremePolaska(m.getVremePolaska());
              
            int idLinija= m.getIdLinija();
                    
                    
            List<Linija> linije = null;
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            q = session.createQuery("from Linija");
            linije = q.list();
            session.getTransaction().commit();
        
            for (Linija linija : linije){
                if (idLinija== linija.getIdLinija()) {
                    String st="";
                    for (Stanica s : linija.getStanicaCollection()){
                        st= st + s.getNazivStan() + "; ";
                    }
                n.setStanice(st);
                break;
                }
            }
            session.close();
            
                    
            int id = m.getIdPrevoznik();
            for (Prevoznik p : prevoznici) {
                int p_id= p.getIdPrevoznik();
                if (p_id == id) {
                    n.setNazivPrevoznika(p.getNaziv());
                    l.add(n);
                    break;
                }
            }
            
        }
          
     return l;
     }       
    
    
    
    //**************************************************************************
    //**************************************************************************
    


    public String[] getSatnica() {
        String[] sati = new String[16];
        sati[0]= " ";
        for (int i = 1; i < 16; i++) {
            int k= i+6;
            sati[i] =" "+ k;
        }
        return sati;
    }

    private Connection con;
    private Statement st;

    public List<String> getStajalista() {
        List<String> l= new ArrayList<String>();
        
        List<Stanica> stanice = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query q = session.createQuery("from Stanica");
        stanice = q.list();
        session.getTransaction().commit();
        
        for (Stanica s : stanice) {
            l.add(s.getNazivStan());
        }
        
        l.add(0, " ");
        
        return l;
    }

    public Date getDanas() {
        return new Date();
    }

    public String getVremeOd() {
        return vremeOd;
    }

    public void setVremeOd(String vremeOd) {
        this.vremeOd = vremeOd;
    }

    public String getVremeDo() {
        return vremeDo;
    }

    public void setVremeDo(String vremeDo) {
        this.vremeDo = vremeDo;
    }

    public Date getDatumPolaska() {
        return datumPolaska;
    }

    public void setDatumPolaska(Date datumPolaska) {
        this.datumPolaska = datumPolaska;
    }

    public String getPrevoznik() {
        return prevoznik;
    }

    public void setPrevoznik(String prevoznik) {
        this.prevoznik = prevoznik;
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
    
    

}
