#include <iostream>
#include <string>
#include <fstream>

    int main(int argc, char *argv[]) {
        if(argc > 1) {
            std::string fileName = std::string(argv[1]) + ".txt";
            std::cout << "Reading file: " << fileName << std::endl;

            std::ifstream file(fileName);
            if(file.is_open()) {
                std::string line;
                while(std::getline(file, line)) {
                    std::cout << line << std::endl;
                }
                file.close();
            } else {
                std::cout << "Unable to open file." << std::endl;
            }
            std::ifstream fileIn(fileName);
            if(file.is_open()) {
                std::string line;
                while(std::getline(file, line)) {
                    std::cout << line << std::endl;
                }
                file.close();
            } else {
                std::cout << "Unable to open file." << std::endl;
            }

        } else {
            std::cout << "No arguments provided." << std::endl;
        }



        return 0;
    }
