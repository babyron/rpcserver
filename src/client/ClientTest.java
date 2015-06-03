package client;

import java.net.MalformedURLException;  
import java.net.URL;

import cache.LRUCache;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;  
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import webserver.ServerStatus;

public class ClientTest {
    LRUCache cache = new LRUCache();
    public final String serverName = "master";
    public final String serverIp = "127.0.0.1";
    public final int serverPort = 8080;

    private XmlRpcClient getApplicationClient(ServerStatus serverStatus) throws MalformedURLException {
        String url = "http://" + serverStatus.getIp() + ":" + serverStatus.getPort() +  "/XML-RPC/service";
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        config.setServerURL(new URL(url));
        config.setEnabledForExtensions(true);

        XmlRpcClient client = new XmlRpcClient();
        client.setConfig(config);
        return client;
    }

    private XmlRpcClient getApplicationClient(String name) throws MalformedURLException, XmlRpcException {
        XmlRpcClient client = cache.getServerStatus("master");
        ServerStatus serverStatus = null;
        if(client == null) {
            serverStatus = new ServerStatus(serverIp, serverPort, true);
            client = getApplicationClient(serverStatus);
            cache.putServerStatus("master", client);
        }

        assert client != null;

        serverStatus = (ServerStatus) client.execute("MasterService.findServer", new Object[]{name});
        System.out.println(serverStatus.getPort());
        return getApplicationClient(serverStatus);
    }


    public static void main(String[] args) {  
        ClientTest clientTest = new ClientTest();
        try {
            XmlRpcClient client = clientTest.getApplicationClient("calculator");
            System.out.println(client.execute("Calculator.add", new Object[]{new Integer(1), new Integer(2)}));
        } catch (XmlRpcException e) {  
            e.printStackTrace();  
        } catch (MalformedURLException e) {  
            e.printStackTrace();  
        }  
  
    }  
}  