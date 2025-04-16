
import java.util.ArrayList;



public class Control {
    public static void main(String[] args) {
        CsvReader reader = new CsvReader(); 
        
        String filePath = "data.csv"; 
        
        try {
            ArrayList<String> rows = reader.readCSVFile(filePath); 
            
            System.out.println("Contents of the csv file are:");
            for (String row : rows) {
                System.out.println(row);
            }

            String newRow = "Low,Positive,Strong,Available,Yes";
            reader.writeRowToCSV(filePath, newRow);
            System.out.println("\nNew row added to the CSV file.");
            
            rows = reader.readCSVFile(filePath);
            
            System.out.println("\nUpdated contents of the csv file are:");
            for (String row : rows) {
                System.out.println(row);
            }


            
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }



        
    }
}