package project4;

import java.util.*;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import java.io.*;

public class WordPuzzle
{
	static int rows=0,columns=0,directions=8;
	static int counter=0,count=0;
	char a[][]= new char[rows][columns];
	static LinkedList<String> l1= new LinkedList<>();
	static AvlTree<String> l2= new AvlTree<>();
	static MyHashTable l3= new MyHashTable();
	static PrintWriter outFile1,outFile2,outFile3;
	static int maxlength=0;
	static 
	{
		
	try 
	{
		 outFile1 = new PrintWriter("Matched_words_LL.txt");
		 outFile2 = new PrintWriter("Matched_words_trees.txt");
		 outFile3 = new PrintWriter("Matched_words_myHash.txt");
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
					if(word.length() > maxlength)
						maxlength=word.length();
					l1.add(word);
					l2.insert(word);
					l3.add(word);
				 	if (++count%50000==0)
						System.out.println("Please wait..."+ count+" words loaded..");
				}
			f.close();
 
			System.out.println("Total words loaded: "+count);
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
				
			//Display the grid on console
			displayGrid(a);
			
// Search from Linked list		
            start = System.currentTimeMillis( );
            counter=0;
            count=0;
            for(int i=0;i<a.length;i++)
				for (int j=0;j<a[i].length;j++)
				{
					forSearch(String.valueOf(a[i][j]),l1);
					for (int k=0;k<directions;k++)
					{	
						if (++count%200==0)
						System.out.println("Search in Linked List: Please wait.."+(count/directions)*100/(a.length*a[i].length)+"% done..");
						goDirections(a,i,j,k,l1);
					}
				}
            System.out.println("Completed Linked list: Number of matched words:- "+counter);
            end = System.currentTimeMillis( );
            System.out.println( "For Linked list, Elapsed time in ms: " + (end-start) );
	

// Search from Tree
            start = System.currentTimeMillis( );
            counter=0;
            count=0;
            for(int i=0;i<a.length;i++)
				for (int j=0;j<a[i].length;j++)
				{
					forSearch(String.valueOf(a[i][j]),l2);
					for (int k=0;k<directions;k++)
					{
						if (++count%100000==0)
							System.out.println("Search in Tree: Please wait.."+(count/directions)*100/(a.length*a[i].length)+"% done..");
						goDirections(a,i,j,k,l2);
					}
				}
            System.out.println("Completed Trees: Number of matched words- "+counter);
            end = System.currentTimeMillis( );
            System.out.println( "For tress, Elapsed time in ms: " + (end-start) );

            
// Search from my Hash table
            start = System.currentTimeMillis( );
            counter=0;
            count=0;
            for(int i=0;i<a.length;i++)
				for (int j=0;j<a[i].length;j++)
				{
					forSearch(String.valueOf(a[i][j]),l3);
					for (int k=0;k<directions;k++)
					{
						if (++count%100000==0)
							System.out.println("Search in My hash table: Please wait.."+(count/directions)*100/(a.length*a[i].length)+"% done..");
						goDirections(a,i,j,k,l3);
					}
				}	
										
            System.out.println("Completed My hash: Number of matched words- "+counter);
            
            end = System.currentTimeMillis( );
            outFile1.close();
            outFile2.close();
            outFile3.close();
            
            System.out.println( "For my hash table, Elapsed time in ms: " + (end-start) );            
			
			System.out.println("CHECK O/P FILES FOR MATCHED STRINGS");
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public static void searchLinkedList(String str,LinkedList<String> l)
	{	
		if (l.contains(str))
			{				
				counter++;
				outFile1.println(str);
				//System.out.println("Matched string:"+str);
			}	
	}
	
	public static void searchTree(String str,AvlTree<String> l)
	{	
		if(l.contains(str))		
		{	
			counter++;
			outFile2.println(str);
			//System.out.println("Matched string:"+str);
		}
	}
	
	
	public static void searchMyHash(String str,MyHashTable l)
	{	
		if(l.contains(str))
		{
			counter++;
			outFile3.println(str);
			//System.out.println("Matched string:"+str);			
		}	
	}
	public static void forSearch(String str,Object obj)
	{
		if (str.length() <= maxlength )
		{
			if (obj instanceof LinkedList<?>)
				searchLinkedList(str,(LinkedList<String>)obj);
			else if (obj instanceof AvlTree<?>)
				searchTree(str,(AvlTree<String>)obj);
			else if (obj instanceof MyHashTable)
				searchMyHash(str,(MyHashTable)obj);
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
