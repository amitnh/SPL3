//
// Created by amit on 1/15/20.
//

#include "../../include/STOMPClient.h"
#include <stdlib.h>
#include <connectionHandler.h>
#include <Books.h>
#include <MessageEncDec.h>

/**
* This code assumes that the server replies the exact text the client sent it (as opposed to the practical session example)
*/

public static boolean terminate=false;
public Books mybooks = new Books();

int main (int argc, char *argv[]) {
    if (argc < 3) {
        std::cerr << "Usage: " << argv[0] << " host port" << std::endl << std::endl;
        return -1;
    }
    std::string host = argv[1];
    short port = atoi(argv[2]);

    connectionHandler connectionHandler(host, port);
    if (!connectionHandler.connect()) {
        std::cerr << "Cannot connect to " << host << ":" << port << std::endl;
        return 1;
    }
    std::cout<<"connected to server"<<std::endl;

    thread::thread keyboard(Keyboard);

    While(!terminate)
    {
        if (!connectionHandler.getLine(answer)) {
            std::cout << "Disconnected. Exiting...\n" << std::endl;
            break;
        }

        len = answer.length();
        // A C string must end with a 0 char delimiter.  When we filled the answer buffer from the socket
        // we filled up to the \n char - we must make sure now that a 0 char is also present. So we truncate last character.
        answer.resize(len - 1);
        if (answer == "bye") {
            std::cout << "Exiting...\n" << std::endl;
            break;
        }
    }
    return 0;
}