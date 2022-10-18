#include <bits/stdc++.h>
#include <stdlib.h>
#include <ctime>
#include <chrono>
#include <thread>
#include <windows.h>
#include <conio.h>
using namespace std;

class game
{
protected:
	string playerName;
	int randomEquation, number1, number2;
	int sum, ans, seconds;

public:
	game() : playerName(), randomEquation(0), number1(0),
	         number2(0), sum(0), ans(0), seconds(10)
	{
	}

	~game()
	{
	}


	string name()
	{
		cin >> playerName;
		return playerName;
	}

	void generate()
	{
		randomEquation = rand() % 3 + 1;

		if (randomEquation == 1)
		{
			number1 = rand() % 50;
			number2 = rand() % 50;

			sum = number1 + number2;
		}

		else if (randomEquation == 2)
		{
			number1 = rand() % 50;
			number2 = rand() % 50;

			sum = number1 - number2;
		}

		else if (randomEquation == 3)
		{
			number1 = rand() % 50;
			number2 = rand() % 50;

			sum = number1 * number2;
		}
	}

	bool gameMech()
	{
		if (randomEquation == 1)
		{
			cout << number1 << " + " << number2 << " = ?\n";
			cin >> ans;

			if (ans == sum)
			{
				return true;
			}
			return false;
		}

		if (randomEquation == 2)
		{
			cout << number1 << " - " << number2 << " = ?\n";
			cin >> ans;

			if (ans == sum)
			{
				return true;
			}
			return false;
		}
		if (randomEquation == 3)
		{
			cout << number1 << " * " << number2 << " = ?\n";
			cin >> ans;

			if (ans == sum)
			{
				return true;
			}
			return false;
		}
	}
};

class ready : public game
{
public:
	void operator ++()
	{
		seconds = 3;
		cout << '\n' << " Are You Ready Kids?\n   (Press anything to continue) ";
		(void)_getch();
		cout << " Ay Ay Captian!\n";
		while (seconds >= 1)
		{
			cout << seconds << endl;
			this_thread::sleep_for(chrono::milliseconds(1000));
			seconds--;
			cout << " OOOOHHHHHHHH\n";
		}
	}
};

int main()
{
	srand(time(nullptr));
rr:

	cout << "Player count? (1-4) \n";
	int playerCount;
	cin >> playerCount;
	system("CLS");

	if (playerCount == 1)
	{
		game one;

		string name;
		bool strik = false;

		cout << "Alone? How bad *cries cries* \n";
		cout << "Joking, joking, but eitherway loner\n";
		cout << "What's your name, huh?\n";
		map<string, int> Scoreboard;
		name = one.name();
		Scoreboard[name] = 0;
		ready ones;
		++ones;

		for (int i = 0; i < 5; i++)
		{
			system("CLS");
			cout << " Round " << i + 1 << ": \n";

			for (int j = 0; j < 10; j++)
			{
				one.generate();
				strik = one.gameMech();

				if (strik == false)
				{
					cout << " Oops, mistake";
					break;
				}
				if (strik == true)
				{
					Scoreboard[name]++;
				}
			}

			cout << "\nScoreboard: \n";
			cout << "=======================================\n";

			for (auto iter = Scoreboard.begin(); iter != Scoreboard.end(); ++iter)
			{
				cout << (*iter).first << " | " << (*iter).second << endl;
			}

			cout << "\nTo continue click any button";
			(void)_getch();
			system("CLS");
		}
		system("CLS");
		cout << " GAME IS FINISHED\n\n";
		cout << "\nScoreboard: \n";
		cout << "=======================================\n";
		for (auto iter = Scoreboard.begin(); iter != Scoreboard.end(); ++iter)
		{
			cout << (*iter).first << " | " << (*iter).second << endl;
		}
		cout << "\nTo end the game click any button";
		(void)_getch();
		return 0;
	}

	// TWO PLAYER -------------------------------------------------
	if (playerCount == 2)
	{
		game one;
		game two;

		string nameFirst, nameSecond;
		bool strik = false;

		map<string, int> Scoreboard;

		cout << "I shall call the first player to state his name: ";
		nameFirst = one.name();
		cout << "Now the second player, state your name: ";
		nameSecond = two.name();

		Scoreboard[nameFirst] = 0;
		Scoreboard[nameSecond] = 0;

		ready ones;
		++ones;

		for (int i = 0; i < 5; i++)
		{
			system("CLS");
			cout << " Round " << i + 1 << ": \n";


			cout << nameFirst << " ready? (Click any button)\n";
			(void)_getch();
			for (int j = 0; j < 10; j++)
			{
				one.generate();
				strik = one.gameMech();

				if (strik == false)
				{
					cout << " Oops, mistake\n";
					j = 10;
				}
				else if (strik == true)
				{
					Scoreboard[nameFirst]++;
				}
			}

			cout << nameSecond << " ready? (Click any button)\n";
			(void)_getch();


			for (int j = 0; j < 10; j++)
			{
				two.generate();
				strik = two.gameMech();

				if (strik == false)
				{
					cout << " Oops, mistake";
					j = 10;
				}
				else if (strik == true)
				{
					Scoreboard[nameSecond]++;
				}
			}

			cout << "\nScoreboard: \n";
			cout << "=======================================\n";

			for (auto iter = Scoreboard.begin(); iter != Scoreboard.end(); ++iter)
			{
				cout << (*iter).first << " | " << (*iter).second << endl;
			}

			cout << "\nTo continue click any button";
			(void)_getch();
			system("CLS");
		}
		system("CLS");
		cout << " GAME IS FINISHED\n\n";
		cout << "\nScoreboard: \n";
		cout << "=======================================\n";
		for (auto iter = Scoreboard.begin(); iter != Scoreboard.end(); ++iter)
		{
			cout << (*iter).first << " | " << (*iter).second << endl;
		}
		cout << "\nTo end the game click any button";
		(void)_getch();
		return 0;
	}
	if (playerCount == 3)
	{
		game one;
		game two;
		game three;

		string nameFirst, nameSecond, nameThird;
		bool strik = false;

		map<string, int> Scoreboard;

		cout << "I shall call the first player to state his name: ";
		nameFirst = one.name();
		cout << "Now the second player, state your name: ";
		nameSecond = two.name();
		cout << "Well, third player, what are you waiting for huh? ";
		nameThird = three.name();

		Scoreboard[nameFirst] = 0;
		Scoreboard[nameSecond] = 0;
		Scoreboard[nameThird] = 0;

		ready ones;
		++ones;

		for (int i = 0; i < 5; i++)
		{
			system("CLS");
			cout << " Round " << i + 1 << ": \n";


			cout << nameFirst << " ready? (Click any button)\n";
			(void)_getch();
			for (int j = 0; j < 10; j++)
			{
				one.generate();
				strik = one.gameMech();

				if (strik == false)
				{
					cout << " Oops, mistake\n";
					j = 10;
				}
				else if (strik == true)
				{
					Scoreboard[nameFirst]++;
				}
			}

			cout << nameSecond << " ready? (Click any button)\n";
			(void)_getch();


			for (int j = 0; j < 10; j++)
			{
				two.generate();
				strik = two.gameMech();

				if (strik == false)
				{
					cout << " Oops, mistake\n";
					j = 10;
				}
				else if (strik == true)
				{
					Scoreboard[nameSecond]++;
				}
			}

			cout << nameThird << " ready? (Click any button)\n";
			(void)_getch();


			for (int j = 0; j < 10; j++)
			{
				three.generate();
				strik = three.gameMech();

				if (strik == false)
				{
					cout << " Oops, mistake";
					j = 10;
				}
				else if (strik == true)
				{
					Scoreboard[nameThird]++;
				}
			}

			cout << "\nScoreboard: \n";
			cout << "=======================================\n";

			for (auto iter = Scoreboard.begin(); iter != Scoreboard.end(); ++iter)
			{
				cout << (*iter).first << " | " << (*iter).second << endl;
			}

			cout << "\nTo continue click any button";
			(void)_getch();
			system("CLS");
		}
		system("CLS");
		cout << " GAME IS FINISHED\n\n";
		cout << "\nScoreboard: \n";
		cout << "=======================================\n";
		for (auto iter = Scoreboard.begin(); iter != Scoreboard.end(); ++iter)
		{
			cout << (*iter).first << " | " << (*iter).second << endl;
		}
		cout << "\nTo end the game click any button";
		(void)_getch();
		return 0;
	}
	if (playerCount == 4)
	{
		game one;
		game two;
		game three;
		game four;

		string nameFirst, nameSecond, nameThird, nameFourth;
		bool strik = false;

		map<string, int> Scoreboard;

		cout << "I shall call the first player to state his name: ";
		nameFirst = one.name();
		cout << "Now the second player, state your name: ";
		nameSecond = two.name();
		cout << "Well, third player, what are you waiting for huh? ";
		nameThird = three.name();
		cout << "Fourth, FOURTH YOUR NAME PLEASE? ";
		nameFourth = four.name();

		Scoreboard[nameFirst] = 0;
		Scoreboard[nameSecond] = 0;
		Scoreboard[nameThird] = 0;
		Scoreboard[nameFourth] = 0;

		ready ones;
		++ones;

		for (int i = 0; i < 5; i++)
		{
			system("CLS");
			cout << " Round " << i + 1 << ": \n";


			cout << nameFirst << " ready? (Click any button)\n";
			(void)_getch();
			for (int j = 0; j < 10; j++)
			{
				one.generate();
				strik = one.gameMech();

				if (strik == false)
				{
					cout << " Oops, mistake\n";
					j = 10;
				}
				else if (strik == true)
				{
					Scoreboard[nameFirst]++;
				}
			}

			cout << nameSecond << " ready? (Click any button)\n";
			(void)_getch();


			for (int j = 0; j < 10; j++)
			{
				two.generate();
				strik = two.gameMech();

				if (strik == false)
				{
					cout << " Oops, mistake\n";
					j = 10;
				}
				else if (strik == true)
				{
					Scoreboard[nameSecond]++;
				}
			}

			cout << nameThird << " ready? (Click any button)\n";
			(void)_getch();


			for (int j = 0; j < 10; j++)
			{
				three.generate();
				strik = three.gameMech();

				if (strik == false)
				{
					cout << " Oops, mistake\n";
					j = 10;
				}
				else if (strik == true)
				{
					Scoreboard[nameThird]++;
				}
			}
			cout << nameFourth << " ready? (Click any button)\n";
			(void)_getch();


			for (int j = 0; j < 10; j++)
			{
				four.generate();
				strik = four.gameMech();

				if (strik == false)
				{
					cout << " Oops, mistake";
					j = 10;
				}
				else if (strik == true)
				{
					Scoreboard[nameFourth]++;
				}
			}

			cout << "\nScoreboard: \n";
			cout << "=======================================\n";

			for (auto iter = Scoreboard.begin(); iter != Scoreboard.end(); ++iter)
			{
				cout << (*iter).first << " | " << (*iter).second << endl;
			}

			cout << "\nTo continue click any button";
			(void)_getch();
			system("CLS");
		}
		system("CLS");
		cout << " GAME IS FINISHED\n\n";
		cout << "\nScoreboard: \n";
		cout << "=======================================\n";
		for (auto iter = Scoreboard.begin(); iter != Scoreboard.end(); ++iter)
		{
			cout << (*iter).first << " | " << (*iter).second << endl;
		}
		cout << "\nTo end the game click any button";
		(void)_getch();
		return 0;
	}
	if (playerCount < 1 || playerCount > 4)
	{
		cout << " No thx\n";
		goto rr;
	}


	return 0;
}
