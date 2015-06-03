package webserver;

/**
 * Created by ron on 2015/6/3.
 */
public class AbstractServer implements Server{
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
    public void registerService(String service) {

    }

    @Override
    public void serverStart() {

    }
}
