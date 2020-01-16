//
// Created by kapelnik@wincs.cs.bgu.ac.il on 11/01/2020.
//

#ifndef BOOST_ECHO_CLIENT_BOOKS_H
#define BOOST_ECHO_CLIENT_BOOKS_H


#include <vector>
#include <iostream>

using namespace std;
class Book {
public:
    Book(string name, string lender,string genre, bool isAvailable);
    const string &getName() const;
    void setName(const string &name);
    bool isAvailable1() const;
    void setIsAvailable(bool isAvailable);
    const string &getLender() const;
    void setLender(const string &lender);
    const string &getGenre() const;
    void setGenre(const string &genre);
    bool isAvailable2() const;

private:
    string name, lender , genre;
    bool isAvailable;
};



class Books {
public:
    Books();
    void addBook(Book book);
    void removeBook(string name);
    Book getBook(string name);
    const string &getMyname() const;
    void setMyname(const string &myname);
    Books getBooksByGenre(string genre);
    vector<Book> getAllBooks();
    void addAskedBook(Book book);
    void removeAskedBook(Book book);

    const vector<Book> &getBooksiAskedFor() const;

protected:
    vector<Book> booksiAskedFor;
    string myname;
    vector<Book> books;//change to vector of arrays -[name,owner,isavailable]
};




#endif //BOOST_ECHO_CLIENT_BOOKS_H
