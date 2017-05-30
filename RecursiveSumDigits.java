
/**
 * Write a description of class RecursiveSumDigits here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RecursiveSumDigits
{
    public static int sumDigits(int n) {

        if (n==0)
            return 0;

        int largestPlace = (int)Math.log(n);
        int largestDigit = (int) n % (int)Math.pow(10,largestPlace)+1;
        int largestDigitVal = largestDigit * (int)Math.pow(10,largestPlace);

        return largestDigit + sumDigits(n-largestDigitVal);
    }
    
   public static void main(String args[]){
       System.out.println("1-> " + sumDigits(1));
    }
}
