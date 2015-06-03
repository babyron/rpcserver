package cache;

import webserver.ServerStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * a simple cache, not complete
 * Created by ron on 2015/6/3.
 */
public class LRUCache {
    Map<String, ServerStatus> cacheMap = new HashMap<String, ServerStatus>();

    public ServerStatus getServerStatus(String name){
        return cacheMap.get(name);
    }

    public void putServerStatus(String name, ServerStatus serverStatus){
        cacheMap.put(name, serverStatus);
    }
}
