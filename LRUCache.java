package Design;

import java.util.HashMap;
import java.util.Map;



// hash table doubly linked list
public class LRUCache {
	
	Map<Integer, ListNode> map = new HashMap<>();
	ListNode head;
	ListNode tail;
	
	int totalItemInCache;
	int maxCapacity;
	
	public LRUCache()
	{
		totalItemInCache = 0;
		maxCapacity = 0;
		
		head = new ListNode();
		tail = new ListNode();
		
		head.next= tail;
		tail.prev = head;
				
	}
	
	public Integer get(int key)
	{
		ListNode node = map.get(key);
		
		if(node == null)
		{
			return null;
		}
		
		moveToHead(node);
		
		return node.value;
	}
	public void put(int key, int value)
	{
		ListNode node = map.get(key);
		if(node == null)
		{
			ListNode newNode = new ListNode();
			newNode.key= key;
			newNode.value = value;
			
			map.put(key, newNode);
			addToFront(newNode);
			totalItemInCache++;
			
			if(totalItemInCache > maxCapacity)
			{
				removeLRUEntry();
			}
			
			
		}
		else
		{
			node.value= value;
			moveToHead(node);
		}

		
	}
	
	private void removeLRUEntry()
	{
		ListNode tail = popTail();
		map.remove(tail.key);
		 --totalItemInCache;
	}
	private ListNode popTail()
	{
		ListNode tailItem = tail.prev;//because we are using dummy tail
		removeFromList(tailItem);
		return tailItem;
		
		
	}
	private void addToFront(ListNode node)
	{
		node.prev = head;
		node.next = head.next;
		head.next.prev = node;
		head.next =node;
	}
    private void removeFromList(ListNode node) {
    	ListNode savedPrev = node.prev;
    	ListNode savedNext = node.next;
    	savedPrev.next = savedNext;
    	savedNext.prev = savedPrev;

    }
    private void moveToHead(ListNode node)
    {
    	removeFromList(node);
    	addToFront(node);
    }

    private class ListNode {
        int key;
        int value;

        ListNode prev;
        ListNode next;
      }


}
