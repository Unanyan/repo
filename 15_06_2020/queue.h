#ifndef QUEUE_H
#define QUEUE_H
#include "list.h"

class Queue : private List {
	public:
		int pop();
		void push(int data);
		int getSize();
		void toString();
};
#endif

