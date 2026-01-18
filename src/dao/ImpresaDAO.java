
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import connessione.Connessione;
import beans.Impresa;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ImpresaDAO {

    private Connection conn = new Connessione().collegare();
    
    public ImpresaDAO(){
    }

    public void inserire(Impresa impresa){
        String sql = "INSERT INTO impresa(nomedellimpresa, areadicompetenza) VALUES (?, ?) ";
        PreparedStatement ps;
        
        try{
            
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, impresa.getNomedellimpresa());
            ps.setString(2, impresa.getAreadicompetenza());
            
            ps.executeUpdate();
        }
        catch(SQLException ex){
            System.out.println("Errore durante il salvataggio dei dati: " + ex.getMessage());
        }
    }
    
    
    public Impresa getImpresa(int id){

        String sql = "SELECT * FROM Impresa WHERE id = ?";
        
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Impresa i = new Impresa();
            rs.next();

            i.setId(id);
            i.setNomedellimpresa(rs.getString("nomedellimpresa"));
            i.setAreadicompetenza(rs.getString("areadicompetenza"));
            return i;


            
        }catch(SQLException ex){
            System.out.println("Errore durante l'esecuzione: " + ex.getMessage());
            return null;
        }
    }
    
    public Impresa getImpresaNome(String nomedellimpresa){
        String sql = "SELECT * FROM impresa WHERE nomedellimpresa LIKE ?";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, nomedellimpresa);
            ResultSet rs = ps.executeQuery();
            
            Impresa imp = new Impresa();
            rs.next();
            
            imp.setId(rs.getInt("id"));
            imp.setAreadicompetenza(rs.getString("areadicompetenza"));
            imp.setNomedellimpresa(rs.getString("nomedellimpresa"));
            
            return imp;
            
        }catch(Exception e){
            System.out.println("Errore trovato: " + e.getMessage());
            return null;
        }
        
    }
    
    public void editare(Impresa impresa){
        String sql = "UPDATE impresa SET nomedellimpresa = ?, areadicompetenza = ? WHERE id = ?";
        try{
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, impresa.getNomedellimpresa());
            ps.setString(2, impresa.getAreadicompetenza());
            ps.setInt(3, impresa.getId());
            
            ps.executeUpdate();
        }catch(SQLException ex){
            System.out.println("Errore durante la edizione: "+ex.getMessage());
        }
    }
    
    
    public void eliminare(int id){
        String sql = "DELETE FROM impresa WHERE id = ?";
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
    
    public List<Impresa> getImpresa(String nomedellimpresa){
        String sql = "SELECT * FROM impresa WHERE nomedellimpresa LIKE ?";
        
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + nomedellimpresa + "%");
            ResultSet rs = ps.executeQuery();
            List<Impresa> listaAziende = new ArrayList<>();
            while(rs.next()){
                Impresa i = new Impresa();
                
                i.setId(rs.getInt("id"));
                i.setNomedellimpresa(rs.getString("nomedellimpresa"));
                i.setAreadicompetenza(rs.getString("areadicompetenza"));
                
                listaAziende.add(i);
            }
            return listaAziende;
        }catch(Exception e){
            System.out.println("Errore trovato: "+e.getMessage());
            return null;
        }
    }
    
    public List<Impresa> getImpresaId(int id1, int id2){
        String sql = "SELECT * FROM impresa WHERE id >= ? AND id <= ?";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, id1);
            ps.setInt(2, id2);
            
            ResultSet rs = ps.executeQuery();
            
            List<Impresa> lista = new ArrayList<>();
            
            while(rs.next()){
                Impresa i = new Impresa();

                i.setId(rs.getInt("id"));
                i.setNomedellimpresa(rs.getString("nomedellimpresa"));
                i.setAreadicompetenza(rs.getString("areadicompetenza"));
                
                
                lista.add(i);
            }
            
            return lista;
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Impresa non trovata.");
            return null;
        }  
    }
    
    
}
