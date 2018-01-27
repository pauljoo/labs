package info.xpanda.structure;

import java.util.Comparator;

public class MyBinarySearchTree<AnyType extends Comparable<? super AnyType>> {
	private static class BinaryNode<AnyType>
	{
		private AnyType element;
		private BinaryNode<AnyType> left;
		private BinaryNode<AnyType> right;
		
		public BinaryNode(AnyType theElement) 
		{
			this(theElement, null, null);
		}
		
		public BinaryNode(AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt) 
		{
			element = theElement;
			left = lt;
			right = rt;
		}
	}
	
	private BinaryNode<AnyType> root;
	private Comparator<? super AnyType> cmp;
	
	public MyBinarySearchTree() 
	{
		this(null);
	}
	
	public MyBinarySearchTree(Comparator<? super AnyType> c) 
	{
		root = null;
		cmp = c;
	}
	
	public void makeEmpty()
	{
		root = null;
	}
	
	public boolean isEmpty()
	{
		return null == root;
	}
	
	public boolean contains(AnyType x)
	{
		return contains(x, root);
	}
	
	public AnyType findMin()
	{
		if(isEmpty())
		{
			//throw new UnderflowException();
		}
		
		return findMin(root).element;
	}
	
	public AnyType findMax()
	{
		if(isEmpty())
		{
			//throw new UnderflowException();
		}
		
		return findMax(root).element;
	}
	
	public void insert(AnyType x)
	{
		root = insert(x, root);
	}
	
	public void remove(AnyType x)
	{
		root = remove(x, root);
	}
	
	public void printTree()
	{
		if(isEmpty())
		{
			System.out.println("Empty Tree");
		}
		else
		{
			printTree(root);
		}
	}
	private boolean contains(AnyType x, BinaryNode<AnyType> t)
	{
		if(null == t)
		{
			return false;
		}
		
		int compareResult = myCompare(x, t.element);
		
		if(compareResult < 0)
		{
			return contains(x, t.left);
		}
		else if(compareResult > 0)
		{
			return contains(x, t.right);
		}
		else
		{
			return true;
		}
	}

	private int myCompare(AnyType lhs, AnyType rhs)
	{
		if(null != cmp)
		{
			return cmp.compare(lhs, rhs);
		}
		else
		{
			return ((Comparable)lhs).compareTo(rhs);
		}
	}
	private BinaryNode<AnyType> findMin(BinaryNode<AnyType> t)
	{
		if(null == t)
		{
			return null;
		}
		else if(null == t.left)
		{
			return t;
		}
		return findMin(t.left);
	}
	
	private BinaryNode<AnyType> findMax(BinaryNode<AnyType> t)
	{
		if(null != t)
		{
			while(null != t.right)
			{
				t = t.right;
			}
		}
		return t;
	}
	
	private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t)
	{
		if(null == t)
		{
			return new BinaryNode<AnyType>(x, null, null);
		}
		
		int compareResult = x.compareTo(t.element);
		
		if(compareResult < 0)
		{
			t.left = insert(x, t.left);
		}
		else if(compareResult > 0)
		{
			t.right = insert(x, t.right);
		}
		else{}
		
		return t;
	}
	
	private BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> t)
	{
		if(null == t)
		{
			return t;
		}
		
		int compareResult = x.compareTo(t.element);
		
		if(compareResult < 0)
		{
			t.left = remove(x, t.left);
		}
		else if(compareResult > 0)
		{
			t.right = remove(x, t.right);
		}
		else if(null != t.left && null != t.right)
		{
			t.element = findMin(t.right).element;
			t.right = remove(t.element, t.right);
		}
		else
		{
			t = (null != t.left)? t.left: t.right;
		}
		return t;
	}
	
	private void printTree(BinaryNode<AnyType> t)
	{
		if(null != t)
		{
			printTree(t.left);
			System.out.println(t.element);
			printTree(t.right);
		}
	}
}
