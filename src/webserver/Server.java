package webserver;

/**
 * Created by ron on 2015/6/3.
 */
public interface Server {
    public void registerService(String service);

    public void serverStart();
}
