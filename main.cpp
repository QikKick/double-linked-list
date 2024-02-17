#include <iostream>
#include <string>
int main(int argc, char *argv[]) {
    std::string fileName = std::string(argv[1]) + ".txt";
    std::cout << "Creating file: " << fileName << std::endl;

    return 0;
}
