package bgu.spl.net.Messages;

import bgu.spl.net.srv.ConnectionHandler;
import bgu.spl.net.srv.DataBase;
import javafx.util.Pair;

import java.util.concurrent.ConcurrentLinkedQueue;

public class SUBSCRIBE implements Message {
    private String topic;
    private int id;
    private String userName;
    private ConnectionHandler handler;
    private DataBase dataBase;
    public SUBSCRIBE(String topic, int id,String userName, ConnectionHandler handler) {
        this.topic = topic;
        this.id = id;
        this.userName=userName;
        this.handler = handler;
        dataBase= DataBase.getInstance();
    }

    @Override
    public void process() {
        dataBase.getTopics().putIfAbsent(topic,new ConcurrentLinkedQueue<>());
        dataBase.getTopics().get(topic).add(new Pair<>(userName,id));
        new RECIEPT("SUBSCRIBE\n" +
                "destination:"+ topic +
                "\nid:" + id +
                "\n\n" +
                "\u0000",handler).process();
    }
}
