package com.interview.dynamic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Date 09/23/2014
 * @author Tushar Roy
 *
 * Given an array of non negative numbers and a total, is there subset of numbers in this array which adds up
 * to given total. Another variation is given an array is it possible to split it up into 2 equal
 * sum partitions. Partition need not be equal sized. Just equal sum.
 *
 * Time complexity - O(input.size * total_sum)
 * Space complexity - O(input.size*total_sum)
 *
 * Youtube video - https://youtu.be/s6FhG--P7z0
 */
public class SubsetSum {

    public boolean subsetSum(int input[], int total) {

        boolean T[][] = new boolean[input.length + 1][total + 1];
        for (int i = 0; i <= input.length; i++) {
            T[i][0] = true;
        }
//        Trace complete path of a journey
        for (int i = 1; i <= input.length; i++) {
            for (int j = 1; j <= total; j++) {
                if (j - input[i - 1] >= 0) {
                    // If total "j" can be made with i - 1 coins
                    // OR
                    // if total of (j - input[i - 1]) coins can be formed using 1 less coin at input[i - 1] (discounting current one)
                    T[i][j] = T[i - 1][j] || T[i - 1][j - input[i - 1]];
                } else {
                    // If total "j" can be made with i - 1 coins
                    T[i][j] = T[i-1][j];
                }
            }
        }

        List<Integer> subset = new ArrayList<Integer>();

        int i = input.length, j = total;

        do  {
             if ((j - input[i - 1]) >= 0 && T[i][j] && T[i - 1][j - input[i - 1]]) {
                subset.add(input[i - 1]);

                j -= input[i - 1];
                 i -= 1;
            } else if (T[i][j] == T[i - 1][j]) {
                 i -= 1;
//                 j -= 1;
             }
        } while(i > 0 && j > 0);

        System.out.printf("Subset %s", Arrays.toString(subset.toArray()));

        return T[input.length][total];

    }

    /**
     * Given a set of m distinct positive integers and a value ‘N’. The problem is to count the total number of ways we
     * can form ‘N’ by doing sum of the array elements. Repetitions and different arrangements are allowed.
     * @param input
     * @param total
     * @return
     */
    public int subsetSumWithRepetitions(int[] input, int total)
    {
        int count[] = new int[total + 1];

        // base case
        count[0] = 1;

        // {1, 5, 6}
        // count ways for all values up
        // to 'N' and store the result
        for (int i = 1; i <= total; i++) {
            for (int j = 0; j < input.length; j++) {

                if (i >= input[j]) {
                    count[i] += count[i - input[j]];
                }
            }
        }

        print(count, input, total, new ArrayList<Integer>());

        // required number of ways
        return count[total];

    }

    private int printCounter = 0;
    void print(int[] count, int[] input, int pos, ArrayList<Integer> parents) {
        if (count[pos] == 0 || pos <= 0) {
            System.out.println(Arrays.toString(parents.toArray()));
            System.out.printf("Print counter: %d %n", ++printCounter);
            return;
        }

        for(int j = 0; j < input.length; j++) {
            if (pos >= input[j]) {
                parents.add(input[j]);
                print(count, input, pos - input[j], parents);
                if (parents.size() > 0) {
                    parents.remove(parents.size() - 1);
                }
            }
        }
    }

    public boolean partition(int arr[]) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }

        if (sum % 2 != 0) {
            return false;
        }
        sum = sum / 2;
        boolean[][] T = new boolean[arr.length + 1][sum + 1];

        for (int i = 0; i <= arr.length; i++) {
            T[i][0] = true;
        }

        for (int i = 1; i <= arr.length; i++) {
            for (int j = 1; j <= sum; j++) {
                if (j - arr[i - 1] >= 0) {
                    T[i][j] = T[i - 1][j - arr[i - 1]] || T[i - 1][j];
                } else {
                    T[i][j] = T[i-1][j];
                }
            }
        }
        return T[arr.length][sum];
    }

    public static void main(String args[]) {
        SubsetSum ss = new SubsetSum();
        int arr[] = {1, 3, 5, 5, 2, 1, 1, 6};
//        System.out.println(ss.partition(arr));

//        int arr1[] = {1, 5, 6};
//        int arr1[] = {2, 3, 7, 8};
//        int arr1[] = {1, 3, 5, 5, 2, 1, 1, 6};
        int arr1[] = {1, 3, 5, 2, 6};
//        System.out.println(ss.subsetSum(arr1, 11));
        System.out.println();
        System.out.println(ss.subsetSumWithRepetitions(arr1, 11));

    }
}
