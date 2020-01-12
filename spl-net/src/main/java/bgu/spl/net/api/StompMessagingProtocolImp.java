package bgu.spl.net.api;

import bgu.spl.net.Messages.Message;
import bgu.spl.net.srv.Connections;

import java.util.function.Supplier;

public class StompMessagingProtocolImp implements StompMessagingProtocol, Supplier {
    private boolean shouldTerminate = false;

    @Override
    public void start(int connectionId, Connections<String> connections) {

    }

    @Override
    public void process(Message msg) {
        msg.process();
    }

    @Override
    public boolean shouldTerminate() {
        return shouldTerminate;
    }
    public void terminate(){ shouldTerminate=true;}



//__________________________________________________________________________________________________
    @Override // factory method
    public StompMessagingProtocolImp get() {
        return new StompMessagingProtocolImp();
    }

}
