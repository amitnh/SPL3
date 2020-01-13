package bgu.spl.net.Messages;

import bgu.spl.net.srv.ConnectionsImp;
import bgu.spl.net.srv.DataBase;

public class BorrowWishTo implements Message {
    private String topic,userName,bookName;
    private DataBase dataBase;

    public BorrowWishTo(String topic, String msg, String userName, String bookName) {
        this.topic = topic;
        this.userName = userName;
        this.bookName = bookName;
        dataBase= DataBase.getInstance();
    }

    @Override
    public void process() {
        ConnectionsImp connections = ConnectionsImp.getInstance();
        connections.send(topic,"SEND\n" +
                "destination:" + topic +
                "\n\n" + userName + " wish to borrow " + bookName +"\n\u0000" );
    }
}
