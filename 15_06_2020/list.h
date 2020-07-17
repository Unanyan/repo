#ifndef LIST_H
#define LIST_H
#include "node.h"


class List {
public:
	List();
	~List();
	void push_back(int data);
	void push_front(int data);
	int get_size() {
		return size;
	}
	int &operator[](const int index);
	void insert(int index, int data);
	void clear();
	int pop_front();
	int pop_back();
	void print();
private:
	int size;	
	Node *head;
	Node *tail;
	void remove_head();
};
#endif
