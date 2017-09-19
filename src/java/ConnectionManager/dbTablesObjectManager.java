package ConnectionManager;

import ConnectionManager.DatabaseBuilder;
import dynamicview.field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class dbTablesObjectManager {
	
	public static Hashtable<String, String>   getTablesOfSchema(String dbName )
	{
		Hashtable<String, String>   retVal= new Hashtable<String, String>  ();
		ResultSet rstablesDatabase=DatabaseBuilder.getInstance().getDatabase(dbName).SelectStatement("SELECT table_schema,table_name,table_type FROM information_schema.tables where table_schema not in  ('information_schema','pg_catalog')");
		
		try {
			while (rstablesDatabase.next()) 
			{
				retVal.put(rstablesDatabase.getString("table_name"),rstablesDatabase.getString("table_schema"));
//			field fieldObject=	prepareXmlFormat(,rs.getString("udt_name"),rs.getString("character_maximum_length"),rs.getString("ordinal_position"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return retVal;
	}
	// the table names string should be in this format table1','table2','table3
		// in case we have only one table it will be only table1
	
	public static Hashtable<String, List<field>>   getTablesSchema(String dbName,String tableNames )
	{
		Hashtable<String, List<field>> retVal= new Hashtable<String, List<field>>();
		ResultSet rs=DatabaseBuilder.getInstance().getDatabase("sysDb").SelectStatement("select column_name,ordinal_position,is_nullable,data_type,character_maximum_length,udt_name,table_name from INFORMATION_SCHEMA.COLUMNS where table_name in('"+tableNames+"')");
		try 
		{
			while (rs.next()) 
			{
				field fieldObject=	prepareXmlFormat(rs.getString("column_name"),rs.getString("udt_name"),rs.getString("character_maximum_length"),rs.getString("ordinal_position"));
                String tablemName=  rs.getString("table_name");
				if(!retVal.containsKey(tablemName))
				{
					List<field> collection=	new ArrayList<field>();
					retVal.put(tablemName, collection );
				}
				retVal.get(tablemName).add(fieldObject);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retVal;
	}
	
	public static Hashtable<String, String>   getTablesSchemaXml(String dbName,String tableNames )
	{
		Hashtable<String, String> retVal= new Hashtable<String, String> ();
		Hashtable<String, List<field>> hashTablesInformation=getTablesSchema(dbName,tableNames);
		for (String tableName : hashTablesInformation.keySet()) 
		{
    		String xmlTableSxchema="<?xml version=1.0 encoding=ISO-8859-1?><fields> ";
			List<field> listFields=	hashTablesInformation.get(tableName);
		 	for (field field : listFields) {
		 		xmlTableSxchema+= getXmlFormat(field);
			}
		 	xmlTableSxchema+="</fields>";
		 	retVal.put(tableName, xmlTableSxchema);
		}
		return retVal;
		
	}
	
	private static String getXmlFormat(field fieldObj)
	{
		String retVal="<field>";
		retVal+="<order>"+fieldObj.getOrder()+"</order>";
                retVal+="<name>"+fieldObj.getName()+"</name>";
		retVal+="<sqlTye>"+fieldObj.getsqlType()+"</sqlType>";
		retVal+="<sqlSize>"+fieldObj.getsqlSize()+"</sqlSize>";
		retVal+="<sqlPK>"+fieldObj.getsqlPK()+"</sqlPK>";
		retVal+="<desc>"+fieldObj.getdesc()+"</desc>";
		retVal+="<type>"+fieldObj.gettype()+"</type>";
		retVal+="<min>"+fieldObj.getmin()+"</min>";
		retVal+="<min_sort_order></min_sort_order>";
		retVal+="<formType>"+fieldObj.getformType()+"</formType>";
		retVal+="<size>"+fieldObj.getsize()+"</size>";
		retVal+="<mandatory>"+fieldObj.getmandatory()+"</mandatory>";
		retVal+="<insert>"+fieldObj.getinsert()+"</insert>";
		retVal+="<update>"+fieldObj.getupdate()+"</update>";
		retVal+="<show>"+fieldObj.getshow()+"</show>";
	    retVal+="<unicity>"+fieldObj.getunicity()+"</unicity>";
		return retVal;
	}
	
	private static field prepareXmlFormat(String fieldName, String typefield, String lengthField, String order)
	{
		field retVal= new field();
		retVal.setOrder(Integer.parseInt(order));
		retVal.setName(fieldName);
		retVal.setsqlType(typefield);
		if(!typefield.contains("int4"))
		{
			retVal.setsqlSize(50);
		}
		retVal.setdesc(fieldName);
     	retVal.settype(typefield);
     	retVal.setmin(Integer.parseInt(order));
     	retVal.setformType(typefield);
     	retVal.setmandatory(false);
     	retVal.setinsert(true);
        retVal.setupdate(true);
        retVal.setshow(true);
        retVal.setunicity(false);
     	return retVal;
    } 
}
