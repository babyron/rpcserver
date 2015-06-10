package webserver;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 目录服务器
 * directory server
 * @author ron
 *
 */
public class MasterServer {

	/**
	 * 单例，保证目录服务器只有一个。
	 */
    private Map<String, ServerStatus> servers = null;
    
    /**
     * 保证线程安全
     */
    private Lock lock = new ReentrantLock();
    
    private MasterServer(){
        servers = new HashMap<String, ServerStatus>();
    };

    private static class SingletonServer{
        private static MasterServer server = new MasterServer();
    }

    public static MasterServer getMaster(){
        return SingletonServer.server;
    }

    /**
     *
     * @param name
     * @param serverStatus
     * @return register result 0. OK 1. name has already existed  2. illegal name
     */
    private int registerServer(String name, ServerStatus serverStatus, boolean force){
    	lock.lock();
        if(servers == null){
            System.out.println("UNKNOWN EXCEPTION");
            System.exit(0);
        }

        if(name == null || name.length() == 0){
        	lock.unlock();
            return 2;
        }

        if(servers.containsKey(name) && !force){
        	lock.unlock();
            return 1;
        }else{
            servers.put(name, serverStatus);
            System.out.println("OK");
            lock.unlock();
            return 0;
        }
    }

    /**
     * override the old server named "name"
     * @param 	name
     * @param	serverStatus
     * @return
     */
    public int forceRegisterServer(String name, ServerStatus serverStatus){
        return registerServer(name, serverStatus, true);
    }

    /**
     * if there is already a server named "name" then do nothing, otherwise put it into the map
     * @param 	name
     * @param 	serverStatus
     * @return
     */
    public int registerServer(String name, ServerStatus serverStatus){
        return registerServer(name, serverStatus, false);
    }

    /**
     * 
     * @param 	name
     * @return
     */
    public ServerStatus findServer(String name){
    	lock.lock();
    	ServerStatus status =  servers.get(name);
    	lock.unlock();
    	return status;
    }
}  