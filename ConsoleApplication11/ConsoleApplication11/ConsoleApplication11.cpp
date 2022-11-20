#include <iostream>
#include <array>
#include <>

using namespace std;

#define MAXSIZE 5


class Stekas
{
private:
	int top; //kur virsus
	int array[MAXSIZE];

public:
	Stekas() // konstruktorius
	{
		top = -1;
	}

	bool isFull()  // tikrina ar stekas pilnas
	{
		return (top == MAXSIZE - 1);
	}

	void push(int value) // prideti elementa
	{
		if (isFull())
			cout << "OMG! Stekas pilnas!" << endl;
		else
		{
			top++;
			array[top] = value;
			//display();
		}
	}

	//~Stekas()

	void pop()
	{
		top--;
	}
	void peek()
{
		cout << array[top] << endl;
}


	void isEmpty()
	{
		if (top == -1)
			cout << "isEmpty";
		else
			cout << "notEmpty";

	}
	void clear()
	{
		top = -1;
	}

	void display()
	{
		for (int i = top; i >= 0; i--)
		{
			cout << array[i] << " ";
		}
		cout << endl;
	}


};

int main() {
	Stekas s;
	s.push(1);
	s.push(2);
	s.push(3);
	s.push(4);
	s.push(5);
	s.push(6);
	s.display();
	s.pop();
	s.display();
	s.isEmpty();
	s.clear();
	s.display();
	s.isEmpty();
	return 0;
}
