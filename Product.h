#include <bits/stdc++.h>

#ifndef PRODUCT_H
#define PRODUCT_H

using namespace std;
//class product to help with sorting
class Product{
    private:
    string ProductID,Name;
    float Price;
    string Quantity;
    public:
    Product(string,string,float,string);
    string getQuantity();
    float getPrice();
    string getID();
    string getName();
};

#endif