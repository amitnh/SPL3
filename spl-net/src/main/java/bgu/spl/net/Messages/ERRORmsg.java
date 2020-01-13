package bgu.spl.net.Messages;

import bgu.spl.net.srv.ConnectionHandler;

import java.io.IOException;

public class ERRORmsg implements Message {
    private String msg;
    private int receiptId;
    private ConnectionHandler handler;
    public ERRORmsg(String msg,int receiptId,ConnectionHandler handler)  {
        this.msg=msg;
        this.receiptId=receiptId;
        this.handler=handler;
    }

    @Override
    public void process() {
        handler.send("ERROR\n" +
                "receipt-id: " + receiptId +
                "\nmessage: " + msg +
                "\n\n" +
                "\u0000");
        try {
            handler.close(); // TODO: B carefull ! it may close before sending the msg in the Reactor
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
