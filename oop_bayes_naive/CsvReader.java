import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class CsvReader {


    public ArrayList<String> readCSVFile(String filePath) throws FileNotFoundException {
        ArrayList<String> rows = new ArrayList<>();
        File file = new File(filePath); 
        
        if (!file.exists()) {
            throw new FileNotFoundException("File not found: " + filePath);
        }
        
        Scanner scanner = null; 
        try {
            scanner = new Scanner(file); 
            
            while (scanner.hasNextLine()) {
                rows.add(scanner.nextLine());
            }
            
            return rows; 
            
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

     public void writeRowToCSV(String filePath, String rowData) throws IOException {

        // Method to write to csv file and throws exception error
        FileWriter fw = new FileWriter(filePath, true); 
        PrintWriter writer = null;
        
        try {
            writer = new PrintWriter(fw);
            writer.println(rowData); 
        } finally {
            if (writer != null) {
                writer.close(); 
            }
        }
    }


   


}