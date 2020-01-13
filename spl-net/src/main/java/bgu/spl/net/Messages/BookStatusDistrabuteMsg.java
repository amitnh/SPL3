package bgu.spl.net.Messages;

import bgu.spl.net.srv.ConnectionsImp;

public class BookStatusDistrabuteMsg implements Message {
    private String topic,msg; // msg = books of someone

    public BookStatusDistrabuteMsg(String topic, String msg) {
        this.topic = topic;
        this.msg = msg;
    }

    @Override
    public void process() {
        ConnectionsImp.getInstance().send(topic,"SEND\n" +
                "destination:" + topic +
                "\n" + msg +
                "\n\n" + "book status\n\u0000" );
    }
}
