package dynamicview;

import java.sql.ResultSet;

public interface IDBManager {
   	public  int  InsertStatement( String sqlInsertStatement);
	public  int updateStatement( String sqlUpdateStatement);
	public  ResultSet SelectStatement( String sqlSelect);
}