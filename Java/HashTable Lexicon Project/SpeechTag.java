/**
 * @author Megan Landau
 */
public class SpeechTag {

	/**
	 * SpeechTag objects have attributes of a word and the corresponding parts of speech tags - as specified by the input LEXICON.txt file
	 * SpeechTags also have an index within the LinkedList, which is calculated when the user calls the find() method.
	 */
	String word;
	String tags;
	int collisionInList;
	
	public SpeechTag( String word, String tags ){
		this.word = word;
		this.tags = tags;
	}
	
	public int getCollisionInList() {
		return collisionInList;
	}

	public void setCollisionInList(int collisionInList) {
		this.collisionInList = collisionInList;
	}
	
	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String toString() {
		return word;
	}
}
