package task3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Trie {	

	private TrieNode root;
	private ArrayList<String> dictionary;

	public Trie() {
		this.root = new TrieNode();
		this.dictionary = new ArrayList<String>(20);
	}

	/**
	 * Add given word to trie and dictionary
	 * @param word
	 */
	public void addWord(String word) {
		root.addWord(word);
		dictionary.add(word);
	}

	/**
	 * Returns all words with given prefix
	 * @param prefix
	 * @return
	 */
	public List<String> getWords(String prefix) {
		// find the last node that represents the last letter of prefix
		TrieNode lastNode = root;
		for (int i = 0; i < prefix.length(); i++) {
			lastNode = lastNode.getNode(prefix.charAt(i));
			// if no node matches, then no words exist, return empty list
			if (lastNode == null) {
				return new ArrayList<String>();
			}
		}
		// return the words proceed from last node
		return lastNode.getWords();

	}
	
	/**
	 * Check if trie contains given word
	 * @param word
	 * @return
	 */
	public boolean contains(String word){
		if(!this.getWords(word).isEmpty()){
			return true;			
		}
		return false;
	}

	/**
	 * Checks if word is compound
	 * @param word
	 * @return
	 */
	public boolean isCompoundWord(String word) {
		int wordLength = word.length();
		if (wordLength == 0) {
			return false;
		}
		for (int i = 1; i < wordLength; i++) {
			String x = word.substring(0, i);
			String y = word.substring(i, wordLength);
			if (this.contains(x)&&this.contains(y)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Finds max compound word in saved array
	 * @return
	 */
	public String maxCompound(){
		Collections.sort(dictionary, new Comparator<String>(){
			@Override
			public int compare(String arg0, String arg1) {
				return arg1.length() - arg0.length();
			}			
		});
		for (String str : dictionary){
			if(isCompoundWord(str)){
				return str;
			}
		}
		return null;
		
	}
	
	public String getRoot(){
		return root.toString();
	}

}
