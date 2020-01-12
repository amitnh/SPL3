package bgu.spl.net.srv;

import javafx.util.Pair;

import java.util.concurrent.ConcurrentHashMap;

public class ConnectionsImp<T> implements Connections{
    private int connectiontId=0;
    private ConcurrentHashMap<Integer,ConnectionHandler> connectionsHandlers;// ConHandler for each active user (by user's connectionId)
    private DataBase dataBase;
    private static ConnectionsImp instance = new ConnectionsImp();
    private ConnectionsImp() {
        dataBase= DataBase.getInstance();
        connectionsHandlers= new ConcurrentHashMap<>();
    }
    public int getConnectionId()
    {
        return ++connectiontId;
    }
    public static ConnectionsImp getInstance(){
        return instance;
    }

    @Override
    public boolean send(int connectionId, Object msg) {
        if(!connectionsHandlers.containsKey(connectionId)) return false;
        connectionsHandlers.get(connectionId).send(msg);
        return true;
    }

    @Override
    public void send(String channel, Object msg) {
        for(Pair<String, Integer> connectionId:dataBase.getTopics().get(channel))
        {
            send(connectionId.getValue(),msg);
        }
    }

    @Override
    public void disconnect(int connectionId) {
        dataBase.removeUser(connectionId);
    }
    public void addHandler(ConnectionHandler handler){
        connectionsHandlers.put(++acceptid,handler);
    }

}
