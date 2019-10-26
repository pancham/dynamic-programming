package com.interview.dynamic;

/**
 * https://algorithmsandme.com/balanced-partition-problem/
 * https://www.geeksforgeeks.org/partition-a-set-into-two-subsets-such-that-the-difference-of-subset-sums-is-minimum/
 * Given a set of integers, partition those integers into two parts where the difference between the two parts is minimum.
 * This problem is known as balanced partition problem. For example, array A = {1,7,4,11},
 * two subsets can be: {1,11} and {7,4}, two have a difference of 1, which is the minimum difference we can get
 * by splitting this array.
 */
public class BalancedPartition {
    public static int findBalancePartition(int[] a) {
        // Calculate sum of all the elements in set
        int S = 0;
        for (int i = 0; i < a.length; i++)
            S += a[i];

        boolean T[][] = new boolean[a.length + 1][S + 1];
        int TI[][] = new int[a.length + 1][S + 1];

        /* Initialize first column as true.
            0 sum is possible with all elements.
        */
        for (int i = 0; i <= a.length; i++)
            T[i][0] = true;

        /*  Initialize top row, except dp[0][0],
            as false. With 0 elements, no other
            sum except 0 is possible
        */
        for (int i = 1; i <= S; i++)
            T[0][i] = false;


        for (int i = 1; i <= a.length; i++) {
            for (int j = 1; j <= S; j++) {
                // If ith element is excluded
                T[i][j] = T[i - 1][j];

                // If ith element is included
                if (a[i - 1] <= j)
                    T[i][j] |= T[i - 1][j - a[i - 1]];
            }
        }

        for(int i = 0; i< T.length; i++){
            for(int j = 0; j< S; j++)
                System.out.print(T[i][j] ? "1 " : "0 ");
            System.out.println();
        }

        // Initialize difference of two sums.
        int diff = Integer.MAX_VALUE;

        // The two halves in last row (1..S/2) and (S/2+1..n) in "T" matrix are identical
        // TODO: Why are htey identical?
        for (int j = S / 2; j >= 0; j--) {
            // Find the
            if (T[a.length][j] == true) {
                diff = S - 2 * j;
                break;
            }
        }
        return diff;
    }

    public static void main(String[] args) {
//        int arr[] = {3, 1, 4, 2, 2, 1};
        int arr[] = {6,2, 3, 4};
//        int arr[] = {5, 4, 1, 3};
        System.out.println("The minimum difference between 2 sets is "
                + findBalancePartition(arr));
    }
}
