#include <iostream>
#include "queue.h"

int main() {
	Queue queue;
	queue.push(1);
	queue.push(2);
	queue.push(3);
	queue.push(4);
	queue.push(5);
	queue.toString();
	std::cout << "after push 1-5 \n size = " << queue.getSize() << std::endl;
	queue.pop();
	queue.pop();
	queue.toString();
        std::cout << "after pop 2* \n size = " << queue.getSize() << std::endl;
	return 0;
}
