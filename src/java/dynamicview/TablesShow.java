package dynamicview;

import ConnectionManager.DatabaseBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.xml.sax.SAXException;

public class TablesShow extends HttpServlet{
private static final long serialVersionUID = 1L;
final ArrayList<String> table_name_list = new ArrayList<String>();

@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    final ArrayList<String> table_name_list = new ArrayList<String>();
    ResultSet rs= DatabaseBuilder.getInstance().getDatabase("sysDb").SelectStatement("SELECT table_schema,table_name\n" +
"FROM information_schema.tables\n" +
"where table_schema ='public'\n" +
"and table_name Not IN \n" +
"(SELECT \"TABLENAME\" FROM PUBLIC.\"CSTABLES\")");
    
    try {
        while (rs.next()) {
            String table_name = rs.getString("table_name");
            table_name_list.add(table_name);     
        }
    } catch (SQLException ex) {
        Logger.getLogger(GetRecord.class.getName()).log(Level.SEVERE, null, ex);
    }
    request.setAttribute("ShowTables",table_name_list);
    request.getRequestDispatcher("/DbTableShow.ftl").forward(request, response);
    

   }

  
    
    
@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        String[] values = request.getParameterValues("ALL");
    for(int i=0;i<values.length;i++){
        ResultSet rs = DatabaseBuilder.getInstance().getDatabase("sysDb").SelectStatement("INSERT INTO public.\"CSTABLES\"(\n" +
                " \"IDDKEY\",\"DBNAME\", \"TABLENAME\", \"XMLFIELD\")\n" +
                "	VALUES (nextval('cstables_id_seq'),'den3sp9ne84ipr','" + values[i] + "','xmlfield');");
    }

       doGet(request, response); 
    }
    
}