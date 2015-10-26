package task3;

public class CompoundWordFinder {
	public static void main(String[] args) {
		String words[] = { "cat", "cats", "catsdogcats", "catdogcatsratrat",
				"dog", "dogcatsdog", "hippopotamuses", "rat", "ratcat",
				"ratcatdog", "ratcatdogcatsxxxxx" };

		Trie trie = new Trie();
		for (String word : words) {
			trie.addWord(word);
		}
		System.out.println(trie.maxCompound());

	}
}
