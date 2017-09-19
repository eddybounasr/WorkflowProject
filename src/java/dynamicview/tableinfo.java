package dynamicview;

public class tableinfo implements java.io.Serializable{
     String tablename;
     String databasename;
    
    tableinfo(){
     
    }
    
    public String gett_name(){
        return tablename;
    }
    
    public String getdb_name(){
        return databasename;
        
    }
    
    public void settablename(String tablename) {
        this.tablename = tablename;
    }
    
    public void setdatabasename(String databasename) {
        this.databasename = databasename;
    }
}
