package bgu.spl.net.Messages;

import bgu.spl.net.srv.ConnectionHandler;

public class CONNECTED implements Message {
    private ConnectionHandler handler;
    private double version;
    public CONNECTED( double version,ConnectionHandler handler)  {
        this.version=version;
        this.handler=handler;
    }

    @Override
    public void process() {
        handler.send("CONNECTED " + "version:" + version + "\n\n" + "\u0000");
    }
}
