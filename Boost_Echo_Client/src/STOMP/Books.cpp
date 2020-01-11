//
// Created by kapelnik@wincs.cs.bgu.ac.il on 11/01/2020.
//

#include "../../include/Books.h"

std::vector<std::string> Books;

Books:Books(){}

void Books::AddBook(String name){
    Books.push_back(name);
}
void Books::RemoveBook(String name){
    int i=0;
    for(auto x : Books){
        if(x==name)
            Books.remove(i);
        i++;
    }
}




