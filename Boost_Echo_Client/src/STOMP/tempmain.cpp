#include <stdlib.h>
#include <connectionHandler.h>
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

    ConnectionHandler connectionHandler(host, port);
    if (!connectionHandler.connect()) {
        std::cerr << "Cannot connect to " << host << ":" << port << std::endl;
        return 1;
    }
    bool terminate = false;
    Books mybooks = new Books();

    while(!terminate){
        const short bufsize = 1024;
        char buf[bufsize];
        std::cout<<"what would you like to do sir?"<<std::endl;
        std::cin.getline(buf, bufsize);  //TODO make thread for keyboard

        std::string line(buf);

        //int len=line.length();
        line  = MessageEncDec.Encode(line);

    }

    return 0;
}