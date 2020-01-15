cmake_minimum_required(VERSION 3.15)
project(Boost_Echo_Client)

set(CMAKE_CXX_STANDARD 11)

include_directories(include)
link_libraries("-lboost_system -pthread")
add_executable(Boost_Echo_Client
        include/Books.h
        include/connectionHandler.h
        include/Keyboard.h
        src/STOMP/Books.cpp
        src/STOMP/Keyboard.cpp
        src/connectionHandler.cpp
        src/echoClient.cpp)
