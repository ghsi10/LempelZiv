
public interface LZ 
{
	
	public void encode(int[] input);//O(|input|)
	
	public void encode(String input);//O(|input|)
	
	public Pair[] getPairs();//O(number of pairs)
	
	public String reconstruct(Pair[] pairs);//O(|pairs|)
	
	public String reconstruct();//O(n)
	
	public void add(String input);//O(input)
	
	public int codes(String prefix);//O(prefix)
	
	public int maxCodeLength(); //O(1)
	
	public String maxCode(); //O(h)
	
	public int[] randomBinarySeries(int length,double ratio);// O(length)

}
