package project4;

public class LinearProbingHashTable
{
/*	private int levels=4;
	private int l;
	private Node root[],parent;
	private static class Node
	{
		String s[];
		Node child;
		Leaf childleaf;
	}
	private class Leaf
	{
		String leafs[]=new String[l];
	}
	public void insert(String x)
	{
		insert(x,null);
	}
	public void insert(String x,Node n)
	{	
		for(int i=0;i<x.length();i++)
		{
			if (n==null)
				n=new Node();
			int idx=((int)Integer.valueOf(x.substring(i+1,i+1)))%26;
			if(i==0)
			{
				
				n.s[idx]=x.substring(i+1,i+1);
				parent=n;
			}			
			else if(i<=levels)
			{
				n.s[idx]=x.substring(i+1,i+1);
				parent.child=n;
				parent=n;
			}
			else
			{
				n.s[idx]=x.substring(i+1,i+1);
				parent.child=n;
			}
			
		}
	}
*/
	private static final int DEFAULT_TABLE_SIZE = 500001;
	String[] array;
	private int currentSize;
	
	LinearProbingHashTable()
	{
		array=new String[DEFAULT_TABLE_SIZE];
	}	
	
	public void add(String x)
	{
		currentSize++;
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
//	    	hashVal++;
	    	 
	    	i=java.lang.Math.pow(i,2);
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
	    	
	    	i=java.lang.Math.pow(i,2);
	    	hashVal+=i;
	    	
	    	hashVal %= array.length;
	    }
	    return false;
	}

	public int getSize()
	{
		return currentSize;
	}
}
