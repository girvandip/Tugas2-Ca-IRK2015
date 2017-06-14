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
      digits = new ArrayList<Integer>();
      negative = false;
      digits.add(0);
    } else {
      digits = new ArrayList<Integer>();
      negative = (number.charAt(0) == '-');
      int end = negative ? 1 : 0;
      for(int i = number.length()-1;i >= end;i--){
        int character = number.charAt(i) - '0';
        digits.add(character);
      }
    }
  }
  //Copying A BigNumber into current Object
  public void copy(BigNumber number) {
    negative = number.negative;
    digits = new ArrayList<Integer>();
    for(int i = 0;i < number.digits.size();i++) {
      digits.add(number.digits.get(i));
    }
  }

  //Comparing 2 bigNumber (if n1 is bigger than n2, return true)
  public static boolean compareBN(BigNumber n1, BigNumber n2) {
    if (n1.negative && !n2.negative) {
      return false;
    } else if (!n1.negative && n2.negative) {
      return true;
    } else {
      boolean isBigger = true;
      if (n1.digits.size() > n2.digits.size()) {
        isBigger = n1.negative && n2.negative ? false : true;
      } else if (n1.digits.size() < n2.digits.size()) {
        isBigger = n1.negative && n2.negative ? true : false;
      } else {
        boolean stop = false;
        int i = n1.digits.size() - 1;
        while (i >= 0 && !stop) {
          if (n1.digits.get(i) > n2.digits.get(i)) {
            isBigger = n1.negative && n2.negative ? false : true;
            stop = true;
          } else if (n1.digits.get(i) < n2.digits.get(i)) {
            isBigger = n1.negative && n2.negative ? true : false;
            stop = true;
          } else {
            i--;
          }
        }
      }
      return isBigger; //n1 > n2 ? true : false
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
    return !compareBN(n1,n2) || equals(n1,n2);
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
    return !compareBN(n1,n2) && !equals(n1,n2);
  }
  public boolean lessThan(BigNumber n) {
    return lessThan(this,n);
  }
  public static boolean moreOrEqual(BigNumber n1, BigNumber n2) {
    return compareBN(n1,n2) || equals(n1,n2);
  }
  public boolean moreOrEqual(BigNumber n) {
    return moreOrEqual(this,n);
  }
  public static boolean moreThan(BigNumber n1, BigNumber n2) {
    return compareBN(n1,n2) && !equals(n1,n2);
  }
  public boolean moreThan(BigNumber n) {
    return moreThan(this,n);
  }
  public static boolean notEqual(BigNumber n1, BigNumber n2) {
    return !equals(n1,n2);
  }
  public boolean notEqual(BigNumber n) {
    return notEqual(this,n);
  }


  //Arithmetic Operators
  //Adding 2 BigNumber
  public static BigNumber add(BigNumber n1, BigNumber n2) {
    if(n1.negative == n2.negative) {
      int current;
      int carry = 0;
      int end = n1.digits.size() >= n2.digits.size() ? n1.digits.size() : n2.digits.size();
      for(int i = 0;i < end;i++) {
        if(i >= n1.digits.size()) {
          n1.digits.add(0);
        }
        if(i < n2.digits.size()) {
          current = n2.digits.get(i);
        } else {
          current = 0;
        }
        n1.digits.set(i,n1.digits.get(i) + carry + current);
        carry = n1.digits.get(i) / 10;
        if(carry > 0) {
          n1.digits.set(i,n1.digits.get(i) - 10);
        }
      }
      if(carry > 0) {
        n1.digits.add(carry);
      }
      return n1;
    } else {
      BigNumber number2 = new BigNumber(n2);
      number2.negate();
      n1.substract(number2);
      return n1;
    }
  }


  //Adding BigNumber into current object
  public void add(BigNumber n) {
    add(this,n);
  }
  //Substracting 2 BigNumber (n1 - n2)
  public static BigNumber substract(BigNumber n1, BigNumber n2) {
    if(!n1.negative && !n2.negative) {
      if(moreOrEqual(n1.absolute(),n2.absolute())) {
        int borrow = 0;
        int current;
        for(int i = 0;i < n1.digits.size();i++) {
          int rhs = i < n2.digits.size() ? n2.digits.get(i) : 0;
          current = n1.digits.get(i) - borrow - rhs;
          borrow = current < 0 ? 1 : 0;
          if(borrow == 1) {
            current += 10;
          }
          n1.digits.set(i,current);
        }
        while(n1.digits.get(n1.digits.size()-1) == 0 && (n1.digits.size() > 1)) {
          n1.digits.remove(n1.digits.size()-1);
        }
        return n1;
      } else { //n1.abs < n2.abs
        BigNumber number2 = new BigNumber(n2);
        number2.substract(n1);
        number2.negate();
        n1.copy(number2);
        return n1;
      }
    } else {
      BigNumber number2 = new BigNumber(n2);
      number2.negate();
      n1.add(number2);
      return n1;
    }
  }

  //Substracting current object with a BigNumber
  public void substract(BigNumber n) {
    substract(this,n);
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

  //Negating a BigNumber
  public void negate() {
    negative = !negative;
  }
  public BigNumber absolute() {
    BigNumber number = new BigNumber(this);
    number.negative = false;
    return number;
  }

  public void print() {
    if(negative) {
      System.out.print('-');
    }
    for(int i = digits.size()-1;i >= 0;i--) {
      System.out.print(digits.get(i));
    }
    System.out.println();
  }
  /*
  //Checking prime number using miller rabin test : t as security parameter
  public boolean isPrime(int t) {

  }
  public static boolean isPrime(BigNumber n, int t) {

  }
  public static BigNumber generateRandom(int size) {

  }

  public static BigNumber generateRandomPrime(int size) {

  } */

  public static void main(String args[]) {
    BigNumber N1 = new BigNumber(36);
    BigNumber N2 = new BigNumber(34);
    BigNumber N3 = new BigNumber(N2);
    BigNumber N4 = new BigNumber();
    BigNumber N5 = new BigNumber(-857336);
    BigNumber N6 = new BigNumber(-435220897);
    BigNumber N7 = new BigNumber("-12364783286123612376123861874123");
    BigNumber N8 = new BigNumber(220897);
    //N2.print();
    //N7.print();
    /*System.out.println(N2.equals(N3));
    System.out.println(N2.equals(N4));
    System.out.println(N2.equals(N5));
    System.out.println(notEqual(N2,N3));
    System.out.println(notEqual(N7,N4));
    System.out.println(notEqual(N7,N5));
    System.out.println(notEqual(N5,N7));
    */
    N2.substract(N1);
    N2.print();
  }
}
