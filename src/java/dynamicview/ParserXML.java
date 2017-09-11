package dynamicview;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
public class ParserXML {


	public static List<field> fillFieldsInformation() throws SAXException, IOException
	{
		
		    String filePath = "C:\\Users\\User\\Desktop\\Dynamicview\\web\\WEB-INF\\fields.xml";
	        BufferedReader reader = new BufferedReader(new FileReader (filePath));
	        String         line = null;
	        StringBuilder  stringBuilder = new StringBuilder();
	        String         ls = System.getProperty("line.separator");

	        try {
	            while((line = reader.readLine()) != null) {
	                stringBuilder.append(line);
	                stringBuilder.append(ls);
	            }

	            
	        } finally {
	            reader.close();
	        }
	       return fillFieldsInformation(stringBuilder.toString());
	     
	}
	public static List<field>  fillFieldsInformation(String xmlToBeParsed)
	{
		        List<field> retVal = new ArrayList<field>();
		          Document doc;
				try {
				
					 doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(xmlToBeParsed)));
					    NodeList nodeList = doc.getElementsByTagName("field");
			            //now XML is loaded as Document in memory, lets convert it to Object List
			            
			            for (int i = 0; i < nodeList.getLength(); i++) {
			            	retVal.add(getfield(nodeList.item(i)));
			                
			            }
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FactoryConfigurationError e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        
	        return retVal;
	}
	
	 private static field getfield(Node node) {
	        //XMLReaderDOM domReader = new XMLReaderDOM();
	        field retVal = new field();
	        if (node.getNodeType() == Node.ELEMENT_NODE) {
	            Element element = (Element) node;
	            retVal.setOrder(Integer.parseInt(getTagValue("order", element)));
	            retVal.setdesc(getTagValue("desc", element));
	            retVal.setName(getTagValue("name", element));
	            retVal.setsqlType(getTagValue("sqlType", element));
	            retVal.setformType(getTagValue("formType", element));
	            retVal.settype(getTagValue("type", element));
	            retVal.setsqlSize(Integer.getInteger(getTagValue("sqlSize", element),0));
	            retVal.setmin(Integer.getInteger(getTagValue("min", element),0));
	            retVal.setmin_sort_order( Integer.getInteger(getTagValue("min_sort_order", element),0));
	            retVal.setsize(Integer.getInteger(getTagValue("size", element),0));
	            retVal.setsqlPK(checked("sqlPK", element));
	            retVal.setmandatory(checked("mandatory", element));
	            retVal.setshow(checked("show",element));
	            retVal.setupdate(checked("update", element));
	            retVal.setinsert(checked("insert", element));
	            retVal.setunicity(checked("unicity", element));
	   
	        }

	        return retVal;
	    }
	 private static String getTagValue(String tag, Element element) {
		    String retVal="";
		 
	        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
	        Node node = (Node) nodeList.item(0);
	        if(node!= null)
	        {
	          retVal=node.getNodeValue();
	        }
	        return retVal; 
	    }
	 
	 private static boolean checked(String tagName, Element element){
	        boolean retVal = true;
	        String fieldValue=getTagValue(tagName,element);
	       if(fieldValue.contains("unchecked"))
           {
	         retVal= false;
	       }
	        return retVal;
	    }
}
