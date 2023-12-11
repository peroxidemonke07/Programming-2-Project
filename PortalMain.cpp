#include "Portal.h"
#include <iostream>


using namespace std;
//main function
int main(){
    //portal which we are testing
    Portal * NaPortal = new Portal();
    //string to store user input
    string NaString;
    
    while(true){
   
    getline(cin,NaString);

    NaPortal->ProcessUserCommand(NaString);

    }

    return 0;
}