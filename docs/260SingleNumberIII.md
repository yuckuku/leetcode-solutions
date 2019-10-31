* 遍历替换(不通用，如果不同的为n个呢)   
  统计数目，空间复杂度高
* 快慢指针？  (不可取)
---
* 位运算   
* a & (-a) 可以获得a最低的非0位 ,比如a的二进制是 ??????10000，取反就是??????01111，加1就是??????10000。前面?的部分是和原来a相反的，相与必然都是0，所以最后整体相与的结果就是00000010000。

```
Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. 
Find the two elements that appear only once.

Example:

Input:  [1,2,1,3,2,5]
Output: [3,5]
Note:

The order of the result is not important. So in the above example, [5, 3] is also correct.
Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
```