#include <iostream>
#include "list.h"

List::List() {
	size = 0;
	head = NULL;
	tail = NULL;
}

List::~List() {
	clear();
}

void List::push_back(int data) {
	if(head == NULL) {
		head = new Node(data);
		tail = head;
	} else {
		tail -> pNext = new Node(data);
		tail = tail -> pNext;
	}
	size++;
}

void List::push_front(int data) {
	head = new Node(data, head);
	size++;
}

int& List::operator[](const int index) {
	if(index < 0) {
		int err = 0;
		std::cout << "IndexOutOfBoundsException" << std::endl;
		return err;
	}
	int counter = 0;
	Node *current = this -> head;
	
	while(current != NULL) {
		if(counter == index) {
			return current -> data;	
		}
		current = current -> pNext;
		counter++;
	}
}

void List::insert(int index, int data) {
	Node *current = this -> head;
	if(index == 0) {
		head = new Node(data, head);
	} else {
		for(int counter = 0;counter != index - 1; ++counter) {
			current = current -> pNext;
		}
		current -> pNext = new Node(data, current -> pNext);
	}
	size++;
}

void List::remove_head() {
	if(head != NULL) {
		Node *temp =head;
		head = head -> pNext;
		delete temp;
		size--;
	}
}

void List::clear() {
	while(size) {
		remove_head();
	}
}

int List::pop_front() {
	if(head != NULL) {
		Node *temp = head;
		head = head -> pNext;
		size--;
		return temp -> data;
	}
}

int List::pop_back() {
	Node *current = this -> head;
	for (int counter = 0; counter < (this -> size - 1); ++counter) {
		current = current -> pNext;
	}
	return current -> data;
}

void List::print() {
	Node *temp = this -> head;
	for(int i = 0; i < size; ++i) {
		std::cout << temp -> data << std::endl;
		temp = temp -> pNext;
	}
}

