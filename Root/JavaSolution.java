

/*

Idea: two strings text and pattern, print/return all indices where
pattern is fully found as a continuous stream of characters that make 
up it as a substring

Example:

text:
xbananabananax

pattern:
banana

should print first index of banana, and second


edge cases:
automatically return 0 if length(pattern) > length(text)

pattern's characters in the middle make the start of a new pattern occurence

brute force approach will cost O(T * P) where:
T -> text length
P -> pattern length

Why?
every character in T will be compared with the entirety of P


*/

import java.util.*;

public class JavaSolution {
    public static void main(String[] args) {
        String text = "abananabananaa", pattern = "banana";
        String text2 = "AABAACAADAABAABA", pattern2 = "AABA";

        List<Integer> testBrute = bruteForce(text2, pattern2);

        // for(int index : testBrute) System.out.println(index);

        // O(N+M), case sensitive
        // builds LPS array to avoid re-calculations in pattern
        // that can be avoided by doing a pre-processing step of calculating
        // longest proper prefix suffix array, which aims to know
        // at each index how much of the characters we can assume are visited
        // excluding the last visited one from the pattern itself
        
        List<Integer> testKMP = KMP(text2, pattern2);
        for(int index : testKMP) System.out.println(index);
    }

    private static List<Integer> bruteForce(String text, String pattern){
        if(text.length() < pattern.length()) return new ArrayList();
        List<Integer> result = new ArrayList();

        for(int i = 0; i < text.length(); i++){
            char textChar = text.charAt(i);

            if(textChar == pattern.charAt(0)){
                int tmpIndex = i+1, j = 1;

                while(tmpIndex < text.length() && j < pattern.length() && text.charAt(tmpIndex) == pattern.charAt(j)){
                    tmpIndex++;
                    j++;
                }

                if(j == pattern.length()){
                    result.add(i);
                }
            }
        }

        return result;
    }

    public static List<Integer> KMP(String text, String pattern){
        if(text.length() == 0) return null;
        List<Integer> result = new ArrayList();

        if(pattern.length() == 0){
            result.add(0);
            return result;
        }

        int[] lps = buildLPS(pattern);


        int n = text.length(), m = pattern.length();

        int i = 0, j = 0;

        while(i < n){
            if(text.charAt(i) == pattern.charAt(j)){
                i++;
                j++;
            }

            if(j == m){
                result.add(i - j);
                j = lps[j-1];
            }else if(i < n && text.charAt(i) != pattern.charAt(j)){
                if(j != 0){
                    j = lps[j-1];
                }else{
                    i++;
                }
            }
        }

        return result;
    }

    private static int[] buildLPS(String pattern){
        int m = pattern.length();
        int[] lps = new int[m];

        int len = 0, i = 1;
        // first index of lps is always 0, not enough chars to be considered lps
        while(i < m){
            if(pattern.charAt(i) == pattern.charAt(len)){
                len++;
                lps[i] = len;
                i++;
            }else{
                if(len > 0){
                    len = lps[len-1];
                }else{
                    i++;
                }
            }
        }

        return lps;
    }
}