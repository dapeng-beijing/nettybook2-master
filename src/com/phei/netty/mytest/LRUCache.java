package com.phei.netty.mytest;

/**
 * 
 * @author jinpeng.zou
 * @created: 2018年7月17日
 * 
 */

public class LRUCache<K,V> {

	private Node<K,V> node;
	private int nodeSize;
	private int cacheSize = 0;
	
	public LRUCache(int cacheSize) {
		this.cacheSize = cacheSize;
		node = new Node<>();
	}
	
	public void put(K key,V value) {
		//原缓存中是否有此key.如有,更新并放至链表头部.否则新增至链表头部
		Node<K,V> current = node;
		boolean isUpdate = false;
		while(current != null) {
			if (key.equals(current.getKey())) {
				isUpdate = true;
				current.setValue(value);
				if(current.hasPrevious()) {
					current.getPrevious().setNext(current.getNext());
				} else {
					break;
				}
				if (current.hasNext()) {
					current.getNext().setPrevious(current.getPrevious());
				}
				current.setPrevious(null);
				current.setNext(node);
				node = current;
				break;
			}
			current = current.getNext();
		}
		
		if (!isUpdate) {
			Node<K,V> newNode = new Node<>(key, value, node, null);
			node.setPrevious(newNode);
			node = newNode;
			nodeSize++;
			if (nodeSize > cacheSize) {
				Node<K,V> lastNode = node;
				for(int i=1;i<cacheSize;i++) {
					lastNode = lastNode.getNext();
				}
				lastNode.setNext(null);
			}
		}
	}
	
	public V get(K key) {
		Node<K,V> currentNode = node;
		while(currentNode != null) {
			if (key.equals(currentNode.getKey())) {
				return currentNode.getValue();
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Node<K,V> current = node;
		while((current != null) && (current.getKey() != null)) {
			sb.append(current.getKey()).append(":").append(current.getValue()).append("→");
			current = current.getNext();
		}
		if (sb.length() > 0) {
			return sb.substring(0, sb.length()-1);
		}
		
		return "";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
