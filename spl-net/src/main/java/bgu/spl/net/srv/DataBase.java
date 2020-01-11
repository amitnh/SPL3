package bgu.spl.net.srv;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class DataBase {
    private DataBase database = new DataBase();
    private ConcurrentHashMap<String,String> users; // user name , passcode
    private ConcurrentHashMap<String,String> topics;// topic, user names list


    private DataBase(){
        users = new ConcurrentHashMap<>();
    }
    public DataBase getInstance(){return database;}// singleton
}
