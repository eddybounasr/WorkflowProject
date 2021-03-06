package dynamicview;

import ConnectionManager.DatabaseBuilder;
import ConnectionManager.dbTablesObjectManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.xml.sax.SAXException;

public class GetRecord extends HttpServlet{
private static final long serialVersionUID = 1L;
private List<Field> FieldList = new ArrayList<Field>();
/*{
    try {
        FieldList.addAll(ParserXML.fillFieldsInformation());
    } catch (SAXException ex) {
        Logger.getLogger(GetRecord.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
        Logger.getLogger(GetRecord.class.getName()).log(Level.SEVERE, null, ex);
    }
    }*/  
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FieldList = new ArrayList<Field>();
        String xmlformat="";
        String dbname = request.getParameter("dbname");
        String tablename = request.getParameter("tablename");
        String Query="SELECT xmlfield\n" +"FROM public.cstables\n" +"where tablename='"+tablename+"'\n and dbname='"+dbname+"'";
    ResultSet rs= DatabaseBuilder.getInstance().getDatabase("sysDb").SelectStatement(Query);
    try {
        while (rs.next()) {
           xmlformat = rs.getString("xmlfield");
        }
    } catch (SQLException ex) {
        Logger.getLogger(GetRecord.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    FieldList.addAll(ParserXML.fillFieldsInformation(xmlformat));

    
   request.setAttribute("Fields",FieldList );
   request.getRequestDispatcher("/fieldTemplate.ftl").forward(request,response);
    
    
  
    }
    
@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
      String tablename = request.getParameter("tablename");
      String database = request.getParameter("dbname");
      String values="";
     String sqlInsert="INSERT INTO public."+ tablename+"(";
      for(Field FieldObj : FieldList){
          String valueObj=request.getParameter(FieldObj.name);
          sqlInsert+=FieldObj.name+",";
          if(FieldObj.gettype().contains("int")){
              values+=valueObj+",";
          }
          else{ values+="'"+valueObj+"',";
          }
      }
      values=values.substring(0,values.length()-1);
      sqlInsert=sqlInsert.substring(0,sqlInsert.length()-1)+") VALUES ("+values+")";
      DatabaseBuilder.getInstance().getDatabase(database).InsertStatement(sqlInsert);
       doGet(request, response);
    }
   
}