package webserver;

import java.util.HashMap;
import java.util.Map;

public class MasterServer {

    private Map<String, ServerStatus> servers = null;
    
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
        if(servers == null){
            System.out.println("UNKNOWN EXCEPTION");
            System.exit(0);
        }

        if(name == null || name.length() == 0){
            return 2;
        }

        if(servers.containsKey(name) && !force){
            return 1;
        }else{
            servers.put(name, serverStatus);
            System.out.println("OK");
            return 0;
        }
    }

    /**
     * override the old server named "name"
     * @param name
     * @param serverStatus
     * @return
     */
    public int forceRegisterServer(String name, ServerStatus serverStatus){
        return registerServer(name, serverStatus, true);
    }

    /**
     * if there is already a server named "name" then do nothing, otherwise put it into the map
     * @param name
     * @param serverStatus
     * @return
     */
    public int RegisterServer(String name, ServerStatus serverStatus){
        return registerServer(name, serverStatus, false);
    }

    public ServerStatus findServer(String name){
        return servers.get(name);
    }
}  