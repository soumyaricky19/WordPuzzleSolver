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
	     int hashVal = x.hashCode( );
	     hashVal %= array.length;
	     if( hashVal < 0 )
	         hashVal += array.length;
	     
	     double i=1;
	     
	     while (array[hashVal] != null && !array[hashVal].equals(x))
	     {    		
//	    	hashVal++;
	    	 
	    	i*=i;
		    hashVal+=i;
		    
	    	hashVal %= array.length;
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
		array=new String[2*array.length];
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
	
	public int getSize()
	{
		return currentSize;
	}
}
