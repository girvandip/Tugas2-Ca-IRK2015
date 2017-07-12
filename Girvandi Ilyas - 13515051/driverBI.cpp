/*File : driverBI.cpp
Author : Girvandi Ilyas */

#include "bigInteger.h"

int main() {
  bigInteger B0(0);
  bigInteger B1(-128813);
  bigInteger B2("12762");
  bigInteger B3(22013);
  bigInteger B4("18261");
  bigInteger B5 = B4;
  bigInteger B6;
  bigInteger B7;
  B7 = B2 + B4;
  cout << B1 + B2 << endl;
  cout << B1 + B3 << endl;
  cout << B7 << endl;
  cout << B3 - B2 << endl;
  cout << B4 - B1 << endl;
  cout << B5 - B1 << endl;
  cout << B2 + B2 << endl;
  return 0;
}