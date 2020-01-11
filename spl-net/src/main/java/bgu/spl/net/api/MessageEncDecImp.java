package bgu.spl.net.api;

import java.util.function.Supplier;

public class MessageEncDecImp implements MessageEncoderDecoder, Supplier {
    @Override
    public Object decodeNextByte(byte nextByte) {
        return null;
    }

    @Override
    public byte[] encode(Object message) {
        return new byte[0];
    }



    //msgEndDec Factory:
    @Override
    public Object get() {
        return new MessageEncDecImp();
    }
}
