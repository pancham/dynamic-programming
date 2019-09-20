package com.interview.dynamic;

/**
 * http://www.geeksforgeeks.org/dynamic-programming-set-11-egg-dropping-puzzle/
 */
public class EggDropping {

    public int calculate(int eggs, int floors){

        int T[][] = new int[eggs+1][floors+1];
        int c =0;
        // With one egg, it will take as many floor as available
        for(int i=0; i <= floors; i++){
            T[1][i] = i;
        }

        /**
         * At any given number of eggs (e) and floor (f), start from 1st floor upto the number of floors.
         * Drop egg from each floor, say k, there are 2 outcomes:
         *      Egg breaks: need to try (k-1) with (e-1) eggs
         *      Egg does not break: need to try (f-k) floors with e eggs
         *      Max of above 2 will give the number of attempts it will take to find out the floor from which egg will break
         *
         * Repeating above step from floor 1 upto f (k), the minimum of all the attempts will give the minimum number of attempts
         *
         */
        for(int e = 2; e <= eggs; e++){
            for(int f = 1; f <=floors; f++){
                T[e][f] = Integer.MAX_VALUE;
                for(int k = 1; k <=f ; k++){
                    c = 1 + Math.max(T[e-1][k-1], T[e][f-k]);  // 1 is for current attempt
                    if(c < T[e][f]){
                        T[e][f] = c;
                    }
                }
            }
        }
        return T[eggs][floors];
    }

    public int calculateRecursive(int eggs, int floors){
        if(eggs == 1){
            return floors;
        }
        if(floors == 0){
            return 0;
        }
        int min = 1000;
        for(int i=1; i <= floors; i++){
            int val = 1 + Math.max(calculateRecursive(eggs-1, i-1),calculateRecursive(eggs, floors-i));
            if(val < min){
                min = val;
            }
        }
        return min;
    }

    public static void main(String args[]){
        EggDropping ed = new EggDropping();
        int r = ed.calculate(3,100);
        System.out.println(r);
    }
}
