package client;

import java.net.MalformedURLException;  
import java.net.URL;  
  
import org.apache.xmlrpc.XmlRpcException;  
import org.apache.xmlrpc.client.XmlRpcClient;  
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import webserver.ServerStatus;

public class ClientTest {  

    private XmlRpcClient getApplicationClient(ServerStatus serverStatus) throws MalformedURLException {
        String url = "http://" + serverStatus.getIp() + ":" + serverStatus.getPort() +  "/XML-RPC/service";
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        config.setServerURL(new URL(url));
        config.setEnabledForExtensions(true);

        XmlRpcClient client = new XmlRpcClient();
        client.setConfig(config);
        return client;
    }

    public static void main(String[] args) {  
  
        try {  
  
            XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();  
            config.setServerURL(new URL("http://127.0.0.1:8080/XML-RPC/service"));
            config.setEnabledForExtensions(true);

            XmlRpcClient client = new XmlRpcClient();
            client.setConfig(config);

            Object[] params = new Object[] {new String("calculator")};
            ServerStatus result = (ServerStatus) client.execute("MasterService.findServer", params);
            System.out.println(result.getIp());
              
//            System.out.println(result);
//
//            result = (Integer) client.execute("Calculator.subtract", params);
              
   //         System.out.println(result);
  
        } catch (XmlRpcException e) {  
            e.printStackTrace();  
        } catch (MalformedURLException e) {  
            e.printStackTrace();  
        }  
  
    }  
}  