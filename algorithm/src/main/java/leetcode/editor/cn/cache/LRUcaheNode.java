package leetcode.editor.cn.cache;

public class LRUcaheNode {

    class Node{
        int key;
        int value;

        Node pre;
        Node next;

        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }

    /**
     * 双向链表
     */
    class DoubleList{

        private Node head, tail;

        private int size;

        public DoubleList(){
            //假节点
            head = new Node(0,0);
            tail = new Node(0,0);
            head.next = tail;
            tail.next = head;
            size = 0;
        }

        /**
         * 尾部是最新的元素
         * @param x
         */
        public void addLast(Node x){
            x.pre = tail.pre;
            x.next = tail;

            tail.pre.next = x;
            tail.pre = x;
            size++;
        }

        public void remove(Node x){

        }

        public Node removeFirst(){
            return null;
        }

        public int getSize(){
            return this.size;
        }

    }

}
