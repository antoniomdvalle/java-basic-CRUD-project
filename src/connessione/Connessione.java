
package connessione;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connessione {
    Connection conn;
        public Connection collegare(){
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost/esempiojavabd", "root", "#cart3ir4el0k4..,");
            System.out.println("Connessione stabilita con successo.");
            return conn;
        }catch(SQLException ex){
            System.out.println("Errore durante la connessione: " + ex.getMessage());
            return null;
        }
    }
    
    public void disconnetersi(){
        try{
            conn.close();
            System.out.println("Disconnessione avvenuta con successo.");
        }catch(SQLException ex){
            
        }
    }
    
}
