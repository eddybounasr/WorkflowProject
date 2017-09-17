package ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

public class DatabaseBuilder {
	private static DatabaseBuilder  instance;
	private static Hashtable<String,IDBManager> hashDatabaseInstance ;
    public IDBManager getDatabase(String databaseName)
    {
    	IDBManager  retVal = null;
        if(hashDatabaseInstance.containsKey(databaseName))
        {
         retVal= hashDatabaseInstance.get(databaseName); 
        }
        return retVal;
   }
    public Hashtable<String,IDBManager> getAllDatabases()
    {
        return hashDatabaseInstance;
    }
   
	 public void doReloadDatabases()
	 {
	   instance= null;
	   DatabaseBuilder.getInstance();
	 }
   
	 
    public static DatabaseBuilder getInstance()
    {
       if(instance == null)
       {
        instance = new DatabaseBuilder  ();
       	ResultSet resultDatabase= hashDatabaseInstance.get("sysDb").SelectStatement("select * FROM CSDATABASES where dbdriver ='org.postgresql.Driver'" );

     	try {
		while (resultDatabase.next()) 
		{
			String dbName=	resultDatabase.getString("DBNAME") ;
			if(!hashDatabaseInstance.containsKey(dbName))
			{
		 	mySqlDbManager mySqlDbManagerconne= new mySqlDbManager(dbName);
			hashDatabaseInstance.put(dbName,mySqlDbManagerconne);
		   }
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 

       }     
       return instance;

    }
    private DatabaseBuilder  ()
    {
    	hashDatabaseInstance = new Hashtable<String,IDBManager> ();
    	mySqlDbManager systemmySqlDbManager= new mySqlDbManager("sysDb");
    	hashDatabaseInstance.put("sysDb",systemmySqlDbManager);
    }
}
