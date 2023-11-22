package _LEETCODE;
import java.util.*; 

public class test {
    public static void main(String args[]){
        String s = "{sdfd"; 
        final String brackets = "{}[]()"; 
        Pattern pat = Pattern.compile(brackets);
        Matcher match = pat.matcher(s);
        
        System.out.println("found: " + match.find());
    }
}
