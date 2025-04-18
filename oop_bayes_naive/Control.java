import java.util.ArrayList;
import java.util.Map;

public class Control {
    public static void main(String[] args) {
        CsvReader reader = new CsvReader();
        String filePath = "Data.csv";
        
        try {
            ArrayList<String> rows = reader.readCSVFile(filePath);
            
            // Create and print the HashMap
            Map<String, int[]> frequencyTable = HashMapMaker.HashMap(rows);
            
            System.out.println("\nFrequency Table");
            for (Map.Entry<String, int[]> entry : frequencyTable.entrySet()) {
                String permutation = entry.getKey();
                int[] counts = entry.getValue();
                System.out.println(permutation + " -> isTransactionPending?"+ "  Yes count: " + counts[0] + ", No count: " + counts[1]);
            }
            
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}