package com.interview.dynamic;

/**
 * http://www.geeksforgeeks.org/dynamic-programming-set-13-cutting-a-rod/
 */
public class CuttingRod {

    public int maxValue(int price[]){
        int max[] = new int[price.length+1];
        int sizes[] = new int[price.length + 1];
        for(int i=1; i <= price.length; i++){
            for(int j=i; j <= price.length; j++){
                if (max[j] < max[j-i] + price[i-1]) {
                    max[j] = max[j-i] + price[i-1];
                    sizes[j] = i;
                }
            }
        }

        System.out.println("Cut sizes: ");
        int j = price.length;
        do {
//            if (sizes[j] == 0) break;
            System.out.printf("%d ", sizes[j]);
            j -= sizes[j];
        } while (j > 0);
        System.out.println();
        return max[price.length];
    }
    
    public int maxValue1(int price[]){
        int max[] = new int[price.length+1];
        for(int i=1; i <= price.length; i++){
            max[i] = price[i-1];
        }
        for(int i=1 ; i <= price.length; i++){
            for(int j=1; j < i ; j++){
                max[i] = Math.max(max[i], max[i-j] + max[j]);
            }
        }
        return max[price.length];
    }
    
    public int recursiveMaxValue(int price[],int len){
        if(len <= 0){
            return 0;
        }
        int maxValue = 0;
        for(int i=0; i < len;i++){
            int val = price[i] + recursiveMaxValue(price, len-i-1);
            if(maxValue < val){
                maxValue = val;
            }
        }
        return maxValue;
    }
    public static void main(String args[]){
        CuttingRod cr =new CuttingRod();
        int[] price = {3,5,8,9,10,20,22,25};
//        int[] price = {3,5,8,9,30,20,22,25};
        int r = cr.maxValue(price);
        System.out.println(r);
    }
}
