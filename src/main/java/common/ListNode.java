package common;

/**
 * Created by Administrator on 2017/11/24.
 */

/**
 * Definition for singly-linked list.
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    public ListNode(int x, ListNode next) {
        val = x;
        this.next = next;
    }

    public ListNode(int[] nums) {
        if (null == nums || nums.length < 1) return;
        this.val = nums[0];
        ListNode tmp = this;
        for (int i = 1; i < nums.length; i++) {
            ListNode nxt = new ListNode(nums[i]);
            tmp.next = nxt;
            tmp = nxt;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(val);
        ListNode tmp = next;
        while (tmp != null) {
            sb.append("->").append(tmp.val);
            tmp = tmp.next;
        }
        return sb.toString();
    }
}

