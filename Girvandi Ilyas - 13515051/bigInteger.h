/* File : bigInteger.h
Author : Girvandi Ilyas */

#include <iostream>
#include <deque>
#include <string>

using namespace std;

class bigInteger {
  public :
    //Constants
    static const bigInteger ZERO, ONE, TWO, THREE, FOUR, FIVE, SEVEN, TEN;
    
    //Default constructor
    bigInteger();
    bigInteger(long long number);
    bigInteger(const string& str);
    bigInteger(const bigInteger &bi);

    //Operator=
    bigInteger& operator=(const bigInteger& bi);
    bigInteger& operator=(long long rhs);

    //Arithmetic Operators
    bigInteger operator+(const bigInteger& bi) const;
    bigInteger operator+(long long rhs) const;
    bigInteger& operator+=(const bigInteger& bi);
    bigInteger& operator+=(long long rhs);

    bigInteger operator-() const; //Used to negate a number
    bigInteger operator-(const bigInteger& bi) const;
    bigInteger operator-(long long rhs) const;
    bigInteger& operator-=(const bigInteger& bi);
    bigInteger& operator-=(long long rhs);

    bigInteger operator*(const bigInteger& bi) const;
    bigInteger operator*(long long rhs) const;
    bigInteger& operator*=(const bigInteger& bi);
    bigInteger& operator*=(long long rhs);

    bigInteger operator/(const bigInteger& bi) const;
    bigInteger operator/(long long rhs) const;
    bigInteger& operator/=(const bigInteger& bi);
    bigInteger& operator/=(long long rhs);

    bigInteger operator%(const bigInteger& bi) const;
    bigInteger operator%(long long rhs) const;
    bigInteger& operator%=(const bigInteger& bi);
    bigInteger& operator%=(long long rhs);

    //Relational Operators
    bool operator==(const bigInteger& bi) const;
    bool operator==(long long rhs) const;
    bool operator!=(const bigInteger& bi) const;
    bool operator!=(long long rhs) const;
    bool operator>(const bigInteger& bi) const;
    bool operator>(long long rhs) const;
    bool operator>=(const bigInteger& bi) const;
    bool operator>=(long long rhs) const;
    bool operator<(const bigInteger& bi) const;
    bool operator<(long long rhs) const;
    bool operator<=(const bigInteger& bi) const;
    bool operator<=(long long rhs) const;

    //Input/Output
    friend istream& operator>>(istream &is, bigInteger& bi);
	  friend ostream& operator<<(ostream &os, const bigInteger& bi);

    //Other Methods
    bigInteger absolute() const;
    string toString();
	  int toInt();
    bool isOdd() const;
  	bool isEven() const;
  

  private :
    deque<int> digits;
    bool isNegative;
    void removeLeadZero();
};