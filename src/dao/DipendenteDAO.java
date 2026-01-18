
package dao;

import beans.Dipendente;
import beans.Impresa;
import connessione.Connessione;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.util.Date;



public class DipendenteDAO {

    private Connection conn = new Connessione().collegare();
    
    public DipendenteDAO(){
    }

    public void inserire(Dipendente dipendente){
        String sql = "INSERT INTO dipendente(nome, impresaid, ammissione) VALUES (?, ?, ?) ";
        PreparedStatement ps;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        try{
            
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, dipendente.getNome());
            ps.setInt(2, dipendente.getImpresa().getId());
            ps.setString(3, sdf.format(dipendente.getAmmissione()));
            
            ps.executeUpdate();
        }
        catch(SQLException ex){
            System.out.println("Errore durante il salvataggio dei dati: " + ex.getMessage());
        }
    }
    
    

    
    public void editare(Dipendente dipendente){
        String sql = "UPDATE dipendente SET nome = ?, areadicompetenza = ? WHERE id = ?";
        try{
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, dipendente.getNome());
            ps.setInt(2, dipendente.getImpresa().getId());
            ps.setInt(3, dipendente.getId());
            
            ps.executeUpdate();
        }catch(SQLException ex){
            System.out.println("Errore durante la edizione: "+ex.getMessage());
        }
    }
    
    
    public void eliminare(int id){
        String sql = "DELETE FROM dipendente WHERE id = ?";
        PreparedStatement ps;
        try{
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "L'eliminazione dei dati è avvenuta con sucesso.");
            
        }catch(SQLException ex){
            System.out.println("Errore durante l'eliminazione dei dati: "+ ex.getMessage());
        }
    }
   
    
    
    public Dipendente getDipendente(int id){
        String sql = "SELECT * FROM dipendente WHERE id = ?";
        PreparedStatement ps;
        Dipendente d = new Dipendente();
        DipendenteDAO ddao = new DipendenteDAO();
        Impresa i = new Impresa();
        ImpresaDAO idao = new ImpresaDAO();


        
        try{
            ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            rs.next();
            
            
            
            rs.first();
            
            
            d.setId(id);
            d.setNome(rs.getString("nome"));
            i.setId(rs.getInt("impresaid"));
            d.setImpresa(i);
            d.setAmmissione(rs.getDate("ammissione"));
            
            return d;
            
            
        }catch(Exception e){
            System.out.println("Errore trovato: " + e.getMessage());
            return null;
        }
    }
    
    
    public List<Dipendente> getDipendente(){
        String sql = "SELECT dipendente.id as id, nome, impresaid, nomedellimpresa, ammissione FROM dipendente INNER JOIN impresa ON dipendente.impresaid = impresa.id";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Dipendente> lista = new ArrayList<>();
            while(rs.next()){
                Dipendente d = new Dipendente();
                Impresa i = new Impresa();
                
                d.setId(rs.getInt("id"));
                d.setNome(rs.getString("nome"));
                d.setAmmissione(rs.getDate("ammissione"));
                i.setId(rs.getInt("impresaid"));
                i.setNomedellimpresa(rs.getString("nomedellimpresa"));
                d.setImpresa(i);
                
                lista.add(d);
            }
            return lista;
            
        }catch(Exception e){
            return null;
        }
    }
}
