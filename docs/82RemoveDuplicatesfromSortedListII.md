* 遍历||递归
------
* 思路一: 迭代 快慢指针,用快指针跳过那些有重复数组,慢指针负责和快指针拼接!
* 思路二:递归

```
Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

Example 1:

Input: 1->2->3->3->4->4->5
Output: 1->2->5
Example 2:

Input: 1->1->1->2->3
Output: 2->3

```