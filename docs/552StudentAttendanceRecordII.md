

```
Given a positive integer n, return the number of all possible attendance records with length n, 
which will be regarded as rewardable. The answer may be very large, return it after mod 109 + 7.

A student attendance record is a string that only contains the following three characters:

'A' : Absent.
'L' : Late.
'P' : Present.
A record is regarded as rewardable if it doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).

Example 1:
Input: n = 2
Output: 8 
Explanation:
There are 8 records with length 2 will be regarded as rewardable:
"PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL"
Only "AA" won't be regarded as rewardable owing to more than one absent times. 
Note: The value of n won't exceed 100,000.

```

*  DP
  如果没有A，DP[n+3]=DP[n+2]+DP[n+1]+DP[n]
  先把没有A的计算出来，然后用加枝的方法，把A的情况加上
  数学归纳法:DP[n]=A[n]+(A[n-1]+A[1]*A[n-2]+A[2]*A[n-3]+...+A[n-1]*A[0])
  
  
