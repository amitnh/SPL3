package bgu.spl.net.srv;

import javafx.util.Pair;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class DataBase {
    int activeid=0;
    private static DataBase instance = new DataBase();
    private ConcurrentHashMap<String, String> usersPass; // user name , passcode
    private ConcurrentHashMap<Integer,String > activeUsers; // userId, user name
    private ConcurrentHashMap<String,ConcurrentLinkedQueue<Integer>> topics;// topic, user id list
    private ConcurrentHashMap<Integer,ConcurrentLinkedQueue<String>> clintesBooks;// user id, books list

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
    public ConcurrentHashMap<Integer, ConcurrentLinkedQueue<String>> getClintesBooks() {
        return clintesBooks;
    }

    public ConcurrentHashMap<String,ConcurrentLinkedQueue<Integer>> getTopics() {
        return topics;
    }
    public void removeUser(int connectionId){
        clintesBooks.remove(connectionId);
        for(ConcurrentLinkedQueue<Integer> topic:topics.values())
        {
            topic.remove(connectionId);
        }
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

    public void setTopics(ConcurrentHashMap<String, ConcurrentLinkedQueue<Integer>> topics) {
        this.topics = topics;
    }

    public void setClintesBooks(ConcurrentHashMap<Integer, ConcurrentLinkedQueue<String>> clintesBooks) {
        this.clintesBooks = clintesBooks;
    }



    public void addActiveUser(String name,String password) {
        usersPass.putIfAbsent(name,password);
        activeUsers.put(++activeid,name);
    }

}
