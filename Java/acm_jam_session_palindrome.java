/* 
Palindrome Maker Program
@author Megan Landau
2016

This program takes in an input number from STDIN and returns the palindrome
given by that number using a recursive method of adding the reversal of the 
number.

Program sample input:
        9
        23
        99988877
        9898
        -1 // this means last line
        
Corresponding output:
        Initial value: 9 gives palindrome 9
        Initial value: 23 gives palindrome 55     
        Initial value: 99988877 gives palindrome 6636446366         
        Initial value: 9898 No palindrome found // no p found in first 1000 iterations of processes

*/

import java.util.*;
import java.math.*;

public class Pal
{
    int originalNum;
    boolean isPal;
    BigInteger workingNum;
    int tries = 0;
    
    public Pal( String lineStr )
    {
        this.originalNum = Integer.valueOf( lineStr );
        this.workingNum = new BigInteger (lineStr);
        this.isPal = isPalindrome();
    }
    
    public boolean isPalindrome( )
    {
        String numString = workingNum.toString( );
        StringBuffer reverse = new StringBuffer( numString ).reverse();
        String strRev = reverse.toString();   
        return strRev.equals( numString );
    }
    
    public BigInteger makePalindrome( )
    {
        tries ++;
        workingNum = workingNum.add( reverse() );
        
        if ( this.isPalindrome() ){
            this.isPal = true;
            return this.workingNum;
        }
        else if ( tries <= 1000 )
            return this.makePalindrome();
        else{
            workingNum = null;
            return workingNum;
        }
            
        
    }
    
    public BigInteger reverse( )
    {
        String numStr = workingNum.toString(  );
        StringBuffer reverse = new StringBuffer( numStr.trim() ).reverse();
        String strRev = reverse.toString(); 
        BigInteger bigInt = new BigInteger( strRev );
        return bigInt;
    }
    
    public String toString()
    {
        String rtnStr;
        if ( this.isPal )
            rtnStr = "Initial value: " + this.originalNum + " gives palindrome " + this.workingNum;
        else
            rtnStr = "Initial Value: " + this.originalNum + " No palindrome found";
        return rtnStr;
    }
    
    public static void main( String [] args )
    {
        //Scanner scan = new Scanner (System.in); // UNCOMMENT FOR JAM SESSION TO WORK
                
        Scanner scan  = new Scanner ( "9\n23\n99988877\n9898\n-1" ); // COMMENT OUT
     
        String line = scan.nextLine();
        
        while ( scan.hasNextLine() && !line.equals( "-1" ))
        {
            Pal num = new Pal( line );
            
            if ( !num.isPal ){
                num.makePalindrome();   
            }
            
            System.out.println( num );
            line = scan.nextLine();
        }
        
        
        /* sample input:
        9
        23
        99988877
        9898
        -1 // this means last line
        
        output:
        Initial value: 9 gives palindrome 9
        Initial value: 23 gives palindrome 55     
        Initial value: 99988877 gives palindrome 6636446366         
        Initial value: 9898 No palindrome found // no p found in first 1000 iterations of process   
        
        ---------
        
        make a pal object from each line read.
        pal objects have an original number and a boolean representing their isPal status
        
        
        */
    }
}

