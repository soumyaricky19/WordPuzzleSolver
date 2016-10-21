package project4;

import java.util.*;
import java.io.*;

public class WordPuzzleSolver 
{
	static int rows,columns,diagonal;
	static int counter=0,count=0;
	public static void main (String args[])
	{		
		long start, end;
		
		
		try 
		{
			System.out.println("Loading dictionary. Please wait ...");
			LinkedList<String> l1= new LinkedList<>();
			BinarySearchTree<String> l2= new BinarySearchTree<>();
			QuadraticProbingHashTable<String> l3=new QuadraticProbingHashTable<String>();
			String word=null;
			//Read dictionary words
			BufferedReader f = new BufferedReader(new FileReader("dictionary.txt"));
			while((word=f.readLine())!=null)
				{
				//System.out.println(word);
					//l1.add(word);
					//l2.insert(word);
					l3.insert(word);
					//System.out.println(count++);
				}

			Scanner s=new Scanner(System.in);
			System.out.println("Enter the number of rows:");
			rows=s.nextInt();
			System.out.println("Enter the number of columns:");
			columns=s.nextInt();
			s.close();
			char a[][]= new char[rows][columns];
			
			//Insert random letters
			for(int i=0;i<rows;i++)
				for (int j=0;j<columns;j++)
				{
					a[i][j]= (char)(97+(java.lang.Math.random()*100)%26);
				}
			
			displayCrossword(a);
// Search from Linked list		
            start = System.currentTimeMillis( );
            for(int i=0;i<rows;i++)
				for (int j=0;j<columns;j++)
				{
					String str=String.valueOf(a[i][j]);
					searchLinkedList(str,l1);
					//Go right
					
					for (int y=j+1; y<columns ;y++)
					{
						str+=String.valueOf(a[i][y]);
						searchLinkedList(str,l1);
					}
						
					//Go left
					str=String.valueOf(a[i][j]);
					for (int y=j-1; y>=0 ;y--)
					{
						str+=String.valueOf(a[i][y]);
						searchLinkedList(str,l1);
					}
						
					//Go down
					str=String.valueOf(a[i][j]);
					for (int x=i+1; x<rows ;x++)
					{
						str+=String.valueOf(a[x][j]);
						searchLinkedList(str,l1);
					}	
						
					//Go up
					str=String.valueOf(a[i][j]);
					for (int x=i-1; x>=0 ;x--)
					{
						str+=String.valueOf(a[x][j]);
						searchLinkedList(str,l1);
					}	
						
					//Go SE	
					str=String.valueOf(a[i][j]);
					for (int x=i+1,y=j+1; x<rows && y<columns ;x++,y++)
					{
						str+=String.valueOf(a[x][y]);
						searchLinkedList(str,l1);
					}	
						
					//Go SW
					str=String.valueOf(a[i][j]);
					for (int x=i+1,y=j-1; x<rows && y>=0 ;x++,y--)
					{
						str+=String.valueOf(a[x][y]);
						searchLinkedList(str,l1);
					}
					//Go NW
					str=String.valueOf(a[i][j]);
					for (int x=i-1,y=j-1; x>=0 && y>=0 ;x--,y--)
					{
						str+=String.valueOf(a[x][y]);
						searchLinkedList(str,l1);
					}
					
					//Go NE
					str=String.valueOf(a[i][j]);
					for (int x=i-1,y=j+1; x>=0 && y<columns ;x--,y++)
					{
						str+=String.valueOf(a[x][y]);
						searchLinkedList(str,l1);
					}			

				}
	System.out.println("Counter"+counter);
	counter=0;
	end = System.currentTimeMillis( );
    System.out.println( "For Linked list, Elapsed time in ms: " + (end-start) );
	

// Search from Trees
    start = System.currentTimeMillis( );
    for(int i=0;i<rows;i++)
		for (int j=0;j<columns;j++)
		{
			String str=String.valueOf(a[i][j]);
			searchTree(str,l2);
			//Go right
			
			for (int y=j+1; y<columns ;y++)
			{
				str+=String.valueOf(a[i][y]);
				searchTree(str,l2);
			}
				
			//Go left
			str=String.valueOf(a[i][j]);
			for (int y=j-1; y>=0 ;y--)
			{
				str+=String.valueOf(a[i][y]);
				searchTree(str,l2);
			}
				
			//Go down
			str=String.valueOf(a[i][j]);
			for (int x=i+1; x<rows ;x++)
			{
				str+=String.valueOf(a[x][j]);
				searchTree(str,l2);
			}	
				
			//Go up
			str=String.valueOf(a[i][j]);
			for (int x=i-1; x>=0 ;x--)
			{
				str+=String.valueOf(a[x][j]);
				searchTree(str,l2);
			}	
				
			//Go SE	
			str=String.valueOf(a[i][j]);
			for (int x=i+1,y=j+1; x<rows && y<columns ;x++,y++)
			{
				str+=String.valueOf(a[x][y]);
				searchTree(str,l2);
			}	
				
			//Go SW
			str=String.valueOf(a[i][j]);
			for (int x=i+1,y=j-1; x<rows && y>=0 ;x++,y--)
			{
				str+=String.valueOf(a[x][y]);
				searchTree(str,l2);
			}
			//Go NW
			str=String.valueOf(a[i][j]);
			for (int x=i-1,y=j-1; x>=0 && y>=0 ;x--,y--)
			{
				str+=String.valueOf(a[x][y]);
				searchTree(str,l2);
			}
			
			//Go NE
			str=String.valueOf(a[i][j]);
			for (int x=i-1,y=j+1; x>=0 && y<columns ;x--,y++)
			{
				str+=String.valueOf(a[x][y]);
				searchTree(str,l2);
			}			

		}
System.out.println("Counter"+counter);
end = System.currentTimeMillis( );
System.out.println( "For tree, Elapsed time in ms: " + (end-start) );

			
// Search from Hash table
start = System.currentTimeMillis( );
for(int i=0;i<rows;i++)
	for (int j=0;j<columns;j++)
	{
		String str=String.valueOf(a[i][j]);
		searchQuadraticProbing(str,l3);
		//Go right
		
		for (int y=j+1; y<columns ;y++)
		{
			str+=String.valueOf(a[i][y]);
			searchQuadraticProbing(str,l3);
		}
			
		//Go left
		str=String.valueOf(a[i][j]);
		for (int y=j-1; y>=0 ;y--)
		{
			str+=String.valueOf(a[i][y]);
			searchQuadraticProbing(str,l3);
		}
			
		//Go down
		str=String.valueOf(a[i][j]);
		for (int x=i+1; x<rows ;x++)
		{
			str+=String.valueOf(a[x][j]);
			searchQuadraticProbing(str,l3);
		}	
			
		//Go up
		str=String.valueOf(a[i][j]);
		for (int x=i-1; x>=0 ;x--)
		{
			str+=String.valueOf(a[x][j]);
			searchQuadraticProbing(str,l3);
		}	
			
		//Go SE	
		str=String.valueOf(a[i][j]);
		for (int x=i+1,y=j+1; x<rows && y<columns ;x++,y++)
		{
			str+=String.valueOf(a[x][y]);
			searchQuadraticProbing(str,l3);
		}	
			
		//Go SW
		str=String.valueOf(a[i][j]);
		for (int x=i+1,y=j-1; x<rows && y>=0 ;x++,y--)
		{
			str+=String.valueOf(a[x][y]);
			searchQuadraticProbing(str,l3);
		}
		//Go NW
		str=String.valueOf(a[i][j]);
		for (int x=i-1,y=j-1; x>=0 && y>=0 ;x--,y--)
		{
			str+=String.valueOf(a[x][y]);
			searchQuadraticProbing(str,l3);
		}
		
		//Go NE
		str=String.valueOf(a[i][j]);
		for (int x=i-1,y=j+1; x>=0 && y<columns ;x--,y++)
		{
			str+=String.valueOf(a[x][y]);
			searchQuadraticProbing(str,l3);
		}			

	}
System.out.println("Counter"+counter);
end = System.currentTimeMillis( );
System.out.println( "For cuckoo hash table, Elapsed time in ms: " + (end-start) );
			
			
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public static void searchLinkedList(String str,LinkedList<String> l)
	{	
		
		//System.out.println("Str:"+str);
		boolean flag=false;
		Iterator<String> itr=l.iterator();
		while(itr.hasNext() && !flag)
		{
			String s1=(String) itr.next();
			if (str.compareTo(s1)== 0)
			{				
				counter++;
				//System.out.println("Matched string:"+str);
				flag=true;						
			}	
			
		}
	}
	
	public static void searchTree(String str,BinarySearchTree<String> l)
	{	
		//System.out.println("Str:"+str);
		//if(l.contains(str))			
			//	System.out.println("Matched string:"+str);
		//	counter++;
	}
	
	public static void searchQuadraticProbing(String str,QuadraticProbingHashTable<String> l)
	{	
		//System.out.println("Str:"+str);
		if(l.contains(str))			
			//	System.out.println("Matched string:"+str);
			counter++;
	}
	
	public static void displayCrossword(char a[][])
	{
		for(int i=0;i<rows;i++)
		{
			for (int j=0;j<columns;j++)
				System.out.print(a[i][j]+" ");
			System.out.println("");
		}	
	}
}
