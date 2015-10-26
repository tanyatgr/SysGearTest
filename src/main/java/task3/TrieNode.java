package task3;

import java.util.ArrayList;
import java.util.List;

public class TrieNode {

	private TrieNode parent;
	private TrieNode[] children;
	private boolean isLeaf;
	private boolean isWord;
	private char character;

	public TrieNode() {
		this.children = new TrieNode[30];
		this.isLeaf = true;
		this.isWord = false;
	}

	public TrieNode(char character) {
		this();
		this.character = character;
	}

	/*
	 * Adds word to this node
	 */
	protected void addWord(String word) {
		isLeaf = false;
		int charPos = word.charAt(0) - 'a';

		if (children[charPos] == null) {
			children[charPos] = new TrieNode(word.charAt(0));
			children[charPos].parent = this;
		}

		if (word.length() > 1) {
			children[charPos].addWord(word.substring(1));
		} else {
			children[charPos].isWord = true;
		}
	}

	/*
	 * Returns the child TrieNode representing the given char
	 */
	protected TrieNode getNode(char c) {
		return children[c - 'a'];
	}

	/*
	 * Returns a list of String objects which are lover in the hierarchy
	 */
	protected List<String> getWords() {
		List<String> result = new ArrayList<String>();
		if (isWord) {
			result.add(toString());
		}
		if (!isLeaf) {
			for (int i = 0; i < children.length; i++) {
				if (children[i] != null) {
					result.addAll(children[i].getWords());
				}
			}

		}
		return result;
	}

	/*
	 * Gets the String that this Node represents
	 */
	public String toString() {
		if (parent == null) {
			return "";
		} else {
			return parent.toString() + new String(new char[] { character });
		}

	}

}
