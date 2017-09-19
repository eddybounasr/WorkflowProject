package dynamicview;

public class tableinfo implements java.io.Serializable{
    private String tablename;
    private String databasename;
    
    tableinfo(String tablename , String dbname){
     this.databasename=dbname;
     this.tablename=tablename;
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
