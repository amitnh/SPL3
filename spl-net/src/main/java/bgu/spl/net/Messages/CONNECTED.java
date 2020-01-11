package bgu.spl.net.Messages;

import bgu.spl.net.srv.ConnectionHandler;

public class CONNECTED implements Message{
    private String msg;
    private ConnectionHandler handler;
    public CONNECTED(String msg,ConnectionHandler handler)  {
        this.msg=msg;
        this.handler=handler;
    }

    @Override
    public void process() {
        handler.send("CONNECTED " + msg);
    }
}
