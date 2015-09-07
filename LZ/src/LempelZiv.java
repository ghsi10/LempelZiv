import java.util.LinkedList;
import java.util.Iterator;

public class LempelZiv implements LZ {
	private Trie root; //the root of the Trie.
	private int index;
	private LinkedList<Pair> pairs;
	private Trie lastAdd; //Saves the last Trie if inserted pair which includes *.
	private Trie trieMaxDepth; //Saves the Trie with the max depth.

	/**
	 * Compresses binary array.
	 */
	public void encode(int[] input) {
		char[] tmp = new char[input.length];
		for(int i=0;i<input.length;i++) //Converts the binary array to char array.
			if (input[i]==1)
				tmp[i]='1';
			else
				tmp[i]='0';
		String str = new String(tmp); //Converts char array to string.
		encode(str); //Encode string.
	}

	/**
	 * Compresses text.
	 */
	public void encode(String input) {
		lastAdd=null; //Initializing
		root = new Trie(0, '*', null, 0);
		trieMaxDepth=root;
		index = 0;
		pairs = new LinkedList<Pair>(); 
		while (input.length()>0)
			input=buildTrie(input, root, 0);
	}

	//Recursion compresses the current character (first in the string).
	private String buildTrie(String input, Trie tree, int depth) {
		while (input.length()>0)
		{
			Trie tmp;
			Iterator<Trie> iter=tree.getLeafs().iterator();
			while (iter.hasNext()) { //Search Is there a character in one of his children.
				tmp = iter.next();
				if (tmp.getValue()==input.charAt(0)) {
					tree.NumOfLeafsPlusOne();
					return buildTrie(input.substring(1), tmp, depth+1);
				}
			}
			index++; //If the character does not exist in his children
			tree.NumOfLeafsPlusOne();
			tree.addLeaf(index, input.charAt(0), depth+1);
			pairs.addLast(new Pair(tree.getIndex(), input.charAt(0)));
			if (trieMaxDepth.getDepth()<=depth+1)
				trieMaxDepth=tree.getLeafs().getFirst();
			return input.substring(1);
		}
		lastAdd=tree; //When the text is finished and we are in the middle of the tree (Add Pair with *).
		pairs.addLast(new Pair(tree.getIndex(), '*'));
		return "";
	}

	/**
	 * Returns the list in pairs as array.
	 */
	public Pair[] getPairs() {
		return pairs.toArray(new Pair[0]);
	}

	/**
	 * Restores the text on the array is obtained as a parameter pairs.
	 */
	public String reconstruct(Pair[] pairs) {
		String ans="";
		String[] str=new String[pairs.length+1];
		str[0]="";
		for (int i=0;i<pairs.length-1;i++)  //Build translation array.
				str[i+1]=str[pairs[i].getIndex()] + pairs[i].getValue();
		if (pairs[pairs.length-1].getValue()=='*') //The last pair.
			str[pairs.length]=str[pairs[pairs.length-1].getIndex()];
		else
			str[pairs.length]=str[pairs[pairs.length-1].getIndex()]+ pairs[pairs.length-1].getValue();
		for (int i=0;i<str.length;i++) //translation the text.
			ans+=str[i];
		return ans;
	}

	/**
	 * Restores the text is compressed before.
	 */
	public String reconstruct() {
		return reconstruct(getPairs());
	}

	/**
	 * Compresses the text with the previous text (from encode).
	 */
	public void add(String input) {
		if (lastAdd!=null && input.length()>0) {
			pairs.removeLast();
			Trie tmp=lastAdd;
			lastAdd=null;
			input=buildTrie(input, tmp ,tmp.getDepth());
		}
		while (input.length()>0)
			input=buildTrie(input, root,0);
	}

	/**
	 * The function returns the number times in a given text is the first part of the text was encoded.
	 */
	public int codes(String prefix) {
		return codes(prefix, root);
	}

	//function codes.
	private int codes(String prefix, Trie tree) {
		Trie tmp;
		Iterator<Trie> iter = tree.getLeafs().iterator();
		while (prefix.length()>0 && iter.hasNext()) {
			tmp = iter.next();
			if (tmp.getValue()==prefix.charAt(0))
				return codes(prefix.substring(1), tmp);
		}
		if (prefix.length()>0)
			return 0;
		return tree.getNumOfLeafs()+1;
	}

	/**
	 * The function returns the number times in a given text is the first part of the text was encoded.
	 */
	public int maxCodeLength() {
		return trieMaxDepth.getDepth();
	}

	/**
	 * The function returns the longest branch tree encoding.
	 */
	public String maxCode() {
		String str="";
		Trie tmp=trieMaxDepth;
		while (tmp!=root) {
			str=tmp.getValue()+str;
			tmp=tmp.getParent();
		}
		return str;
	}

	/**
	 * Build random binary  array.
	 */
	public int[] randomBinarySeries(int length, double ratio) {
		int[] arr = new int[length];
		for (int i=0;i<length;i++)
			if (Math.random()<ratio)
				arr[i]=0;
			else
				arr[i]=1;
		return arr;
	}
}
