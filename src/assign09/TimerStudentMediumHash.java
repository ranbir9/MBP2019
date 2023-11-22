package assign09;
import java.util.Random;
public class TimerStudentMediumHash {

    public static void main(String[] args) {

        final int N = 1000;
        Random random = new Random();
        w
        HashTable<StudentMediumHash, Double> studentGpaTable2 = new HashTable<StudentMediumHash, Double>();

        long startTime = System.nanoTime();
        int j; 
        for (j = 100; j <= N; j += 100) {
            // medium student
            for (int i = 0; i < j; i++) {
                int uid = 1000000 + random.nextInt(1000000);
                String firstName = "Name" + uid;
                String lastName = "LastName" + uid;
                double gpa = 1.0 + random.nextDouble() * 3.0;

                StudentMediumHash student = new StudentMediumHash(uid, firstName, lastName);
                studentGpaTable2.put(student, gpa);
                //System.out.println(i + " " + studentGpaTable2.toString());
            }

            long endTime = System.nanoTime();
            long elapsedTime = endTime - startTime;

            System.out.println("N: " + j + ", " + elapsedTime + " nanoseconds");
            //System.out.println("table size: " + studentGpaTable2.size());
            System.out.println("Collisons : " + studentGpaTable2.coll()); 
        }
    }
}
