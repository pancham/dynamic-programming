package com.interview.dynamic;

/**
 * Find length of longest substring of a given string of digits, such that sum of digits in the first half and second half
 * of the substring is same.
 * For example, Input: “142124” Output: 6 The whole string is answer,
 * because, sum of first 3 digits = sum of last 3 digits (1+4+2 = 1+2+4).
 *
 * Input: “9430723” Output: 4 Longest substring with first and second half having equal sum is “4307”.
 */
public class MaxSumSubstringEqualHalves {
    /* sum[i][j] = Sum of digits from i to j
     * if i>j, then value holds no meaning.
     */

//    int maxSubStringLengthDP(char *str){
//        int sum[N][N];
//        int n = strlen(str);
//        int maxLen = 0;
//
//        // Lower diagonal of matrix is not used (i>j)
//        // Filling diagonal values.
//        for (int i =0; i<n; i++)
//            sum[i][i] = str[i]-’0’;
//
//        for (int len=2; len<=n; len++)
//        {
//            // Pick i and j for current substring
//            for (int i=0; i<n-len+1; i++)
//            {
//                int j = i+len-1;
//                int k = len/2;
//                // Calculate value of sum[i][j]
//                sum[i][j] = sum[i][j-k] +sum[j-k+1][j];
//
//                // Update if ‘len’ is even, left and right
//                // sums are same and len is more than maxLen
//                if (len%2 == 0 && sum[i][j-k] ==sum[(j-k+1)][j]
//                        && len > maxLen)
//                    maxLen = len;
//            }
//        }
//        return maxLen;
//    }
}