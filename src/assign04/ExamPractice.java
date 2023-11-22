package assign04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.HashSet;
import java.util.TreeSet;

public class ExamPractice {
    public static void main(String[] args){

        ArrayList<String> list = new ArrayList<>(); 
        list.add("what");
        list.add("what");
        System.out.println(list.toString());

        //TreeSet does not add duplicates
        TreeSet<String> tree = new TreeSet<>(); 
        tree.add("hello");
        tree.add("hello");
        System.out.println(tree.toString());
        //handles duplicates again
        tree.addAll(list);
        System.out.println(tree.toString());

        //pass in list w/ dups through constructor
        TreeSet<String> tree2 = new TreeSet<>(list); 
        System.out.println(tree2.toString());


        

    }
}
