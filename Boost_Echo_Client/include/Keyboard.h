//
// Created by amit on 1/14/20.
//

#ifndef BOOST_ECHO_CLIENT_KEYBOARD_H
#define BOOST_ECHO_CLIENT_KEYBOARD_H


#include "ConnectionHandler.h"
#include "Books.h"

class Keyboard {
public:
    void process(ConnectionHandler handler, Books books);

};


#endif //BOOST_ECHO_CLIENT_KEYBOARD_H
