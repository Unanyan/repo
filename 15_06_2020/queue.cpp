#include <iostream>
#include "list.h"
#include "queue.h"

void Queue::push(int data) {
	push_back(data);
}

int Queue::pop() {
	pop_front();
}

int Queue::getSize() {
	get_size();
}

void Queue::toString() {
	print();
}
