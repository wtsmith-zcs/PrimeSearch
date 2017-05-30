/**
 * This class reads a prime file and makes an array.
 * @WT Smith (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import java.io.*;

public class PrimeFile
{
    // instance variables - replace the example below with your own
    private ArrayList<Integer> primeList= new ArrayList<Integer>();
    String inputFileName;
    
    /**
     * Given a prime number, determines it is the nth prime
     * 
     * @param n the prime number to search for
     * @returns [0] the ordinal number of the prime 1->1, 5->4, etc...
     *          -1 if the prime number is not in this list. 
     *          -2 if the number is is not prime.
     *          [1] the number of comparisons
     */
    public int[] primeOrderSearch(int searchNum)
    {
        // set these vars with the results
        int searchOrdinal = -2; // by default we assume that the number is not in the list
        int numChecks = 0; // by default we begin with zero checks;
        
        int lowerBound = 0;
        int upperBound = primeList.size();
        
        // check to see if the prime is out of range of the list
        if (searchNum > primeList.get(primeList.size()-1)) {
            int[] outOfBoundsAry = {-1,1};
            return outOfBoundsAry ;
        }
        // the prime could be in the list ... check
        else{ // ADD YOUR CODE HERE
            System.err.println("executing your code");
        
            // While we haven't found the prime
                // increment the number of checks by one 
                
                // Identify the element halfway between the lower and upper bounds
                // Check to see if this is the element            
                    // if so 
                    // set the searchOrdinal to the current index and 
                    // set the while control variable to false
                    
                    // if not
                    // check to see if the element is less than or greater than 
                    // the searchNum
                        // if greater
                        // reset the lower bound to halfway between upper and lower bound
                        
                        // if less
                        // reset the upper bound to halfway betwwen the upper and lower bound
                        
                        // if lowerBound == upper Bound
                            // the element is not present in the list
                            // set the while control variable to false
                            // set the searchOrdinal to -2 (not prime)
        }
        
             
        // DONT MODIFY THE CODE BELOW HERE
        // creates an array containing the outcome of the search and the number of checks
        int[] returnArray = {searchOrdinal,numChecks};
        return returnArray;
    }

    /**
     * Constructor for objects of class ReadAPrimeFile
     */
    public PrimeFile(String primeFileName)
    {
        // initialise instance variables
        this.inputFileName = primeFileName;
        
    }
    
    public int readPrimes()
    {   
        Scanner inFile;
        
        try {    
            System.err.println("Opening primeFile file " + this.inputFileName);
            inFile = new Scanner ( new File(this.inputFileName));
        }
        catch(java.io.FileNotFoundException error){
            System.err.println("File not found, exiting");
            return -1;
        }
        

        String line = inFile.nextLine();
        
        // read number of primes from the file header and ensure array capacity.
        int numPrimes = Integer.parseInt(line);       
        primeList.ensureCapacity(numPrimes);
        
        while (inFile.hasNext())
        {
            line = inFile.nextLine();                     
            primeList.add(Integer.parseInt(line));
        }
        
        // close the file.
        inFile.close();
        
        return 1;
        
    }
}
