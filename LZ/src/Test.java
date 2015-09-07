public class Test {

	public static void main ( String[] args ){
		
		boolean succeed = true;

		LZ lz= new LempelZiv();

		lz.encode("a");
		lz.encode("b");
		if(lz.getPairs()[0].getValue() != 'b' && lz.getPairs()[0].getIndex() != 0){
			System.out.println("PLEASE PAY ATTENTION!!!");
			System.out.println("You need to reset your ADT when u do a new Encode, pls fix it first");
			System.out.println("After u will fix it we can continue ;)");
			return;
		}

		
		
		
		lz.encode("aababcabcd");
		
		if(lz.codes("a")==4)
			System.out.println("Test 0 succeed");
		else{
			System.out.println("Test 0 failed");
			succeed = false;
		}
		
		System.out.println();

		lz.add("abc");

		if(lz.codes("a")==4 || lz.codes("a")==5)
			System.out.println("Test 1 succeed");
		else{
			System.out.println("Test 1 failed");
			succeed = false;
		}
		
		System.out.println();
		System.out.println("Now we are checking 200,000 long String, pls wait ...");
		System.out.println();

		lz.encode("1");
		for(int i = 0 ; i < 199999 ; i++)
			lz.add("1");
		
		if(lz.codes("a")==0)
			System.out.println("Test 2 succeed");
		else{
			System.out.println("Test 2 failed");
			succeed = false;
		}
		
		System.out.println();
		
		if(lz.codes("1")==631 || lz.codes("1")==632)
			System.out.println("Test 3 succeed");
		else{
			System.out.println("Test 3 failed");
			succeed = false;
		}
		
		
		int[] test1 = {1,0,1,0,0,1,1,1,1,1,0};
		
		lz.encode(test1);
		
		System.out.println();
		
		if(lz.codes("1")==4)
			System.out.println("Test 4 succeed");
		else{
			System.out.println("Test 4 failed");
			succeed = false;
		}
		
		System.out.println();
		
		if(lz.maxCodeLength()==3)
			System.out.println("Test 5 succeed");
		else{
			System.out.println("Test 5 failed");
			succeed = false;
		}
		
		System.out.println();
		
		if(lz.maxCode().equals("110"))
			System.out.println("Test 6 succeed");
		else{
			System.out.println("Test 6 failed");
			succeed = false;
		}

		System.out.println();
		
		if(lz.reconstruct().equals("10100111110"))
			System.out.println("Test 7 succeed");
		else{
			System.out.println("Test 7 failed");
			succeed = false;
		}
	
		System.out.println();
		
		lz.encode(test1);
		lz.add("110");
		
		Pair[] pairs = lz.getPairs();
		
		Pair[] answer8 = {
				new Pair(0,'1'),
				new Pair(0,'0'),
				new Pair(1,'0'),
				new Pair(2,'1'),
				new Pair(1,'1'),
				new Pair(5,'0'),
				new Pair(6,'*')
		};
		
		
		if(samePairs(answer8, pairs))
			System.out.println("Test 8 succeed");
		else{
			System.out.println("Test 8 failed");
			succeed = false;
		}
		
		if(succeed){
			System.out.println("\n\n\n\n\n\n\n\n");
			System.out.println("All tests passed successfully !!!!!! \nGood Job mate");
		}
		
		
		
	}
	
	
	
	private static boolean samePairs(Pair[] expectedPairs, Pair[] test1Pairs) 
	{
		if (expectedPairs.length!=test1Pairs.length)
		{
			if (expectedPairs.length!=test1Pairs.length+1)
				return false;
			if (expectedPairs[expectedPairs.length-1].getIndex()!=0 || expectedPairs[expectedPairs.length-1].getValue()!='*')
				return false;
			boolean ans=true;
			for (int i=0;i<expectedPairs.length-1;i++)
				ans=ans&test1Pairs[i].equals(expectedPairs[i]);
			return ans;
		}
		boolean ans=true;
		for (int i=0;i<expectedPairs.length;i++)
			ans=ans&test1Pairs[i].equals(expectedPairs[i]);
		return ans;
	}
	
}
