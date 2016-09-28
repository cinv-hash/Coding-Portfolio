/**
 * @author Megan Landau
 */
import java.math.BigInteger;

public class HashTable {
/**
 * Attributes include an array of LinkedList objects called hashTable - similar to Java's ArrayList class. 
 * numEntries is the actual number of SpeechTag objects put into the HashTable
 * numCollisions is how many times SpeechTags share the same index in the HashTable (and create adjacency lists)
 * size is the total amount of space the HashTable has to offer
 */
	LinkedList[] hashTable;
	int numEntries;
	int numCollisions;
	BigInteger size;
	
	/**
	 * Constructor creates a HashTable of a size that the user specifies and initializes the hashTable array of LinkedList objects
	 * so that each index in the HashTable has a LinkedList object just waiting to be filled with data.
	 * 
	 * @param size
	 */
	public HashTable( BigInteger size ){
		this.hashTable = new LinkedList[size.intValue()]; 
		this.numEntries = 0;
		this.numCollisions = 0;
		this.size = size;
		for ( int i =  0 ; i < size.intValue() ; i++ ){
			hashTable[i] = new LinkedList();
		}
	}
	
	/**
	 * Method takes in a key and calculates [ Πi=1..n ASCIIvalue(key[i])*i ] mod 24593 as the index in the HashTable.
	 * @param key
	 * @return BigInteger representing the index of the word in the HashTable
	 */
	public BigInteger hashFunction( String key ){
		BigInteger total = BigInteger.valueOf(1);
		for (int i = 0; i < key.length(); i++ ){
			total = total.multiply( BigInteger.valueOf( key.charAt(i) * ( i + 1 )));
		}
		return total.mod(this.size);
	}
	
	/**
	 * Method finds the index that has the most amount of collisions in the HashTable
	 * @return BigInteger representing the index
	 */
	public BigInteger findIndexWithMostCollisions(){
		int collisionsAtIndex = 0;
		BigInteger index = BigInteger.valueOf(0);
		for ( int i =  0 ; i < this.size.intValue() ; i++ ){
			if( hashTable[i].size > collisionsAtIndex ){
				collisionsAtIndex = hashTable[i].size;
				index = BigInteger.valueOf(i);
			}
		}
		return index;
	}
	
	public LinkedList[] getHashTable() {
		return hashTable;
	}

	public void setHashTable(LinkedList[] hashTable) {
		this.hashTable = hashTable;
	}

	public int getNumEntries() {
		return numEntries;
	}

	public void setNumEntries(int numEntries) {
		this.numEntries = numEntries;
	}

	public int getNumCollisions() {
		return numCollisions;
	}

	public void setNumCollisions(int numCollisions) {
		this.numCollisions = numCollisions;
	}
	
	/**
	 * Method finds the LinkedList at a specified index and prints each node's data 
	 * (which is a SpeechTag's word attribute) on a new line, indented in once
	 * @param index
	 * @return String representing the LinkedList's data at that index
	 */
	public String printListAtIndex( BigInteger index ){
		String rtnString = "";
		if ( this.hashTable[index.intValue()].getHead() == null ){
			rtnString = "List is empty";
		}else{
			LinkedListNode current = this.hashTable[index.intValue()].getHead();
			while( current.getNext() != null ){
				rtnString += "\n\t" + current.getData().toString();
				current = current.getNext();
			}
			rtnString += current.getData().toString();
		}
		return rtnString;
	}
	
	/**
	 * Method finds the reference to a specified SpeechTag within the HashTable
	 * @param word
	 * @return SpeechTag that has the exact same word attribute that the user was looking for
	 */
	public SpeechTag find( String word ){
		SpeechTag rtnVal = null;
		BigInteger index = this.hashFunction( word );
		LinkedListNode current = this.hashTable[ index.intValue() ].getHead();
		int specificCollisions = 0;
		boolean found = false;
		while (!found){
			if( current == null ){
				found = true;
			}else if( ((SpeechTag) current.getData()).getWord().equals( word ) ){
				rtnVal = (SpeechTag) current.getData();
				found = true;
			}else{
				current = current.getNext();
				specificCollisions++;
			}
		}
		rtnVal.setCollisionInList( specificCollisions );		
		return rtnVal;
	}
}
