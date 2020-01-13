package bgu.spl.net.api;

import bgu.spl.net.Messages.Message;
import bgu.spl.net.frames.*;
import bgu.spl.net.srv.Connections;
import bgu.spl.net.srv.ConnectionsImp;
import bgu.spl.net.srv.DataBase;
import bgu.spl.net.srv.User;

import javax.xml.crypto.Data;
import java.util.function.Supplier;

public class StompMessagingProtocolImp implements StompMessagingProtocol, Supplier {
    private boolean shouldTerminate = false;
    private int connectionId;
    private User user;
    private ConnectionsImp connections;
    @Override
    public void start(int connectionId, ConnectionsImp connections) {
        this.connections = ConnectionsImp.getInstance(); //TODO: maybe change it to not instance
        this.connectionId=connectionId;
        this.user = DataBase.getInstance().getUserByConnectionId(connectionId);// user has only ConnectionId and Handler !
    }
    @Override
    public void process(Frame frame) {
        if(frame.getClass()== CONNECT.class)
        {

        }
        else if (frame.getClass()== SEND.class)
        {
            frame.process();
        }
        else if (frame.getClass()== SUBSCRIBE.class)
        {
            frame.process();
            String[] receipt = {frame.getHeaders()[1]}; // headers[1] == id
            new RECEIPT(connectionId,receipt,"").process();
        }
        else if (frame.getClass()== UNSUBSCRIBE.class)
        {
            frame.process();
            String[] receipt = {frame.getHeaders()[1]}; // headers[1] == id
            new RECEIPT(connectionId,receipt,"").process();
        }
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
