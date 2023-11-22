package assign09;

import java.util.Random;

public class TimerStudentGoodHash {

    public static void main(String[] args) {

        final int N = 1000;
        Random random = new Random();
        

        //HashTable<StudentBadHash, Double> studentGpaTable = new HashTable<StudentBadHash, Double>();
        HashTable<StudentGoodHash, Double> studentGpaTable2 = new HashTable<StudentGoodHash, Double>();

        long startTime = System.nanoTime();
        int j; 
        for (j = 100; j <= N; j += 100) {

            // medium student
            for (int i = 0; i < j; i++) {
                int uid = 1000000 + random.nextInt(1000000);
                String firstName = "Name" + uid;
                String lastName = "LastName" + uid;
                double gpa = 1.0 + random.nextDouble() * 3.0;

                StudentGoodHash student = new StudentGoodHash(uid, firstName, lastName);
                studentGpaTable2.put(student, gpa);
                // System.out.println(i + " " + studentGpaTable2.toString());
            }

            long endTime = System.nanoTime();
            long elapsedTime = endTime - startTime;

            System.out.println("N: " + j + ", " + elapsedTime + " nanoseconds");
            //System.out.println("table size: " + studentGpaTable2.size());
            System.out.println("Collisons : " + studentGpaTable2.coll());
        }
    }
}
