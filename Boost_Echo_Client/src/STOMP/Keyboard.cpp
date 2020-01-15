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


            //MAKE STOMP FROM LINE



            //ALREADY AS STOMP, SEND AS BYTES TO SERVER:



            if (!connectionHandler.sendLine(line)) {
                std::cout << "Disconnected. Exiting...\n" << std::endl;
                break;
            }



    }
}



