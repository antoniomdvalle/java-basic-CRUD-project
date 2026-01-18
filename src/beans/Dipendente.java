
package beans;

import java.util.Date;


public class Dipendente {
    private int id;
    private String nome;
    private Impresa impresaid;
    private Date ammissione;
    
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    
    
    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    
    
    public Impresa getImpresa(){
        return impresaid;
    }
    public void setImpresa(Impresa impresaid){
        this.impresaid = impresaid;
    }
    
    
    public Date getAmmissione(){
        return ammissione;
    }
    public void setAmmissione(Date ammissione){
        this.ammissione = ammissione;
    }
}
