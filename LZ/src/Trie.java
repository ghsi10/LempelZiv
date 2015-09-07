import java.util.LinkedList;

public class Trie {
	private Pair pair;
	private LinkedList<Trie> leafs;
	private Trie parent;
	private int numOfLeafs;
	private int depth;

	/**
	 * Build Trie.
	 */
	public Trie(int index, char value, Trie parent, int depth) {
		pair=new Pair(index, value);
		this.leafs=new LinkedList<Trie>();
		this.parent=parent;
		this.numOfLeafs=0;
		this.depth=depth;
	}
	
	/**
	 * Add new leaf to Trie.
	 */
	public void addLeaf(int index, char value, int depth) {
		leafs.addFirst(new Trie(index, value, this, depth));
	}
	
	/**
	 * Add one leaf (to counter).
	 */
	public void NumOfLeafsPlusOne() {
		numOfLeafs++;
	}
	
	//Getters.
	
	public char getValue() {
		return pair.getValue();
	}
	
	public int getIndex() {
		return pair.getIndex();
	}
	
	public LinkedList<Trie> getLeafs() {
		return leafs;
	}
	
	public Trie getParent() {
		return parent;
	}
	
	public int getNumOfLeafs() {
		return numOfLeafs;
	}
	
	public int getDepth() {
		return depth;
	}
}
