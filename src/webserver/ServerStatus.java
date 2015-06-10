package webserver;

import java.io.Serializable;

/**
 * class implements Serializable can be recognized by xml-rpc framework
 * class variable ip and port should be focused
 * 实现Serializable接口的类，可以实现序列化反序列化，目前主要用到该类的ip port属性
 */
public class ServerStatus implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String ip;
    private int port;
    private transient boolean status;
    private transient long time;
    
    public ServerStatus(String ip, int port, boolean status){
        this.ip = ip;
        this.port = port;
        this.status = status;
        this.time = System.currentTimeMillis();
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

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
    
    private void setStatus(boolean status){
        this.status = status;
    }

    private void setTime(){
        this.time = System.currentTimeMillis();
    }

    /**
     * object method override, not used
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServerStatus that = (ServerStatus) o;

        if (port != that.port) return false;
        if (ip != null ? !ip.equals(that.ip) : that.ip != null) return false;

        return true;
    }

    /**
     * object method override, not used
     */
    @Override
    public int hashCode() {
        int result = ip != null ? ip.hashCode() : 0;
        result = 31 * result + port;
        return result;
    }
}
