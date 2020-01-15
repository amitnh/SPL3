//
// Created by amit on 1/14/20.
//

#include "../include/Keyboard.h"
#include "../include/connectionHandler.h"

using namespace std;

//eyboard thread
void Keyboard::process(ConnectionHandler handler) {
    const short bufsize = 1024;
    int id = 0;
    int receiptnumber = 0;

    char buf[bufsize];
    bool terminate = false;
    while (!terminate) {
        cin.getline(buf, bufsize); // blocked
        string line(buf);
        int len = line.length();

        //MAKE STOMP FROM LINE

        string stompframe;
        int spaceindex = line.find_first_of(' ');
        string firstword = line.substr(0, spaceindex);
        line = line.substr(spaceindex+1);
        //now checking for command:
        if (firstword == "login") {
            spaceindex = line.find_first_of(' ');
            string hostport = line.substr(0, spaceindex);
            line = line.substr(spaceindex+1);

            spaceindex = line.find_first_of(' ');
            string loginName = line.substr(0, spaceindex);
            line = line.substr(spaceindex+1);

            spaceindex = line.find_first_of(' ');
            string passcode = line.substr(0, spaceindex);
            line = line.substr(spaceindex+1);

            stompframe = "CONNECT\naccept-version:1.2\nhost:"+hostport+"login:"+loginName+"passcode:"+passcode+"\n\n\0";

        }
        if (firstword == "join") {
            stompframe = "SUBSCRIBE\ndestination:/topic/"+
                    line.substr(0,line.find_first_of(' '))+
                    "\nid:"+to_string(id)+"\nreceipt:"+to_string(receiptnumber)+"\n\n\0";
            id++;
            receiptnumber++;
        }
        if (firstword == "exit") {
            stompframe = "UNSUBSCRIBE\n"+******TODO*ID FROM DATA BASE*****+"\n\n\0";

        }
        if (firstword == "add") {

        }
        if (firstword == "borrow") {

        }
        if (firstword == "return") {

        }
        if (firstword == "logout") {

        }


        //ALREADY AS STOMP, SEND AS BYTES TO SERVER:



        if (!handler.sendLine(line)) {
            std::cout << "Disconnected. Exiting...\n" << std::endl;
            terminate = true;
        }


    }
}





