package project4;

import java.util.*;
import java.io.*;

public class WordPuzzle
{
	static int rows=0,columns=0,directions=8;
	static int counter=0,count=0;
	char a[][]= new char[rows][columns];
	static LinkedList<String> l1= new LinkedList<>();
	//static BinarySearchTree<String> l2= new BinarySearchTree<>();
	static AvlTree<String> l2= new AvlTree<>();
	static QuadraticProbingHashTable<String> l3=new QuadraticProbingHashTable<String>();
	static MyHashTable l4= new MyHashTable();
	static PrintWriter outFile1,outFile2,outFile3,outFile4;
	static 
	{
		
	try 
	{
		 outFile1 = new PrintWriter("Matched_words_LL.txt");
		 outFile2 = new PrintWriter("Matched_words_trees.txt");
		 outFile3 = new PrintWriter("Matched_words_QH.txt");
		 outFile4 = new PrintWriter("Matched_words_myHash.txt");
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
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
					l1.add(word);
					l2.insert(word);
					l3.insert(word);
					l4.add(word);
					//System.out.println(count++);
				}
			f.close();
			
			//System.out.println(l1.size());
			//System.out.println(l2.nodeCount());
			System.out.println(l3.size());
			System.out.println(l4.getSize());
		/*	for(int i=0;i<l3.size();i++)
			{
				if (l3.valueAt(i)!=l4.valueAt(i) && l3.valueAt(i)!=null && l4.valueAt(i)!=null)
				{
					System.out.println("Something fishy:"+l3.valueAt(i)+":"+l4.valueAt(i));
				}
			}
			
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
		/*		
									
			char[][] a = { 
                    {'f','y','y','h','n','r','d'},
                    {'r','l','j','c','i','n','u'},
                    {'a','a','w','a','a','h','r'},
                    {'n','t','k','l','p','n','e'},
                    {'c','i','l','f','s','a','p'},
                    {'e','o','g','o','t','p','n'},
                    {'h','p','o','l','a','n','d'}
                  };			
			
			char[][] a = { 
                    {'f','a'},
                    {'r','l'}};
      */              
                  
                  
			
			displayGrid(a);
			
// Search from Linked list		
            start = System.currentTimeMillis( );
            counter=0;
            
            for(int i=0;i<a.length;i++)
				for (int j=0;j<a[i].length;j++)
				{
					forSearch(String.valueOf(a[i][j]),l1);
					for (int k=0;k<directions;k++)
	//					break;
						goDirections(a,i,j,k,l1);						
				}
            System.out.println("Linked list: Counter"+counter);
            end = System.currentTimeMillis( );
            System.out.println( "For Linked list, Elapsed time in ms: " + (end-start) );
	

// Search from Trees
            start = System.currentTimeMillis( );
            counter=0;
            
            for(int i=0;i<a.length;i++)
				for (int j=0;j<a[i].length;j++)
				{
					forSearch(String.valueOf(a[i][j]),l2);
					for (int k=0;k<directions;k++)
		//				break;
						goDirections(a,i,j,k,l2);							
				}
            System.out.println("Trees: Counter"+counter);
            end = System.currentTimeMillis( );
            System.out.println( "For tress, Elapsed time in ms: " + (end-start) );

			
// Search from Quadratic probing Hash table
            start = System.currentTimeMillis( );
            counter=0;
            for(int i=0;i<a.length;i++)
				for (int j=0;j<a[i].length;j++)
				{
					forSearch(String.valueOf(a[i][j]),l3);
					for (int k=0;k<directions;k++)						
						goDirections(a,i,j,k,l3);
				}
										
            System.out.println("Quadratic probing: Counter"+counter);
            end = System.currentTimeMillis( );
            System.out.println( "For Quadratic probing hash table, Elapsed time in ms: " + (end-start) );
            
// Search from Linear probing Hash table
            start = System.currentTimeMillis( );
            counter=0;
            for(int i=0;i<a.length;i++)
				for (int j=0;j<a[i].length;j++)
				{
					forSearch(String.valueOf(a[i][j]),l4);
					for (int k=0;k<directions;k++)						
						goDirections(a,i,j,k,l4);
					
				}	
										
            System.out.println("Linear probing: Counter"+counter);
            end = System.currentTimeMillis( );
            outFile1.close();
            outFile2.close();
            outFile3.close();
            outFile4.close();
            
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
		boolean flag=false;
		Iterator<String> itr=l.iterator();
		while(itr.hasNext() && !flag)
		{
			String s1=(String) itr.next();
			if (str.compareTo(s1)== 0)
			{				
				counter++;
				outFile1.println(str);
				//System.out.println("Matched string:"+str);
				flag=true;						
			}	
			
		}
	}
	
//	public static void searchTree(String str,BinarySearchTree<String> l)
	public static void searchTree(String str,AvlTree<String> l)
	{	
		if(l.contains(str))		
		{	
			counter++;
			outFile2.println(str);
			//System.out.println("Matched string:"+str);
		}
	}
	
	public static void searchQuadraticProbing(String str,QuadraticProbingHashTable<String> l)
	{	
		if(l.contains(str))
		{
			counter++;
			outFile3.println(str);
			//System.out.println("Matched string:"+str);			
		}	
	}
	
	public static void searchLinearProbing(String str,MyHashTable l)
	{	
		//System.out.println("Str:"+str);
		if(l.contains(str))
		{
			counter++;
			outFile4.println(str);
			//System.out.println("Matched string:"+str);			
		}	
	}
	public static void forSearch(String str,Object obj)
	{
		//System.out.println("Object:"+obj);
		//System.out.println("Random string:"+str);
		if (obj instanceof LinkedList<?>)
			searchLinkedList(str,(LinkedList<String>)obj);
//		else if (obj instanceof BinarySearchTree<?>)
//			searchTree(str,(BinarySearchTree<String>)obj);
		else if (obj instanceof AvlTree<?>)
 			searchTree(str,(AvlTree<String>)obj);
		else if (obj instanceof QuadraticProbingHashTable<?>)
			searchQuadraticProbing(str,(QuadraticProbingHashTable<String>)obj);
		else if (obj instanceof MyHashTable)
			searchLinearProbing(str,(MyHashTable)obj);
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
	
	
	public static void goDirections(char a[][],int i,int j,int k,Object o)
	{
		
		StringBuilder sb = new StringBuilder();
		sb.append(a[i][j]);
		switch (k)
		{
			case 0:
				for (int y=j+1; y<a[i].length ;y++)
				{
					sb.append(a[i][y]);
					forSearch(sb.toString(),o);
				}
				break;
			case 1:
				for (int x=i+1,y=j+1; x<a.length && y<a[i].length ;x++,y++)
				{
					sb.append(a[x][y]);
					forSearch(sb.toString(),o);
				}
				break;
			case 2:
				for (int x=i+1; x<a.length ;x++)
				{
					sb.append(a[x][j]);
					forSearch(sb.toString(),o);
				}
				break;
			case 3:
				for (int x=i+1,y=j-1; x<a.length && y>=0 ;x++,y--)
				{
					sb.append(a[x][y]);
					forSearch(sb.toString(),o);
				}
				break;
			case 4:
				for (int y=j-1; y>=0 ;y--)
				{
					sb.append(a[i][y]);
					forSearch(sb.toString(),o);
				}
				break;
			case 5:
				for (int x=i-1,y=j-1; x>=0 && y>=0 ;x--,y--)
				{	
					sb.append(a[x][y]);
					forSearch(sb.toString(),o);
				}
				break;
			case 6:	
				for (int x=i-1; x>=0 ;x--)
				{
					sb.append(a[x][j]);
					forSearch(sb.toString(),o);
				}
				break;
			case 7:
				for (int x=i-1,y=j+1; x>=0 && y<a[i].length ;x--,y++)
				{
					sb.append(a[x][y]);
					forSearch(sb.toString(),o);
				}
				break;
		}
		
	}
}
