package webserver;

import org.apache.xmlrpc.XmlRpcException;
import service.Calculator;

import java.io.IOException;

/**
 * Created by ron on 2015/6/3.
 */
public class CalculatorServer extends AbstractServer{

    public void registerService() throws IOException, XmlRpcException{
        setServerName("calculator");
        setIp("127.0.0.1");
        setPort(8089);
        setStatus(true);
        super.registerService();
    }

    public void serverStart(Class cls) throws XmlRpcException, IOException {
        registerService();
        super.serverStart(Calculator.class);
    }

    public static void main(String []args) throws XmlRpcException, IOException {
        new CalculatorServer().serverStart(Calculator.class);
    }
}
