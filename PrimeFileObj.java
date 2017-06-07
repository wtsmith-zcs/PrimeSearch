/**
 * Reads and writes to a prime file
 * 
 * Threadsafe ... with ensure that only once instance of this class will run
 * simultaneously for any PrimeFile
 *
 * @author WT Smith
 * @version 6/7/2017
 */

import java.util.Scanner;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.*;
import java.util.*;

public class PrimeFileObj
{
    private String fileName;    // contains the name of the file to read to or from
    private boolean initialized = false;  // indicates if the file has been initialized

    public boolean isFileChanged = false;

    private Scanner inFile = null;
    private PrintWriter writer = null;

    private ArrayList<Integer> primeList;

    /**
     * Constructor for objects of class PrimeFileObj
     */
    public PrimeFileObj(String fileName)
    {
        // initialise instance variables
        this.fileName = fileName;                

        if( this.fileName.indexOf(".txt") < 1 )
            this.fileName += ".txt";     
            
        primeList = new ArrayList<Integer>();
    }

    public void newFile(int initialSize) throws IOException
    {
        //  TODO: handle filename collissions gracefully
        try {
            Path filePath = Paths.get(fileName);
            Files.deleteIfExists(filePath);
        }
        catch (IOException exp) {
            System.err.println("Could not delete target filename");
        }
      
        try {
            writer = new PrintWriter(fileName, "UTF-8");

            // write a header for the prime file.            
            //writer.close();  
            this.initialized = true;

            // temporary placeholder till we know how many primes there are
            writer.println("-1");
            primeList.ensureCapacity(initialSize);
        }
        catch (IOException exp) {
            System.out.println("Sorry, there was a File IOException");
            System.err.println(exp.toString());
            throw exp;
        }    
    }
    
    public int getSize() 
    { 
        return primeList.size();
    };

    public void closeFile ()
    {
        // if not present, add the number of elements in the prime file to the header
        if (writer != null && isFileChanged == true) {

            // output coins to a file

            // write a header for the prime file.

            writer.println(primeList.size());
            // write primes to the file
            for (int pos=0; pos < primeList.size(); pos++)
            {
                writer.println(primeList.get(pos));
            }

            writer.close();  
        }
    }

    /**
     * opens the specified file for reading.
     */
    public int openFile() throws IOException
    {
        try {    
            System.err.println("Opening primeFile file " + this.fileName);
            inFile = new Scanner ( new File(this.fileName));
        }
        catch(java.io.FileNotFoundException error){
            System.err.println("File not found, exiting");
            return -1;
        }

        String line = inFile.nextLine();   

        // read number of primes from the file header and ensure array capacity.
        int numPrimes = Integer.parseInt(line);       

        primeList = new ArrayList<Integer>();
        primeList.ensureCapacity(numPrimes);

        while (inFile.hasNext())
        {
            line = inFile.nextLine();                     
            primeList.add(Integer.parseInt(line));
        }

        return 1;     
    }


    /**
     * adds a prime to the file
     * precondition - the file must be opened or created first.
     *
     * @param  p    a prime to add  
     */
    public void addPrime(int p)
    {
        primeList.add(p);        
    }        

    public int getPrimeNum(int n)
    {
        return primeList.get(n);
    }
}
