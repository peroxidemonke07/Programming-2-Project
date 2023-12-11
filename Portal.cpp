#include "Portal.h"
#include <iostream>
//method to split a string 
//similar to String.split(" ") in java
vector<string> Split(string s){
    int i,j=0;
    vector<string> v;
    for(i=0;i<s.length();i++){
        if(s[i] == ' '){
            v.push_back(s.substr(j,i-j));
            j = i+1;
        }
        else if(i == s.length()-1){
            v.push_back(s.substr(j));
        }
    }
    return v;
}
//comparator for price
bool CompPrice(Product *p1, Product * p2){
    return p1->getPrice() < p2->getPrice();
}
//comparator for name
bool CompName(Product *p1, Product * p2){
    return p1->getName() < p2->getName();
}
//counter to generate portalIDs
int Portal::portalIDCounter =0;
//constructor
Portal ::Portal(){
    this->PortalID = ++portalIDCounter; 
} 
int Portal::getPortalID(){return this->PortalID;}

//method to process user command
void Portal::ProcessUserCommand(string Command){
    //vector that contains individual parameters of the command
    vector<string> NaVector = Split(Command);
    //stream to writeto string
    ofstream fWrite;
    
    bool keepRunning = true;
    
    string temp1,temp2,temp3;
    if(NaVector.size() > 0){
        temp1 = NaVector[0];
    }
    
    if(NaVector.size() == 3){
        temp2 = NaVector[1],temp3 = NaVector[2]; 
    }
    //processing the command:
    // 1) find which kind of command it is and add the corresponding request to the queue 
    //    we pop this when we finish processing the coressponding response
    // 2) write the request to PortalToPlatform.txt
    // 3) if command is "Check" then read the PlatformToPortal.txt
        if(temp1 == "Start"){
            PendingResponses.push({currReqNo,"Start"});
            fWrite.open("PortalToPlatform.txt",ios::app);
            fWrite<<this->getPortalID() <<" "<< this->currReqNo<<" "<<"Start"<<endl;
            fWrite.close();
            this->currReqNo++;
        }
        else if(temp1 == "List"){
            PendingResponses.push({currReqNo,"List"+temp3});
            fWrite.open("PortalToPlatform.txt",ios::app);
            fWrite<<this->getPortalID() <<" "<< this->currReqNo << " " << "List"<<" "<<temp2<<endl;
            this->currReqNo++;
        }
        else if(temp1 == "Buy"){

            PendingResponses.push({currReqNo,"Buy"});
            fWrite.open("PortalToPlatform.txt",ios::app);
            fWrite<<this->getPortalID() <<" "<< this->currReqNo <<" Buy "<<temp2<<" "<<temp3<<endl;
            this->currReqNo++;
        }
        else if(temp1 == "Check"){
            this->CheckResponse();
           
        }   
}
//method to process the response from the platform
void Portal::CheckResponse(){
    //current line we are at
    int currentLine = 0;
    string response;
    //stream to read the platformtoportal.txt file
    ifstream fReader;
    //opening file
    fReader.open("PlatformToPortal.txt");
    while(getline(fReader,response)){
        currentLine++;
        if(currentLine == nextLineTobeRead){
            vector<string> ResponseArray = Split(response);
            stringstream myStream(ResponseArray[1]);
            int reqID = 0;
            myStream>>reqID;
            if(!PendingResponses.empty()){
            currentResponseString = PendingResponses.front().second;
            }
            //if current pending response is for start request
            if(currentResponseString.substr(0,5) == "Start"){
                cout<<"Categories:"<<endl;
                for(int i=2;i<ResponseArray.size();i++){
                    cout<<ResponseArray[i]<<endl;
                }
                
                PendingResponses.pop();
            }
            
            //if current pending response is for buy request
            else if(currentResponseString.substr(0,3) == "Buy"){
                
                cout<<"Your Transaction for request RequesID: "<<reqID<<" was a "<<ResponseArray[2]<<endl;
                PendingResponses.pop();
            }
            //if current pending response is for list request
            else if(currentResponseString.substr(0,4) == "List"){
               
                //if you have not reached the end of list response
                if(ResponseArray[2] != "EndOfList"){
                    //create product object
                    

                    string pid = ResponseArray[2];
                    string pname = ResponseArray[3];
                    string pquantity = ResponseArray[5];

                    float pprice = stof(ResponseArray[4]);
                    

                    Product * p = new Product(pid,pname,pprice,pquantity);
                    
                    //add element to vector of products
                    ListArray.push_back(p);
                }
                //when you reach the end of the list response
                else {
                    
                    string parameter = currentResponseString.substr(4);
                    //sort based on the parameter
                    if(parameter == "Price"){
                        sort(ListArray.begin(),ListArray.end(),CompPrice);
                    }
                    else if(parameter == "Name"){
                        sort(ListArray.begin(),ListArray.end(),CompName);
                    }
                    //print sorted list
                    for(Product* myProduct : ListArray){
                        cout<<myProduct->getID()<<" "<<myProduct->getName()<<" "<<myProduct->getPrice()<<" "<<myProduct->getQuantity()<<endl;
                    }
                    //clear for reuse
                    ListArray.clear();
                    //popping the current response from the queue
                    PendingResponses.pop();
                }
            }
            //inc next line to be read
            nextLineTobeRead++;   
        }   
    }
}

