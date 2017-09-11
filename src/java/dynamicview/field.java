package dynamicview;

public class field  implements java.io.Serializable {
  
    private int order;
    String name;
    private String sqlType;
    private int sqlSize;
    private boolean sqlPK;
    private String desc;
    private String type;
    private int min;
    private int min_sort_order;
    private String formType;
    private int size;
    private boolean mandatory;
    private boolean insert;
    private boolean update;
    private boolean show;
    private boolean unicity;
   
    public void setOrder(int order) {
        this.order = order;
    }
    public int getOrder() {
        return order;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

     public void setsqlType(String sqlType) {
        this.sqlType = sqlType;
    }
    public String getsqlType() {
        return sqlType;
    }
    
     public void setformType(String formType) {
        this.formType = formType;
    }
    public String getformType() {
        return formType;
    }
    
      public void settype(String type) {
        this.type = type;
    }
    public String gettype() {
        return type;
    }
    
       public void setsqlSize(int sqlSize) {
        this.sqlSize = sqlSize;
    }
    public int getsqlSize() {
        return sqlSize;
    }
    
       public void setmin(int min ) {
        this.min = min;
    }
    public int getmin() {
        return min;
    }
    
           public void setdesc(String desc ) {
        this.desc = desc;
    }
    public String getdesc() {
        return desc;
    }
    
       public void setmin_sort_order(int min_sort_order) {
        this.min_sort_order = min_sort_order;
    }
    public int getmin_sort_order() {
        return min_sort_order;
    }
    
       public void setsize(int size) {
        this.size = size;
    }
    public int getsize() {
        return size;
    }
    
       public void setsqlPK(boolean sqlPK) {
        this.sqlPK = sqlPK;
    }
    public boolean getsqlPK() {
        return sqlPK;
    }
    
          public void setmandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }
    public boolean getmandatory() {
        return mandatory;
    }
    
          public void setinsert(boolean insert) {
        this.insert = insert;
    }
    public boolean getinsert() {
        return insert;
    }
    
          public void setupdate(boolean update) {
        this.update = update;
    }
    public boolean getupdate() {
        return update;
    }
    
          public void setshow(boolean show) {
        this.show = show;
    }
    public boolean getshow() {
        return show;
    }
    
          public void setunicity(boolean unicity) {
        this.unicity = unicity;
    }
    public boolean getunicity() {
        return unicity;
    }
  

    
}

