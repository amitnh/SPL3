#include <stdlib.h>
#include <connectionHandlerImp.h>
#include <Books.h>
#include <MessageEncDec.h>

/**
* This code assumes that the server replies the exact text the client sent it (as opposed to the practical session example)
*/
int main (int argc, char *argv[]) {
    if (argc < 3) {
        std::cerr << "Usage: " << argv[0] << " host port" << std::endl << std::endl;
        return -1;
    }
    std::string host = argv[1];
    short port = atoi(argv[2]);

    connectionHandlerImp connectionHandlerImp(host, port);
    if (!connectionHandlerImp.connect()) {
        std::cerr << "Cannot connect to " << host << ":" << port << std::endl;
        return 1;
    }
    bool terminate = false;
    Books mybooks = new Books();

    while(!terminate){
        const short bufsize = 1024;
        char buf[bufsize];
        std::cout<<"what would you like to do sir?"<<std::endl;
        {
            std::cin.getline(buf, bufsize);
            std::string line(buf);
                                                          //TODO make thread for keyboard
            int len = line.length();
            if (!connectionHandlerImp.sendLine(line)) {
                std::cout << "Disconnected. Exiting...\n" << std::endl;
                break;
            }
        }
        
        if (!connectionHandlerImp.getLine(answer)) {
            std::cout << "Disconnected. Exiting...\n" << std::endl;
            break;
        }

        len=answer.length();
        // A C string must end with a 0 char delimiter.  When we filled the answer buffer from the socket
        // we filled up to the \n char - we must make sure now that a 0 char is also present. So we truncate last character.
        answer.resize(len-1);
        std::cout << "Reply: " << answer << " " << len << " bytes " << std::endl << std::endl;
        if (answer == "bye") {
            std::cout << "Exiting...\n" << std::endl;
            break;
        }
        
        
    }

    return 0;
}