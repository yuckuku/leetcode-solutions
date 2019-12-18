* DP
* 数学：
拉格朗日四平方和定理： 每个正整数均可表示成不超过四个整数的平方之和
重要的推论：

1.数 n 如果只能表示成四个整数的平方和，不能表示成更少的数的平方之和，必定满足 4^a(8b+7)
2.如果 n%4==0，k=n/4，n 和 k 可由相同个数的整数表示

如何利用推论求一个正整数最少需要多少个数的平方和表示：

先判断这个数是否满足  4^a(8b+7)，如果满足，那么这个数就至少需要 4 个数的平方和表示。
如果不满足，再在上面除以 4 之后的结果上暴力尝试只需要 1 个数就能表示和只需要 2 个数就能表示的情况。
如果还不满足，那么就只需要 3 个数就能表示。

```
Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

Example 1:
Input: n = 12
Output: 3 
Explanation: 12 = 4 + 4 + 4.

Example 2:
Input: n = 13
Output: 2
Explanation: 13 = 4 + 9.

```