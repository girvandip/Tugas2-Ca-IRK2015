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
  public static boolean equals(BigNumber n1, long n2) {
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
  public static boolean lessOrEqual(BigNumber n1, long n2) {
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
    if(negative == n.negative) {
      int current;
      int carry = 0;
      int end = digits.size() >= n.digits.size() ? digits.size() : n.digits.size();
      for(int i = 0;i < end;i++) {
        if(i >= digits.size()) {
          digits.add(0);
        }
        if(i < n.digits.size()) {
          current = n.digits.get(i);
        } else {
          current = 0;
        }
        digits.set(i,digits.get(i) + carry + current);
        carry = digits.get(i) / 10;
        if(carry > 0) {
          digits.set(i,digits.get(i) - 10);
        }
      }
      if(carry > 0) {
        digits.add(carry);
      }
    } else {
      BigNumber number2 = new BigNumber(n);
      number2.negate();
      substract(number2);
    }
    //add(this,n);
  }
  //Substracting 2 BigNumber (n1 - n2)
  public static BigNumber substract(BigNumber n1, BigNumber n2) {
    if(!n1.negative && !n2.negative) {
      if(moreOrEqual(n1.absolute(),n2.absolute())) {
        BigNumber number1 = new BigNumber(n1);
        int borrow = 0;
        int current;
        for(int i = 0;i < number1.digits.size();i++) {
          int rhs = i < n2.digits.size() ? n2.digits.get(i) : 0;
          current = number1.digits.get(i) - borrow - rhs;
          borrow = current < 0 ? 1 : 0;
          if(borrow == 1) {
            current += 10;
          }
          number1.digits.set(i,current);
        }
        number1.removeLeadZero();
        return number1;
      } else { //n1.abs < n2.abs
        BigNumber number1 = new BigNumber(n1);
        BigNumber number2 = new BigNumber(n2);
        number2.substract(number1);
        number2.negate();
        number1.copy(number2);
        return number1;
      }
    } else {
      BigNumber number2 = new BigNumber(n2);
      BigNumber number1 = new BigNumber(n1);
      number2.negate();
      number1.add(number2);
      return number1;
    }
  }

  //Substracting current object with a BigNumber
  public void substract(BigNumber n) {
    BigNumber number = substract(this,n);
    copy(number);
  }
  //Multiply 2 BigNumber
  public static BigNumber multiply(BigNumber n1, BigNumber n2) {
    BigNumber number1 = new BigNumber(n1);
    BigNumber number2 = new BigNumber(n2);
    if(n1.digits.size() > n2.digits.size()) {
      int difference = n1.digits.size()-n2.digits.size();
      for(int i = 0;i < difference;i++) {
        number2.digits.add(0);
      }
    } else if(n1.digits.size() < n2.digits.size()) {
      int difference = n2.digits.size()-n1.digits.size();
      for(int i = 0;i < difference;i++) {
        number1.digits.add(0);
      }
    }
    int length = number1.digits.size();
    if(length == 1) {
      BigNumber result = new BigNumber(number1.digits.get(0) * number2.digits.get(0));
      result.negative = number1.negative ^ number2.negative;
      return result;
    } else {
      int middle = length / 2;
      BigNumber a = new BigNumber();
      BigNumber b = new BigNumber();
      BigNumber c = new BigNumber();
      BigNumber d = new BigNumber();
      for(int i = 0;i < middle;i++) {
        a.digits.add(number1.digits.get(i));
      }
      for(int i = middle;i < number1.digits.size();i++) {
        b.digits.add(number1.digits.get(i));
      }
      for(int i = 0;i < middle;i++) {
        c.digits.add(number2.digits.get(i));
      }
      for(int i = middle;i < number1.digits.size();i++) {
        d.digits.add(number2.digits.get(i));
      }
      int exp = 0;
      int exp2 = 0;
      for(int i = 0;i < middle;i++) {
        exp += 1;
        exp2 += 2;
      }
      BigNumber z0 = new BigNumber(multiply(a,c));//a,c
      BigNumber z1 = new BigNumber(multiply(add(a,b),add(c,d)));//a,b c,d
      BigNumber z2 = new BigNumber(multiply(b,d)); //b,d
      BigNumber z = new BigNumber(z2);
      z.addZeroToBack(exp2);
      BigNumber z3 = new BigNumber(substract(z1,add(z2,z0)));
      BigNumber x = new BigNumber(z3);
      x.addZeroToBack(exp);
      BigNumber result = new BigNumber(add(z,add(x,z0)));
      result.removeLeadZero();
      result.negative = n1.negative ^ n2.negative;
      return result;
    }
  }
  //Multiplying BigNumber into current object
  public void multiply(BigNumber n) {
    BigNumber number = multiply(this,n);
    copy(number);
  }
  //Dividing 2 BigNumber (n1/n2)
  public static BigNumber div(BigNumber n1, BigNumber n2) {
    BigNumber result = new BigNumber();
    if(n2.equals(0)) {
      System.out.println("rhs can't be zero");
    } else if(n1.moreThan(n2)) {
      BigNumber number1 = new BigNumber(n1);
      BigNumber snumber = new BigNumber();
      do {
        snumber.addNumberToBack(number1.digits.get(number1.digits.size()-1));
        snumber.print();
        if(lessThan(snumber,n2)) {
          result.addNumberToBack(0);
          if(equals(snumber,0)){
            snumber = new BigNumber();
          }
        } else if(moreThan(snumber,n2)) {
          int divider = 1;
          BigNumber multiplier = new BigNumber(divider);
          while(moreOrEqual(snumber,multiply(n2,multiplier))) {
            divider++;
            if(divider > 9) {
              multiplier.digits.set(0,0);
              multiplier.digits.add(1);
            } else {
              multiplier.digits.set(0,divider);
            }
          }
          divider--;
          multiplier.digits.set(0,divider);
          BigNumber temp = substract(snumber,multiply(n2,multiplier));
          if(equals(temp,0)) {
            snumber = new BigNumber();
          } else {
            snumber.copy(temp);
          }
          result.addNumberToBack(divider);
        } else {
          result.addNumberToBack(1);
          snumber = new BigNumber();
        }
        number1.digits.remove(number1.digits.size()-1);
        number1.print();
      } while (number1.digits.size() != 0);
    } else if(equals(n1,n2)) {
      result.digits.add(1);
    } else {
      result.digits.add(0);
    }
    result.removeLeadZero();
    result.negative = n1.negative ^ n2.negative;
    return result;
  }
  //Dividing current object with a BigNumber
  public void div(BigNumber n) {
    BigNumber number = div(this,n);
    copy(number);
  }
  //Modulo operation (n1 mod n2)
  public static BigNumber mod(BigNumber n1, BigNumber n2) {
    BigNumber result = new BigNumber();
    if(n2.equals(0)) {
      System.out.println("rhs can't be zero");
    } else if(n1.moreThan(n2)) {
      BigNumber number1 = new BigNumber(n1);
      BigNumber snumber = new BigNumber();
      do {
        snumber.addNumberToBack(number1.digits.get(number1.digits.size()-1));
        if(lessThan(snumber,n2)) {
          if(equals(snumber,0)){
            snumber = new BigNumber();
          }
        } else if(moreThan(snumber,n2)) {
          int divider = 1;
          BigNumber multiplier = new BigNumber(divider);
          while(moreOrEqual(snumber,multiply(n2,multiplier))) {
            divider++;
            if(divider > 9) {
              multiplier.digits.set(0,0);
              multiplier.digits.add(1);
            } else {
              multiplier.digits.set(0,divider);
            }
          }
          divider--;
          multiplier.digits.set(0,divider);
          BigNumber temp = substract(snumber,multiply(n2,multiplier));
          if(equals(temp,0)) {
            snumber = new BigNumber();
          } else {
            snumber.copy(temp);
          }
        } else {
          snumber = new BigNumber();
        }
        number1.digits.remove(number1.digits.size()-1);
      } while (number1.digits.size() != 0);
      result.copy(snumber);
    } else if(equals(n1,n2)) {
      result.digits.add(0);
    } else {
      result.copy(n1);
    }
    result.removeLeadZero();
    result.negative = n1.negative;
    return result;
  }
  //Modulo operation (current object / n)
  public void mod(BigNumber n) {
    BigNumber number = mod(this,n);
    copy(number);
  }
  //modPow to calculate (n1 ^ n2) % n3
  public static BigNumber modPow(BigNumber n1, BigNumber n2, BigNumber n3) {
    BigNumber result = new BigNumber(1);
    BigNumber iterator = new BigNumber(0);
    while(lessThan(iterator,n2)) {
      result.multiply(n1);
      result.mod(n3);
      iterator.digits.set(0,iterator.digits.get(0) + 1);
    }
    result.mod(n3);
    return result;
  }
  //mulMod to calculate (n1 * n2) % n3
  public static BigNumber mulMod(BigNumber n1, BigNumber n2, BigNumber n3) {
    BigNumber result = new BigNumber(n1);
    result.multiply(n2);
    result.mod(n3);
    return result;
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
  //Print to screen
  public void print() {
    if(negative) {
      System.out.print('-');
    }
    for(int i = digits.size()-1;i >= 0;i--) {
      System.out.print(digits.get(i));
    }
    System.out.println();
  }
  //Remove leading zero
  public void removeLeadZero() {
    while (digits.get(digits.size() - 1) == 0 && (digits.size() > 1)) {
      digits.remove(digits.size() - 1);
    }
  }
  //Adding zero to back
  public void addZeroToBack(int n) {
    for(int i = 0;i < n;i++)
      digits.add(0,0);
  }
  //Add certain number to back
  public void addNumberToBack(int number) {
    digits.add(0,number);
  }
  //Checking prime number using miller rabin test : t as security parameter
  /*public boolean isPrime(int t) {

  }
  public static boolean isPrime(BigNumber n, int t) {

  }
  public static BigNumber generateRandom(int size) {

  }

  public static BigNumber generateRandomPrime(int size) {

  }*/

  public static void main(String args[]) {
    BigNumber N1 = new BigNumber("128723");
    BigNumber N2 = new BigNumber("423");
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
    BigNumber number1 = mod(N1,N2);
    //BigNumber number2 = new BigNumber(25);
    //number2.addZeroToBack(10);
    number1.print();
  }
}
