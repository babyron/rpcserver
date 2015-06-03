package webserver;

import java.util.concurrent.TimeUnit;

/**
 * Created by ron on 2015/6/3.
 */
public class ServerStatus {
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
