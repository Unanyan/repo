CC=g++
OBJECTS=main.o list.o queue.o node.o
HEADER=list.h queue.h node.h

listOrQueue: ${OBJECTS}
	${CC} -lm ${OBJECTS} -o listOrQueue

main.o: ${HEADER} main.cpp
	${CC} -c main.cpp -o main.o

list.o: ${HEADER} list.cpp
	${CC} -c list.cpp -o list.o

queue.o: ${HEADER} queue.cpp
	${CC} -c queue.cpp -o queue.o

node.o: ${HEADER} node.cpp
	${CC} -c node.cpp -fpermissive -o node.o

.PHONY: clean run

clean:
	\rm -f *.o listOrQueue
