//
// Created by kapelnik@wincs.cs.bgu.ac.il on 11/01/2020.
//

#include "../../include/Books.h"
#include <vector>
#include <iostream>

using namespace std;
//book [name,owner,isavailable]

Books::Books(){}
void Books::addBook(Book book){
    books.push_back(book);
}
void Books::removeBook(string name){
    int i=0;
    for(Book b:books){
        if (b.getName()==name)
            books.erase(books.begin()+i);
        i+=1;
    }
}
Book Books::getBook(string name){
    for(Book b:books){
        if (b.getName()==name)
            return b;
    }
    return *(new Book("","","", false));
}
Books Books::getBooksByGenre(string genre){
    Books booksByGenre;
    for(Book b:books){
        if (b.getGenre()==genre)
            booksByGenre.addBook(b);
    }
    return booksByGenre;
}





//-------------BOOK----------------
const string &Book::getName() const {
    return name;
}

void Book::setName(const string &name) {
    Book::name = name;
}
bool Book::isAvailable1() const {
    return isAvailable;
}

void Book::setIsAvailable(bool isAvailable) {
    Book::isAvailable = isAvailable;
}

const string &Book::getLender() const {
    return lender;
}

void Book::setLender(const string &lender) {
    Book::lender = lender;
}

const string &Book::getGenre() const {
    return genre;
}

void Book::setGenre(const string &genre) {
    Book::genre = genre;
}

bool Book::isAvailable2() const {
    return isAvailable;
}

Book::Book(string name, string lender, string genre, bool isAvailable) {

}
