package ConnectionManager;

import ConnectionManager.IDBManager;
import ConnectionManager.DatabaseBuilder;
import org.apache.commons.dbcp.BasicDataSource;
import javax.sql.DataSource;
import java.sql.*;
import java.net.URI;
import java.net.URISyntaxException;

public class mySqlDbManager implements IDBManager {
	    private DataSource dataSource;
	    private Statement statement;
        public String getName()
	    {
	    	return "postgresql";
	    }
        
	    /*public static mySqlDbManager getInstance(String dbName)
	    {
	    	if(mySqlDbMan== null)
	    	{
	    		mySqlDbMan= new mySqlDbManager(dbName);
	    	}
	    	return mySqlDbMan;
	    }
	    */
	   public  mySqlDbManager(String dbName)
	    {
	    	if(dataSource== null)
	    	{
	    		try {
					dataSource=this.dataSource(dbName);
					 try {
						statement = dataSource.getConnection().createStatement();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
	    }
	    
	    public int  InsertStatement( String sqlInsertStatement)
	    {
	    	//Statement stmt = dataSource.getConnection().createStatement();
	    	//return statement.executeUpdate("INSERT INTO ticks VALUES (now())");
	    	int retVal =-1;
	    	try {
	    		if(statement==null)
	    		{
	    			statement=dataSource.getConnection().createStatement();
	    		}
	    		retVal= statement.executeUpdate(sqlInsertStatement);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return retVal;
	    }
	    	public int  updateStatement( String sqlUpdateStatement)
		    {
		    	//Statement stmt = dataSource.getConnection().createStatement();
		    	//return statement.executeUpdate("INSERT INTO ticks VALUES (now())");
		    	int retVal =-1;
		    	try {
		    		retVal= statement.executeUpdate(sqlUpdateStatement);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	return retVal;
		    }
	    
	    public ResultSet SelectStatement( String sqlSelect)
	    {
	    	
	        ResultSet retVal= null;
			try {
				retVal = statement.executeQuery(sqlSelect);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	    	return retVal;
	    }
	    /*
	    public void myRealMainMethod() throws SQLException, URISyntaxException {
	    	if(dataSource== null)
	    	{
	    		dataSource=this.dataSource();
	    	}
	    
	        Statement stmt = dataSource.getConnection().createStatement();
	        stmt.executeUpdate("DROP TABLE IF EXISTS ticks");
	        stmt.executeUpdate("CREATE TABLE ticks (tick timestamp)");
	        stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
	        ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");
	        while (rs.next()) {
	            System.out.println("Read from DB: " + rs.getTimestamp("tick"));
	        }
	    }
	    */
	    public BasicDataSource dataSource(String dbName) throws URISyntaxException {
	       // URI dbUri = new URI(System.getenv("DATABASE_URL"));
     	    String dbConnexion="";
			if(dbName.contains("sysDb"))
			{
				dbConnexion="postgres://bgwxgbubrqrjsq:78115fbce2a2c65bb5ccff43eb440ce85bd09de4aaabbd5788496e1af481ab4c@ec2-54-228-235-185.eu-west-1.compute.amazonaws.com:5432/den3sp9ne84ipr";
			}
			else
			{
    			
    			//mySqlDbManager mySqlDbManagerconne= new mySqlDbManager("sysDb");
    			//ResultSet resultDb=	mySqlDbManagerconne.SelectStatement("select * from public.\"CSDATABASES\" where dbname ='"+dbName+"'");
				ResultSet resultDb= DatabaseBuilder.getInstance().getDatabase("sysDb").SelectStatement("select * from CSDATABASES where dbname ='"+dbName+"'");
				try {
				while (resultDb.next()) 
					{
					   try {
						dbConnexion="postgres://" + resultDb.getString("USERNAME")+":"+ resultDb.getString("PASSWORD") +"@"+resultDb.getString("DBURL")  +"/"+resultDb.getString("DBSCHEMA") ;
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   

			}
      	    URI dbUri = new URI(dbConnexion);

	        String username = dbUri.getUserInfo().split(":")[0];
	        String password = dbUri.getUserInfo().split(":")[1];
	        System.out.println("sss"+ dbUri.getUserInfo());
	        String dbUrl = "jdbc:postgresql://" + dbUri.getHost()+":"+dbUri.getPort() + dbUri.getPath()+"?sslmode=require";
           
	        BasicDataSource basicDataSource = new BasicDataSource();
	        basicDataSource.setUrl(dbUrl);
	        basicDataSource.setUsername(username);
	        basicDataSource.setPassword(password);
	      
           //  basicDataSource.setDriverClassName("org.apache.commons.dbcp.BasicDataSource");
	        basicDataSource.setDriverClassName("org.postgresql.Driver");
	        return basicDataSource;
	    }
}