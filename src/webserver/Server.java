package webserver;

import org.apache.xmlrpc.XmlRpcException;

import java.io.IOException;


public interface Server {
    public void registerService() throws IOException, XmlRpcException;

    public void serverStart(Class<?> cls)throws XmlRpcException, IOException;
}
