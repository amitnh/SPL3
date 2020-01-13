package bgu.spl.net.Messages;

import bgu.spl.net.srv.ConnectionHandler;
import bgu.spl.net.srv.DataBase;
import javafx.util.Pair;

public class UNSUBSCRIBE implements Message {
    private String topic;
    private int id;
    private String userName;
    private ConnectionHandler handler;
    private DataBase dataBase;
    public UNSUBSCRIBE(String topic, int id,String userName, ConnectionHandler handler) {
        this.topic = topic;
        this.id = id;
        this.userName=userName;
        this.handler = handler;
        dataBase= DataBase.getInstance();
    }

    @Override
    public void process() {
        dataBase.getTopics().get(topic).remove(new Pair<>(userName,id));

    }
}
