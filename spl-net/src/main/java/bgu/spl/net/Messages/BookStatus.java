package bgu.spl.net.Messages;

import bgu.spl.net.srv.ConnectionsImp;

public class BookStatus implements Message {
    private String topic;

    public BookStatus(String topic) {
        this.topic = topic;
    }

    @Override
    public void process() {
        ConnectionsImp.getInstance().send(topic,"SEND\n" +
                "destination:" + topic +
                "\n\n" + "book status\n\u0000" );
    }
}
