#include "Product.h"
//constructor
Product::Product(string id, string name, float price, string quantity){
    this->Name = name;
    this->ProductID =id;
    this->Price = price;
    this->Quantity = quantity;
}
//getters
string Product::getID(){
    return this->ProductID;
} 
string Product::getName(){
    return this->Name;
}
string Product::getQuantity(){
    return this->Quantity;
}
float Product::getPrice(){
    return this->Price;
}
