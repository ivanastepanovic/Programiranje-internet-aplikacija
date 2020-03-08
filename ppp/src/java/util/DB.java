package util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Drazen
 */
public class DB {
    private static DB instance;
    private static final int MAX_CON=5;
    private static final Connection[] bafer=new Connection[MAX_CON];
    private int first=0,last=0, free=MAX_CON;
    
    /* Pristup za MS Access bazu
    private DB(){ 
        try{
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        for(int i=0; i<MAX_CON; i++)
           bafer[i] = DriverManager.getConnection("jdbc:odbc:lab1kol");
        }catch(Exception e){}
    }
    */
    
    private DB(){ //za MySQL
        try{
        Class.forName("com.mysql.jdbc.Driver");
        for(int i=0; i<MAX_CON; i++)
            //argumenti su:
            //1. host je localhost i port je 3306 za mysql
            //   naziv baze: lab1student
            //2. korisnicko ime za MySQL: root
            //3. lozinka za MySQL je prazna, ako je setovana onda ubaciti njenu vrednost
           bafer[i] = DriverManager.getConnection("jdbc:mysql://localhost:3306/projekat","root","");
        } catch(Exception e){ 
            e.printStackTrace();
        }
    }
    
    public static DB getInstance(){
     if(instance==null) instance=new DB();
     return instance;
    }
    public synchronized Connection getConnection(){
        if(free==0) return null;
        free--; 
        Connection con=bafer[first];
        first=(first+1)%MAX_CON;
        return con;
    }
    public synchronized void putConnection(Connection con){
        if(con==null) return;
        free++;
        bafer[last]=con; 
        last=(last+1)%MAX_CON;        
    }
}
