
package beans;


public class Impresa {
    private int id;
    private String nomedellimpresa;
    private String areadicompetenza;
    
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    
    
    public String getNomedellimpresa(){
        return nomedellimpresa;
    }
    public void setNomedellimpresa(String nomedellimpresa){
        this.nomedellimpresa = nomedellimpresa;
    }
    
    
    public String getAreadicompetenza(){
        return areadicompetenza;
    }
    public void setAreadicompetenza(String areadicompetenza){
        this.areadicompetenza = areadicompetenza;
    }
    
    
    @Override
    public String toString(){
        return this.nomedellimpresa;
    }
    
    @Override
    public boolean equals (Object o){
        Impresa i = (Impresa) o;
        if (this.id == i.getId()){
            return true;
        }else{
            return false;
        }
    }
}
