package cls.workflowengine;


public class WorkflowManager {

	public static ResponseObject doCreateProcess(String flowSchemaXml)
	{
		return null;
				
	}

	public static ResponseObject doUpdateProcess(String flowSchemaXml, Integer processId)
	{
		return null;
				
	}
	
	public static ResponseObject doDeleteProcess( Integer processId)
	{
		return null;
				
	}
	

	public static ResponseObject doDeployProcess(Integer processId)
	{
		return null;
				
	}
	
	public static class ResponseObject implements java.io.Serializable
	{
		
	/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
	 
	    public String excpetionResponse;
	    public Integer processId;
        public boolean isSaveOk;	    
	}
}
