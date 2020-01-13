package bgu.spl.net.Messages;

import bgu.spl.net.srv.ConnectionHandler;
import bgu.spl.net.srv.ConnectionsImp;
import bgu.spl.net.srv.DataBase;

public class DISCONNECT implements Message {
    private ConnectionHandler handler;
    private int id;
    private String userName;
    private DataBase dataBase;

    public DISCONNECT(ConnectionHandler handler, int id,String userName) {
        this.handler = handler;
        this.id = id;
        this.dataBase=DataBase.getInstance();
        this.userName=userName;
    }

    @Override
    public void process() {
    new RECIEPT("RECIEPT\n" +
            "receipt-id:"+id +
            "\n\n" +
            "\u0000",handler);

    dataBase.removeUser(userName);
    }
}
