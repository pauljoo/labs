package info.xpanda.structure;

import java.util.Iterator;

public class MyLinkedList<AnyType> implements Iterable<AnyType>
{
	private static class Node<AnyType>
	{
		public AnyType data;
		public Node<AnyType> prev;
		public Node<AnyType> next;
		public Node(AnyType d, Node<AnyType> p, Node<AnyType> n)
		{
			data = d;
			prev = p;
			next = n;
		}
	}
	
	private int theSize;
	//自从链表构造以来对链表所做的改变次数
	//与迭代器里的modCount进行比较
	private int modCount = 0;
	//头节点
	private Node<AnyType> beginMarker;
	//尾节点
	private Node<AnyType> endMarker;
	
	public int size()
	{
		return theSize;
	}
	public boolean add(AnyType x)
	{
		add(size(), x);
		return true;
	}
	
	public void add(int index, AnyType x)
	{
		//根据index获取节点，在该节点前添加x
		addBefore(getNode(index), x);
	}
	
	public void addBefore(Node<AnyType> p, AnyType x)
	{
		//在p和p之前插入新节点
		Node<AnyType> newNode = new Node<AnyType>(x, p.prev, p);
		//更新新节点前一节点的后一节点链接
		newNode.prev.next = newNode;
		//更新p的前一节点链接
		p.prev = newNode;
		theSize++;
		modCount++;
	}
	public AnyType get(int index)
	{
		return getNode(index).data;
	}
	
	public AnyType set(int index, AnyType newVal)
	{
		Node<AnyType> p = getNode(index);
		AnyType oldVal = p.data;
		p.data = newVal;
		return oldVal;
	}
	
	public AnyType remove(int index)
	{
		return remove(getNode(index));
	}
	
	private AnyType remove(Node<AnyType> p)
	{
		p.next.prev = p.prev;
		p.prev.next = p.next;
		theSize--;
		modCount++;
		
		return p.data;
	}
	
	private Node<AnyType> getNode(int index)
	{
		Node<AnyType> p;
		
		if(index < 0 || index > size())
		{
			throw new IndexOutOfBoundsException();
		}
		
		//从头节点或者尾节点开始查找
		if(index < size() / 2)
		{
			p = beginMarker.next;
			for(int i = 0; i < index; i++)
			{
				p = p.next;
			}
		}
		else
		{
			p = endMarker;
			for(int i = size(); i > index; i--)
			{
				p = p.prev;
			}
		}
		
		return p;
	}
	
	private class LinkedListIterator implements Iterator<AnyType>
	{
		private Node<AnyType> current = beginMarker.next;
		private int expectedModCount = modCount;
		private boolean okToRemove = false;
		
		public boolean hasNext()
		{
			return current != endMarker;
		}
		
		public AnyType next()
		{
			if(modCount != expectedModCount)
			{
				throw new java.util.ConcurrentModificationException();
			}
			if(!hasNext())
			{
				throw new java.util.NoSuchElementException();
			}
			
			AnyType nextItem = current.data;
			current = current.next;
			okToRemove = true;
			return nextItem;
		}
		
		public void remove()
		{
			if(modCount != expectedModCount)
			{
				throw new java.util.ConcurrentModificationException();
			}
			if(!okToRemove)
			{
				throw new IllegalStateException();
			}
			
			MyLinkedList.this.remove(current.prev);
			okToRemove = false;
			expectedModCount++;
		}
	}

	@Override
	public Iterator<AnyType> iterator() {
		return new LinkedListIterator();
	}
}
