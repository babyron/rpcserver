package webserver;

import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.WebServer;
import service.MasterService;

/**
 * Created by ron on 2015/6/3.
 * 启动目录服务器
 */
public class Main {
    private static final int port = 8080;
    public static void main(String[] args) throws Exception {
        WebServer webServer = new WebServer(port);

        XmlRpcServer xmlRpcServer = webServer.getXmlRpcServer();

        PropertyHandlerMapping phm = new PropertyHandlerMapping();

        /* 从配置文件中加载.
         * The property file might look like:
         *   Calculator=org.apache.xmlrpc.demo.Calculator
         *   org.apache.xmlrpc.demo.proxy.Adder=org.apache.xmlrpc.demo.proxy.AdderImpl
         */
        //phm.load(Thread.currentThread().getContextClassLoader(), "service.properties");
        phm.addHandler(MasterService.class.getSimpleName(), MasterService.class);
        /* 也可以像这样直接指定:
         * phm.addHandler("Calculator", org.apache.xmlrpc.demo.Calculator.class);
         * phm.addHandler(org.apache.xmlrpc.demo.proxy.Adder.class.getName(), org.apache.xmlrpc.demo.proxy.AdderImpl.class);
         */
        xmlRpcServer.setHandlerMapping(phm);

        XmlRpcServerConfigImpl serverConfig = (XmlRpcServerConfigImpl) xmlRpcServer.getConfig();
        /**
         * 开启EnabledForExtensions,可以识别Serializable类型
         */
        serverConfig.setEnabledForExtensions(true);
        serverConfig.setContentLengthOptional(false);

        webServer.start();
    }
}
