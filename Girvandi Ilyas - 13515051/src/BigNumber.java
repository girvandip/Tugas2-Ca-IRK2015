import java.awt.Panel;
import java.util.ArrayList;

/**
 * Created by Girvandip on 08-Jun-17.
 */

public class BigNumber {
  private ArrayList<Integer> digits; //Array containing digits
  private boolean negative; //Sign : Negative or Positive

  //Default Constructor
  public BigNumber() {
    negative = false;
    digits = new ArrayList<Integer>();
    digits.add(0);
  }
  //Constructor with long parameter
  public BigNumber(long number) {
    digits = new ArrayList<Integer>();
    if(number > 0) {
      long temp = number;
      negative = false;
      while (temp > 0) {
        digits.add((int) (temp % 10));
        temp /= 10;
      }
    } else if (number == 0) {
      negative = false;
      digits.add(0);
    } else {
      long temp = number * -1;
      negative = true;
      while (temp > 0) {
        digits.add((int) (temp % 10));
        temp /= 10;
      }
    }
  }
  //Constructor with BigNumber parameter
  public BigNumber(BigNumber number) {
    negative = number.negative;
    digits = new ArrayList<Integer>();
    for(int i = 0;i < number.digits.size();i++) {
      digits.add(number.digits.get(i));
    }
  }
  //Constructor with string parameter
  public BigNumber(String number) {
    if(number.length() == 0) {
      negative = false;
      digits.add(0);
    } else {
      negative = (number.charAt(0) == '-');
      System.out.println(negative);
      int end = negative ? 1 : 0;
      System.out.println(end);
      for(int i = number.length()-1;i >= end;i--){
        int character = number.charAt(i) - '0';
        System.out.print(character);
        //digits.add(character);
      }
    }
  }
  //Relational Operators
  public static boolean equals(BigNumber n1, BigNumber n2) {
    if(n1.digits.size() != n2.digits.size() || n1.negative != n2.negative) {
      return false;
    } else {
      boolean isEqual = true;
      int i = 0;
      while(isEqual && i < n1.digits.size()) {
        if(n1.digits.get(i) == n2.digits.get(i)) {
          i++;
        } else {
          isEqual = false;
        }
      }
      return isEqual;
    }
  }
  public boolean equals(BigNumber n) {
    return equals(this,n);
  }
  public boolean equals(BigNumber n1, long n2) {
    BigNumber number = new BigNumber(n2);
    return equals(n1,number);
  }
  public boolean equals(long n) {
    BigNumber number = new BigNumber(n);
    return equals(this,number);
  }
  public static boolean lessOrEqual(BigNumber n1, BigNumber n2) {
    if(!n1.negative && n2.negative) {
      return false;
    } else if(n1.negative && !n2.negative) {
      return true;
    } else {
        boolean lessOrEq;
        if(n1.digits.size() > n2.digits.size()) {
          lessOrEq = n1.negative && n2.negative ? true : false;
        } else if (n1.digits.size() < n2.digits.size()){
          lessOrEq = n1.negative && n2.negative ? false : true;
        } else {
          int i = n1.digits.size() - 1;
          while(i >= 0) {
            if(n1.digits.get(i) > n2.digits.get(i)) {
              lessOrEq = n1.negative && n2.negative ? true : false;
            } else if(n1.digits.get(i) < n2.digits.get(i)) {
              lessOrEq = n1.negative && n2.negative ? false : true;
            } else {
              i--;
            }
          }
          lessOrEq = true;
        }
      return lessOrEq;
    }
  }
  public boolean lessOrEqual(BigNumber n) {
    return lessOrEqual(this,n);
  }
  public boolean lessOrEqual(BigNumber n1, long n2) {
    BigNumber number = new BigNumber(n2);
    return lessOrEqual(n1,number);
  }
  public boolean lessOrEqual(long n) {
    BigNumber number = new BigNumber(n);
    return lessOrEqual(this,n);
  }
  public static boolean lessThan(BigNumber n1, BigNumber n2) {
    return true;
  }
  public static boolean lessThan(BigNumber n) {
    return true;
  }
  public static boolean moreOrEqual(BigNumber n1, BigNumber n2) {
    return true;
  }
  public static boolean moreOrEqual(BigNumber n) {
    return true;
  }
  public static boolean moreThan(BigNumber n1, BigNumber n2) {
    return true;
  }
  public static boolean moreThan(BigNumber n) {
    return true;
  }
  public static boolean notEqual(BigNumber n1, BigNumber n2) {
    return true;
  }
  public static boolean notEqual(BigNumber n) {
    return true;
  }


  //Arithmetic Operators
  //Adding 2 BigNumber
  public static BigNumber add(BigNumber n1, BigNumber n2) {
    BigNumber result = new BigNumber(n2);
    if(n1.negative == n2.negative) {

    }

    return result;
  }


  //Adding BigNumber into current object
  public void add(BigNumber n) {

  }
  //Substracting 2 BigNumber (n1 - n2)
  public static BigNumber substract(BigNumber n1, BigNumber n2) {
    BigNumber result = new BigNumber(n1);

    return result;
  }
  //Substracting current object with a BigNumber
  public void substract(BigNumber n) {

  }
  //Multiply 2 BigNumber
  public static BigNumber multiply(BigNumber n1, BigNumber n2) {
    BigNumber result = new BigNumber(n1);

    return result;
  }
  //Multiplying BigNumber into current object
  public void multiply(BigNumber n) {

  }
  //Dividing 2 BigNumber (n1/n2)
  public static BigNumber div(BigNumber n1, BigNumber n2) {
    BigNumber result = new BigNumber(n1);

    return result;
  }
  //Dividing current object with a BigNumber
  public void div(BigNumber n) {

  }
  //Modulo operation (n1 mod n2)
  public static BigNumber mod(BigNumber n1, BigNumber n2) {
    BigNumber result = new BigNumber(n1);

    return result;
  }
  //Modulo operation (current object / n)
  public void mod(BigNumber n) {

  }

  public void print() {
    for(int i = digits.size()-1;i >= 0;i--) {
      System.out.print(digits.get(i));
    }
    System.out.println();
  }
  /*
  //Checking prime number using miller rabin test : t as security parameter
  public static boolean isPrime(int t) {

  }
  public static boolean isPrime(BigNumber n, int t) {

  }
  public static BigNumber generateRandom(int size) {

  }

  public static BigNumber generateRandomPrime(int size) {

  } */

  public static void main(String args[]) {
    BigNumber N1 = new BigNumber();
    BigNumber N2 = new BigNumber(-2208);
    BigNumber N3 = new BigNumber(N2);
    BigNumber N4 = new BigNumber(-22);
    BigNumber N5 = new BigNumber(220897);
    BigNumber N6 = new BigNumber(-220897);
    BigNumber N7 = new BigNumber("-1234567890123456789012345");
    N7.print();
    N2.print();
    /*System.out.println(N2.equals(N3));
    System.out.println(N2.equals(N4));
    System.out.println(N2.equals(N5));
    System.out.println(N2.lessOrEqual(N3));
    System.out.println(N2.lessOrEqual(N4));
    System.out.println(N2.lessOrEqual(N5));
    System.out.println(N5.lessOrEqual(N2));
    */
  }
}
