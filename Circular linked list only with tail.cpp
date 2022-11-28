#include <iostream>
#include <chrono>
#include <thread>

static int counter = 0;

struct node
{
    int data;
    node *next;
};

class linkedList
{
private:
    node *tail;
public:
    linkedList()  //konstruktorius
    {
        tail = NULL;
    }

    ~linkedList(){};

    void display() {
        // std::cout << "kvailas";
        if(tail == nullptr)
        {
            std::cout<< isEmpty();
            return;
        }
        node* temp = tail;
        int displayCounter = 0;
        while (displayCounter != counter)
        {
            temp = temp->next;
            std::cout << temp->data << "\t";
            //std::cout << "\n" << temp->next << "\n";
            displayCounter+=1;
        }
        std::cout << std::endl;
    }
    std::string isEmpty()
    {
        if (tail == NULL) // tail
            return "\nSarasas Tuscias\n";
        else
            return "\nSarasas ne Tuscias\n";
    }
    void addNodeEnd(int value)
    {
        //std::cout<<"kvailas";
        node *temp = new node;
        temp->data = value;

        if(tail == NULL)
        {
            tail = temp;
            tail->next = temp;
            counter+=1;

        } else
        {
            //std::cout<<"kvailsa";
            temp->next = tail->next;
            tail->next = temp;
            tail = temp;
            counter += 1;
        }

    }
    void addNodeStart(int value)
    {
        if(tail == nullptr)
            addNodeEnd(value);
        else{
            node* temp = new node;
            temp->data = value;
            temp->next = tail->next;
            tail->next = temp;
            counter+=1;

        }
    }
    void addNodePos(int pos, int value)
    {
        if(pos == 0 || pos > counter)
        {
            std::cout << " Give position needs to exist";
            return;
        }

        if(tail == nullptr)
        {
            addNodeEnd(value);
        }

        else if (pos == 1)
        {
            addNodeStart(value);
        }

        else
        {
            node* temp = new node;
            temp->data = value;
            node* cur = tail;
            for(int i = 1; i<pos; i++) { cur = cur->next; }
            temp->next = cur->next;
            cur->next = temp;
            counter+=1;
        }
    }

    void delNodeStart()
    {
        if(tail == nullptr)
        {
           std::cout << isEmpty();
            return;
        }
        tail->next = tail->next->next;
        counter-=1;
    }
    void delNodeEnd(){
        if(tail == nullptr)
        {
          std::cout <<  isEmpty();
            return;
        }
        node* cur;
        cur = tail;
        for(int i =1; i<counter;i++)
            cur = cur->next;
        cur->next = tail ->next;
        tail = cur;
        counter-=1;
    }
    void delNodePos(int pos){

        if(tail == nullptr)
        {
            isEmpty();
            return;
        }
        else if(pos < 1 || pos > counter)
        {
            std::cout << "Given position should exist";
            return;
        }

        node* cur= tail;

        for(int i = 1; i <pos; i++)
        {
            cur = cur->next;
        }
        cur->next = cur ->next ->next;
        counter-=1;
    }

    void clear(){
        node* cur = tail;
        node* next = nullptr;

        while (cur!= nullptr)
        {
            next = cur->next;
            free(cur);
            if(next = nullptr)
            {}
                else
                cur = next;   /// Neklauskit manes sito, nezinau kodel neveikia su next!=nullptr
        }
        tail = nullptr;
    }
    int search(int num){
        int count= 1;
        node *cur = tail->next;

            while (cur->data != num)
            {
//                int i = 1;
//                std::cout << " " << count << " ";
//                i++;
                if (cur->data == num)
                {
                    count++;
                    return count;
                };
                count++;
                cur = cur->next;
            }

        return count;
    }

};

int main()
{
    using namespace std::this_thread; // sleep_for, sleep_until
    using namespace std::chrono; // nanoseconds, system_clock, seconds
    int choice = 0;
    int temp;
    int pos;
    linkedList listas;
    std::cout << "Tiesinio sasaro programa\n";

    while(choice != 11)
    {
        std::cout << "<---------------------------------------->\n";
        std::cout << "Tiesinis Sarasas: ";
        listas.display();
        std::cout << "<---------------------------------------->\n";
        std::cout << "Funkcijos (1-10) : \n";
        std::cout << "1. Prideti elementa gale\n";
        std::cout << "2. Prideti elementa eiles pradzioje\n";
        std::cout << "3. Prideti elementa pasirinktoje pozicijoje\n";
        std::cout << "4. Pasalinti elemeta gale\n";
        std::cout << "5. Pasalinti elementa pradzioje\n";
        std::cout << "6. Pasalinti elementa pasirinktoje pozicijoje\n";
        std::cout << "7. Istrinti sarasa\n";
        std::cout << "8. Rasti elemento vieta sarase\n";
        std::cout << "9. Patikrinti ar sarasas tuscias\n";
        std::cout << "10. Atspausdinti sarasa\n";
        std::cout << "<---------------------------------------->\n";
        std::cout << " Noredami baigti darba iveskite 11\n";
        std::cout << "Pasirinkimas: ";
        std::cin >> choice;

        if (choice == 1)
        {
            system("CLS");
            std::cout << "Koki elementa noretumere prideti?\n Elementas: ";
            std::cin >> temp;
            listas.addNodeEnd(temp);

            //system("CLS");
            std::cout << "Elementas " << temp << " pridetas!\n ";
            sleep_for(seconds(2));
            system("CLS");
        }

        else if (choice == 2)
        {
            system("CLS");
            std::cout << "Koki elementa noretumere prideti?\n Elementas: ";
            std::cin >> temp;
            listas.addNodeStart(temp);

            system("CLS");
            std::cout << "Elementas " << temp << " pridetas!\n ";
            sleep_for(seconds(2));
            system("CLS");
        }

        else if (choice == 3)
        {
            system("CLS");
            std::cout << "Koki elementa noretumere prideti?\n Elementas: ";
            std::cin >> temp;
            std::cout << "\nKokioje pozicijoje ji prideti?\n Pozicija: ";
            std::cin >> pos;

            if (pos == 1)
                listas.addNodeStart(temp);
            else
                listas.addNodePos(pos, temp);

            //system("CLS");
            std::cout << "Elementas " << temp << " pridetas " << pos << " pozicijoje\n";
            sleep_for(seconds(2));
            system("CLS");
        }

        else if (choice == 4)
        {
            system("CLS");
            listas.delNodeEnd();
            std::cout << "Paskutinis elementas pasalintas \n";
            sleep_for(seconds(2));
            system("CLS");
        }

        else if (choice == 5)
        {
            system("CLS");
            listas.delNodeStart();
            std::cout << "Pirmas elementas pasalintas\n";
            sleep_for(seconds(2));
            system("CLS");
        }

        else if (choice == 6)
        {
            system("CLS");
            std::cout << "Iveskite elemento kurio norite pasalinti pozicija ";
            std::cin >> pos;

            if (pos == 1)
                listas.delNodeStart();
            else
                listas.delNodePos(pos);
            std::cout << pos << "-asis elementas pasalintas\n";
            sleep_for(seconds(2));
            system("CLS");
        }

        else if (choice == 7)
        {
            system("CLS");
            listas.clear();
            std::cout << " Sarasas Tuscias\n";
            sleep_for(seconds(2));
            system("CLS");
        }

        else if (choice == 8)
        {
            system("CLS");
            std::cout << "Koki elementa norite rasti?\n Elementas: ";
            std::cin >> temp;
            if(listas.search(temp ) == -2147483648)
            {
                std::cout << "\nSarase nera tokio elemento";
                sleep_for(seconds(2));
                system("CLS");
            }
            else {
                std::cout << "Norimo elemento pozicija: " << listas.search(temp);
                system("pause");
                system("CLS");
            }
        }

        else if (choice == 9)
        {
            system("CLS");
            std::cout << listas.isEmpty();
            sleep_for(seconds(2));
            system("CLS");
        }

        else if (choice == 10)
        {
            system("CLS");
            listas.display();
            system("pause");
            system("CLS");
        }

    }
    return 0;
}
