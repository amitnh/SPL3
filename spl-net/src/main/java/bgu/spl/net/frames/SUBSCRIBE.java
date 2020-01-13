package bgu.spl.net.frames;

import bgu.spl.net.Messages.RECIEPT;
import bgu.spl.net.srv.DataBase;
import javafx.util.Pair;

import java.util.concurrent.ConcurrentLinkedQueue;

public class SUBSCRIBE  extends Frame{
    public SUBSCRIBE(String[] headers, String body) {
        super(headers, body);
    }

    @Override
    public void process() {
        DataBase.getInstance().getTopics().putIfAbsent(topic,new ConcurrentLinkedQueue<>());
        DataBase.getInstance().getTopics().get(topic).add(new Pair<>(userName,id));
        new RECIEPT().process();
    }
}
