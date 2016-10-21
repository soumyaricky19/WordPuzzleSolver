package project4;
// BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
 
import java.util.* ;
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
    /**
     * Construct the tree.
     */
    public BinarySearchTree( )
    {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */ 
    public void insert( AnyType x )
    {
        root = insert( x, root );
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     * @param x the item to remove.
     */
    public void remove( AnyType x )
    {
        root = remove( x, root );
    }

    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     */
    public AnyType findMin( )
    {
        if( isEmpty( ) )
            System.out.println("UnderflowException");
    //        throw new UnderflowException( );
        return findMin( root ).element;
    }

    /**
     * Find the largest item in the tree.
     * @return the largest item of null if empty.
     */
    public AnyType findMax( )
    {
        if( isEmpty( ) )
            System.out.println("UnderflowException");
 //           throw new UnderflowException( );
        return findMax( root ).element;
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return true if not found.
     */
    public boolean contains( AnyType x )
    {
        return contains( x, root );
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty( )
    {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree( )
    {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            printTree( root );
    }

    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return new BinaryNode<>( x, null, null );
        
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = insert( x, t.left );
        else if( compareResult > 0 )
            t.right = insert( x, t.right );
        else
            ;  // Duplicate; do nothing
        return t;
    }

    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return t;   // Item not found; do nothing
                    
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = remove( x, t.left );
        else if( compareResult > 0 )
            t.right = remove( x, t.right );
        else if( t.left != null && t.right != null ) // Two children
        {
            t.element = findMin( t.right ).element;
            t.right = remove( t.element, t.right );
        }
        else
            t = ( t.left != null ) ? t.left : t.right;
        return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
     */
    private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t )
    {
        if( t == null )
            return null;
        else if( t.left == null )
            return t;
        return findMin( t.left );
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the largest item.
     */
    private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t )
    {
        if( t != null )
            while( t.right != null )
                t = t.right;

        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */
    private boolean contains( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return false;
                
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            return contains( x, t.left );
        else if( compareResult > 0 )
            return contains( x, t.right );
        else
            return true;    // Match
    }
    
//Recursively traverses the tree and returns the count of nodes    
    public int nodeCount()
    {
      return nodeCount(root,1);
    } 
    private int nodeCount(BinaryNode<AnyType> t,int count)
    {
      if (t==null)
         return 0;
      else
      {     
         count+=nodeCount(t.left,count)+nodeCount(t.right,count);  
         return count;
      }  
    }
    
//Returns true if the tree is full
    public boolean isFull()
    {
      if (!isEmpty())
         return isFull(root);
      else 
         return true;   
    }
    private boolean isFull(BinaryNode<AnyType> t)
    {
      if ((t.left == null && t.right == null))
         return true;
      else
      {     
         if ((t.left != null && t.right != null))
            if (isFull(t.left) && isFull(t.right))
               return true;
            else
               return false;    
         else
            return false;   
      }        
    }
    
//Compares the structure of current tree to another tree and returns true if they match    
    public boolean compareStructure(BinarySearchTree<AnyType> t)
    {
      return compareStructure(this.root,t.root);
    }
    
    private boolean compareStructure(BinaryNode<AnyType> r1,BinaryNode<AnyType> r2)
    {
      if (r1.left == null && r1.right==null && r2.left == null && r2.right==null)
         return true;
      else
         {
            if (((r1.left != null) && (r1.right != null)) && ((r2.left != null) && (r2.right != null)))
            {           
               if (compareStructure(r1.left,r2.left) && compareStructure(r1.right,r2.right))
                  return true;
               else
                  return false;
            }      
            else if (((r1.left != null) && (r1.right == null)) && ((r2.left != null) && (r2.right == null)))
            {
               if (compareStructure(r1.left,r2.left))
                  return true;
               else
                  return false;  
            }
            else if (((r1.left == null) && (r1.right != null)) && ((r2.left == null) && (r2.right != null)))
            {
               if (compareStructure(r1.right,r2.right))
                  return true;
               else
                  return false;  
            }
            else
               return false;
         }
    }
    
    
//Compares the current tree to another tree and returns true if they are identical.    
    public boolean equals(BinarySearchTree<AnyType> t)
    {
      return equals(this.root,t.root);
    }
    private boolean equals(BinaryNode<AnyType> r1,BinaryNode<AnyType> r2)
    {

           if (r1.left == null && r1.right==null && r2.left == null && r2.right==null)
              return true;           
           else
             {          
                if (r1.element == r2.element)
                   if (r1.left != null && r1.right!=null && r2.left != null && r2.right!=null)
                     if (equals(r1.left,r2.left) && equals(r1.right,r2.right))
                        return true;
                     else
                        return false;  
                   if (r1.left != null && r1.right==null && r2.left != null && r2.right==null)    
                     if (equals(r1.left,r2.left))
                        return true;
                     else
                        return false;      
                   if (r1.left == null && r1.right!=null && r2.left == null && r2.right!=null)     
                     if (equals(r1.right,r2.right))
                        return true;
                     else
                        return false;      
                else
                   return false;      
             }
              
    }  
    
//Creates and returns a new tree that is a copy of the original tree    
    public BinarySearchTree<AnyType> copy()
    {
      BinarySearchTree<AnyType> ntree= new BinarySearchTree<>(); 
      ntree.root=copy(ntree.root,this.root);
      return ntree; 
    }
    private BinaryNode<AnyType> copy(BinaryNode<AnyType> n, BinaryNode<AnyType> t)
    {
      if (t!=null)
      {
         n=new BinaryNode<>(t.element,t.left,t.right);
         n.left=copy(n.left,t.left);
         n.right=copy(n.right,t.right);
      }
      return n;
    }
    
//Creates and returns a new tree that is a mirror image of the original tree
    public BinarySearchTree<AnyType> mirror()
    {
      BinarySearchTree<AnyType> ntree= new BinarySearchTree<>(); 
      ntree.root=mirror(this.root);
      return ntree; 
    }
    private BinaryNode<AnyType> mirror(BinaryNode<AnyType> t)
    {
      BinaryNode<AnyType> n=null;
      if (t!=null)
      {
         n=new BinaryNode<>(t.element,null,null);
         n.right=mirror(t.left);
         n.left=mirror(t.right);
      }
      return n;
    }


//Returns true if the tree is a mirror of the passed tree
    public boolean isMirror(BinarySearchTree<AnyType> t)
    {
      return isMirror(t.root,this.root);
    }
    private boolean isMirror(BinaryNode<AnyType> t1,BinaryNode<AnyType> t2)
    {
      if (t1!=null & t2!=null)
      {
         if(t1.element == t2.element)
            if(isMirror(t1.left,t2.right) && isMirror(t1.right,t2.left))
               return true;
            else
               return false;
         else
            return false;   
      }
      else
         return true;
    }
    
    //right rotation
    public void rotateRight(AnyType n)
    {
      root=rotateRight(root,n,false);
    }    
    private BinaryNode<AnyType> rotateRight(BinaryNode<AnyType> t,AnyType n,boolean found)
    {
      if (found == false)
      {
         if (t!=null)
         {
            if (n.compareTo(t.element) < 0)
               t.left=rotateRight(t.left,n,false);
            else if (t.element.compareTo(n) < 0)
               t.right=rotateRight(t.right,n,false);
            else
               t=rotateRight(t,n,true);   
               
            return t;  
         }   
         else
            return null;                     
      }
      else
      {
         BinaryNode<AnyType> newt,temp;
         newt=t.left;
         if (newt != null)
         {
            temp=newt.right;
            t.left=temp;
            newt.right=t;   
            return newt;   
         }
         else
         //Do nothing
            return t;            
      }         
    }
    
    //left rotation 
    public void rotateLeft(AnyType n)
    {
      root=rotateLeft(root,n,false);
    }    
    private BinaryNode<AnyType> rotateLeft(BinaryNode<AnyType> t,AnyType n,boolean found)
    {
      if (found == false)
      {
         if (t!=null)
         {
            if (n.compareTo(t.element) < 0)
               t.left=rotateLeft(t.left,n,false);
            else if (t.element.compareTo(n) < 0)
               t.right=rotateLeft(t.right,n,false);
            else
               t=rotateLeft(t,n,true);   
               
            return t;  
         }   
         else
            return null;                     
      }
      else
      {
         BinaryNode<AnyType> newt,temp;         
         newt=t.right;
         if (newt != null)
         {
            temp=newt.left;
            t.right=temp;
            newt.left=t;   
            return newt;
         }   
         else
         //Do nothing
            return t;             
      }         
    }
    
    //performs a level-by-level printing of the tree
    public void printLevels()
    {
      printLevels(root);
    }
    private void printLevels(BinaryNode<AnyType> t)
    {
      BinaryNode<AnyType> n;
      java.util.Queue<BinaryNode<AnyType>> q=new java.util.LinkedList<BinaryNode<AnyType>>();
      q.add(t);
      while(!q.isEmpty())
      {
         n=q.remove();
         System.out.println(n.element);
         if(n.left!=null)
            q.add(n.left);
         if(n.right!=null)
            q.add(n.right);   
      }
    }
    
    
    //Print tree in pre-order
    public void printTreePre( )
    {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            printTreePre( root );
    }
    private void printTreePre( BinaryNode<AnyType> t )
    {
        if( t != null )
        {
            System.out.println( t.element );
            printTreePre( t.left );
            printTreePre( t.right );
        }
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the subtree.
     */
    private void printTree( BinaryNode<AnyType> t )
    {
        if( t != null )
        {
            printTree( t.left );
            System.out.println( t.element );
            printTree( t.right );
        }
    }

    /**
     * Internal method to compute height of a subtree.
     * @param t the node that roots the subtree.
     */
    private int height( BinaryNode<AnyType> t )
    {
        if( t == null )
            return -1;
        else
            return 1 + Math.max( height( t.left ), height( t.right ) );    
    }
    
    // Basic node stored in unbalanced binary search trees
    private static class BinaryNode<AnyType>
    {
            // Constructors
        BinaryNode( AnyType theElement )
        {
            this( theElement, null, null );
        }

        BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
        }

        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
    }


      /** The tree root. */
    private BinaryNode<AnyType> root;


    // Test program
    public static void main( String [ ] args )
    {
      BinarySearchTree<Integer> t = new BinarySearchTree<>( );
      t.insert(5);
      t.insert(3);
      t.insert(8);
      t.insert(30);

      if (t.isFull())
         System.out.println("FULL");
      else 
         System.out.println("NOT FULL");
         
      t.printTree( );
      System.out.println("Node count:"+t.nodeCount());

       BinarySearchTree<Integer> t1 = new BinarySearchTree<>( );
      t1.insert(15);
      t1.insert(13);
      t1.insert(18);
      t1.insert(130);
      

      if (t.compareStructure(t1))
            System.out.println("Trees have the same structure");
      else
            System.out.println("Trees DO NOT have the same structure"); 
           
            
      BinarySearchTree<Integer> t2 = new BinarySearchTree<>( );
      t2.insert(5);
      t2.insert(3);
      t2.insert(8);
      t2.insert(30);
      if (t.equals(t2))
            System.out.println("Trees are identical");
      else
            System.out.println("Trees re NOT identical");            

      BinarySearchTree<Integer> t3 = t.copy();
      t3.printTree( );
      
      /*if (t.equals(t3))
            System.out.println("Trees are identical");
      else
            System.out.println("Trees re NOT identical");
      */

      BinarySearchTree<Integer> t4 = new BinarySearchTree<>();
      t4.insert(100);
      t4.insert(50);
      t4.insert(40);
      t4.insert(45);
      t4.insert(150);
      
      System.out.println("Print per levels:");
      t4.printLevels();

      System.out.println("Orginal tree:");
      t4.printTree( );
      BinarySearchTree<Integer> t5 = t4.mirror();
      System.out.println("Mirror tree:");
      t5.printTree( );
         
   

      if (t4.isMirror(t5))
         System.out.println("Trees are mirrors");
      else
         System.out.println("Trees are NOT mirrors");      
   
   

      System.out.println("Original tree:");
      //Pre-order traversal
      t4.printTreePre();
      System.out.println("Rotate right:");
      t4.rotateRight(50);
      //Pre-order traversal
      t4.printTreePre();   
      System.out.println("Rotate left:");
      t4.rotateLeft(50);
      //Pre-order traversal
      t4.printTreePre();
      

    }
}
