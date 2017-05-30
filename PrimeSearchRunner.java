
/**
 * This file reads a prime file and initiates a search
 * 
 * @author WT SMith
 * @version (a version number or a date)
 */
import java.io.*;
import java.util.Scanner;

public class PrimeSearchRunner
{
    /** Run the prime file search
     * 
     * @param args[0] name of the file to read
     */
    public static void main(String args[])
    {
        String primeFileName = "";

        // check to see if there are command line arguments to read
        if (args.length >0) {
            primeFileName = args[0];
        }

        // if the user did not specify the filename ... ask  ..

        // Create a primefile object and read the file
        PrimeFile myPrimeFile = new PrimeFile(primeFileName);

        if (myPrimeFile.readPrimes() == -1) {
            System.out.println("Sorry could not open that filename");
            System.exit(0);
        }

        boolean continueAskingForPrimes = true;
        int searchNumber, primeOrdinal, numChecks;
        Scanner consoleIn = new Scanner(System.in);

        while ( continueAskingForPrimes )
        {
            // Ask the user for a prime number to search for
            System.out.println("What prime would you like to search for? " + 
                "(Type 0 to exit):   ");
            searchNumber = consoleIn.nextInt();

            if (searchNumber >0)
            {
                int[] searchResults = myPrimeFile.primeOrderSearch(searchNumber);;
                primeOrdinal = searchResults[0];
                numChecks = searchResults[1];

                // check result of the prime ordinal
                switch( primeOrdinal ) {
                    case -1:
                    System.out.println("Your prime is too large to appear in this list");
                    break;

                    case -2: 
                    System.out.println("Your number was not found in the list \n"  
                        + "it isn't prime or the search programmer "
                        + "made a mistake in their code");
                    break;

                    default:
                    System.out.println("The prime you typed appears at position " 
                        + primeOrdinal + "in the list of primes");
                    System.out.println("It took " + numChecks + " to find that prime");
                }

            }//if
            else {
                if (searchNumber == 0) {
                    continueAskingForPrimes = false;
                    System.out.println("OK, no more searches. Exiting ...");
                }
                else {
                    System.out.println("Sorry, only positive numbers please. Try again.");
                }
            }//else

        }

        // inform the user position, if it is not in the list, or it is not prime

        // gracefully exit
        System.out.println("Goodbye");
    }

}
