    // TransactionModel.java
    import java.io.*;
    import java.util.ArrayList;
    import java.util.List;

    public class TransactionModel {
        private List<Transaction> transactions;
        private String filePath;
        private static TransactionModel TransactionModel;

        private TransactionModel(String filePath) {
            this.filePath = filePath;
            this.transactions = new ArrayList<>();
            this.loadTransactions();
            
        }

        public static synchronized TransactionModel getTransactionModel(String filePath) {
            if (TransactionModel == null) {
                TransactionModel = new TransactionModel(filePath);
            }
            return TransactionModel;
        }

        private void loadTransactions() {
            File file = new File(filePath);
            if (!file.exists()) return;

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                boolean isFirstLine = true;

                while ((line = reader.readLine()) != null) {
                    if (isFirstLine) {
                        isFirstLine = false;
                        continue;
                    }

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

        public void addTransaction(Transaction transaction) {
            transactions.add(transaction);
            saveTransaction(transaction);
        }

        private void saveTransaction(Transaction transaction) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                if (!new File(filePath).exists() || new File(filePath).length() == 0) {
                    writer.write("Transaction Type,Payment Method,Customer Verified,Weekend Transfer,Transaction Pending");
                    writer.newLine();
                }
                writer.write(transaction.toString());
                writer.newLine();
            } catch (IOException e) {
                System.err.println(e);
            }
        }

        public List<Transaction> getTransactions() {
            return new ArrayList<>(transactions);
        }
    }