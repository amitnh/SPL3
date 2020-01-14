package bgu.spl.net.api;

import bgu.spl.net.frames.*;

import java.util.function.Supplier;

public class MessageEncDecImp implements MessageEncoderDecoder, Supplier {
    private byte[] bytes = new byte[1 << 10]; //start with 1k
    private Frame frame;
    private int state=0,i=0;
    @Override
    public Frame decodeNextByte(byte nextByte) {
    if (nextByte=='\u0000')
    {
        frame.setBody(bytes.toString());
        bytes= new byte[1 << 10];
        return frame;
    }
    if(i==0)
    {
        state=0;
    }
    if (state==0)
    {
        if (nextByte=='\n')
        {
            String command = new String(bytes);
            switch (command){
                case "SEND":
                    frame = new SEND(null,null);
                    break;
                case "CONNECT":
                    frame = new CONNECT(null,null,null);
                    break;
                case "SUBSCRIBE":
                    frame = new SUBSCRIBE(null,null,null);
                    break;
                case "UNSUBSCRIBE":
                    frame = new UNSUBSCRIBE(null,null,null);
                    break;
                case "DISCONNECT":
                    frame = new DISCONNECT(null,null);
                    break;
            }
            state=1;
        }
        else
        {
            bytes[i] = nextByte;
        }
    }


        return null;
    }

//    @Override
//    public byte[] encode(Frame message) {
//        return new byte[0];
//    }
    @Override
    public byte[] encode(Frame message) {
        String str;
        String frameClass;
        int last= message.getClass().toString().lastIndexOf('.');// TODO:check
        frameClass = message.getClass().toString().substring(last);
        str = frameClass;
        str += "\n";
        switch (frameClass) {
            case "RECEIPT":
                str+= "receipt-id:" + message.getHeaders()[0];
                break;
            case "CONNECTED":
                str+= "accept-version:" + message.getHeaders()[0];
                str+= "host:" + message.getHeaders()[1];
                str+= "login:" + message.getHeaders()[2];
                str+= "passcode:" + message.getHeaders()[3];
                break;
            case "ERRORfrm":
                if(message.getHeaders()[0]!="")
                    str+= "receipt-id:" + message.getHeaders()[0];
                str+= "message: " + message.getHeaders()[1];
                break;
            case "SEND":
                str+= "destination:" + message.getHeaders()[0];
                break;
        }
        str += "\n\n";
        str += message.getBody();
        str +="\u0000";

        return str.getBytes(); //uses utf8 by default
    }





//__________________________________________________________________________________________________
    //msgEndDec Factory:
    @Override
    public Object get() {
        return new MessageEncDecImp();
    }
}
