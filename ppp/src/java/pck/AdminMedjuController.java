/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pck;

import entity.Autobus;
import entity.Linija;
import entity.Medjugradskalin;
import entity.Prevoznik;
import entity.Slike;
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
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
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
@Named(value = "adminMedju")
@SessionScoped

public class AdminMedjuController implements Serializable {

    //za unos novog vozaca
    private String ime;
    private String prezime;
    private Date datumRodjenja;
    private int godinaPocetka;

    //podaci za unos autobusa
    private String marka;
    private String model;
    private int brSedista;

    //podaci za unos prevoznika
    private String naziv;
    private String adresa;
    private String telefon;
    private String logo; //isto kao i za pdf, kako vec treba

    //podaci za unos linije 
    private int polaziste;
    private int odrediste;
    private Date datumPolaska;
    private Date datumDolaska;
    private Date vremePolaska;
    private Date vremeDolaska;
    private int idAutobus;
    private int idPrevoznik;
    private List<String> medjustanice;
    private List<Integer> vozaci;

    private int idAutobusaZaSlike;

    private UploadedFile file;
    private List<String> nazivSlike = new ArrayList<String>();

    public void unesiSliku(int id) {

        while (nazivSlike.size()>0) {
        Slike slika = new Slike();
        slika.setNazivSlike("busImages/" + nazivSlike.get(0));
        nazivSlike.remove(0);
        slika.setIdAutobus(id);
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.save(slika);
        s.getTransaction().commit();
        s.close();
        }
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
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

    public static String dohvatiPutanjuSlike() {
        try {
            ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String pathString = ctx.getRealPath("/");
            Path path = Paths.get(pathString);
            Path path2 = path.getParent().getParent();
            pathString = path2.toString();
            pathString = pathString + "\\web\\busImages";
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
            File f = new File(dohvatiPutanjuSlike() + "\\" + fileName);
            nazivSlike.add(fileName);
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

    public void showGrowl4() {
        unesiVozaca();
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Uspešno ste uneli vozača!", " "));
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

    public List<SelectItem> spisakAutobusa() {
        List<SelectItem> spisak = new ArrayList<SelectItem>();
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Query q = s.createQuery("from Autobus");
        List<Autobus> autobusi = q.list();
        s.getTransaction().commit();
        s.close();

        for (Autobus a : autobusi) {
            String label = "";
            label = label + a.getMarka() + " " + a.getModel() + " (broj sedišta: " + a.getBrSedista() + ")";
            SelectItem i = new SelectItem(a.getIdAutobus(), label);
            spisak.add(i);
        }
        return spisak;
    }

    public List<SelectItem> spisakPrevoznika() {
        List<SelectItem> spisak = new ArrayList<SelectItem>();
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Query q = s.createQuery("from Prevoznik");
        List<Prevoznik> prevoznici = q.list();
        s.getTransaction().commit();
        s.close();

        for (Prevoznik p : prevoznici) {
            String label = "";
            label = label + p.getNaziv();
            SelectItem i = new SelectItem(p.getIdPrevoznik(), label);
            spisak.add(i);
        }
        return spisak;
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

        List<Linija> linijeKojeSuGradske;

        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        q = session.createQuery("SELECT l \n"
                + "FROM Linija as l, Gradskalin as m\n"
                + "WHERE l.idLinija= m.idLinija");
        linijeKojeSuGradske = q.list();
        session.getTransaction().commit();

        boolean flag = true;

        for (Stanica s : sveStanice) {
            for (Linija lin : linijeKojeSuGradske) {
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

    public void showGrowl3() {
        unesiAutobus();
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Uspešno ste uneli autobus!", " "));
    }

    public void unesiAutobus() {
        int id;
        Autobus a = new Autobus();
        a.setBrSedista(brSedista);
        a.setMarka(marka);
        a.setModel(model);
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.save(a);
        s.getTransaction().commit();

        s.beginTransaction();
        Query q = s.createQuery("select max(idAutobus) from Autobus");
        id = (int) q.list().get(0);
        s.close();

        unesiSliku(id);
    }

    public void showGrowl2() {
        unesiPrevoznika();
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Uspešno ste dodali prevoznika!", " "));
    }

    public void unesiPrevoznika() {
        Prevoznik p = new Prevoznik();
        p.setNaziv(naziv);
        p.setAdresa(adresa);
        p.setTelefon(telefon);
        //treba da resim za logo ako zeli da unese
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.save(p);
        s.getTransaction().commit();
        s.close();
    }

    public void showGrowl1() {
        unesiLiniju();
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Uspešno ste dodali liniju!", " "));
    }

    public void unesiLiniju() {
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
        while (medjustanice.size() != 0) {

            int m;
            m = Integer.parseInt(medjustanice.get(0));

            medjustanice.remove(0);
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

        int brojSedista;
        s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        q = s.createQuery("select brSedista from Autobus where idAutobus=" + idAutobus);
        brojSedista = (int) q.list().get(0);
        s.getTransaction().commit();
        s.close();

        Medjugradskalin medju = new Medjugradskalin();
        medju.setIdLinija(id);
        medju.setDatumPolaska(datumPolaska);
        medju.setVremePolaska(vremePolaska);
        medju.setDatumDolaska(datumDolaska);
        medju.setVremeDolaska(vremeDolaska);
        medju.setIdAutobus(idAutobus);
        medju.setIdPrevoznik(idPrevoznik);
        medju.setBrSlobodnihMesta(brojSedista);

        s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.save(medju);
        s.getTransaction().commit();
        s.close();

    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getBrSedista() {
        return brSedista;
    }

    public void setBrSedista(int brSedista) {
        this.brSedista = brSedista;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getPolaziste() {
        return polaziste;
    }

    public void setPolaziste(int polaziste) {
        this.polaziste = polaziste;
    }

    public int getOdrediste() {
        return odrediste;
    }

    public void setOdrediste(int odrediste) {
        this.odrediste = odrediste;
    }

    public Date getDatumPolaska() {
        return datumPolaska;
    }

    public void setDatumPolaska(Date datumPolaska) {
        this.datumPolaska = datumPolaska;
    }

    public Date getDatumDolaska() {
        return datumDolaska;
    }

    public void setDatumDolaska(Date datumDolaska) {
        this.datumDolaska = datumDolaska;
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

    public Date getVremePolaska() {
        return vremePolaska;
    }

    public void setVremePolaska(Date vremePolaska) {
        this.vremePolaska = vremePolaska;
    }

    public Date getVremeDolaska() {
        return vremeDolaska;
    }

    public void setVremeDolaska(Date vremeDolaska) {
        this.vremeDolaska = vremeDolaska;
    }

    public int getIdAutobus() {
        return idAutobus;
    }

    public void setIdAutobus(int idAutobus) {
        this.idAutobus = idAutobus;
    }

    public int getIdPrevoznik() {
        return idPrevoznik;
    }

    public void setIdPrevoznik(int idPrevoznik) {
        this.idPrevoznik = idPrevoznik;
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

    public int getIdAutobusaZaSlike() {
        return idAutobusaZaSlike;
    }

    public void setIdAutobusaZaSlike(int idAutobusaZaSlike) {
        this.idAutobusaZaSlike = idAutobusaZaSlike;
    }

}
