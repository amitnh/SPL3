package bgu.spl.net.api;

import bgu.spl.net.frames.Frame;

import java.util.function.Supplier;

public class MessageEncDecImp implements MessageEncoderDecoder, Supplier {
    private byte[] bytes = new byte[1 << 10]; //start with 1k

    @Override
    public Frame decodeNextByte(byte nextByte) {

        return null;
    } //TODO: copy the encoder decoder from a friend

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
