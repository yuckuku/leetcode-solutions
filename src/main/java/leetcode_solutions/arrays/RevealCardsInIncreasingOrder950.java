package leetcode_solutions.arrays;

import java.time.format.DecimalStyle;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Auther: L'Nan
 * @Date: 2019/4/4 10:18
 * @Description:
 */
public class RevealCardsInIncreasingOrder950 {
    class Mysolution {
        public int[] deckRevealedIncreasing(int[] deck) {
            int[] re = new int[deck.length];
            Arrays.sort(deck);
            for (int i = deck.length - 1; i >= 0; i--) {
                moveSequentially(re, i + 1, re.length - 1);
                re[i] = deck[i];
            }
            return re;
        }

        private void moveSequentially(int a[], int i, int j) {
            if (i >= a.length) {
                return;
            }

            int temp = a[j];
            for (int k = j; k > i; k--) {
                a[k] = a[k - 1];
            }
            a[i] = temp;
        }
    }

    class SolutionOnLeetCode1 {
        class Node {
            int val;
            Node next;
            Node pre;

            public Node(int val) {
                this.val = val;
                next = null;
                pre = null;
            }
        }

        private Node head = null, tail = null;

        public int[] deckRevealedIncreasing(int[] deck) {
            //将输入从大到小的排序
            quickSort(deck, 0, deck.length - 1);

            // Arrays.sort(deck);
            // for(int i=0;i<deck.length/2;i++){
            //     int temp = deck[deck.length-1-i];
            //     deck[deck.length-1-i] = deck[i];
            //     deck[i] = temp;
            // }

            for (int i = 0; i < deck.length; i++) {
                Node newNode = new Node(deck[i]);
                shiftRight(head, tail);
                if (head == null) {
                    head = newNode;
                    tail = newNode;
                    continue;
                }


                newNode.next = head;
                head.pre = newNode;
                head = newNode;
            }

            int index = 0;
            while (head != null) {
                deck[index] = head.val;
                head = head.next;
                index++;
            }

            return deck;
        }

        private void quickSort(int[] deck, int low, int high) {
            if (low >= high) {
                return;
            }

            int position = partion(deck, low, high);
            quickSort(deck, low, position - 1);
            quickSort(deck, position + 1, high);
        }

        private int partion(int[] deck, int low, int high) {
            int povintValue = deck[low];
            while (low != high) {
                int temp = 0;
                while (deck[high] < povintValue) {
                    high--;
                }
                temp = deck[high];
                deck[high] = povintValue;
                deck[low] = temp;
                while (deck[low] > povintValue) {
                    low++;
                }
                temp = deck[low];
                deck[low] = povintValue;
                deck[high] = temp;
            }
            return low;
        }

        private void shiftRight(Node head1, Node tail1) {
            if (head1 == tail1) {
                return;
            }

            Node temp = tail;
            //处理尾节点
            tail1 = temp.pre;
            tail1.next = null;
            //处理头节点
            temp.pre = null;
            temp.next = head1;
            head1.pre = temp;
            head1 = temp;

            tail = tail1;
            head = head1;

        }
    }


    class SolutionOnLeetCode2 {
        public int[] deckRevealedIncreasing(int[] deck) {
            if (deck.length < 2) return deck;
            quicksort(deck, 0, deck.length - 1);
            Queue<Integer> queue = new LinkedList();
            queue.offer(deck[deck.length - 1]);
            for (int i = deck.length - 2; i >= 0; i--) {
                int tem = queue.poll();
                queue.offer(tem);
                queue.offer(deck[i]);
            }
            for (int i = deck.length - 1; i >= 0; i--)
                deck[i] = queue.poll();
            return deck;
        }

        public  void quicksort(int[] num, int left, int right) {
            int lf = left;
            int rt = right;
            int temp = 0;
            if (lf <= rt) {
                temp = num[lf];
                while (lf != rt) {
                    while (lf < rt && temp <= num[rt])
                        rt--;
                    num[lf] = num[rt];
                    while (lf < rt && temp >= num[lf])
                        lf++;
                    num[rt] = num[lf];
                }
                num[lf] = temp;
                quicksort(num, left, lf - 1);
                quicksort(num, rt + 1, right);
            }
        }
    }


}
