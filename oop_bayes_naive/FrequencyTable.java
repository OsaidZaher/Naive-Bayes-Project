

import java.util.*;

public class FrequencyTable {

    // return a map with some string as key and arry of type int as value
    public Map<String, int[]> makeFrequencyTable(List<Transaction> transactions) {
        // creating hashmap of said type
        Map<String, int[]> frequencyTable = new HashMap<>();

        // iterate over transaction permutation and format as string to create a key to lookup    
        for (Transaction transaction : transactions) {
            String permutationKey = String.format("%s,%s,%s,%s",
                    transaction.getTransactionType(),
                    transaction.getPaymentMethod(),
                    transaction.getCustomerVerified(),
                    transaction.getWeekendTransfer());

            // if permutation instancde ddoes not exist add the permutation and a new value of int{0,0}            
            if (!frequencyTable.containsKey(permutationKey)) {
                frequencyTable.put(permutationKey, new int[]{0, 0});
            }
            
            // let counts be the value of key
            int[] counts = frequencyTable.get(permutationKey);
            // if the label is yes increase first index count else increase second index. count[0]== yes count[no]== no
            if (transaction.getTransactionPending().equalsIgnoreCase("yes")) {
                counts[0]++;
            } else {
                counts[1]++;
            }
        }

        return frequencyTable;
    }
}