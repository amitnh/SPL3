package bgu.spl.net.api;

import bgu.spl.net.frames.*;

import java.util.function.Supplier;

public class MessageEncDecImp implements MessageEncoderDecoder, Supplier {
    private byte[] bytes = new byte[1 << 10]; //start with 1k
    private Frame frame;
    private int state=0,i=0;
    private boolean isHeader=false;
    private String[] headers;
    private int headersIndex=0;
    private String command;
    @Override
    public Frame decodeNextByte(byte nextByte) {
        if (nextByte=='\u0000')
        {
            switch (command){
                case "SEND":
                    frame = new SEND(headers,new String(bytes));
                    break;
                case "CONNECT":
                    frame = new CONNECT(null,headers,new String(bytes));
                    break;
                case "SUBSCRIBE":
                    frame = new SUBSCRIBE(null,headers,new String(bytes));
                    break;
                case "UNSUBSCRIBE":
                    frame = new UNSUBSCRIBE(null,headers,new String(bytes));
                    break;
                case "DISCONNECT":
                    frame = new DISCONNECT(headers,new String(bytes));
                    break;
            }
            bytes= new byte[1 << 10];
            i=0;
            state=0;
            isHeader=false;
            headersIndex=0;
            return frame;
        }
        if (state==0)
        {
            if (nextByte=='\n')
            {
                command = new String(bytes);
                bytes = new byte[1 << 10];
                i=0;
                // switc
                state=1;
                headers = new String[]{};
            }
            else
            {
                bytes[i] = nextByte;
                i++;
            }
        }
        else if (state==1)
        {
            if(nextByte=='\n') // end of line
            {
                if (bytes[i-1]=='\n')
                {
                    state=2; // going to body state
                }
                else if(isHeader) // headline or the data ?
                {
                    headers[headersIndex]=new String(bytes);
                    headersIndex++;
                    bytes = new byte[1 << 10];
                    i=0;
                    isHeader=false;
                }
            }
            else if(nextByte==':')
            {
                bytes = new byte[1 << 10];
                i=0;
                isHeader=true;
            }
            else
            {
                bytes[i] = nextByte;
                i++;
            }
        }
        else if(state==2)
        {
            bytes[i] = nextByte;
            i++;
        }

        return null;
    }


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
