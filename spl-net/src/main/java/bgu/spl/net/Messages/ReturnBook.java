package bgu.spl.net.Messages;

import bgu.spl.net.srv.DataBase;

import javax.xml.crypto.Data;

public class ReturnBook implements Message {
    private String topic, bookName, userFrom, userTo;
    private DataBase dataBase;

    public ReturnBook(String topic, String bookName, DataBase dataBase) {
        this.topic = topic;
        this.bookName = bookName;
        this.dataBase = DataBase.getInstance();
    }

    @Override
    public void process() {
        dataBase.getClintesBooks().get(userFrom).remove(bookName);

    }
}
