package leetcode_solutions;

import common.ListNode;
import org.junit.Test;

import java.util.PriorityQueue;

public class SortList148 {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    //执行用时:11ms,在所有java提交中击败了34.90%的用户内存消耗:40.7MB,在所有java提交中击败了97.75%的用户
    class Solution {
        public ListNode sortList(ListNode head) {
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            ListNode tmp = head;
            while (tmp != null) {
                pq.offer(tmp.val);
                tmp = tmp.next;
            }
            tmp = head;
            while (tmp != null) {
                tmp.val = pq.poll();
                tmp = tmp.next;
            }
            return head;
        }
    }

    @Test
    public void test() {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(1);
        pq.offer(2);
        pq.offer(-1);
        System.out.println(pq.peek());
    }

    // 递归栈的额外空间复杂度为O(logN)，不为常数级
    // partition + recursion ???
    // 实质为快速排序
    // 结合递归时，应采用"荷兰国旗"分三区，测试中遇过<=区无限循环，递归栈溢出
    class Solution2ms {
        public ListNode sortList(ListNode head) {
            // base case of the recursion
            if (head == null || head.next == null) {
                return head;
            }
            // partition
            ListNode cur = head;
            ListNode smaller = null;
            ListNode equal = null;
            ListNode larger = null;
            while (cur != null) {
                ListNode next = cur.next;
                if (cur.val < head.val) {
                    cur.next = smaller;
                    smaller = cur;
                } else if (cur.val == head.val) {
                    cur.next = equal;
                    equal = cur;
                } else {
                    cur.next = larger;
                    larger = cur;
                }
                cur = next;
            }
            // recursion
            smaller = sortList(smaller);
            larger = sortList(larger);
            return connect(smaller, connect(equal, larger));
        }

        public ListNode connect(ListNode h1, ListNode h2) {
            if (h1 == null || h2 == null) {
                return h1 == null ? h2 : h1;
            }
            ListNode tail = h1;
            while (tail.next != null) {
                tail = tail.next;
            }
            tail.next = h2;
            return h1;
        }
    }

    /**
     * 归并排序（自底向上）
     */
    public class Solution1 {

        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            // 这里设置 64 ，是一个绰绰有余的数字，可以满足结点数量为 2^64 这么多的单链表的排序
            ListNode[] counter = new ListNode[64];
            ListNode curNode = head;
            // 遍历到的最大的 counter 数组的索引
            int maxIndex = 0;
            while (curNode != null) {
                // 先把当前元素暂存起来，马上我们就要把它放到 counter 数组合适的位置上
                ListNode carryNode = curNode;
                // curNode 指针马上后移，方便下次处理
                curNode = curNode.next;
                // 拿出的节点就和原来的链表没有关系了，我们在 counter 数组中完成排序，所以要切断它和原链表的关系
                carryNode.next = null;
                // 尝试从 counter 数组 0 号索引开始放置
                int i = 0;
                // 只要非空当前位置非空，就进行一次 merge，merge 以后尝试放到下一格，如果下一格非空就继续合并
                // 合并以后再尝试放到下一格，直到下一格为空，直接放在那个为空的下一格就好
                while (counter[i] != null) {
                    ListNode newMergeNode = mergeOfTwoSortedListNode(carryNode, counter[i]);
                    counter[i] = null;
                    i++;
                    carryNode = newMergeNode;
                }
                // 遇到了空，就把 carryNode 放在数组的这个位置上
                counter[i] = carryNode;
                // 记录最多使用到 counter 数组的第几位，最后合并的时候要用上
                if (i > maxIndex) {
                    maxIndex = i;
                }
            }
            // 遍历整个 count 数组，将它们全部归并，这个操作就和归并 n 个有序单链表是一样的了，我们这里采用两两归并
            // 还可以采用 LeetCode 第 23 题的办法完成这一步
            // 参考：https://liweiwei1419.github.io/leetcode-solution/leetcode-0023-merge-k-sorted-lists/
            ListNode res = null;
            for (int i = 0; i <= maxIndex; i++) {
                if (counter[i] != null) {
                    res = mergeOfTwoSortedListNode(res, counter[i]);
                }
            }
            return res;
        }

        /**
         * 归并两个已经排好序的单链表，是我们非常熟悉的操作了，可以递归完成，也可以穿针引线，这里我们递归完成
         *
         * @param l1 顺序存放的单链表1
         * @param l2 顺序存放的单链表2
         * @return 合并以后的单链表
         */
        private ListNode mergeOfTwoSortedListNode(ListNode l1, ListNode l2) {
            if (l1 == null) {
                return l2;
            }
            if (l2 == null) {
                return l1;
            }
            if (l1.val < l2.val) {
                l1.next = mergeOfTwoSortedListNode(l1.next, l2);
                return l1;
            } else {
                l2.next = mergeOfTwoSortedListNode(l1, l2.next);
                return l2;
            }
        }


    }

    @Test
    public void test1() {
        int[] nums = new int[]{9, 8, 2, 7, 6, 5, 4, 3, 1};
        ListNode head = new ListNode(nums);
        Solution1 solution1 = new Solution1();
        ListNode sortList = solution1.sortList(head);
        System.out.println(sortList);
    }


    /**
     * “自顶向下” 的 归并排序”
     */
    class Solution2 {
        //python 代码
/**
 * class ListNode:
 *     def __init__(self, x):
 *         self.val = x
 *         self.next = None
 *
 *
 * class Solution:
 *     def sortList(self, head: ListNode) -> ListNode:
 *         if head is None or head.next is None:
 *             return head
 *
 *         # 找到中点
 *         slow = head
 *         fast = head
 *         # 使用这种方式，当结点个数为 2 个时候，slow 在左结点
 *         # 不会导致死循环
 *         while fast.next and fast.next.next:
 *             slow = slow.next
 *             fast = fast.next.next
 *         head2 = slow.next
 *         slow.next = None
 *
 *         lnode = self.sortList(head)
 *         rnode = self.sortList(head2)
 *
 *         return self.__merge_two_sorted_list(lnode, rnode)
 *
 *     def __merge_two_sorted_list(self, head1, head2):
 *         if head1 is None:
 *             return head2
 *         if head2 is None:
 *             return head1
 *
 *         if head1.val < head2.val:
 *             head1.next = self.__merge_two_sorted_list(head1.next, head2)
 *             return head1
 *         else:
 *             head2.next = self.__merge_two_sorted_list(head1, head2.next)
 *             return head2
 *
 */

/**
 * class ListNode:
 *     def __init__(self, x):
 *         self.val = x
 *         self.next = None
 *
 *
 * # 这里有个小陷阱，如果遇到问题，不要着急，代码调试就好了
 *
 * class Solution:
 *     def sortList(self, head: ListNode) -> ListNode:
 *         if head is None or head.next is None:
 *             return head
 *         # 找到中点
 *         slow = head
 *         fast = head
 *         while fast and fast.next:
 *             # 这里要保存一下前一个指针
 *             p = slow
 *             slow = slow.next
 *             fast = fast.next.next
 *
 *         p.next = None
 *         # print_node_list(head)
 *         # print_node_list(head2)
 *         lnode = self.sortList(head)
 *         rnode = self.sortList(slow)
 *         return self.__merge_two_sorted_list(lnode, rnode)
 *
 *     def __merge_two_sorted_list(self, head1, head2):
 *         if head1 is None:
 *             return head2
 *         if head2 is None:
 *             return head1
 *
 *         if head1.val < head2.val:
 *             head1.next = self.__merge_two_sorted_list(head1.next, head2)
 *             return head1
 *         else:
 *             head2.next = self.__merge_two_sorted_list(head1, head2.next)
 *             return head2
 *
 *
 * def create_node_list(arr):
 *     head = ListNode(arr[0])
 *     cur = head
 *     for i in range(1, len(arr)):
 *         cur.next = ListNode(arr[i])
 *         cur = cur.next
 *     return head
 *
 *
 * def print_node_list(head):
 *     while head:
 *         print(head.val, '->', end=' ')
 *         head = head.next
 *     print('NULL')
 *
 *
 * if __name__ == '__main__':
 *     arr = [4, 2, 1, 3]
 *     head = create_node_list(arr)
 *     print_node_list(head)
 *
 *     solution = Solution()
 *     result = solution.sortList(head)
 *     print_node_list(result)
 *
 */

/**
 * class ListNode:
 *     def __init__(self, x):
 *         self.val = x
 *         self.next = None
 *
 *
 * # 这里有个小陷阱，如果遇到问题，不要着急，代码调试就好了
 *
 * class Solution:
 *     def sortList(self, head: ListNode) -> ListNode:
 *         if head is None or head.next is None:
 *             return head
 *         # 玄机在这里，如果非要用 while fast and fast.next:
 *         # 让快指针先走一步，以避免死循环
 *         slow = head
 *         fast = head.next
 *         while fast and fast.next:
 *             slow = slow.next
 *             fast = fast.next.next
 *
 *         new_head = slow.next
 *         slow.next = None
 *
 *         lnode = self.sortList(head)
 *         rnode = self.sortList(new_head)
 *         return self.__merge_two_sorted_list(lnode, rnode)
 *
 *     def __merge_two_sorted_list(self, head1, head2):
 *         if head1 is None:
 *             return head2
 *         if head2 is None:
 *             return head1
 *
 *         if head1.val < head2.val:
 *             head1.next = self.__merge_two_sorted_list(head1.next, head2)
 *             return head1
 *         else:
 *             head2.next = self.__merge_two_sorted_list(head1, head2.next)
 *             return head2
 *
 *
 * def create_node_list(arr):
 *     head = ListNode(arr[0])
 *     cur = head
 *     for i in range(1, len(arr)):
 *         cur.next = ListNode(arr[i])
 *         cur = cur.next
 *     return head
 *
 *
 * def print_node_list(head):
 *     while head:
 *         print(head.val, '->', end=' ')
 *         head = head.next
 *     print('NULL')
 *
 *
 * if __name__ == '__main__':
 *     arr = [4, 2, 1, 3]
 *     head = create_node_list(arr)
 *     print_node_list(head)
 *
 *     solution = Solution()
 *     result = solution.sortList(head)
 *     print_node_list(result)
 *
 */
    }

}
