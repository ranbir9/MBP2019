package _LEETCODE;

import java.util.Arrays;

class Solution {
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort();
        for(int i = 0; i < nums.length; i++){
            System.out.println(nums[i]); 
        }
    }
    public void main(String args[]){
        String s = "{sdfd"; 
        inal String brackets = "{}[]()"; 
        Pattern pat = Pattern.compile(brackets);
        Matcher match = pat.matcher(s);
        
        System.out.println("found: " + match.find());
    }
}
