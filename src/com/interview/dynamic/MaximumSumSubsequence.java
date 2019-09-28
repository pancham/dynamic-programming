package com.interview.dynamic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Problem Statement:
 *
 * Given an array of n positive integers. Write a program to find the sum of maximum sum subsequence of the given array
 * such that the integers in the subsequence are in increasing order.
 *
 *
 * Video: https://youtu.be/99ssGWhLPUE
 *
 * Reference:
 * http://www.geeksforgeeks.org/dynamic-programming-set-14-maximum-sum-increasing-subsequence/
 */
public class MaximumSumSubsequence {

    public int maxSum(int arr[]){
        // This array maintains max increasing sum till ith postition
        int T[] = new int[arr.length];
        int S[] = new int[arr.length];

        for (int i = 0; i < T.length; i++) {
            T[i] = arr[i];
            S[i] = -1;
        }

        for(int i=1; i < T.length; i++){
            for(int j = 0; j < i; j++){
                // If value at j is less than value at i, it is an increasing sequence,
                // So - look the sum may also form an increasing sum subsequence, hence take max of t[i] and
                // max till j - t[j] + value at i
                if(arr[j] < arr[i]){
                    if (T[j] + arr[i] > T[i]) {
                        T[i] = T[j] + arr[i];
                        S[i] = j;
                    }
                }
            }
        }

        int max = T[0];
        int maxIndex = -1;
        for (int i=1; i < T.length; i++){
            if(T[i] > max){
                max = T[i];
                maxIndex = i;
            }
        }

        ArrayList<Integer> path = new ArrayList<Integer>();
        for (int i = maxIndex; i >=0;) {
            path.add(arr[i]);

            i = S[i];

//            if (S[i] == -1)
//                break;
        }
        Collections.reverse(path);
        System.out.println(Arrays.toString(path.toArray()));

        return max;
    }

    public static void main(String args[]){
        MaximumSumSubsequence mss = new MaximumSumSubsequence();
        int arr[] = {1, 101, 10, 2, 3, 100,4};
        int r = mss.maxSum(arr);
        System.out.print(r);
    }
}
