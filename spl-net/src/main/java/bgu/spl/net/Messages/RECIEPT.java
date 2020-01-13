package bgu.spl.net.Messages;

import bgu.spl.net.srv.ConnectionHandler;

public class RECIEPT implements Message {
    private String msg;
    private int id;
    private ConnectionHandler handler;

    public RECIEPT(String msg, ConnectionHandler handler) {
        this.msg = msg;
        this.id = id;
        this.handler = handler;
    }

    @Override
    public void process() {
        handler.send(msg);
    }
}
