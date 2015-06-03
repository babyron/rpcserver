package webserver;

import org.apache.xmlrpc.XmlRpcException;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by ron on 2015/6/3.
 */
public interface Server {
    public void registerService() throws IOException, XmlRpcException;

    public void serverStart(Class cls)throws XmlRpcException, IOException;
}
