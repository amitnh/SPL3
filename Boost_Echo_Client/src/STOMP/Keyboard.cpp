//
// Created by amit on 1/14/20.
//

#include "include/Keyboard.h"
#include "include/MessageEncDec.h"
using namespace std;

//eyboard thread
void Keyboard() {
    const short bufsize = 1024;
    char buf[bufsize];
    while(!terminate) {

        std::cin.getline(buf, bufsize);
        std::string line(buf);
        int len = line.length();

        if(len>0) {
            //encode
            MesseageEncDec.Decode(ConnectionHandlerImp.getFrame(line););
        }

    }
}