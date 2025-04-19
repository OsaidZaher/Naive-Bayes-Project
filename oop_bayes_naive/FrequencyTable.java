

import java.util.*;

public class FrequencyTable {
    public Map<String, int[]> makeFrequencyTable(List<Transaction> transactions) {
        Map<String, int[]> frequencyTable = new HashMap<>();

        for (Transaction transaction : transactions) {
            String permutationKey = String.format("%s,%s,%s,%s",
                    transaction.getTransactionType(),
                    transaction.getPaymentMethod(),
                    transaction.getCustomerVerified(),
                    transaction.getWeekendTransfer());

            if (!frequencyTable.containsKey(permutationKey)) {
                frequencyTable.put(permutationKey, new int[]{0, 0});
            }

            int[] counts = frequencyTable.get(permutationKey);
            if (transaction.getTransactionPending().equalsIgnoreCase("yes")) {
                counts[0]++;
            } else {
                counts[1]++;
            }
        }

        return frequencyTable;
    }
}