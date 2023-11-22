package _LEETCODE;
import java.util.*;
import java.util.regex.Matcher; 

public class isAnagram {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length())
            return false; 

        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) != t.charAt(i))
                return false; 
        }

        s.toCharArray();
        Arrays.equals(sChars, tChars); 

        s.contains()
    }

    public static void main(String args[]){
        String s = "{sdfd"; 
        inal String brackets = "{}[]()"; 
        Pattern pat = Pattern.compile(brackets);
        Matcher match = pat.matcher(s);
        
        System.out.println("found: " + match.find());
    }

    
}


