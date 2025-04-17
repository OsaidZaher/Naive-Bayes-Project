import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;



// hashmap for each permutation with yes and no count
public class HashMapMaker {
    public static Map<String, int[]> HashMap(ArrayList<String> rows) {

        Map<String,int[]> permutation = new HashMap<>();

        for(int i =1; i< rows.size(); i++){

            String row = rows.get(i);
            String[]  column = row.split(",");

            String transactionType = column[0].trim();
            String paymentMethod = column[1].trim();
            String customerVerified = column[2].trim();
            String weekendTransaction = column[3].trim();
            String isPaymentPending = column[4].trim();

            String permutationKey = transactionType +','+ paymentMethod + ','+ customerVerified + ',' + weekendTransaction + ',' + isPaymentPending;
            if (!permutation.containsKey(permutationKey)) {
                permutation.put(permutationKey, new int[]{0, 0});
            }

            int counts[] = permutation.get(permutationKey);
            if (isPaymentPending=="yes"){
                counts[0]++;
            }else{
                counts[1]++;
            }
        }



        return permutation;
    }
}