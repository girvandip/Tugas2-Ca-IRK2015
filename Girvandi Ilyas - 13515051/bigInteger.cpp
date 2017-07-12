/* File : bigInteger.cpp
Author : Girvandi Ilyas */

#include "bigInteger.h"
#include <iostream>
#include <cmath>
#include <deque>
#include <ctime>

using namespace std;

//Constants
const bigInteger bigInteger::ZERO = bigInteger();
const bigInteger bigInteger::ONE = bigInteger(1);
const bigInteger bigInteger::TWO = bigInteger(2);
const bigInteger bigInteger::THREE = bigInteger(3);
const bigInteger bigInteger::FOUR = bigInteger(4);
const bigInteger bigInteger::FIVE = bigInteger(5);
const bigInteger bigInteger::SEVEN = bigInteger(7);
const bigInteger bigInteger::TEN = bigInteger(10);

//Constructors
bigInteger :: bigInteger() {
  isNegative = false;
  digits.push_back(0);
}

bigInteger :: bigInteger(long long number) {
  isNegative = number < 0;
  long long temp = number;
  if(isNegative) {
    temp = -temp;
  }
  while(temp != 0) {
    int digit = temp % 10;
    digits.push_back(digit);
    temp /= 10;
  }
  if(digits.size() == 0) {
    digits.push_back(0);
  }
}

bigInteger :: bigInteger(const string& str) {
  if(str.length() == 0) {
    isNegative = false;
    digits.push_back(0);
  } else {
    isNegative = str[0] == '-';
    int end = isNegative ? 1 : 0;
    for(int i = str.length()-1;i >= end;i--) {
      int digit = str[i] - '0';
      digits.push_back(digit);
    }
  }
}

bigInteger :: bigInteger(const bigInteger &bi) : isNegative(bi.isNegative), digits(bi.digits) {
}

//Operator=
bigInteger& bigInteger :: operator=(const bigInteger &bi) {
  if(this != &bi) {
    isNegative = bi.isNegative;
    digits = bi.digits;
  }
  return *this;
}

bigInteger& bigInteger :: operator=(long long rhs) {
  return operator=(bigInteger(rhs));
}


//Arithmetic Operators
bigInteger bigInteger :: operator+(const bigInteger& bi) const {
  if(isNegative == bi.isNegative) {
    cout << "masuk negative = negative" << endl;
    int end = digits.size() >= bi.digits.size() ? digits.size() : bi.digits.size();
    int carry = 0;
    bigInteger result = bi;
    for(int i = 0;i < end;i++) {
      if(i == result.digits.size()) {
        result.digits.push_back(0);
      }
      result.digits[i] += carry + (i < digits.size() ? digits[i] : 0);
      carry = result.digits[i] / 10;
      if(carry) {
        result.digits[i] -= 10;
      }
    }
    if(carry) {
      result.digits.push_back(carry);
    }
    return result;
  } else {
    cout << "masuk negative != negative (plus)" << endl;
    if(bi == bigInteger :: ZERO) {
      cout << "masuk = 0" << endl;
      return *this;
    }
    cout << "masuk yg ke -" << endl;
    return *this - (-bi);
  }
}
bigInteger bigInteger :: operator+(long long rhs) const {
  return operator+(bigInteger(rhs));
}
bigInteger& bigInteger :: operator+=(const bigInteger& bi) {
  *this = *this + bi;
  return *this;
}
bigInteger& bigInteger :: operator+=(long long rhs) {
  return operator+=(bigInteger(rhs));
}

bigInteger bigInteger :: operator-() const {
  bigInteger result = *this;
  if(result != bigInteger :: ZERO) {
    result.isNegative = !result.isNegative;
  }
  return result;
}
bigInteger bigInteger :: operator-(const bigInteger& bi) const {
  if(isNegative == bi.isNegative) {
    cout << "absolute this " << absolute() << endl;
    cout << "absolut bi " << bi.absolute() << endl;
    if(absolute() >= bi.absolute()) {
      cout << "maasuk abs >= abs" << endl;
      bigInteger result = *this;
      int borrow = 0;
      for(int i = 0; i < bi.digits.size();i++) {
        result.digits[i] -= borrow;
        if(i < bi.digits.size()) {
          result.digits[i] -= bi.digits[i];
        }
        borrow = (result.digits[i] < 0) ? 1 : 0;
        if(borrow) {
          result.digits[i] += 10;
        }
      }
      result.removeLeadZero();
      return result;
    } else {
      cout << "maasuk abs < abs" << endl;
      return -(bi - *this); 
    }
  } else {
    cout << "masuk negative != newgative (minus)" << endl;
    if(bi == bigInteger :: ZERO) {
      return *this;
    } 
    return *this + (-bi);
  }
}
bigInteger bigInteger :: operator-(long long rhs) const {
  return operator-(bigInteger(rhs));
}
bigInteger& bigInteger :: operator-=(const bigInteger& bi) {
  *this = *this - bi;
  return *this;
}
bigInteger& bigInteger :: operator-=(long long rhs) {
  return operator-=(bigInteger(rhs));
}

bigInteger bigInteger :: operator*(const bigInteger& bi) const {

}
bigInteger bigInteger :: operator*(long long rhs) const {

}
bigInteger& bigInteger :: operator*=(const bigInteger& bi) {

}
bigInteger& bigInteger :: operator*=(long long rhs) {

}
//Relational Operators
bool bigInteger :: operator==(const bigInteger& bi) const {
  bool isEqual = (digits.size() == bi.digits.size() && (isNegative == isNegative));
  int i = 0;
  while(isEqual && i < digits.size()) {
    isEqual = (digits[i] == bi.digits[i]);
    i++;
  }
  return isEqual;
}
bool bigInteger :: operator==(long long rhs) const {
  return operator==(bigInteger(rhs));
}
bool bigInteger :: operator!=(const bigInteger& bi) const {
  return !(*this == bi);
}
bool bigInteger :: operator!=(long long rhs) const {
  return operator!=(bigInteger(rhs));
}
bool bigInteger :: operator>(const bigInteger& bi) const {
  if(isNegative != bi.isNegative) {
    return isNegative == false;
  } else if (digits.size() != bi.digits.size()) {
    return isNegative ? digits.size() < bi.digits.size() : digits.size() > bi.digits.size();
  } else {
    for(int i = digits.size() - 1;i >= 0;i--) {
      if(digits[i] != bi.digits[i]) {
        return isNegative ? digits[i] < bi.digits[i] : digits[i] > bi.digits[i];
      }
      return false;
    }
  }
}
bool bigInteger :: operator>(long long rhs) const {
  return operator>(bigInteger(rhs));
}
bool bigInteger :: operator>=(const bigInteger& bi) const {
  return (*this > bi) || (*this == bi);
}
bool bigInteger :: operator>=(long long rhs) const {
  return operator>=(bigInteger(rhs));
}
bool bigInteger :: operator<(const bigInteger& bi) const {
  return (*this != bi) && (*this > bi);
}
bool bigInteger :: operator<(long long rhs) const {
  return operator<(bigInteger(rhs));
}
bool bigInteger :: operator<=(const bigInteger& bi) const {
  return (*this < bi) || (*this == bi);
}
bool bigInteger :: operator<=(long long rhs) const {
  return operator<=(bigInteger(rhs));
}

//Input/Output
istream& operator>>(istream &is, bigInteger& bi) {
  string input;
  is >> input;
  bi = bigInteger(input);
  return is;
}
ostream& operator<<(ostream &os, const bigInteger& bi) {
  if(bi.isNegative) {
    os << '-';
  }
  for(int i = bi.digits.size() - 1;i >= 0;i--) {
    os << bi.digits[i];
  }
  return os;
}
//Other Methods
bigInteger bigInteger :: absolute() const {
  bigInteger result = *this;
  result.isNegative = false;
  return result;
}

void bigInteger :: removeLeadZero() {
  while ((digits[digits.size()-1] == 0) && (digits.size() > 1)){
		digits.pop_back();
	}
	if ((digits.size() == 1) && (digits[0] == 0)){
		isNegative = false; 
	}
}

string bigInteger :: toString() {
  string str;
  if(isNegative) {
    str += '-';
  } 
  for(int i = digits.size()-1;i >= 0;i--) {
    str += digits[i] + '0';
  }
  return str;
}

bool bigInteger :: isOdd() const {
  return digits[0] % 2 == 1;
}

bool bigInteger :: isEven() const {
  return !isOdd();
}








