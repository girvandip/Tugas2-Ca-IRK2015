import java.util.ArrayList;

/**
 * Created by DELL on 08-Jun-17.
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
  //Adding 2 BigNumber
  public static BigNumber Add(BigNumber n1, BigNumber n2) {
    BigNumber result = new BigNumber(n2);
    
    return result;
  }
}
