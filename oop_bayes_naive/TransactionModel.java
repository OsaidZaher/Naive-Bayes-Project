    import java.io.*;
    import java.util.ArrayList;
    import java.util.List;

    public class TransactionModel {

        // initilizing variables 
        private List<Transaction> transactions;
        private String filePath;
        private static TransactionModel TransactionModel;


        // construtor using private to ensure singleton design pattern where one instance of Transaction model exists, not accessed      
        private TransactionModel(String filePath) {
            this.filePath = filePath;

            // instance of list transaction already exist
            this.transactions = new ArrayList<>();
            this.loadTransactions();
            
        }

        // only create instance of transactionModel if it does not exist singleton design, this is the accessed function
        public static TransactionModel getTransactionModel(String filePath) {
            if (TransactionModel == null) {
                TransactionModel = new TransactionModel(filePath);
            }
            return TransactionModel;
        }

        private void loadTransactions() {

            // new file pointer
            File file = new File(filePath);

            //error handling not existent file
            if (!new File(filePath).exists()) {
                System.err.println("file does not exist");
                return;
            }

            // new buffer reader instance
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

                //line to read and skip first line because it is headers    
                String line;
                reader.readLine();

                // while loop to add transactions
                while ((line = reader.readLine()) != null) {
                    
                    // comma delimtted file so split using the comma
                    String[] columns = line.split(",");
                    transactions.add(new Transaction(
                            columns[0].trim(),
                            columns[1].trim(),
                            columns[2].trim(),
                            columns[3].trim(),
                            columns[4].trim()
                    ));
                }
            } catch (IOException e) {
                System.err.println(e);

            }
        }


        // adding transaction method and saving it to the file
        public void addTransaction(Transaction transaction) {
            transactions.add(transaction);
            
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                if (!new File(filePath).exists()) {
                    System.err.println("file does not exist");
                    return;
                }
                
                writer.write(transaction.toString());
                writer.newLine();
            } catch (IOException e) {
                System.err.println(e);
            }
        }


        //encapsulation
        public List<Transaction> getTransactions() {
            return new ArrayList<>(transactions);
        }
    }