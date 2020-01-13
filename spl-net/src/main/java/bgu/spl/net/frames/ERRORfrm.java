package bgu.spl.net.frames;

import bgu.spl.net.srv.ConnectionsImp;

import java.io.IOException;
public class ERRORfrm extends Frame {
    private int connectionId;
    public ERRORfrm(int connectionId, String[] headers, String body) {
        super(headers, body);
        this.connectionId=connectionId;
    }

    @Override
    public void process() {

        ConnectionsImp.getInstance().send(connectionId,body);


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
