package service;

import webserver.MasterServer;
import webserver.ServerStatus;

/**
 * Created by ron on 2015/6/3.
 */
public class MasterService {

	/**
	 * 应用服务器向目录服务器注册，查找到已经存在name的应用，覆盖，返回（暂时未用）
	 * application registers to the directory server, if there has already a application named "name", overwrite it
	 * @param name 	application name
	 * @param ip 	application server listen ip
	 * @param port	application server listen port
	 * @return		register result
	 */
    public int forceRegisterServer(String name, String ip, int port){
        ServerStatus serverStatus = new ServerStatus(ip, port, true);
        MasterServer server = MasterServer.getMaster();
        return server.forceRegisterServer(name, serverStatus);
    }

	/**
	 * 应用服务器向目录服务器注册，查找到已经存在name的应用，什么都不干，返回
	 * application registers to the directory server, if there has already a application named "name", 
	 * do nothing and return
	 * @param name 	application name
	 * @param ip 	application server listen ip
	 * @param port	application server listen port
	 * @return 		register result
	 */
    public int registerServer(String name, String ip, int port){
        ServerStatus serverStatus = new ServerStatus(ip, port, true);
        MasterServer server = MasterServer.getMaster();
        return server.registerServer(name, serverStatus);
    }

    /**
     * 通过应用名称查找应用
     * find the application server through name
     * @param name	application name
     * @return		application status
     */
    public ServerStatus findServer(String name){
        MasterServer server = MasterServer.getMaster();
        return server.findServer(name);
    }
}
