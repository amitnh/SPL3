package bgu.spl.net.srv;

import java.util.concurrent.ConcurrentHashMap;

public class ConnectionsImp implements Connections{
    private DataBase dataBase;
    public ConnectionsImp()
    {
        dataBase = dataBase.getInstance();

    }
    @Override
    public boolean send(int connectionId, Object msg) {
        try{
            dataBase.getConnectionsHandlers().get(connectionId).send(msg);
        return true;
        } catch (Exception ignored){}
        return false;
    }

    @Override
    public void send(String channel, Object msg) {
        for(Integer connectionId:dataBase.getTopics().get(channel))
        {
            send(connectionId,msg);
        }
    }

    @Override
    public void disconnect(int connectionId) {
        dataBase.getConnectionsHandlers().get(connectionId).close();
    }
}
