
/**
 * Write a description of class CreatePrimeFile here.
 * 
 * @WT Smith (your name) 
 * @2/14 (a version number or a date)
 */
import java.util.*;
import java.io.*;
import java.util.Date;
import java.sql.Timestamp;

public class CreatePrimeFile
{
    static int[] piFunc = {4,25,168,1229,9592,78498,664579,5761455,50847534,455052511
     //                      4118054813,37607918018,346065536839
                           };                  
    
    public static void main(String[] args) {
        
        int primesUpTo = 100000;
    
        long time1,time2;
        Timestamp timer = new Timestamp(System.currentTimeMillis());
        time1=timer.getTime();
        
        ArrayList<Integer> primeList = new ArrayList<Integer>();
        // set the initial size of the arrayList

        
        if (args.length > 0)
        {
            primesUpTo = Integer.parseInt(args[0]);
        }
    
        String primesFileName = "PrimesUpTo" + Integer.toString(primesUpTo) + ".txt";
        /*
        int PUT = primesUpTo;
        double fPredictedNumPrimes = 
            PUT* (Math.log(PUT) + Math.log( 
                    Math.log(PUT-1) + 1.8* Math.log(Math.log(PUT))/Math.log(PUT)));
        int iPredictedNumPrimes = (int) fPredictedNumPrimes;           
        */
        int iPredictedNumPrimes = 0;
        int primeMagnitude = (int)( Math.log(primesUpTo)/Math.log(10));
        
        if (primeMagnitude < piFunc.length)
            iPredictedNumPrimes = piFunc[primeMagnitude];
            
        primeList.ensureCapacity ( (iPredictedNumPrimes ));
        
        if (args.length >1)
        {
            primesFileName = args[0];
            
            if (primesFileName.indexOf(".txt") < 0)
                primesFileName += ".txt";
        }
    
        System.out.println("Generate Prime numbers between 1 and " + primesUpTo);
        
        
        // loop through the numbers one by one
        //for (int i = 1; i < primesUpTo; i++) {
       
        // get the list started so we don't have to check special cases on small numbers.    
        primeList.add(new Integer(2));
        primeList.add(new Integer(3));
        
        int testNum = 5; // we will start our search with 5
        boolean keepSearching = true;
        // find a prime
        while( keepSearching == true )  {                              
            // default the number to prime when we start
            // this will change if we find it is divisible by another prime.
            boolean isPrimeNumber = true;   
            
            // check to see if the number is prime            
            double maxCandidate = Math.sqrt(testNum);           
            
            // iterate through prime factors.  Only until it's up to the 
            // square root of the testNum
            // we will break out of the for loop as soon as we know it can't
            // be prime.
            for (int primeIdx = 0; primeIdx < primeList.size(); primeIdx++) {                

                int factor = primeList.get(primeIdx);
                
                if (factor > maxCandidate){
                    isPrimeNumber = true;
                    break;
                }
                
                // this statement should trigger long before we reach 
                // the end of the prime list.
                if (testNum % factor == 0) {
                    isPrimeNumber = false;
                    break; // exit the inner for loop
                }
            }
    
            // print the number if prime
            if (isPrimeNumber) {
                System.err.print(testNum + " ");
                primeList.add(new Integer(testNum));
                if ((testNum/2)%5 == 0)
                    System.err.println();
            }
            
            testNum += 2;  // bump up by two, there is no reason to check even numbers
            
            // stop the loop once we've gone over our test limit
            if (testNum > primesUpTo)
                keepSearching = false;
                

        }
        
        Timestamp timer2 = new Timestamp(System.currentTimeMillis());
        time2 = timer2.getTime();
            
            System.out.println(time1 + "," + time2 + "," + (time2-time1));
        // output coins to a file
        try {
            PrintWriter writer = new PrintWriter(primesFileName, "UTF-8");
            
            // write a header for the prime file.
            
            writer.println(primeList.size());
            // write primes to the file
            for (int pos=0; pos < primeList.size(); pos++)
            {
                writer.println(primeList.get(pos));
            }
            
            writer.close();  
        }
        catch (IOException e) {
            System.out.println("Sorry, there was a File IOException");
            System.err.println(e.toString());
            System.exit(0);
        }    
        
        // inform the user that the file was successfully read
        System.out.println("The list of primes up to " + primesUpTo + 
                            " was sucessfully written to " + primesFileName);
    }   
}
