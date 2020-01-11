package bgu.spl.net.srv;

import java.util.concurrent.ConcurrentHashMap;

public class ConnectionsImp implements Connections{
    private int acceptid=0;
    private ConcurrentHashMap<Integer,ConnectionHandler> connectionsHandlers;// ConHandler for each active user (by user's connectionId)
    private DataBase dataBase;
    private static ConnectionsImp instance = new ConnectionsImp();
    private ConnectionsImp() {
        dataBase= DataBase.getInstance();
        connectionsHandlers= new ConcurrentHashMap<>();
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
        for(Integer connectionId:dataBase.getTopics().get(channel))
        {
            send(connectionId,msg);
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
