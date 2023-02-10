#include <iostream>
#include <chrono>
#include <thread>
#include <stack>

static int counter = 0;

using namespace std;

struct nodeC{
    int data;
    nodeC *next;
};

struct nodeD{
    int data;
    nodeD *next;
    nodeD *prev;
};

    class doubleLinkedList{
    private:
        nodeD* head;
        nodeD* tail;

    public:
        doubleLinkedList(){
            head = nullptr;
            tail = nullptr;
            int counter = 0;
        }

        ~doubleLinkedList(){
            nodeD* current = head;
            while (current != nullptr) {
                nodeD* next = current->next;
                delete current;
                current = next;
            }
            head = nullptr;
            tail = nullptr;

        };

        //---------------------------------------------------//

        void display(){
            nodeD* temporary = head;
            while(temporary != nullptr){
                cout << temporary->data << "\t";
                temporary=temporary->next;
            }
            cout << "\n\n";
        }

        string isEmpty(){
            if(head == nullptr && tail == nullptr){
                return "\nList is empty\n";
            } else {
                return "\nList is not Empty\n";
            }
        }

        void addNodeEnd( int value ){
            nodeD* newNode = new nodeD;
            newNode->data = value;
            newNode->next = nullptr;

                if(head == nullptr){
                    newNode->prev = nullptr;
                    head = newNode;
                    tail = newNode;
                    counter+=1;
                }else{
                     newNode->prev = tail;
                     tail->next = newNode;
                     tail = newNode;
                     counter+=1;
                }
        }

        void addNodeStart(int value){
            if(head == nullptr){
                addNodeEnd(value);
            }else{
                nodeD* newNode = new nodeD;
                newNode->data = value;
                newNode->next = head;
                newNode->prev = nullptr;
                head->prev = newNode;
                head = newNode;
            }
            counter+=1;
        }

        void addNodePos(int position, int value) {
            if (position < 0) {
                cout << "Invalid position" << endl;
                return;
            }

            nodeD* newNode = new nodeD;
            newNode->data = value;

            if (head == nullptr) {
                newNode->prev = nullptr;
                newNode->next = nullptr;
                head = newNode;
                tail = newNode;
            }
            else if (position == 0) {
                newNode->prev = nullptr;
                newNode->next = head;
                head->prev = newNode;
                head = newNode;
            }
            else if (position >= counter) {
                newNode->prev = tail;
                newNode->next = nullptr;
                tail->next = newNode;
                tail = newNode;
            }
            else {
                nodeD* currentNode;
                if(position <= counter/2){
                    currentNode = head;
                    for (int i = 0; i < position; i++) {
                        currentNode = currentNode->next;
                    }
                }

                else{
                    currentNode = tail;
                    for(int i = counter-1; i>position; i--){
                        currentNode = currentNode->prev;
                    }
                }

                newNode->prev = currentNode->prev;
                newNode->next = currentNode;
                currentNode->prev->next = newNode;
                currentNode->prev = newNode;
            }
            counter++;
        }

        void delNodeStart(){
            if(tail == nullptr){
                cout << "\nList is Empty\n";
                return;
            }

            else if(tail == head){
                delete tail;
                //delete head;
                tail = nullptr;
                head = nullptr;
                counter-=1;
            }

            else
            {
                head = head->next;
                delete head->prev;
                head->prev = nullptr;
                counter-=1;
            }
        }

        void delNodeEnd(){
            if(tail == nullptr){
                cout << "\nList is Empty\n";
                return;
            }

            else if(tail == head){
                delete tail;
                delete head;
                tail = nullptr;
                head = nullptr;
                counter-=1;
            }

            else{
                tail = tail->prev;
                delete tail->next;
                tail->next = nullptr;
                counter -=1;
            }
        }

        void delNodePos(int position){
        if(tail == nullptr){
            cout << "\nList is Empty\n";
            return;
        }
        else if (position < 0 || position >= counter) {
            cout << "Invalid position" << endl;
            return;
        }

        else if(tail == head){
            delete tail;
            tail = nullptr;
            head = nullptr;
            counter-=1;
            return;
        }

        else if (position == 0){
            delNodeStart();
            return;
        }

        else if (position == counter-1){
            delNodeEnd();
            return;
        }

        nodeD* temporary;
        if(position<=counter/2)
        {
            temporary = head;
            for(int i = 0; i< position; i++){
                temporary = temporary->next;
            }
        }
        else
        {
            temporary = tail;
            for(int i = counter-1; i>position; i--){
                temporary = temporary->prev;
            }
        }
        temporary->prev->next = temporary->next;
        temporary->next->prev = temporary->prev;
        delete temporary;
        counter-=1;
    }

        void clear() {
            nodeD* current = head;
            while (current != nullptr) {
                nodeD* next = current->next;
                delete current;
                current = next;
            }
            head = nullptr;
            tail = nullptr;
            counter = 0;
        }

        void search(int value){
            nodeD* current = head;
            int position = 1;
            int count = 0;
            while (current != nullptr) {
                if (current->data == value) {
                    cout << "Value " << value << " found at position " << position << endl;
                    count++;
                }
                current = current->next;
                position++;
            }
            if(count == 0)
                cout << "Value not found" << endl;
        }

        void reverse() {
            nodeD* current = head;
            nodeD* temp = nullptr;
            while (current != nullptr) {
                temp = current->prev;
                current->prev = current->next;
                current->next = temp;
                current = current->prev;
            }
            if (temp != nullptr) {
                head = temp->prev;
            }
        }
    };

    class loopedLinkedList {

    private:
        nodeC *tail;

    public:
        loopedLinkedList() {
            tail = nullptr;
        }

        ~loopedLinkedList() {
            if (tail == nullptr) {
                return;
            }
            nodeC *current = tail->next;
            nodeC *next;
            tail->next = nullptr;
            while (current != nullptr) {
                next = current->next;
                delete current;
                current = next;
            }
            delete tail;
            tail = nullptr;
        }

//---------------------------------------------------//

        void display() {
            if (tail == nullptr) {
                cout << " List Clear ";
            }
            nodeC *temp = tail;
            int displayCounter = 0;
            while (displayCounter != counter) {
                temp = temp->next;
                cout << temp->data << "\t";
                displayCounter += 1;
            }
        }

        string isEmpty() {
            if (tail == nullptr) {
                return "\nList is empty\n";
            } else {
                return "\nList is not empty\n";
            }
        }

        void addNodeEnd(int value)
        {
            nodeC *temp = new nodeC;
            temp -> data = value;

            if(tail == nullptr){
                tail = temp;
                tail->next = temp;
                counter +=1;
            }
            else{
                temp->next = tail->next;
                tail->next = temp;
                tail = temp;
                counter +=1;
            }
        }

        void addNodeStart(int value)
        {
            if(tail == nullptr)
                addNodeEnd(value);
            else{
                nodeC *temp = new nodeC;
                temp->data = value;
                temp->next = tail->next;
                tail->next = temp;
                counter+=1;
            }
        }

        void addNodePos(int pos, int value)
        {
            if(pos < 0){
                cout << "Position cannot be negative";
                return;
            }
            if(tail == nullptr){
                addNodeEnd(value);
            }
            else if(pos == 0){
                nodeC* newNode = new nodeC;
                newNode->data = value;
                newNode->next = tail->next;
                tail->next = newNode;
                if (counter == 0) {
                    tail = newNode;
                }
                counter += 1;
            }
            else{
                if(pos >= counter)
                {
                    addNodeEnd(value);
                    return;
                }
                nodeC* temp = new nodeC;
                temp ->data = value;
                nodeC* currentPosition = tail;
                for(int i = 0; i<pos; i++) {
                    currentPosition = currentPosition->next;
                }
                temp->next = currentPosition->next;
                currentPosition->next = temp;
                counter += 1;
            }
        }

        void delNodeStart()
        {
            if(tail == nullptr){
                cout << "\nList is Empty\n";
                return;
            }

            else if(tail->next == tail)
            {
                delete tail;
                tail = nullptr;
                counter-=1;
            }

            else
            {
                nodeC* temp = tail->next;
                tail->next = temp->next;

                counter-=1;
            }

        }

        void delNodeEnd()
        {
            if(tail == nullptr){
                cout << "\nList is Empty\n";
                return;
            }

            else if( tail->next == tail)
            {
                delete tail;
                tail = nullptr;
            }

            else{
                nodeC* temp = tail;
                for(int i = 1; i<counter; i++)
                    temp = temp->next;
                temp->next = tail->next;
                delete tail;
                tail = temp;
                counter-=1;
            }

        }

    void delNodePos(int pos)
    {
        if(tail == nullptr){
            cout << "\nList is Empty\n";
            return;
        }
        else if (pos < 1 || pos > counter) {
            cout << "Invalid position" << endl;
            return;
        }
        else if( tail->next == tail)
        {
            delete tail;
            tail = nullptr;
            return;
        }
        nodeC* temp = tail;
        nodeC* delNode;
        cout << delNode << endl;
        cout << tail;
        if(pos == 1)
        {
            delNode = tail;
            tail = tail->next;
        }
        else
        {
            for(int i = 1; i < pos; i++)
            {
                temp = temp->next;
            }
            delNode = temp->next;
            temp->next = temp->next->next;
        }
        if(delNode == tail) tail = temp;
        delete delNode;
        counter -= 1;
    }

        void search(int element) {
            nodeC* current = tail->next;
            int position = 1;
            int count = 0;
            while (current != tail) {
                if (current->data == element) {
                    cout << "Element " << element << " found at position " << position << endl;
                    count++;
                }
                current = current->next;
                position++;
            }
            if (current->data == element) {
                cout << "Element " << element << " found at position " << position << endl;
                count++;
            }
            if(count == 0)
                cout << "Element not found"<<endl;

            cout << "\n To Continue press any button\n";
            system("pause");
            system("CLS");
        }

        void clear() {
            if (tail == nullptr) {
                return;
            }

            nodeC* currentPosition = tail->next;
            while (currentPosition != tail) {
                nodeC* next = currentPosition->next;
                delete currentPosition;
                currentPosition = next;
            }
            delete tail;
            tail = nullptr;
        }

    void reverse() {
        if (tail == nullptr) {
            return;
        }
        nodeC* current = tail->next;
        nodeC* prev = nullptr;
        nodeC* next;
        tail->next = nullptr;
        while (current != nullptr) {
            next = current->next;
            current->next = prev;
            prev = current;
            current = next;
        }
        tail = prev;
        while (tail->next != nullptr) {
            tail = tail->next;
        }
        tail->next = prev;
    }
    };

    class queueArray{

    private:
        int front, rear, capacity;
        int* queue;

    public:
        queueArray(int c){
            front = 0;
            rear = 0;
            capacity = c;
            queue = new int;
        }

        ~queueArray(){delete[] queue;}

        void enqueue(int value){
            if(capacity == rear)
            {
                cout << "\nQueue is full\n";
                return;
            }

            else{
                queue[rear] = value;
                rear++;
            }
            return;
        }

        void dequeue(){
            if( front == rear){
                cout << "\nQueue is empty\n";
                return;
            }

            else{
                for(int i = 0; i<rear; i++){
                    queue[i] = queue[i+1];
                }
            rear--;
            }
            return;
        }

        void qFront(){
            if(front == rear){
                cout << "\nList is Empty\n";
                return;
            }
            cout << "\n The Front Element is: " << queue[front] << endl;
            return;
        }

        void qRear(){
            if(front == rear){
            cout << "\nList is Empty\n";
            return;
            }
            cout << "\n The Rear Element is: " << queue[rear-1] << endl;
            return;
        }

        bool isEmpty(){
            return rear == 0;
        }

        bool isFull(){
            return(rear == capacity);
        }

        void clear(){
            front = 0;
            rear = 0;
            for(int i = 0; i < capacity; i++)
                queue[i] = 0;
        }

        void display() {
            if (isEmpty()) {
                cout << "\nQueue is empty\n";
                return;
            }

            queueArray tempQueue(capacity);
            while (!isEmpty()) {
                int value = queue[front];
                cout << value << " ";
                tempQueue.enqueue(value);
                dequeue();
            }
            while (!tempQueue.isEmpty()) {
                int value = tempQueue.queue[tempQueue.front];
                enqueue(value);
                tempQueue.dequeue();
            }
            cout << endl;
        }

        void reverse() {
            if (isEmpty()) {
                cout << "\nQueue is empty\n";
                return;
            }

            stack<int> tempStack;
            while (!isEmpty()) {
                int value = queue[front];
                tempStack.push(value);
                dequeue();
            }
            while (!tempStack.empty()) {
                int value = tempStack.top();
                enqueue(value);
                tempStack.pop();
            }
        }

    };

    //-----------------------------------------------------

    void queue()
    {
        using namespace std::this_thread;
        using namespace std::chrono;
        queueArray q(5);
        int choice = 0;
        int element = 0;
        system("CLS");
        cout << "Queue Array implementation";

        while(choice != 10){
            cout << "<---------------------------------------->\n";
            cout << "Queue | Array Implementation";
            q.display();
            cout << "<---------------------------------------->\n";
            cout << "\t\t1. Enqueue an element\n";
            cout << "\t\t2. Dequeue an element\n";
            cout << "\t\t3. Peek the front element\n";
            cout << "\t\t4. Peek the rear element\n";
            cout << "\t\t5. Check if the queue is empty\n";
            cout << "\t\t6. Check if the queue is full\n";
            cout << "\t\t7. Display the queue\n";
            cout << "\t\t8. Reverse the queue\n";
            cout << "\t\t9. Clear the queue\n";
            cout << "<---------------------------------------->\n";
            cout << "If you want to quit press 12";
            cout << "Beware if you leave everything there is no save list option\n";
            cout << "Choice: ";
            cin >> choice;

            if(choice == 1)
            {
                system("CLS");
                cout << "What element would you like to enqueue?\n Element: ";
                cin >> element;
                q.enqueue(element);

                cout << "Element: " << element << " has been added to the queue\n";
                sleep_for(seconds(2));
                system("CLS");
            }

            else if(choice == 2)
            {
                system("CLS");
                q.dequeue();
                cout << "1st Element has been dequeued";
                sleep_for(seconds(2));
                system("CLS");
            }

            else if(choice == 3)
            {
                system("CLS");
                q.qFront();
                sleep_for(seconds(2));
                system("CLS");
            }

            else if(choice == 4)
            {
                system("CLS");
                q.qRear();
                sleep_for(seconds(2));
                system("CLS");
            }

            else if(choice == 5)
            {
                system("CLS");
                if (q.isEmpty())
                {
                    cout << "The queue is empty\n";
                }
                else
                {
                    cout << "The queue is not empty\n";
                }
                sleep_for(seconds(2));
                system("CLS");
            }

            else if(choice == 6)
            {
              system("CLS");
              if(q.isFull())
              {
                  cout << "The queue is full\n";
              }
              else
              {
                  cout << "The queue is not full\n";
              }
                sleep_for(seconds(2));
                system("CLS");
            }

            else if (choice == 7)
            {
                system("CLS");
                q.display();
                sleep_for(seconds(2));
                system("CLS");
            }

            else if (choice == 8)
            {
                system("CLS");
                q.reverse();
                cout << "The queue has been reversed\n";
                sleep_for(seconds(2));
                system("CLS");
            }

            else if (choice == 9)
            {
                system("CLS");
                cout << "The queue has been cleared\n";
                q.clear();
                sleep_for(seconds(2));
                system("CLS");
            }
        }
        system("CLS");
        cout << "Exiting Queue";
        q.clear();
        sleep_for(2000ms);
        system("CLS");
    }

    void dLinkedList()
    {
        using namespace std::this_thread; // sleep_for, sleep_until
        using namespace std::chrono; // nanoseconds, system_clock, seconds
        doubleLinkedList dll;
        int choice = 0;
        int element = 0;
        int position = 0;
        system("CLS");
        std::cout << "Double Linked List\n";

        while(choice != 12)
        {
            std::cout << "<---------------------------------------->\n";
            std::cout << "Linked List: ";
            dll.display();
            std::cout << "<---------------------------------------->\n";
            std::cout << "Functions: (1-11) : \n";
            std::cout << "\t\t1. Add an element to the end of the list\n";
            std::cout << "\t\t2. Add an element to the start of the list\n";
            std::cout << "\t\t3. Add an element to a given position\n";
            std::cout << "\t\t4. Delete the last element\n";
            std::cout << "\t\t5. Delete the first element\n";
            std::cout << "\t\t6. Delete an element at given position\n";
            std::cout << "\t\t7. Delete the List\n";
            std::cout << "\t\t8. Find the place of a given element\n";
            std::cout << "\t\t9. Check if the linked list is empty\n";
            std::cout << "\t\t10. Print out the linked list\n";
            std::cout << "\t\t11. Reverse the linked list\n";

            std::cout << "<---------------------------------------->\n";
            std::cout << "If you want to quit press 12\n";
            std::cout << "Beware if you leave everything there is no save list option\n";
            std::cout << "Choice: ";
            std::cin >> choice;

            if (choice == 1)
            {
                system("CLS");
                std::cout << "What element would you like to add?\n Element: ";
                std::cin >> element;
                dll.addNodeEnd(element);

                std::cout << "Element " << element << " is successfully added!\n ";
                sleep_for(seconds(2));
                system("CLS");
            }

            else if (choice == 2)
            {
                system("CLS");
                std::cout << "What element would you like to add?\n Element: ";
                std::cin >> element;
                dll.addNodeStart(element);

                system("CLS");
                std::cout << "Element " << element << " is successfully added!\n ";
                sleep_for(seconds(2));
                system("CLS");
            }

            else if (choice == 3)
            {
                system("CLS");
                std::cout << "What element would you like to add?\n Element: ";
                std::cin >> element;
                std::cout << "\nAt what position do you want to add the element?\n Position: ";
                std::cin >> position;

                if (position == 0)
                    dll.addNodeStart(element);
                else
                    dll.addNodePos(position, element);

                //system("CLS");
                std::cout << "Element " << element << " added at " << position << " position\n";
                sleep_for(seconds(2));
                system("CLS");
            }

            else if (choice == 4)
            {
                system("CLS");
                dll.delNodeEnd();
                std::cout << "Last element has been deleted \n";
                sleep_for(seconds(2));
                system("CLS");
            }

            else if (choice == 5)
            {
                system("CLS");
                dll.delNodeStart();
                std::cout << "First element has been deleted\n";
                sleep_for(seconds(2));
                system("CLS");
            }

            else if (choice == 6)
            {
                system("CLS");
                std::cout << "Please input the position of the element you would like to delete ";
                std::cin >> position;

                if (position == 0)
                    dll.delNodeStart();
                else
                    dll.delNodePos(position);
                std::cout << position << " element deleted\n";
                sleep_for(seconds(2));
                system("CLS");
            }

            else if (choice == 7)
            {
                system("CLS");
                dll.clear();
                std::cout << " List is now Empty\n";
                sleep_for(seconds(2));
                system("CLS");
            }

            else if (choice == 8)
            {
                system("CLS");
                std::cout << "What element would u like to find?\n element: ";
                std::cin >> element;
                dll.search(element);
            }

            else if (choice == 9)
            {
                system("CLS");
                std::cout << dll.isEmpty();
                sleep_for(seconds(2));
                system("CLS");
            }

            else if (choice == 10)
            {
                system("CLS");
                dll.display();
                system("pause");
                system("CLS");
            }

            else if (choice == 11)
            {
                system("CLS");
                dll.reverse();
                cout << "The list has been reversed\n";
                sleep_for(1500ms);
                system("CLS");
            }

        }
        system("CLS");
        cout << "Exiting circular linked list";
        dll.clear();
        sleep_for(2000ms);
        system("CLS");
    }

    void cLinkedList()
    {
        using namespace std::this_thread; // sleep_for, sleep_until
        using namespace std::chrono; // nanoseconds, system_clock, seconds
        loopedLinkedList cll;
        int choice = 0;
        int element = 0;
        int position = 0;
        system("CLS");
        std::cout << "Circular Linked List\n";

        while(choice != 12)
        {
            std::cout << "<---------------------------------------->\n";
            std::cout << "Linked List: ";
            cll.display();
            std::cout << "\n<---------------------------------------->\n";
            std::cout << "Functions: (1-11) : \n";
            std::cout << "\t\t1. Add an element to the end of the list\n";
            std::cout << "\t\t2. Add an element to the start of the list\n";
            std::cout << "\t\t3. Add an element to a given position\n";
            std::cout << "\t\t4. Delete the last element\n";
            std::cout << "\t\t5. Delete the first element\n";
            std::cout << "\t\t6. Delete an element at given position\n";
            std::cout << "\t\t7. Delete the List\n";
            std::cout << "\t\t8. Find the place of a given element\n";
            std::cout << "\t\t9. Check if the linked list is empty\n";
            std::cout << "\t\t10. Print out the linked list\n";
            cout << "\t\t11. Reverse the linked list\n";

            std::cout << "<---------------------------------------->\n";
            std::cout << "If you want to quit press 12\n";
            std::cout << "Beware if you leave everything there is no save list option\n";
            std::cout << "Choice: ";
            std::cin >> choice;

            if (choice == 1)
            {
                system("CLS");
                std::cout << "What element would you like to add?\n Element: ";
                std::cin >> element;
                cll.addNodeEnd(element);

                //system("CLS");
                std::cout << "Element " << element << " is successfully added!\n ";
                sleep_for(seconds(2));
                system("CLS");
            }

            else if (choice == 2)
            {
                system("CLS");
                std::cout << "What element would you like to add?\n Element: ";
                std::cin >> element;
                cll.addNodeStart(element);

                system("CLS");
                std::cout << "Element " << element << " is successfully added!\n ";
                sleep_for(seconds(2));
                system("CLS");
            }

            else if (choice == 3)
            {
                system("CLS");
                std::cout << "What element would you like to add?\n Element: ";
                std::cin >> element;
                std::cout << "\nAt what position do you want to add the element?\n Position: ";
                std::cin >> position;

                if (position == 0)
                    cll.addNodeStart(element);
                else
                    cll.addNodePos(position, element);

                //system("CLS");
                std::cout << "Element " << element << " added at " << position << " position\n";
                sleep_for(seconds(2));
                system("CLS");
            }

            else if (choice == 4)
            {
                system("CLS");
                cll.delNodeEnd();
                std::cout << "Last element has been deleted \n";
                sleep_for(seconds(2));
                system("CLS");
            }

            else if (choice == 5)
            {
                system("CLS");
                cll.delNodeStart();
                std::cout << "First element has been deleted\n";
                sleep_for(seconds(2));
                system("CLS");
            }

            else if (choice == 6)
            {
                system("CLS");
                std::cout << "Please input the position of the element you would like to delete ";
                std::cin >> position;

                if (position == 1)
                    cll.delNodeStart();
                else
                    cll.delNodePos(position);
                std::cout << position << " element deleted\n";
                sleep_for(seconds(2));
                system("CLS");
            }

            else if (choice == 7)
            {
                system("CLS");
                cll.clear();
                std::cout << " List is now Empty\n";
                sleep_for(seconds(2));
                system("CLS");
            }

            else if (choice == 8)
            {
                system("CLS");
                std::cout << "What element would u like to find?\n element: ";
                std::cin >> element;
                cll.search(element);
            }

            else if (choice == 9)
            {
                system("CLS");
                std::cout << cll.isEmpty();
                sleep_for(seconds(2));
                system("CLS");
            }

            else if (choice == 10)
            {
                system("CLS");
                cll.display();
                system("pause");
                system("CLS");
            }

            else if (choice == 11)
            {
                system("CLS");
                cll.reverse();
                cout << "The list has been reversed\n";
                sleep_for(1500ms);
                system("CLS");
            }

        }
        system("CLS");
        cout << "Exiting circular linked list";
        cll.clear();
        sleep_for(2000ms);
        system("CLS");
    }

    int main() {
    using namespace std::this_thread; // sleep_for, sleep_until
    using namespace std::chrono; // nanoseconds, system_clock, seconds

    int dataStructure = 0;
    while(dataStructure !=4)
    {
        cout << "Welcome\n";
        cout << "This is a code that shows three different data structures\n";
        cout << "\t\t 1. Queue - Linked List Implementation\n";
        cout << "\t\t 2. Doubly linked list\n";
        cout << "\t\t 3. Circular Linked List\n";
        cout << "\t\t 4. Close the program\n";
        cout << "Please pick which data structure do you want to try (1/3)\n";

        cin >> dataStructure;

        if (dataStructure == 1){
            queue();
            break;
        }else if(dataStructure == 2){
            dLinkedList();
            break;
        }else if(dataStructure == 3){
            cLinkedList();
            break;
        }else if(dataStructure == 4){
            cout << "Farewell Traveller :)";
            return 0;
        }else{
            cout << "Please follow the instructions >:(\n";
            sleep_for(seconds(1));
            cout << "Trying to relauch";
            for(int i = 0; i<3;i++)
            {
                sleep_for(500ms);
                cout << ".";
            }
            cout << "Success!\n Now please choose wisely!";
            sleep_for(2000ms);
            system("CLS");
            cout << endl;
        }
    }
    cout << "I hope you had a great time using this program";
    cout << "\nFarewell!";
    cout << "\n To close the app press any button\n";
    system("pause");
    return 0;
}