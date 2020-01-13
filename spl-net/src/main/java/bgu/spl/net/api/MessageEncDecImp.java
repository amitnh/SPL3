package bgu.spl.net.api;

import bgu.spl.net.frames.Frame;

import java.util.function.Supplier;

public class MessageEncDecImp implements MessageEncoderDecoder, Supplier {

    @Override
    public Frame decodeNextByte(byte nextByte) {
        return null;
    } //TODO: copy the encoder decoder from a friend

    @Override
    public byte[] encode(Frame message) {
        return new byte[0];
    }






//__________________________________________________________________________________________________
    //msgEndDec Factory:
    @Override
    public Object get() {
        return new MessageEncDecImp();
    }
}
