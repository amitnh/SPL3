package bgu.spl.net.Messages;

import bgu.spl.net.srv.ConnectionHandler;
import bgu.spl.net.srv.DataBase;

public class SENDadd implements Message {
    private String topic,userName,bookName;
    private DataBase dataBase;

    public SENDadd(String topic, String userName, String bookName) {
        this.topic = topic;
        this.userName = userName;
        this.bookName = bookName;
        dataBase= DataBase.getInstance();
    }

    @Override
    public void process() {
        dataBase.getClintesBooks().get(userName).add(bookName);
    }
}
