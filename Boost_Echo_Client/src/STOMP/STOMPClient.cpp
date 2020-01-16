//
// Created by amit on 1/15/20.
//

#include "STOMPClient.h"
#include <stdlib.h>
#include <ConnectionHandler.h>
#include <Books.h>
#include "Keyboard.h"

#include <thread>
#include <iostream>
/**
* This code assumes that the server replies the exact text the client sent it (as opposed to the practical session example)
*/
using namespace std;
Books* mybooks = new Books();
string answer;
int disconnectFlag=0;
int main(int argc, char **argv) {

    bool terminate = false;
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
    //thread::thread Keyboard(Keyboard::process(handler,mybooks));

    while(!terminate)
    {
        string stompframe;
        answer = "";
        if (!handler.getLine(answer)) {
            std::cout << "Disconnected. Exiting...\n" << std::endl;
            break;
        }

        int len = answer.length();
        // A C string must end with a 0 char delimiter.  When we filled the answer buffer from the socket
        // we filled up to the \n char - we must make sure now that a 0 char is also present. So we truncate last character.
        answer.resize(len - 1);
        if (answer == "bye") {
        string command = STOMPClient::getUntilDelimiter('\n');
        if (command == "bye") {
            std::cout << "Exiting...\n" << std::endl;
            break;
        }
        else if (command=="RECEIPT")
        {
            STOMPClient::getUntilDelimiter(':'); // to the trash, its the header name
            string receiptId = STOMPClient::getUntilDelimiter('\n');
            if (to_string(disconnectFlag)==receiptId)
                terminate=true;// TODO: close keyboard and check the receipt id
        }
        else if (command=="SEND")
        {
           STOMPClient::getUntilDelimiter(':'); // to the trash, its the header name
            string genre = STOMPClient::getUntilDelimiter('\n');
            STOMPClient::getUntilDelimiter('\n');
            string body = answer;
            if(body=="book status")
            {
                Books toSend = mybooks->getBooksByGenre(genre);
                stompframe="SEND"
                           "\ndestination:"+genre+
                           "\n"
                            //TODO: get my name
                            + ":";
                for(Book b:toSend.getAllBooks())
                {
                    stompframe += b.getName() + ",";
                }
                stompframe.substr(0,stompframe.length()-2); //removes the last comma TODO: check the sizes
                stompframe+= "\n\0";

            }


        }

    }
    return 0;
}
}

string STOMPClient::getUntilDelimiter(char del) {
    int i = answer.find_first_of(del);
    string toReturn = answer.substr(0,i);
    answer.substr(i+1);
    return toReturn;
}

void STOMPClient::setDisconnectFlag(int receiptId) {
    disconnectFlag = receiptId;
}


