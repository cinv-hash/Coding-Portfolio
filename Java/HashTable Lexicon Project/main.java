/**
 * @author Megan Landau
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;

public class main {
/**
 * Reads LEXICON.txt file and creates a SpeechTag object for each line in the file. Each SpeechTag is then inserted into a HashTable
 * at index ( [ Πi=1..n ASCIIvalue(key[i])*i ] mod 24593 ). Program then outputs the total number of entries in the HashTable, total
 * number of collisions, the index that has the most collisions, and the associated words in that index. 
 * 
 * Program then runs a continuous loop asking user to input a word and then outputs the parts of speech tags and how many collisions 
 * it took to get to that word at its associated index in the HashTable.
 * 
 * @param args
 */
	public static void main(String[] args) {
		File file = new File( "LEXICON.txt");
		Scanner scan = null;
		try {
			scan = new Scanner( file );
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		BigInteger size = BigInteger.valueOf( 24593 ); //specify how large the hash table is
		HashTable ht = new HashTable( size );
		while ( scan.hasNextLine() ){
			String line = scan.nextLine();
			//make a speech tag object from each line
			Scanner lineScan = new Scanner( line );
			String word = lineScan.next();
			String tags = lineScan.nextLine();
			SpeechTag st = new SpeechTag( word, tags );
			
			//find the index of where the speech tag should go in the hash table based on the hash function equation
			BigInteger index = ht.hashFunction( st.getWord() );
			
			//insert the speech tag into the hash table
			if ( ht.getHashTable()[ index.intValue() ].getHead() == null ){ //if linked list is empty, just insert new speech tag
				ht.getHashTable()[ index.intValue() ].insertFront( st ); 
			}else{
				ht.getHashTable()[ index.intValue() ].insertFront( st ); //else if there is already data in there, there is a collision, add a linkedlist
				ht.numCollisions ++;
			}
			ht.numEntries ++;
			lineScan.close();
		}
		
		System.out.println( String.format("%1$-30s %2$-20s", "Number of lexical entries:", ht.getNumEntries()));
		System.out.println( String.format("%1$-30s %2$-20s", "Number of collisions:", ht.getNumCollisions()));
		System.out.println( String.format("%1$-30s %2$-20s", "Average collisions per entry:", ((float)ht.getNumCollisions() / (float)ht.getNumEntries())));
		BigInteger index = ht.findIndexWithMostCollisions();
		System.out.println( String.format("%1$-30s %2$-20s", "Index with most collisions: ", index ));
		
		System.out.println("Associated colliding words: " + ht.printListAtIndex(index) );
		scan.close();
		
		Scanner loopScan = new Scanner( System.in );
		while( true ){
			System.out.println("\nEnter a word: ");
			try{
				String entry = loopScan.nextLine();
				SpeechTag stReference = ht.find( entry );
				System.out.println( String.format("%1$-15s %2$-20s", "Word:", stReference.getWord() ));
				System.out.println( String.format("%1$-15s%2$-20s", "Speech tags:", stReference.getTags() ));
				System.out.println( String.format("%1$-15s %2$-20s", "Collisions:", stReference.getCollisionInList() ));
			}catch (NullPointerException e) {
				System.out.println("Word not found. Try again.");
			}
		}
	}
}
