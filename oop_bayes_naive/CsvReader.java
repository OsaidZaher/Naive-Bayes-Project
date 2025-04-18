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

    
}