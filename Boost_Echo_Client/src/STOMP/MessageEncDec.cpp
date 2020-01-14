//
// Created by kapelnik@wincs.cs.bgu.ac.il on 11/01/2020.
//

#include "../../include/MessageEncDec.h"
std::vector<std::string> FramesReceived;

MessageEncDec::MessageEncDec(){


    void MessageEncDec::Decode(std::string frame, int length){

        //frame already in UTF8, UNSTOMP it:
        //Switch(first line of frame)
        //....
        //....
        //send to client thread to process..


    }
    std::string MessageEncDec::Encode(const std::string& frame, int length){

        //frame already ready as STOMP, Send as bytes:
        return frame;
    }

    void MessageEncDec::add(std::string frametodecode){
        FramesReceived.pushback(frametodecode);
    }



}