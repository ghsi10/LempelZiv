
public class Pair 
{
	//the index of the pair
	private final int index;
	//the value - either boolean or char
	private char value;
	//Constructor
	public Pair(int index, char value)
	{
		this.index=index;
		this.value=value;
	}
	
	public int getIndex()
	{
		return index;
	}
	
	public char getValue()
	{
		return value;
	}
	
	public String toString()
	{
		return "("+ index + "," + value + ")";
	}
	
	public boolean equals(Object other)
	{
		return this.toString().equals(other.toString());
	}

}
