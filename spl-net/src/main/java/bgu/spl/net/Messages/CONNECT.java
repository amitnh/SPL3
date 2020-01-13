package bgu.spl.net.Messages;

import bgu.spl.net.srv.ConnectionHandler;
import bgu.spl.net.srv.DataBase;

public class CONNECT implements Message {
    private String host,login,password;
    private int receiptId;
    private DataBase dataBase;
    private ConnectionHandler handler;
    public CONNECT(String host, String login, String password,int receiptId, ConnectionHandler  handler) {
        dataBase= DataBase.getInstance();
        this.host = host;
        this.login = login;
        this.password = password;
        this.receiptId = receiptId;
        this.handler=handler;
    }

    @Override
    public void process() {
        try{
            if(dataBase.getUsersPass().containsKey(login)) // user Exists
            {
                if(dataBase.getUsersPass().get(login)==password) // correct password
                {
                    if (!dataBase.getActiveUsers().contains(login))// user is not in active users
                    {
                        dataBase.addActiveUser(login,password);
                        new CONNECTED(1.2,handler).process(); // TODO: check what is that version
                    }
                    else
                        new ERRORmsg("User already logged in",receiptId,handler).process();
                }
                else
                    new ERRORmsg("Wrong password",receiptId,handler).process();
            }
            else // new user
            {
                dataBase.addActiveUser(login,password);
                new CONNECTED(1.2,handler).process();
            }
        }catch (Exception exp) {System.out.println("Could not connect to server");}
    }
}
