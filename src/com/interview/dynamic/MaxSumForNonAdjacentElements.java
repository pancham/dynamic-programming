package com.interview.dynamic;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Date 11/03/2016
 * @author Tushar Roy
 *
 * Find maximum sum for non adjacent elements.
 * Variation is finding maximum sum non adjacent elements assuming its a circular array.
 * So first element cannot be with last element.
 *
 * Time complexity O(n)
 * Space complexity O(1)
 *
 * https://leetcode.com/problems/house-robber/
 * https://leetcode.com/problems/house-robber-ii/
 */
public class MaxSumForNonAdjacentElements {

    /**
     * Fast DP solution.
     */
    public int maxSum(int arr[]) {
        // Holds max sum possible excluding previous element, this can be added to current element to find probable next
        // maximum since previous elements is guaranteed to be not included. Note that this may have not been
        // included in multiple previous elements
        int maxSumExcludingPrevious = 0;
        int maxNonAdjacentSumSoFar = arr[0];

        int[] col1 = new int[arr.length];
        int[] col2 = new int[arr.length];

        col1[0] = arr[0];
        col2[0] = -1;
        boolean isNextCol1 = false;
        int lastIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            int temp = maxNonAdjacentSumSoFar;
            col1[i] = -1;
            col2[i] = -1;
            // { 10, 12, 9, 13, 10};
            if (maxSumExcludingPrevious + arr[i] > maxNonAdjacentSumSoFar) {
                maxNonAdjacentSumSoFar = maxSumExcludingPrevious + arr[i];
//                System.out.printf("Diff %d%n", i - lastIndex);
                if (i - lastIndex > 1) {
                    if (isNextCol1) {
                        copy(col2, col1);
                    } else {
                        copy(col1, col2);
                    }
                }

                if (isNextCol1) {
                    col1[i] = arr[i];
                } else {
                    col2[i] = arr[i];
                }
                isNextCol1 = !isNextCol1;
//                System.out.printf("isNextCol1 %b%n", isNextCol1);
//                print(col1, col2);
                lastIndex = i;

            }

            maxSumExcludingPrevious = temp;

        }

        int[] col = isNextCol1 ? col2 : col1;
        for (int i = 0; i < col.length; i++) {
            if (col[i] != -1) {
                System.out.printf("%d ", col[i]);
            }
        }
        System.out.println();

        return maxNonAdjacentSumSoFar;
    }

    private void print(int[] col1, int[] col2) {
        System.out.println("col1");
        for (int i = 0; i < col1.length; i++) {
//            if (col1[i] != -1) {
            System.out.printf("%d ", col1[i]);
//            }
        }
        System.out.println();
        System.out.println("col2");
        for (int i = 0; i < col2.length; i++) {
//            if (col2[i] != -1) {
                System.out.printf("%d ", col2[i]);
//            }
        }

        System.out.println();
    }

    private void copy(int[] from, int[] to) {
        for (int i = 0; i < from.length; i++) {
            to[i] = from[i];
        }
    }

    /**
     * Recursive slow solution.
     */
    public int maxSum(int arr[], int index) {
        if (index == 0) {
            return arr[0];
        } else if (index == 1) {
            return Math.max(arr[0], arr[1]);
        }
        return Math.max(this.maxSum(arr, index - 2) + arr[index], this.maxSum(arr, index - 1));
    }

    /**
     * Find maximum sum from left to right ignoring first element.
     * Find maximum sum from right to left ignoring last element.
     * Maximum of two will be the answer. It gurantees that both first and last element
     * will be not selected together.
     */
    public int maxSumCircularArray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int with = nums[1];
        int without = 0;
        for (int i = 2; i < nums.length; i++) {
            int newWith = without + nums[i];
            without = with;
            with = Math.max(with, newWith);
        }

        int with1 = nums[nums.length - 2];
        int without1 = 0;
        for (int i = nums.length - 3; i >= 0; i--) {
            int newWith1 = without1 + nums[i];
            without1 = with1;
            with1 = Math.max(with1, newWith1);
        }
        return Math.max(with, with1);
    }

    public static void main(String args[]) {
        MaxSumForNonAdjacentElements msn = new MaxSumForNonAdjacentElements();
//        int arr[] = { 2, 10, 13, 4, 2, 15, 10 };
//        int arr[] = { 2, 1, 13, 4, 2, 15, 10 };
//        int arr[] = { 2, 1, 2, 4, 2, 15, 10 };
//        int arr[] = { 2, 1, 2, 4, 2, 0, 10 };
//        int arr[] = { 2, 1, 2, 4, 2, 0, 10, 12, 9, 13 };
//        int arr[] = { 10, 12, 9, 13, 10};
        int arr[] = { 2, 1, 2, 4, 2, 0, 10, 12, 9, 13, 5, 6, 19, 40 };
        System.out.println(msn.maxSum(arr));
//        System.out.println(msn.maxSum(arr, arr.length-1));

    }
}
