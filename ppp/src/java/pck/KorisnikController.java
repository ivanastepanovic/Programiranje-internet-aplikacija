package pck;

import entity.Korisnik;
import java.io.Serializable;
import java.sql.*;

import java.sql.SQLException;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import org.hibernate.Query;
import org.hibernate.Session;
import util.DB;

/**
 *
 * @author Ivana
 */
@SessionScoped
@Named(value = "korisnik")

public class KorisnikController implements Serializable {

    private String username;
    private String password;
    private String potvrdaPassword;

    private String ime;
    private String prezime;
    private String grad;
    private String opstina;
    private String adresa;
    private Date datumRodjenja;
    private String telefon;
    private String zaposlenje;
    private String mail;
    private String msg=" ";

    private Connection connect = DB.getInstance().getConnection();
    
    
    public String logout(){
        FacesContext fc=FacesContext.getCurrentInstance();
        fc.getExternalContext().invalidateSession();
        return "index.xhtml";
    }

    public String login() {
        String ret="index";
        msg=" ";
        
        Session s= HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Query q= s.createQuery("from Korisnik where username= '" + username + "'");
        List<Korisnik> k= q.list();
        s.getTransaction().commit();
        s.close();
        
        if (k.isEmpty()) {
            msg= msg+ "Uneli ste nevalidno korisničko ime!";
        }
        else {
            Korisnik kor= k.get(0);
            if (kor.getPassword().equals(password)) {
                //pogodio ime i sifru
                if (username.equals("admin")) ret="adminGradske.xhtml";
                else {
                    if (kor.getPrihvacen()==1) ret="regisGradske.xhtml";
                else ret="gost.xhtml";
                }
                
            }
            else {
                msg= msg+"Uneli ste pogrešnu šifru!";
            }
        }    
        return ret;
    }

    //treba zatvoriti konekciju, rs, stat...
    public String regKorisnika() {
       Korisnik k= new Korisnik();
       k.setAdresa(adresa);
       k.setDatumRodjenja(datumRodjenja);
       k.setGrad(grad);
       k.setIme(ime);
       k.setMail(mail);
       k.setOpstina(opstina);
       k.setPassword(password);
       k.setPrezime(prezime);
       k.setPrihvacen((short)0);
       k.setTelefon(telefon);
       k.setUsername(username);
       k.setZaposlenje(zaposlenje);
       
       Session s= HibernateUtil.getSessionFactory().openSession();
       s.beginTransaction();
       s.save(k);
       s.getTransaction().commit();
       s.close();

       return "index.xhtml";
    }

    public void proveraUsername(FacesContext context, UIComponent component, Object value)
            throws ValidatorException {
        try {
            if (connect == null) {
                connect = DB.getInstance().getConnection();
            }
            Statement st = connect.createStatement();
            String s = "SELECT * FROM `korisnik` WHERE username=' " + value + "'";
            ResultSet rs = st.executeQuery(s);

            if (rs.next()) {
                String message = "Korisničko ime mora biti jedinstveno, pokusašajte ponovo.";
                throw new ValidatorException(new FacesMessage(message));
            }
        } catch (SQLException ex) {

        }
    }

    public String getPotvrdaPassword() {
        return potvrdaPassword;
    }

    public void setPotvrdaPassword(String potvrdaPassword) {
        this.potvrdaPassword = potvrdaPassword;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getOpstina() {
        return opstina;
    }

    public void setOpstina(String opstina) {
        this.opstina = opstina;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getZaposlenje() {
        return zaposlenje;
    }

    public void setZaposlenje(String zaposlenje) {
        this.zaposlenje = zaposlenje;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public SelectItem[] getZaposlenjeItems() {
        return zap;
    }
    private static SelectItem[] zap;

    static {
        zap = new SelectItem[5];
        zap[0] = new SelectItem("nezaposlen");
        zap[1] = new SelectItem("zapsolen");
        zap[2] = new SelectItem("student");
        zap[3] = new SelectItem("lice sa invaliditetom");
        zap[4] = new SelectItem("penzioner");
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    
    
    
    
    

}
