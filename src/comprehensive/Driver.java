package comprehensive;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class Driver {

/**
 * This is a driver class to read in files to create a ForestDisjointSet object
 * 
 * @author Ranbir Singh & Anuvesha Chilwal
 * @version April 26, 2022
 */
    public static void main(String[] args) {
        if (!(args.length == 1)) {
            return;
        }

        String file = args[0];
        ForestDisjointSet<String> ds = new ForestDisjointSet<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            List<String> nodes = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                nodes.add(line.trim());
                ds.makeSet(line.trim());
            }

            while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                String[] edge = line.trim().split(" ");
                ds.union(edge[0], edge[1]);
            }

            while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                String[] query = line.trim().split(" ");
                String rep1 = ds.getRepresentative(query[0]);
                String rep2 = ds.getRepresentative(query[1]);
                String result = rep1.equals(rep2) ? "connected" : "not connected";

                System.out.println(result);
            }
        } catch (IOException e) {
            System.err.println("error reading file: " + file);
        }
    }
}
