/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pck;

import entity.Gradskalin;
import entity.Linija;
import entity.Otkazane;
import entity.Stanica;
import entity.Vozac;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.ServletContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Ivana
 */
@Named(value = "adminGrad")
@SessionScoped

public class adminGradController implements Serializable {

    private String ime;
    private String prezime;
    private Date datumRodjenja;
    private int godinaPocetka;

    private int brojLinije;
    private int polaziste;
    private int odrediste;
    private List<String> medjustanice;
    private List<Integer> vozaci;
    private String redVoznje=""; //ili vec kako, ako treba da ga uploadujem u projekat

    private int gradskaOtkaz;
    private Date datumOd, datumDo;
    private String otkazaneLinije;
    
    
    private UploadedFile file;
    
    public static String dohvatiPutanjuPdf() {
        try {
            ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String pathString = ctx.getRealPath("/");
            Path path = Paths.get(pathString);
            Path path2 = path.getParent().getParent();
            pathString = path2.toString();
            pathString = pathString + "\\web\\redoviVoznje";
            return pathString;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }

    public void copyFile(String fileName, InputStream in) {
        try {
            // write the inputStream to a FileOutputStream
            // File f= new File("C:\\Users\\Ivana\\Documents\\NetBeansProjects\\ppp\\web\\busImages\\" +fileName);
            File f = new File(dohvatiPutanjuPdf() + "\\" + fileName);
            redVoznje=fileName;
            OutputStream out = new FileOutputStream(f);
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            in.close();
            out.flush();
            out.close();

            System.out.println("New file created!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    public void upload(FileUploadEvent event) {
        if (file != null) {
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        try {
            copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    
    
    public Date danas() {
        return new Date();
    }

    public void showGrowl3() {
        otkazi();
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Uspešno ste otkazali liniju!", " "));
    }

    public void otkazi() {
        Otkazane o = new Otkazane();
        o.setDatumOd(datumOd);
        o.setDatumDo(datumDo);
  
        Session se= HibernateUtil.getSessionFactory().openSession();
        se.beginTransaction();
        Query q= se.createQuery(" from Linija where idLinija=" + gradskaOtkaz);
        Linija lin= (Linija) q.list().get(0);
        o.setIdLinija(lin);
        
        se.getTransaction().commit();
        se.close();

        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.save(o);
        s.getTransaction().commit();
        s.close();
    }

    public List<SelectItem> gradskeZaOtkazivanje() {
        List<Gradskalin> g;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query q = session.createQuery("from Gradskalin");
        g = q.list();
        session.getTransaction().commit();
        session.close();

        List<SelectItem> l = new ArrayList<SelectItem>();
        for (Gradskalin gr : g) {
            int value = gr.getIdLinija();
            String label = Integer.toString(gr.getBrojLinije());
            SelectItem i = new SelectItem(gr.getIdLinija(), label);
            l.add(i);
        }
        return l;
    }

    public void showGrowl2() {
        unesiLiniju();
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Uspešno ste uneli liniju u bazu",
                " "));
    }

    public void unesiLiniju() {
        //unesemo liniju
        Linija linija = new Linija();
        List<Stanica> stanice;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query q = session.createQuery("from Stanica");
        stanice = q.list();
        session.getTransaction().commit();
        session.close();
        for (Stanica s : stanice) {
            if (s.getIdStanica() == polaziste) {
                linija.setPolazakStanica(s);
                break;
            }
        }
        for (Stanica s : stanice) {
            if (s.getIdStanica() == odrediste) {
                linija.setDolazakStanica(s);
                break;
            }
        }

        String maxId = "SELECT MAX(idLinija) FROM Linija";
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        q = session.createQuery(maxId);
        int id = (int) (q.list().get(0)) + 1;
        session.getTransaction().commit();
        session.close();
        linija.setIdLinija(id);

        //Unesemo medjustanice, ali za njih nemam tabelu nego moram iz linije nekako ono collection
        //vazno je dodati i polaziste i odrediste u medjustanice
        List<Stanica> stanicaCollection = new ArrayList<Stanica>();
        medjustanice.add(Integer.toString(polaziste));
        medjustanice.add(Integer.toString(odrediste));
        while (medjustanice.size()!=0) {

            String medj = medjustanice.get(0);
            medjustanice.remove(0);
            int m = Integer.parseInt(medj);
            for (Stanica st : stanice) {
                if (st.getIdStanica() == m) {
                    stanicaCollection.add(st);
                    break;
                }
            }
        }
        linija.setStanicaCollection(stanicaCollection);

        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.save(linija);
        s.getTransaction().commit();
        s.close();

        //Unesemo gradsku liniju
        Gradskalin gradska = new Gradskalin();
        gradska.setBrojLinije(brojLinije);
        gradska.setRedVoznje("redoviVoznje/" + redVoznje);
        gradska.setIdLinija(id);
        s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.save(gradska);
        s.getTransaction().commit();
        s.close();

    }

    public List<SelectItem> spisakVozaca() {
        List<SelectItem> l = new ArrayList<SelectItem>();

        List<Vozac> sviVozaci;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query q = session.createQuery("from Vozac");
        sviVozaci = q.list();
        session.getTransaction().commit();
        session.close();

        for (Vozac v : sviVozaci) {
            int value = v.getIdVozac();
            String label = v.getIme() + " " + v.getPrezime();
            SelectItem i = new SelectItem(value, label);
            l.add(i);
        }
        return l;
    }

    public List<SelectItem> spisakStanica() {
        List<SelectItem> l = new ArrayList<SelectItem>();

        List<Stanica> sveStanice;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query q = session.createQuery("from Stanica");
        sveStanice = q.list();
        session.getTransaction().commit();
        session.close();

        List<Linija> linijeKojeSuMedjugradske;

        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        q = session.createQuery("SELECT l \n"
                + "FROM Linija as l, Medjugradskalin as m\n"
                + "WHERE l.idLinija= m.idLinija");
        linijeKojeSuMedjugradske = q.list();
        session.getTransaction().commit();

        boolean flag = true;

        for (Stanica s : sveStanice) {
            for (Linija lin : linijeKojeSuMedjugradske) {
                for (Stanica stan : lin.getStanicaCollection()) {
                    if (stan.getIdStanica() == s.getIdStanica()) {
                        flag = false;
                        break;
                    }
                }
            }
            if (flag) {
                int value = s.getIdStanica();
                String label = s.getNazivStan();
                SelectItem i = new SelectItem(value, label);
                l.add(i);
            } else {
                flag = true;
            }
        }
        session.close();
        return l;
    }

    public void showGrowl() {
        unesiVozaca();
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Uspešno ste uneli vozača u bazu",
                " "));
    }

    public void unesiVozaca() {
        Vozac vozac = new Vozac();
        vozac.setIme(ime);
        vozac.setPrezime(prezime);
        vozac.setDatumRodjenja(datumRodjenja);
        vozac.setGodinaPocetka(godinaPocetka);

        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.save(vozac);
        s.getTransaction().commit();
        s.close();

    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public int getGodinaPocetka() {
        return godinaPocetka;
    }

    public void setGodinaPocetka(int godinaPocetka) {
        this.godinaPocetka = godinaPocetka;
    }

    public int getBrojLinije() {
        return brojLinije;
    }

    public void setBrojLinije(int brojLinije) {
        this.brojLinije = brojLinije;
    }

    public int getPolaziste() {
        return polaziste;
    }

    public void setPolaziste(int polaziste) {
        this.polaziste = polaziste;
    }

    public List<String> getMedjustanice() {
        return medjustanice;
    }

    public void setMedjustanice(List<String> medjustanice) {
        this.medjustanice = medjustanice;
    }

    public List<Integer> getVozaci() {
        return vozaci;
    }

    public void setVozaci(List<Integer> vozaci) {
        this.vozaci = vozaci;
    }

    public String getRedVoznje() {
        return redVoznje;
    }

    public void setRedVoznje(String redVoznje) {
        this.redVoznje = redVoznje;
    }

    public int getOdrediste() {
        return odrediste;
    }

    public void setOdrediste(int odrediste) {
        this.odrediste = odrediste;
    }

    public int getGradskaOtkaz() {
        return gradskaOtkaz;
    }

    public void setGradskaOtkaz(int gradskaOtkaz) {
        this.gradskaOtkaz = gradskaOtkaz;
    }

    public Date getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(Date datumOd) {
        this.datumOd = datumOd;
    }

    public Date getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(Date datumDo) {
        this.datumDo = datumDo;
    }

}
