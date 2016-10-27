package project4;

public class MyHashTable
{

	private static final int DEFAULT_TABLE_SIZE = 101;
	String[] array;
	private int currentSize;
	public int count=0;
	MyHashTable()
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
		int hashVal=x.hashCode();
		hashVal%=array.length;
		if (hashVal<0)
			hashVal+=array.length;
		
		//int hashVal = hashFunc(x);
	     int i=0;
	     
	     while (array[hashVal] != null && !array[hashVal].equals(x))
	     {    		
	    	count++; 
	    	i++;
		    hashVal+=i;	  
		    hashVal%=array.length ;
	    	 
//	    	 // Own collision resolution strategy
//	    	 i*=i;
//	    	 hashVal+=i;
//	    	 hashVal%=array.length;
	    	
	     }		     

	     return hashVal;
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
		array=new String[nextPrime(array.length*array.length*array.length)];
		for (String s: oldarray)
		{
			if (s!=null)
				add(s);
		}
		currentSize=oldcurrentSize;
	}
	
	public int hashFunc(String x)
	{
		int n=7;
		StringBuilder sb= new StringBuilder(x);
		for(int i=0;i< sb.length();i++)
			n=31*n+(int)sb.charAt(i);
		
		n%=array.length;
		if (n<0)
			n+=array.length;

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
