import java.util.Map;

public class Control {
    public static void main(String[] args) {
        TransactionModel transactionModel = TransactionModel.getTransactionModel("Data.csv");
        FrequencyTable frequencyTableService = new FrequencyTable();

        Map<String, int[]> frequencyTable = frequencyTableService.makeFrequencyTable(transactionModel.getTransactions());

        System.out.println("\nFrequency Table");
        for (Map.Entry<String, int[]> entry : frequencyTable.entrySet()) {
            String permutation = entry.getKey();
            int[] counts = entry.getValue();
            System.out.println(permutation + " -> isTransactionPending? Yes count: " + counts[0] + ", No count: " + counts[1]);
        }
    }
}