package leetcode_solutions;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class LRUCache146 {
    //执行用时:205ms,在所有java提交中击败了13.12%的用户内存消耗:57.4MB,在所有java提交中击败了95.45%的用户
    class LRUCache {

        private Map<Integer, Integer> map;
        private LinkedList<Integer> list;
        private int capacity;
        private int currentKeyNumber;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.currentKeyNumber = 0;
            map = new HashMap<>(capacity);
            list = new LinkedList<>();
        }

        public int get(int key) {
            //缓存有元素key
            if (map.containsKey(key)) {
                //更新使用队列
                if (list.contains(key)) {
                    list.remove(Integer.valueOf(key));
                    list.offer(key);
                } else {
                    list.offer(key);
                }
                return map.get(key);
            }
            return -1;
        }

        public void put(int key, int value) {
            if (map.containsKey(key)) {
                //已经存在key，更新map和list
                map.put(key, value);
                list.remove(Integer.valueOf(key));
                list.offer(key);
            } else if (currentKeyNumber == capacity) {
                int k = list.pollFirst();
                map.remove(k);
//                System.out.println("remove " + k);
                map.put(key, value);
                list.offer(key);
            } else if (currentKeyNumber < capacity) {
                map.put(key, value);
                list.offer(key);
                currentKeyNumber++;
            }
        }
    }

    /**
     * Your LRUCache object will be instantiated and called as such:
     * LRUCache obj = new LRUCache(capacity);
     * int param_1 = obj.get(key);
     * obj.put(key,value);
     */

    @Test
    public void test() {
        LinkedList<Integer> list = new LinkedList<>();
        list.offer(1);
        list.offer(2);
        list.offer(3);
        System.out.println(list);

        int i = list.pollFirst();
        System.out.println(i);
        System.out.println(list);

        System.out.println("-----------------");
        list.offer(1);
        if (list.contains(2)) {
            list.remove(Integer.valueOf(2));
            list.offer(2);
        } else {
            list.offer(2);
        }
        System.out.println(list);
    }

    @Test
    public void test1() {
        //["LRUCache","get","put","get","put","put","get","get"]
        //[[2],[2],[2,6],[1],[1,5],[1,2],[1],[2]]
        LRUCache cache = new LRUCache(2);
        System.out.println(cache.get(2));
        cache.put(2, 6);
        System.out.println(cache.get(1));
        cache.put(1, 5);
        cache.put(1, 2);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));

    }

    class LRUCache14ms {
        private LinkedHashMap<Integer, Integer> cache;

        public LRUCache14ms(int capacity) {
            this.cache = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
                public boolean removeEldestEntry(Map.Entry eldest) {
                    return size() > capacity;
                }
            };
        }

        public int get(int key) {
            Integer val = cache.get(key);
            return val == null ? -1 : val;
        }

        public void put(int key, int value) {
            cache.put(key, value);
        }
    }

    class LRUCache16ms {
        //此处是static
        //        static class Node {
        class Node {
            int k;
            int v;
            Node after, before; // for lru cache linkedlist, 尾节点是最新加入的
            Node pre, next; // for hashmap hash collision linkedlist

            public Node(int k, int v) {
                this.k = k;
                this.v = v;
            }
        }

        final int CAP;
        int hold = 0;
        Node head = null;
        Node tail = null;

        final int MOD = 1000;
        Node[] hashmap = new Node[MOD];

        int hash(int key) {
            return key % MOD;
        }


        public LRUCache16ms(int capacity) {
            this.CAP = capacity;
        }

        public int get(int key) {
            //通过hashmap获取到要查询的节点
            Node target = null;

            int hash = hash(key);
            Node hashNode = hashmap[hash];
            while (hashNode != null) {
                if (hashNode.k == key) target = hashNode;
                hashNode = hashNode.next;
            }

            //把该节点在lru链表中调整到尾部
            if (target != null) {
                mvToTail(target);
            }

            //返回
            return target == null ? -1 : target.v;
        }

        private void mvToTail(Node target) {
            //先把节点从当前位置删除
            if (target.after != null) target.after.before = target.before;
            if (target == head) {
                head = target.after;
            } else {
                target.before.after = target.after;
            }
            if (tail == target) tail = target.before;

            //重新添加到尾部
            if (head != null) {
                tail.after = target;
                target.before = tail;
                target.after = null;
                tail = target;
            } else {
                head = tail = target;
            }
        }

        public void put(int key, int value) {
            Node node = new Node(key, value);

            // add to hashmap
            int hash = hash(node.k);
            Node hashNode = hashmap[hash];
//        boolean replaced = false;
            Node replaceNode = null;
            if (hashNode == null) {
                hashmap[hash] = node;
            } else {
                Node last = null;
                while (hashNode != null) {
                    last = hashNode;
                    if (hashNode.k == node.k) {
                        //replace exist
                        hashNode.v = node.v;
                        replaceNode = hashNode;
                        break;
                    }
                    hashNode = hashNode.next;
                }
                if (replaceNode == null) {
                    last.next = node;
                    node.pre = last;
                }
            }

            if (replaceNode != null) {
                mvToTail(replaceNode); //replace了原来的key, 把这个节点移到lru链表尾部
            } else {
                //缓存满了, 清除lru头结点
                if (hold == CAP) {
                    Node del = head;
                    //从lru链表中删除
                    if (del.after != null) del.before = null;
                    head = del.after;
                    if (tail == del) tail = null;

                    //从hashmap中删除
                    int delHash = hash(del.k);
                    if (del.next != null) del.next.pre = del.pre;

                    if (del.pre == null) hashmap[delHash] = del.next; //是head
                    else del.pre.next = del.next; //不是head

                    hold--;
                }

                // add to lru cache linkedlist
                if (head == null) {
                    head = tail = node;
                } else {
                    tail.after = node;
                    node.before = tail;
                    tail = node;
                }
                hold++;
            }
        }


    }
}
