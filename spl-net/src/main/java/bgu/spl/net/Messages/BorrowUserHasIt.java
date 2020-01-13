package bgu.spl.net.Messages;

import bgu.spl.net.srv.ConnectionsImp;

public class BorrowUserHasIt implements Message {
    private String topic,bookName,userName;

    public BorrowUserHasIt(String topic, String bookName, String userName) {
        this.topic = topic;
        this.bookName = bookName;
        this.userName = userName;
    }

    @Override
    public void process() {
        ConnectionsImp.getInstance().send(topic,"SEND\n" +
                "destination:" + topic +
                "\n\n" + userName + " has " + bookName +"\n\u0000" );
    }
}
