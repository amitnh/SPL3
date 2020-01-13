package bgu.spl.net.frames;

import bgu.spl.net.srv.DataBase;
import javafx.util.Pair;

public class UNSUBSCRIBE extends Frame {
    public UNSUBSCRIBE(String[] headers, String body) {
        super(headers, body);
    }

    @Override
    public void process() {
        DataBase.getInstance().getTopics().
        DataBase.getInstance().getTopics().get(topic).remove(new Pair<>(userName,id));
    }
}
