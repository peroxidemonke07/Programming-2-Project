#include "Product.h"

#ifndef PORTAL_H
#define PORTAL_H

using namespace std;
//Portal class 
class Portal{
    private:
    //Attributes
    int PortalID,currReqNo = 1,currentResponseID,currentUnansweredResponse = 1;
    int nextLineTobeRead =1;
    string currentResponseString;
    //Queue to store pending requests
    queue<pair<int,string>> PendingResponses;
    
    vector<Product *> ListArray;
    public:
    static int portalIDCounter;
    Portal();
    int getPortalID();
    void ProcessUserCommand(string);
    void CheckResponse();
    void createPorToPlat();
};

#endif