package bgu.spl.net.Messages;

import bgu.spl.net.srv.ConnectionHandler;

import java.io.IOException;

public class ERRORmsg implements Message{
    private String msg;
    private ConnectionHandler handler;
    public ERRORmsg(String msg,ConnectionHandler handler)  {
        this.msg=msg;
        this.handler=handler;
    }

    @Override
    public void process() {
        handler.send("ERROR " + msg);
        try {
            handler.close(); // TODO: B carefull ! it may close before sending the msg in the Reactor
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
