//
// Created by amit on 1/14/20.
//

#include "../include/Keyboard.h"
#include "../include/ConnectionHandler.h"
#include "../include/Books.h"


#include <unordered_map>
#include <iostream>
#include <cassert>
using namespace std;

//eyboard thread
void Keyboard::process(ConnectionHandler handler, Books books) {
    const short bufsize = 1024;
    int id = 0;
    int receiptnumber = 0;
    string myname;
    unordered_map<string, int> genreIdMap;

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

            myname = loginName;
            stompframe = "CONNECT\naccept-version:1.2\nhost:"+hostport+"login:"+loginName+"passcode:"+passcode+"\n\n\0";

        }
        if (firstword == "join") {
            string genre = line.substr(0,line.find_first_of(' '));
            stompframe = "SUBSCRIBE"
                         "\ndestination:" + genre +
                         "\nid:"+to_string(id)+
                         "\nreceipt:"+to_string(receiptnumber)+
                         "\n\n\0";
            id++;
            receiptnumber++;
            genreIdMap[genre] = id;
        }
        if (firstword == "exit") {
            string genre = line;

            stompframe = "UNSUBSCRIBE\n"+ to_string(genreIdMap[genre]) + "\n\n\0";
            genreIdMap.erase(genre); // deletes it from the map
        }
        if (firstword == "add") {
            spaceindex = line.find_first_of(' ');
            string genre = line.substr(0, spaceindex);
            line = line.substr(spaceindex + 1);       ///line=book name

            Book *book = new Book(line, "myself", genre, true);
            books.addBook(*book);
        }


        if (firstword == "borrow") {
            spaceindex = line.find_first_of(' ');
            string genre = line.substr(0, spaceindex);
            line = line.substr(spaceindex + 1);       ///line=book name
            stompframe="SEND\ndestination:"+genre+
                    "\n\n"+myname+" wish to borrow "+line+"\n\0";

        }
        if (firstword == "return") {
            spaceindex = line.find_first_of(' ');
            string genre = line.substr(0, spaceindex);
            line = line.substr(spaceindex + 1);       ///line=book name

            stompframe="SEND\ndestination:"+genre+
                       "\n\nReturning "+line+" to "+books.getBook(line).getLender()+"\n\0";
        }
        if (firstword == "logout") {
            stompframe="DISCONNECT"
                       "\nreceipt:"+to_string(receiptnumber)+"\n\n\0";
            receiptnumber++;
        }


        //ALREADY AS STOMP, SEND AS BYTES TO SERVER:
        if (!handler.sendLine(stompframe)) {
            std::cout << "Disconnected. Exiting...\n" << std::endl;
            terminate = true;
        }


    }
}





