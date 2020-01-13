package bgu.spl.net.srv;

import javafx.util.Pair;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class DataBase {
    int activeid=0;
    private static DataBase instance = new DataBase();
    private ConcurrentHashMap<String, String> usersPass; // user name , passcode
    private ConcurrentHashMap<Integer,String > activeUsers; // userId, user name
    private ConcurrentHashMap<String,ConcurrentLinkedQueue<Pair<String,Integer>>> topics;// topic, Pair<user name ,id> list
    private ConcurrentHashMap<String,ConcurrentLinkedQueue<String>> clintesBooks;// user name, books list

    private DataBase(){
        usersPass = new ConcurrentHashMap<>();
        activeUsers = new ConcurrentHashMap<>();
        topics = new ConcurrentHashMap<>();
        clintesBooks = new ConcurrentHashMap<>();
    }
    public static DataBase getInstance(){return instance;}// singleton

    public ConcurrentHashMap<String, String> getUsersPass() {
        return usersPass;
    }


    //Getters
    public ConcurrentHashMap<Integer, String> getActiveUsers() {
        return activeUsers;
    }
    public ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> getClintesBooks() {
        return clintesBooks;
    }

    public ConcurrentHashMap<String,ConcurrentLinkedQueue<Pair<String,Integer>>>  getTopics() {
        return topics;
    }
    public void removeUser(int connectionId){
        clintesBooks.remove(connectionId);
//        for(ConcurrentHashMap<String,ConcurrentLinkedQueue<Pair<String,Integer>>> topic:topics.values())TODO: LATER.....
//        {
//            topic.remove(connectionId);
//        }
       usersPass.remove(activeUsers.get(connectionId));
        activeUsers.remove(connectionId);
    }


    //Setters
       public void setUsersPass(ConcurrentHashMap<String, String> usersPass) {
        this.usersPass = usersPass;
    }

    public void setUsersId(ConcurrentHashMap<Integer, String> usersId) {
        this.activeUsers = usersId;
    }

    public void setTopics(ConcurrentHashMap<String,ConcurrentLinkedQueue<Pair<String,Integer>>>  topics) {
        this.topics = topics;
    }

    public void setClintesBooks(ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> clintesBooks) {
        this.clintesBooks = clintesBooks;
    }



    public void addActiveUser(String name,String password) {
        usersPass.putIfAbsent(name,password);
        activeUsers.put(++activeid,name);
    }

}
