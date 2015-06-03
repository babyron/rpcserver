package cache;

import org.apache.xmlrpc.client.XmlRpcClient;
import webserver.ServerStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * a simple cache, not complete
 * Created by ron on 2015/6/3.
 */
public class LRUCache {
    Map<String, XmlRpcClient> cacheMap = new HashMap<String, XmlRpcClient>();

    public XmlRpcClient getServerStatus(String name){
        return cacheMap.get(name);
    }

    public void putServerStatus(String name, XmlRpcClient xmlRpcClient){
        cacheMap.put(name, xmlRpcClient);
    }
}
