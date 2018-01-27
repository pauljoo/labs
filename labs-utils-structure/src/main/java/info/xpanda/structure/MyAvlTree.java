package info.xpanda.structure;

public class MyAvlTree<AnyType extends Comparable<? super AnyType>>{
	private static class AvlNode<AnyType>
	{
		AnyType element;
		AvlNode<AnyType> left;
		AvlNode<AnyType> right;
		int height;
		
		AvlNode(AnyType theElement)
		{
			this(theElement, null, null);
		}
		
		AvlNode(AnyType theElement, AvlNode<AnyType> lt, AvlNode<AnyType> rt)
		{
			element = theElement;
			left = lt;
			right = rt;
			height = 0;
		}
	}
	
	private int height(AvlNode<AnyType> t)
	{
		return null == t? -1 : t.height;
	}
	
	private AvlNode<AnyType> rotateWithLeftChild(AvlNode<AnyType> k2)
	{
		AvlNode<AnyType> k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;
		k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
		k1.height = Math.max(height(k1.left), k2.height) + 1;
		return k1;
	}
	
	private AvlNode<AnyType> doubleWithLeftChild(AvlNode<AnyType> k3)
	{
		k3.left = rotateWithLeftChild(k3.left);
		return rotateWithLeftChild(k3);
	}
}
