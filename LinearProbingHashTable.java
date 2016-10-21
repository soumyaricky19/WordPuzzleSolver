package project4;

public class LinearProbingHashTable
{

	private static final int DEFAULT_TABLE_SIZE = 200000;
	String[] array;
	private int currentSize;
	
	LinearProbingHashTable()
	{
		array=new String[DEFAULT_TABLE_SIZE];
	}	
	
	public void add(String x)
	{
		currentSize++;
		//if(currentSize++> array.length / 2 )
		//	rehash();
		int idx;
		idx=myhash(x);
		array[idx]=x;
    }
	
	private int myhash(String x )
	 {
	     int hashVal = x.hashCode( );
	     hashVal %= array.length;
	     if( hashVal < 0 )
	         hashVal += array.length;
	     
	     double i=1;
	     
	     while (array[hashVal] != null)
	     {
	    	if (array[hashVal].equals(x))
	    	{
	    		System.out.println("Duplicate insert: "+x);
	    		break;
	    	}	
//	    	hashVal++;
	    	 
	    	i*=i;
		    hashVal+=i;
		    
	    	hashVal %= array.length;
	     }	
	     return hashVal;
	 }
	
	public boolean contains (String x )
	{
		int hashVal = x.hashCode( );	     
	    hashVal %= array.length;
	    if( hashVal < 0 )
	         hashVal += array.length;
	    
	    double i=1;
	    while (array[hashVal] != null)
	    {
	    	if (array[hashVal].equals(x))
	    		return true;
//	    	hashVal++;
	    	
	    	i*=i;
	    	hashVal+=i;
	    	
	    	hashVal %= array.length;
	    }
	    return false;
	}

	public void rehash()
	{
		String [] oldarray= array;
		array=new String[2*array.length];
		for (String s: oldarray)
		{
			if (s!=null)
				add(s);
		}
	}
	
	public int getSize()
	{
		return currentSize;
	}
}
