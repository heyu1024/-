package com.cxw.datesource.redis;

import com.sun.xml.internal.ws.api.pipe.NextAction;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shkstart
 * @create 2020-12-12 17:04
 */
public class LRUCacheDemo02 {

    class Node<K,V>{
        K key;
        V value;
        Node<K,V> prev;
        Node<K,V> next;

        public Node(){
            this.prev=this.next=null;//初始化
        }

        public Node(K key,V value){
            this.key=key;
            this.value=value;
            this.prev=this.next=null;//初始化
        }
    }

    //构建一个双向链表
    class DoubleLinkedList<K,V>{
        Node<K,V> head;
        Node<K,V> tail;

        public DoubleLinkedList(){
            head=new Node<>();
            tail=new Node<>();
            head.next=tail;
            tail.prev=head;
        }

        //添加到表头
        public void addHead(Node<K,V> node){
            node.next=head.next;
            node.prev=head;
            head.next.prev=node;
            head.next=node;
        }

        //删除节点
        public void removeNode(Node<K,V> node){
            node.next.prev=node.prev;
            node.prev.next=node.next;
            node.prev=null;
            node.next=null;
        }

        //获取最后一个节点
        public Node getLast(){
            return tail.prev;
        }
    }

    private int cacheSize;
    private Map<Integer,Node<Integer,Integer>> map;
    private DoubleLinkedList<Integer,Integer> doubleLinkedList;

    public LRUCacheDemo02(int cacheSize){
        this.cacheSize=cacheSize;
        map=new HashMap<>();//查找
        doubleLinkedList=new DoubleLinkedList<>();
    }

    public int get(int key){
        if(!map.containsKey(key)){
            return -1;
        }

        Node<Integer, Integer> node = map.get(key);

        doubleLinkedList.removeNode(node);
        doubleLinkedList.addHead(node);

        return node.value;
    }

    public void put(int key,int value){
        if(map.containsKey(key)){
            Node<Integer, Integer> node = map.get(key);
            node.value=value;
            map.put(key,node);
            doubleLinkedList.removeNode(node);
            doubleLinkedList.addHead(node);
        }else {
            if(map.size()==cacheSize){//坑位满了
                Node lastNode = doubleLinkedList.getLast();
                map.remove(lastNode.key);
                doubleLinkedList.removeNode(lastNode);
            }

            Node<Integer,Integer> newNode = new Node(key, value);
            map.put(key,newNode);
            doubleLinkedList.addHead(newNode);
        }
    }

    public static void main(String[] args) {
        LRUCacheDemo02 lruCacheDemo=new LRUCacheDemo02(3);
        lruCacheDemo.put(1,1);
        lruCacheDemo.put(2,2);
        lruCacheDemo.put(3,3);

        System.out.println(lruCacheDemo.map.keySet());

        lruCacheDemo.put(4,4);
        System.out.println(lruCacheDemo.map.keySet());

        lruCacheDemo.put(3,3);

        System.out.println(lruCacheDemo.map.keySet());
        lruCacheDemo.put(3,3);

        System.out.println(lruCacheDemo.map.keySet());
        lruCacheDemo.put(3,3);

        System.out.println(lruCacheDemo.map.keySet());

        lruCacheDemo.put(5,5);

        System.out.println(lruCacheDemo.map.keySet());
    }
}
