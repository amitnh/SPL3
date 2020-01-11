package bgu.spl.net.Messages;

import bgu.spl.net.srv.DataBase;

public class ERRORmsg implements Message{
    private String errorMsg;
    private String name;
    private DataBase dataBase;
    public ERRORmsg(String errorMsg) {
        this.errorMsg=errorMsg;
        this.name = name;
        dataBase= DataBase.getInstance();
    }
    @Override
    public void process() {
        dataBase.getConnectionsHandlers();

    }
}
