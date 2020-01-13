package bgu.spl.net.Messages;

public interface Message {
    String command = null;
    String[] headers = null;
    String body = null;

    String getCommand();
    void setCommand(String command);
    String[] getHeaders();
    void setHeaders(String[] headers);
    String getBody();
    void setBody(String body);

    void process();
}
