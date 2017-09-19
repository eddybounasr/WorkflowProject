package dynamicview;

public class tableinfo implements java.io.Serializable{
    private String tablename;
    private String databasename;
    
    tableinfo(String tablename , String dbname){
     this.databasename=dbname;
     this.tablename=tablename;
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
