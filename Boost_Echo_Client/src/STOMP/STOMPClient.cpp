//
// Created by amit on 1/15/20.
//

#include "../../include/STOMPClient.h"
#include <stdlib.h>
#include <connectionHandler.h>
#include <Books.h>
#include <thread>
#include "../include/Keyboard.h"
#include <iostream>


/**
* This code assumes that the server replies the exact text the client sent it (as opposed to the practical session example)
*/

static bool terminate=false;
Books mybooks;

int main(int argc, char **argv) {

    if (argc < 3) {
        std::cerr << "Usage: " << argv[0] << " host port" << std::endl << std::endl;
        return -1;
    }
    std::string host = argv[1];
    short port = atoi(argv[2]);

    ConnectionHandler handler(host, port);
    if (!handler.connect()) {
        std::cerr << "Cannot connect to " << host << ":" << port << std::endl;
        return 1;
    }
    std::cout<<"connected to server"<<std::endl;

    thread::thread keyboard(Keyboard::process(handler,mybooks));

    While(!terminate)
    {
        string stompframe;
        string answer;
        if (!handler.getLine(answer)) {
            std::cout << "Disconnected. Exiting...\n" << std::endl;
            break;
        }
        string command = getUntilDelimiter(*answer,'\n');
        if (command == "bye") {
            std::cout << "Exiting...\n" << std::endl;
            break;
        }
        else if (command=="RECEIPT")
        {
            getUntilDelimiter(*answer,':'); // to the trash, its the header name
            string receiptId = getUntilDelimiter(*answer,'\n');
            if (disconnectFlag)
                terminate=true;// TODO: close keyboard and check the receipt id
        }
        else if (command=="SEND")
        {
            getUntilDelimiter(*answer,':'); // to the trash, its the header name
            string genre = getUntilDelimiter(*answer,'\n');
            getUntilDelimiter(*answer,'\n');
            string body = answer;
            if(body=="book status")
            {
                Book toSend = mybooks.getBooksByGenre(genre);
                stompframe="SEND"
                           "\ndestination:"+genre+
                           "\n" +
                            //TODO: get my name
                            + ":");
                for(name n:toSend.getName())
                {
                    stompframe += n + ",";
                }

            }


        }

    }
    return 0;
}

string STOMPClient::getUntilDelimiter(string *s, char del) { // updates s and
    int i = s->find_first_of(del);
    string toReturn = s->substr(0,i);
    s->substr(i+1);
    return toReturn;
}

