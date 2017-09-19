package dynamicview;

public class tableinfo implements java.io.Serializable{
     String tablename;
     String databasename;
    
    public tableinfo(){
     
    }
    
    public String gettablename(){
        return tablename;
    }
    
    public String getdatabasename(){
        return databasename;
        
    }
    
    public void settablename(String tablename) {
        this.tablename = tablename;
    }
    
    public void setdatabasename(String databasename) {
        this.databasename = databasename;
    }
}
