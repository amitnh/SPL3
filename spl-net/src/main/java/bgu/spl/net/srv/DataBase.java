package bgu.spl.net.srv;

import javafx.util.Pair;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class DataBase {
    private ConcurrentLinkedQueue<User> users;
    private ConcurrentHashMap<String,ConcurrentLinkedQueue<User>> topics;// topic, list of users

    int id=0;
    private static DataBase instance = new DataBase();
    public static DataBase getInstance(){return instance;}

    public DataBase(ConcurrentLinkedQueue<User> users, ConcurrentHashMap<String, ConcurrentLinkedQueue<User>> topics, int id) {
        this.users = users;
        this.topics = topics;
        this.id = id;
    }

    public ConcurrentLinkedQueue<User> getUsers() {
        return users;
    }

    public void setUsers(ConcurrentLinkedQueue<User> users) {
        this.users = users;
    }

    public ConcurrentHashMap<String, ConcurrentLinkedQueue<User>> getTopics() {
        return topics;
    }

    public void setTopics(ConcurrentHashMap<String, ConcurrentLinkedQueue<User>> topics) {
        this.topics = topics;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addActiveUser(String name, String password) {
        usersPass.putIfAbsent(name,password);
        activeUsers.put(++id,name);
    }

    public User getUserByConnectionId(int connectionId) {
        for( User u:users)
        {
            if(u.getConnectionId()==connectionId)
                return u;
        }
        return null;
    }
}
