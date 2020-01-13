package bgu.spl.net.frames;

import bgu.spl.net.srv.ConnectionsImp;

public class SEND extends Frame {

    public SEND(String[] headers, String body) {
        super(headers, body);
    }

    @Override
    public void process() {
        ConnectionsImp.getInstance().send(headers[0],this);
    }
}
