package com.interview.dynamic;

// Java program to find number of ways to get sum 'x' with 'n'
// dice where every dice has 'm' faces
// https://www.geeksforgeeks.org/dice-throw-dp-30/

import java.util.*;
import java.lang.*;
import java.io.*;

class DiceThrow {
    /* The main function that returns the number of ways to get sum 'x' with 'n' dice and 'm' with m faces. */
    public static long findWays(int m, int n, int x) {

        /* Create a table to store the results of subproblems.
        One extra row and column are used for simplicity
        (Number of dice is directly used as row index and sum is directly used as column index).
        The entries in 0th row and 0th column are never used. */
        long[][] table = new long[n + 1][x + 1];
        int[][][] path = new int[n + 1][x + 1][m];


        /* Table entries for only one dice */
        for (int j = 1; j <= m && j <= x; j++)
            table[1][j] = 1;

        /* Fill rest of the entries in table using recursive relation
        i: number of dice, j: sum */
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= x; j++) {
                for (int k = 1; k < j && k <= m; k++)
                    table[i][j] += table[i - 1][j - k];
//                    path[i][j][k-1] = j - k;
            }
        }

		/* Uncomment these lines to see content of table */
		for(int i = 0; i< n+1; i++){
			for(int j = 0; j< x+1; j++)
				System.out.print(table[i][j] + " ");
			System.out.println();
		}

        System.out.println();
        printDiceCombinations(table,  m,table.length - 1, table[0].length - 1, -1);
        System.out.println();

        return table[n][x];
    }

    public static void printDiceCombinations2(long[][] table, int startX, int startY) {


        if (startX == 0 || startY == 0) {
            System.out.println();
            return;
        }
//        System.out.printf("%d ", table[startX][startY]);
        for (int j = startY - 1; j > 0; j--) {
            int x = startX;
            int y = j;

            System.out.printf("%d ", startY - j);
            do {
                if (x == 1 || y == 1) {
                    System.out.println(y);
                    x = 0;
                }
                x -= 1;
                y -= j;
            } while (x >= 1 && y >= 1);
//            printDiceCombinations(table, startX - 1, startY - j);
        }
    }

    public static void printDiceCombinations(long[][] table, int m, int startX, int startY, int parent) {

        if (table[startX][startY] == 0) {
            return;
        }
        if (startX == 1 || startY == 1) {
            if (parent != -1) {
                System.out.printf("%d ", parent);
            }
            System.out.println(startY);
            return;
        }

        for (int j = startY - 1; j > 0; j--) {
            if (table[startX - 1][j] > 0 && (startY - j) <= m) {
                if (parent != -1) {
                    System.out.printf("%d ", parent);
                }
                printDiceCombinations(table, m, startX - 1, j, startY - j);
            }
        }
    }

    private static void print(int[][] table, int start, int start2) {
        System.out.printf("%d ", start - start2);

    }

    public static long findWays2(int m, int n, int x) {
        // Create a table to store results of subproblems.  One extra
        // row and column are used for simpilicity (Number of dice
        // is directly used as row index and sum is directly used
        // as column index).  The entries in 0th row and 0th column
        // are never used.
        long mem[][] = new long[n + 1][x + 1];
        // Table entries for no dices
        // If you do not have any data, then the value must be 0, so the result is 1
        mem[0][0] = 1;
        // Iterate over dices
        for(int i=1; i<=n; i++) {
            // Iterate over sum
            for(int j=i; j<=x; j++) {
                // The result is obtained in two ways, pin the current dice and spending 1 of the value,
                // so we have mem[i-1][j-1] remaining combinations, to find the remaining combinations we
                // would have to pin the values ??above 1 then we use mem[i][j-1] to sum all combinations
                // that pin the remaining j-1's. But there is a way, when "j-f-1> = 0" we would be adding
                // extra combinations, so we remove the combinations that only pin the extrapolated dice face and
                // subtract the extrapolated combinations.
                mem[i][j] = mem[i][j-1] + mem[i-1][j-1];
                if(j-m-1 >= 0)
                    mem[i][j] -= mem[i-1][j-m-1];
            }
        }

        for(int i = 0; i< n+1; i++){
            for(int j = 0; j< x+1; j++)
                System.out.print(mem[i][j] + " ");
            System.out.println();
        }
        return mem[n][x];
    }

    // Driver Code
    public static void main(String[] args) {
//        System.out.println(findWays(4, 2, 1));
//        System.out.println(findWays(2, 2, 3));
        System.out.println(findWays(6, 3, 8));
//        System.out.println(findWays(4, 2, 5));
//        System.out.println(findWays(4, 3, 5));
//        System.out.println(findWays(2, 3, 5));
        System.out.println();
//        System.out.println(findWays2(4, 3, 5));
    }
}

