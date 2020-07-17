#ifndef NODE_H
#define NODE_H
#include <iostream>
class Node {
        public:
                Node *pNext;
                int data;
                Node(int data = int(), Node *pNext = NULL);
};
#endif
