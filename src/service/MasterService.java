package service;

import webserver.MasterServer;
import webserver.ServerStatus;

/**
 * Created by ron on 2015/6/3.
 */
public class MasterService {

    public int forceRegisterServer(String name, String ip, int port){
        ServerStatus serverStatus = new ServerStatus(ip, port, true);
        MasterServer server = MasterServer.getMaster();
        return server.forceRegisterServer(name, serverStatus);
    }

    public int RegisterServer(String name, String ip, int port){
        ServerStatus serverStatus = new ServerStatus(ip, port, true);
        MasterServer server = MasterServer.getMaster();
        return server.RegisterServer(name, serverStatus);
    }

    public ServerStatus findServer(String name){
        MasterServer server = MasterServer.getMaster();
        return server.findServer(name);
    }
}
