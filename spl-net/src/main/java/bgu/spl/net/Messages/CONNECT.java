package bgu.spl.net.Messages;

import bgu.spl.net.srv.ConnectionHandler;
import bgu.spl.net.srv.ConnectionsImp;
import bgu.spl.net.srv.DataBase;

public class CONNECT implements Message {
    private String host,login,password;
    private DataBase dataBase;
    private ConnectionHandler handler;
    public CONNECT(String host, String login, String password, ConnectionHandler handler) {
        dataBase= DataBase.getInstance();
        this.host = host;
        this.login = login;
        this.password = password;
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
                        handler.send(new CONNECTED("Login successful"));
                    }
                    else
                        handler.send(new ERRORmsg("User already logged in"));
                }
                else
                    handler.send(new ERRORmsg("Wrong password"));
            }
            else // new user
            {
                dataBase.addActiveUser(login,password);
                handler.send(new CONNECTED("Login successful"));
            }
        }catch (Exception exp) {System.out.println("Could not connect to server");}
    }
}
