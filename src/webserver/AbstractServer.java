package webserver;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.WebServer;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/**
 * Created by ron on 2015/6/3.
 */
abstract public class AbstractServer implements Server{
    protected String serverName = null;
    protected String ip = null;
    protected int port;
    protected boolean status;

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public void registerService() throws IOException, XmlRpcException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("master.properties");
        properties.load(fileInputStream);
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        String url = "http://" + ip + ":" + port + "/XML-RPC/service";
        config.setServerURL(new URL(url));

        XmlRpcClient client = new XmlRpcClient();
        client.setConfig(config);

        Object[] params = new Object[] { serverName, new ServerStatus(ip, port, true)};
        client.execute("MasterService.RegisterServer", params);
    }

    @Override
    public void serverStart(Class cls) throws XmlRpcException, IOException {
        WebServer webServer = new WebServer(port);

        XmlRpcServer xmlRpcServer = webServer.getXmlRpcServer();

        PropertyHandlerMapping phm = new PropertyHandlerMapping();

        phm.addHandler(cls.getName(), cls);

        xmlRpcServer.setHandlerMapping(phm);

        XmlRpcServerConfigImpl serverConfig = (XmlRpcServerConfigImpl) xmlRpcServer.getConfig();
        serverConfig.setEnabledForExtensions(true);
        serverConfig.setContentLengthOptional(false);

        webServer.start();
    }
}
