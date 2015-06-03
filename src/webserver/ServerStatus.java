package webserver;

import java.io.Serializable;

/**
 * Created by ron on 2015/6/3.
 */
public class ServerStatus implements Serializable{
    private String ip;
    private int port;
    private boolean status;
    private long time;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServerStatus that = (ServerStatus) o;

        if (port != that.port) return false;
        if (ip != null ? !ip.equals(that.ip) : that.ip != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ip != null ? ip.hashCode() : 0;
        result = 31 * result + port;
        return result;
    }
}
