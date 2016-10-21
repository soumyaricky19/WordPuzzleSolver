package project4;

public class LinearProbingHashTable
{

	private static final int DEFAULT_TABLE_SIZE = 101;
	String[] array;
	private int currentSize;
	
	LinearProbingHashTable()
	{
		array=new String[DEFAULT_TABLE_SIZE];
		currentSize=0;
	}	
	
	public void add(String x)
	{
		if(currentSize++> array.length / 2 )
			rehash();
		int idx=myhash(x);
		if (array[idx]!=null && array[idx].equals(x))
		{
			currentSize--;
			System.out.println("Duplicate insert: "+x);
		}	
		array[idx]=x;
    }
	
	private int myhash(String x )
	 {
	     int hashVal = hashFunc1(x);
	     int temp=hashVal;
	     int i=0;
	     
	     while (array[temp] != null && !array[temp].equals(x))
	     {    		
//	    	hashVal++; 
//	    	i+=2;
//		    hashVal+=i;	  
	    	i++; 
	    	temp=hashVal+i*i*hashFunc2(x);
	    	temp=temp%array.length;
	    	if (temp<0)
				temp+=array.length;
	    	
	//    	System.out.println(i);
	 //   	System.out.println(array.length);
	 //   	System.out.println(temp);
	    	
	     }		     

	     return temp;
	 }
	
	public boolean contains (String x )
	{
		if (array[myhash(x)]!=null)
			return true;
		else 
			return false;
	}

	public void rehash()
	{
		String [] oldarray=array;
		int oldcurrentSize=currentSize;
		
		//array=new String[nextPrime(2*array.length)];
		array=new String[nextPrime(2*array.length)];
		for (String s: oldarray)
		{
			if (s!=null)
				add(s);
		}
		currentSize=oldcurrentSize;
	}
	
/*	public int nextPrime(int n)
	{
		
	}
*/	
	public int hashFunc1(String x)
	{
		int n=7;
		StringBuilder sb= new StringBuilder(x);
		for(int i=0;i< sb.length();i++)
		{
			n=31*n+(int)sb.charAt(i);
		}
		n%=array.length;
		if (n<0)
			n+=array.length;
		
		return n;
	}
	
	public int hashFunc2(String x)
	{
		int n=7;
		StringBuilder sb= new StringBuilder(x);
		for(int i=0;i< sb.length();i++)
		{
			n=31*n+(int)sb.charAt(i);
		}
		n%=array.length;
		if (n<0)
			n+=array.length;
		
		//System.out.println(prevPrime(array.length));
		n=prevPrime(array.length)-(n%prevPrime(array.length));
		return n;
	}

	 private static int nextPrime( int n )
	 {
	     if( n % 2 == 0 )
	         n++;

	     for( ; !isPrime( n ); n += 2 )
	         ;

	     return n;
	 }
	 
	 private static int prevPrime( int n )
	 {
	     if( n % 2 == 0 )
	         n--;

	     for( ; !isPrime( n ); n -= 2 )
	         ;

	     return n;
	 }
	 
	 private static boolean isPrime( int n )
	 {
	     if( n == 2 || n == 3 )
	         return true;

	     if( n == 1 || n % 2 == 0 )
	         return false;

	     for( int i = 3; i * i <= n; i += 2 )
	         if( n % i == 0 )
	             return false;

	     return true;
	 }

	
	public String valueAt(int n)
	{
		return array[n];
	}
	public int getSize()
	{
		return currentSize;
	}
}
