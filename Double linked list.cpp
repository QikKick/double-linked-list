#include <iostream>
#include <chrono>
#include <thread>

struct node
{
    int data;
    node* next;
    node *prev;
};

class linkedList
{
private:
    node* head;
    node* tail;
public:
    linkedList()
    {
        head = NULL;
        tail = NULL;
    }

    ~linkedList(){};

    void display() //1
    {
        node* temp = head;
        node* atemp=NULL;
        while (temp != NULL)
        {
            atemp = temp;
            std::cout << temp->data << "\t";
            temp = temp->next;
        }
        std::cout << std::endl;
        std::cout << "Tiesinis sarasas atvirksciai: ";
        while(atemp !=NULL)
        {
            std::cout << atemp->data << "\t";
            atemp = atemp->prev;
        }
        std::cout << std::endl << std::endl;
    }
    std::string isEmpty()
    {
        if (head == NULL) // tail
            return "Sarasas Tuscias";
        else
            return "Sarasas ne Tuscias";
    }
    void addNodeEnd(int value)
    {
        node* temp = new node;
        temp->data = value;
        temp->next = NULL;
        if (head == NULL)
        {
            temp->prev = nullptr;
            head = temp;
            tail = temp;
        }
        else
        {
            temp->prev = tail;
            tail->next = temp;
            tail = temp;
            std::cout << " bla " << head->data << " bla \n";
        }
    }
    void addNodeStart(int value)
    {
        node* temp = new node;
        temp->data = value;

        if(head == nullptr){ // viena karta tik pradzioje;
            head = temp;
            tail = temp;
            head->prev = nullptr;
            tail->next = nullptr;
        }
        else {
            temp->next = head;
            temp->prev = NULL;
            head->prev = temp;
            head = temp;
        }
       
    }
    void addNodePos(int pos, int value)
    {
        node* temp = new node;
        temp->data = value;
        if(head == nullptr)
        {
            head = temp;
            tail = temp;
            head->prev = nullptr;
            tail->next = nullptr;
            return;
        }

        int count = 0;
        node* cur = head;
        while (cur != NULL)
        {
            count++;
            cur = cur->next;
        }


        //std::cout << " BLA \n" << count << "\n BLA \n";

       // node* pre = nullptr;

        if(pos<0 || pos>count)
        {
            std::cout << "Duota pozicija negali buti NULL";
            return;
        }
        if(pos == count)
        {
            addNodeEnd(value);
            return;
        }

        int count2 = count / 2;

        if (pos <= count2)
        {
            cur = head;
            for(int i = 1; i<pos; i++)
            {
                //pre = cur;
                cur = cur->next;
                std::cout << "\n buvo pirmas \n";
            }

            
        }
        else if( pos > count2)
        {
            cur = tail;
            int count3 = count - 1;
	        for(int i = count; i>pos; i--)
	        {
                //pre = cur;
                cur = cur->prev;
                std::cout << "\n buvo antras \n";
	        }

            
            //
        }
        temp->next = cur;
        temp->prev = cur->prev;
        cur->prev->next = temp;
        cur->prev = temp;

       /* temp->next = pre->next; // tempnext = cur;
        pre->next = temp; //temp prer = cur prev
        temp->prev = pre; // cur prev next = temp // cur prev = temp
        if (temp->next != NULL)
        {
            temp->next->prev = temp;
        }
        */
        return;
    }
    void delNodeStart()
    {
       /* if (isEmpty())
        else if(head == tail)
        else*/
        //node* temp;
       // temp = head;
        head = head->next;
        delete head->prev;
        head->prev = nullptr;
       // delete temp;


    }
    void delNodeEnd()
    {
        node* temp;
        temp = tail;
        temp = temp->prev;

        delete tail;
        tail = temp;
        tail->next = NULL;
    }
    void delNodePos(int pos)
    {
        int count = 0;
        node* cur = head;
        while (cur != NULL)
        {
            count++;
            cur = cur->next;
        }

        if (pos == count)
        {
            delNodeEnd();
            return;
        }

        int  count2 = count / 2;
        node* temp = new node;

        if (pos<0 || pos>count)
        {
            std::cout << "Duota pozicija neegzituoja\n";
            return;
        }

        if (pos <= count2)
        {
            cur = head;
            for (int i = 1; i < pos; i++)
            {
                //pre = cur;
                cur = cur->next;
                std::cout << "\n buvo pirmas \n";
            }


        }
        else if (pos > count2)
        {
            cur = tail;
            int count3 = count - 1;
            for (int i = count; i > pos; i--)
            {
                //pre = cur;
                cur = cur->prev;
                std::cout << "\n buvo antras \n";
            }

 

        }
        cur->prev->next = cur->next;
        cur->next->prev = cur->prev;
    }
    void clear()
    {
        node* current = head;
        node* next = NULL;

        while (current != NULL)
        {
            next = current->next;
            free(current);
            current = next;
        }
        head = NULL;
    }
    int search(int num)
    {
        node* cur = head;
        int count = 0;

        while (cur != NULL)
        {
            if (cur->data == num)
            {
                count++;
                return count;
            };
            count++;
            cur = cur->next;
        }

        return -2147483648;
    } // 
};

int main()
{
    using namespace std::this_thread; // sleep_for, sleep_until
    using namespace std::chrono; // nanoseconds, system_clock, seconds

    std::cout << "Loading....";

    /*for (int i = 0; i < 100; i++)
    {
        std::cout << "Loading " << i << "%";
        sleep_for(nanoseconds(10000));
        system("CLS");
    }*/

    std::cout << " Program Loaded, thank you for waiting dear Lecturer";
    //sleep_for(seconds(2));
    system("CLS");

	linkedList listas;
    int choice = 0, pos = -1;
    int temp = 0;
    std::cout << "Tiesinio sasaro programa\n";
    
		while(choice != 11)
		{
            std::cout << "<---------------------------------------->\n";
            std::cout << "Tiesinis Sarasas: ";
            listas.display();
            std::cout << "<---------------------------------------->\n";
            std::cout << "Funkcijos (1-10) : \n";
            std::cout << "1. Prideti elementa gale\ Prideti pirma elementa jeigu sarasas tuscias\n";
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


	/*std::cout << listas.isEmpty() << std::endl;
    listas.addNodeEnd(25);
    listas.addNodeEnd(10);
    listas.addNodeEnd(3);
    listas.addNodeEnd(125);
    listas.addNodeStart(1);
    listas.display();
    listas.addNodePos(4, 99);
    listas.display();
    listas.delNodeStart();
    listas.display();
    listas.delNodeEnd();
    listas.display();
    listas.delNodePos(2);
    listas.display();
    std::cout << listas.isEmpty() << std::endl;
    listas.clear();
    listas.display();
    std::cout << listas.isEmpty() << std::endl;
    listas.addNodeEnd(5);
    listas.addNodeEnd(6);
    listas.addNodeEnd(2);
    listas.addNodeEnd(8);
    std::cout << "blah blah" << std::endl;
    listas.display();
    std::cout << listas.search(2) << std::endl;*/
    return 0;
}
/*
        if(count % 2 == 0)
        {
            int m = count / 2;
            int m2 = count++;

            if (pos <= m)
            {
                cur = head;
                for (int i = 1; i < pos; i++)
                {
                    pre = cur;
                    cur = cur->next;
                }
            }
            else if (pos >= m2)
            {
                cur = tail;
                for(int i = count; i > pos-2; i--)
                {
                    pre = cur;
                    cur = cur->prev;
                }
            }
        }

        if(count % 2 != 0)
        {
            int m = count / 2;
            m++;

            if(pos <= m)
            {
                cur = head;
                for (int i = 1; i < pos; i++)
                {
                    pre = cur;
                    cur = cur->next;
                }
            }

            if (pos > m)
            {
                cur = tail;
                for (int i = count; i > pos-2; i--)
                {
                    pre = cur;
                    cur = cur->prev;
                }
            }
        }*/