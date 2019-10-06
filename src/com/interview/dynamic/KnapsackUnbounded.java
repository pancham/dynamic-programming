package com.interview.dynamic;

// Java program to find maximum achievable
// value with a knapsack of weight W and
// multiple instances allowed.
// https://www.geeksforgeeks.org/unbounded-knapsack-repetition-items-allowed/

import java.util.ArrayList;
import java.util.Arrays;

public class KnapsackUnbounded {

    private static int max(int i, int j) {
        return (i > j) ? i : j;
    }

    // Returns the maximum value with knapsack
    // of W capacity
    private static int knapsackUnbounded(int total,
                                         int[] val, int[] wt) {

        // dp[i] is going to store maximum value
        // with knapsack capacity i.
        int dp[] = new int[total + 1];
        int weights[] = new int[total + 1];
//        ArrayList<ArrayList<Integer>> weights = new ArrayList<ArrayList<Integer>>(dp.length);
//
//        for (int i = 0; i < weights.size(); i++) {
//            weights.add(new ArrayList<Integer>());
//        }

        // Fill dp[] using above recursive formula
        for(int i = 0; i <= total; i++){
            for(int j = 0; j < wt.length   ; j++){
                if(wt[j] <= i) {
                    if (dp[i - wt[j]] + val[j] > dp[i]) {
                        dp[i] = dp[i - wt[j]] + val[j];

                        weights[i] = wt[j];
                    }
                }
            }
        }

        System.out.println(Arrays.toString(dp));
        System.out.println(Arrays.toString(weights));

        System.out.println("Weights to carry: ");

        int i = weights.length - 1;
        do {
            System.out.printf("%d ", weights[i]);
            i -= weights[i];
        } while (i > 0);
        System.out.println();

        return dp[total];
    }

    // Driver program
    public static void main(String[] args) {
        int W = 100;
        int val[] = {10, 30, 20};
        int wt[] = {5, 10, 15};

//        int W = 8;
//        int val[] = {10, 30, 20};
//        int wt[] = {1, 2, 3};

        int n = val.length;
        System.out.println(knapsackUnbounded(W, val, wt));
    }
}
// This code is contributed by Aditya Kumar
