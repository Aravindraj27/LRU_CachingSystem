package com.gar.CachingSystem.cache.Policies;

import com.gar.CachingSystem.LinkedList.DoublyLinkedList;
import com.gar.CachingSystem.LinkedList.DoublyLinkedListNode;

import java.util.HashMap;

public class LRUEvictionPolicy<Key> implements EvictionPolicy<Key> {
    private DoublyLinkedList<Key> doublyLinkedList;
    private HashMap<Key, DoublyLinkedListNode<Key>> mapper;

    public LRUEvictionPolicy() {
        doublyLinkedList = new DoublyLinkedList<>();
        mapper = new HashMap<>();
    }


    @Override
    public void keyAccessed(Key key) {
        if (mapper.containsKey(key)) {
            doublyLinkedList.detachNode(mapper.get(key));
        } else {
            DoublyLinkedListNode<Key> newNode = doublyLinkedList.addElementAtLast(key);
            mapper.put(key, newNode);
        }
    }

    @Override
    public Key evitKey() {
        DoublyLinkedListNode<Key> first = doublyLinkedList.getFirstNode();
        if(first == null) {
            return null;
        }
        doublyLinkedList.detachNode(first);
        return first.getElement();
    }
}
