package com.interview.dynamic;

/**
 http://www.geeksforgeeks.org/dynamic-programming-set-4-longest-common-subsequence/
 */
public class LongestCommonSubsequence {

    public int lcs(char str1[],char str2[],int len1, int len2){
        
        if(len1 == str1.length || len2 == str2.length){
            return 0;
        }
        if(str1[len1] == str2[len2]){
            return 1 + lcs(str1,str2,len1+1,len2+1);
        }
        else{
            return Math.max(lcs(str1,str2,len1+1,len2),lcs(str1,str2,len1,len2+1));
        }
    }

    public int lcsDynamic(char str1[],char str2[]){
    
        int temp[][] = new int[str1.length + 1][str2.length + 1];
        int max = 0;
        for(int i=1; i < temp.length; i++){
            for(int j=1; j < temp[i].length; j++){
                if(str1[i-1] == str2[j-1]) {
                    temp[i][j] = temp[i - 1][j - 1] + 1;
                }
                else
                {
                    temp[i][j] = Math.max(temp[i][j-1],temp[i-1][j]);
                }
                if(temp[i][j] > max){
                    max = temp[i][j];
                }
            }
        }

        int i = str1.length, j = str2.length;
        StringBuffer buf = new StringBuffer();
        do {
            if (temp[i][j] == temp[i - 1][j - 1] + 1) {
                buf.append(str1[i - 1]);
                i += -1;
                j += -1;
            } else if (temp[i][j - 1] > temp[i - 1][j]) {
                j += -1;
            } else {
                i += -1;
            }

        } while(i > 0 && j > 0 );

        System.out.println(String.format("Longest common subsequence: %s", buf.reverse().toString()));

        return max;
    
    }
    
    public static void main(String args[]){
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();
        String str1 = "ABCDGHLQR";
        String str2 = "AEDPHR";
        
        int result = lcs.lcsDynamic(str1.toCharArray(), str2.toCharArray());
        System.out.println(result);
    }
    
    
    
}
