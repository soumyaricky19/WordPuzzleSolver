package project4;

import java.util.*;
import java.io.*;

public class WordPuzzle 
{
	static int rows=0,columns=0,directions=8;
	static int counter=0,count=0;
	char a[][]= new char[rows][columns];
	static LinkedList<String> l1= new LinkedList<>();
	static BinarySearchTree<String> l2= new BinarySearchTree<>();
	static QuadraticProbingHashTable<String> l3=new QuadraticProbingHashTable<String>();
	static LinearProbingHashTable l4= new LinearProbingHashTable();
	public static void main (String args[])
	{		
		long start, end;
		
		
		try 
		{
			System.out.println("Loading dictionary. Please wait ...");

			String word=null;
			//Read dictionary words
			BufferedReader f = new BufferedReader(new FileReader("dictionary.txt"));
			while((word=f.readLine())!=null)
				{
				//System.out.println(word);
					l1.add(word);
					//l2.insert(word);
					l3.insert(word);
					l4.add(word);
					//System.out.println(count++);
				}

			/*
			System.out.println(l1.size());
			System.out.println(l2.nodeCount());
			System.out.println(l3.size());
			System.out.println(l4.getSize());
			if (1==1)
				return;
			*/
			
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
					a[i][j]= (char)(97+(java.lang.Math.random()*100)%26);
			
			
			displayGrid(a);
			
// Search from Linked list		
            start = System.currentTimeMillis( );
            counter=0;
            
            for(int i=0;i<a.length;i++)
				for (int j=0;j<a[i].length;j++)
					for (int k=0;k<directions;k++)
						searchLinkedList(goDirections(a,i,j,k),l1);						
				
            System.out.println("Linked list: Counter"+counter);
            end = System.currentTimeMillis( );
            System.out.println( "For Linked list, Elapsed time in ms: " + (end-start) );
	

// Search from Trees
            start = System.currentTimeMillis( );
            counter=0;
            
            for(int i=0;i<a.length;i++)
				for (int j=0;j<a[i].length;j++)
					for (int k=0;k<directions;k++)
						searchTree(goDirections(a,i,j,k),l2);							
			
            System.out.println("Trees: Counter"+counter);
            end = System.currentTimeMillis( );
            System.out.println( "For tress, Elapsed time in ms: " + (end-start) );

			
// Search from Quadratic probing Hash table
            start = System.currentTimeMillis( );
            counter=0;
            for(int i=0;i<a.length;i++)
				for (int j=0;j<a[i].length;j++)
					for (int k=0;k<directions;k++)
						searchQuadraticProbing(goDirections(a,i,j,k),l3);
										
            System.out.println("Quadratic probing: Counter"+counter);
            end = System.currentTimeMillis( );
            System.out.println( "For Quadratic probing hash table, Elapsed time in ms: " + (end-start) );
            
// Search from Linear probing Hash table
            start = System.currentTimeMillis( );
            counter=0;
            for(int i=0;i<a.length;i++)
				for (int j=0;j<a[i].length;j++)
					for (int k=0;k<directions;k++)
						searchLinearProbing(goDirections(a,i,j,k),l4);
										
            System.out.println("Linear probing: Counter"+counter);
            end = System.currentTimeMillis( );
            System.out.println( "For Linear probing hash table, Elapsed time in ms: " + (end-start) );            
			
			
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
		if(l.contains(str))		
		{	
			counter++;
			//System.out.println("Matched string:"+str);
		}
	}
	
	public static void searchQuadraticProbing(String str,QuadraticProbingHashTable<String> l)
	{	
		//System.out.println("Str:"+str);
		if(l.contains(str))
		{
			counter++;
			//System.out.println("Matched string:"+str);			
		}	
	}
	
	public static void searchLinearProbing(String str,LinearProbingHashTable l)
	{	
		//System.out.println("Str:"+str);
		if(l.contains(str))
		{
			counter++;
			//System.out.println("Matched string:"+str);			
		}	
	}

	public static void displayGrid(char a[][])
	{
		for(int i=0;i<a.length;i++)
		{
			for (int j=0;j<a[i].length;j++)
				System.out.print(a[i][j]+" ");
			System.out.println("");
		}	
	}
	
	
	public static String goDirections(char a[][],int i,int j,int k)
	{
		
		StringBuilder sb = new StringBuilder();
		//System.out.println("k:"+k);
		switch (k)
		{
			case 0:
				sb.setLength(0);
				for (int y=j; y<a[i].length ;y++)
					sb.append(a[i][y]);
				break;
			case 1:
				sb.setLength(0);
				for (int x=i,y=j; x<a.length && y<a[i].length ;x++,y++)
					sb.append(a[x][y]);
				break;
			case 2:
				sb.setLength(0);
				for (int x=i; x<a.length ;x++)
					sb.append(a[x][j]);
				break;
			case 3:
				sb.setLength(0);
				for (int x=i,y=j; x<a.length && y>=0 ;x++,y--)
					sb.append(a[x][y]);
				break;
			case 4:
				sb.setLength(0);
				for (int y=j; y>=0 ;y--)
					sb.append(a[i][y]);
				break;
			case 5:
				sb.setLength(0);
				for (int x=i,y=j; x>=0 && y>=0 ;x--,y--)
					sb.append(a[i][y]);
				break;
			case 6:	
				sb.setLength(0);
				for (int x=i; x>=0 ;x--)
					sb.append(a[x][j]);
				break;
			case 7:
				sb.setLength(0);
				for (int x=i,y=j; x>=0 && y<a[i].length ;x--,y++)
					sb.append(a[i][y]);
				break;
		}
		
		return new String(sb);
	}
}
