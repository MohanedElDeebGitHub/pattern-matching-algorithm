

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

        for(int index : testBrute) System.out.println(index);
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
}