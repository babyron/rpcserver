package service;

class ConfManager{
	
	private ConfManager(){}
	
	private static class ConfManagerFactory{
		public static ConfManager cm = new ConfManager();
	}
	
	public void putConf(){
		
	}
	
	public void getConf(){
		
	}
	
	public void compareConf(){
		
	}
	
	public static ConfManager getConfManager(){
		return ConfManagerFactory.cm;
	}
}

public class TaskService {
	
	public void putConf(){
		ConfManager cm = ConfManager.getConfManager();
		cm.putConf();
	}
	
	public void getConf(){
		ConfManager cm = ConfManager.getConfManager();
		cm.getConf();
	}
	
	public void compareConf(){
		ConfManager cm = ConfManager.getConfManager();
		cm.compareConf();
	}
}