package bgu.spl.net.Messages;

import bgu.spl.net.srv.DataBase;

public class BorrowTakingBook implements Message {
    private String topic,bookName,userFrom,userTo;
    private DataBase dataBase;

    public BorrowTakingBook(String topic, String bookName, String userFrom, String userTo) {
        this.topic = topic;
        this.bookName = bookName;
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.dataBase=DataBase.getInstance();
    }

    @Override
    public void process() {
        dataBase.getClintesBooks().get(userFrom).remove(bookName);
        dataBase.getClintesBooks().get(userTo).add(bookName);
    }
}
