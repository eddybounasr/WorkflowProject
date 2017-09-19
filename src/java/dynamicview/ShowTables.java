package dynamicview;
import ConnectionManager.DatabaseBuilder;
import ConnectionManager.dbTablesObjectManager;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowTables extends HttpServlet{

 private static final long serialVersionUID = 1L;

 @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 List<tableinfo> table_name_list = new ArrayList<tableinfo>();
 Hashtable<String, String> hashTablesExistngTables= new Hashtable<String,String>();
 ResultSet rs= DatabaseBuilder.getInstance().getDatabase("sysDb").SelectStatement("SELECT * from PUBLIC.cstables") ;
    try {
        while (rs.next()) {
            String table_name = rs.getString("tablename");
            String db_name = rs.getString("dbname");
       hashTablesExistngTables.put(table_name+""+db_name,table_name); 
        }   
    } catch (SQLException ex) {
        Logger.getLogger(GetRecord.class.getName()).log(Level.SEVERE, null, ex);
    }
    for (String dbName : DatabaseBuilder.getInstance().getAllDatabases().keySet()) 
          {
                    Hashtable< String , String> hashTableNames= dbTablesObjectManager.getTablesOfSchema(dbName);
                      for (String tableName : hashTableNames.keySet()) {                     
                          String tablename_dbname=tableName+""+dbName;
                             if (!hashTablesExistngTables.containsKey(tablename_dbname)){
                                 tableinfo table_info=new tableinfo();
                                 table_info.setdatabasename(dbName);
                                 table_info.settablename(tableName);
                                  table_name_list.add(table_info);                                    
                          }                
	            }
        
             }

              request.setAttribute("TablesShow",table_name_list);
              request.getRequestDispatcher("/DbTableShow.ftl").forward(request,response);
    }
    
@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {  
  
        String[] values = request.getParameterValues("ALL");
        for(int i=0;i<values.length;i++){
           Hashtable<String, String> hashTablesInformationxml=dbTablesObjectManager.getTablesSchemaXml("sysDb",values[i]);
           
           
           String str=hashTablesInformationxml.get(values[i]);
                       /*File output
			Writer file = new FileWriter (new File("C:\\xml.txt"));
			file.write(str);
			file.flush();
			file.close();*/
  
           String Query = "INSERT INTO public.cstables(dbname,tablename,xmlfield) VALUES ('sysDb','"+values[i]+"','"+hashTablesInformationxml.get(values[i])+"')";
            DatabaseBuilder.getInstance().getDatabase("sysDb").InsertStatement(Query);
           
    doGet(request, response);
   
        } 
  
    }

}