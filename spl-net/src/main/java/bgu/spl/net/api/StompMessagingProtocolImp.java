package bgu.spl.net.api;

import bgu.spl.net.srv.Connections;

import java.util.function.Supplier;

public class StompMessagingProtocolImp implements StompMessagingProtocol, Supplier {
    @Override
    public void start(int connectionId, Connections<String> connections) {

    }

    @Override
    public void process(String message) {

    }

    @Override
    public boolean shouldTerminate() {
        return false;
    }

    @Override
    public Object get() {
        return new StompMessagingProtocolImp();
    }
}
