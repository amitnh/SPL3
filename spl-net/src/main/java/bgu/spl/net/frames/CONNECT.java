package bgu.spl.net.frames;

import bgu.spl.net.Messages.CONNECTED;
import bgu.spl.net.Messages.ERRORmsg;
import bgu.spl.net.srv.DataBase;
import bgu.spl.net.srv.User;

public class CONNECT extends Frame {
    public CONNECT(String[] headers, String body) {
        super(headers, body);
    }

    @Override
    public void process() {
        DataBase dataBase = DataBase.getInstance();
        for (User u:dataBase.getUsers())
        {
            if(u.getName()==headers[2]) // login
            {
                if (u.getPassword()==headers[3]) // password
                {
                    if (!u.isActive())
                    {
                        u.setActive(true);
                    }
                    else //already inside, ignore ?
                    {

                    }
                }
                else if (u.getPassword()!=null)
                {
                    //wrong password
                }
            }
            else // new User name and password
            {

            }
        }




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
