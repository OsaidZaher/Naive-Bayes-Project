import java.util.*;

public class TrainTest {
    private List<Transaction> trainingData;
    private List<Transaction> testingData;
    private Map<String, int[]> frequencyTable;

    public void trainAndEvaluate(List<Transaction> allTransactions) {

        // Stratified split
        splitData(allTransactions);
        
        // Train the model
        frequencyTable = trainModel(trainingData);
        
        // Evaluate the model
        double accuracy = evaluateModel(testingData, frequencyTable);
        
        System.out.println("Model Accuracy: " + String.format("%.2f", accuracy * 100) + "%");
        
    }
    
    private void splitData(List<Transaction> allTransactions) {
        // Count total "Yes" and "No" transactions
        int yesCount = 0;
        int noCount = 0;
        
        // if transaction label is yes increment yes counter else increment no counter
        for (Transaction t : allTransactions) {
            if (t.getTransactionPending().equalsIgnoreCase("Yes")) {
                yesCount++;
            } else {
                noCount++;
            }
        }
        
        // Calculate proportions
        double yesAmount = (double) yesCount / allTransactions.size();
        double noAmount= (double) noCount / allTransactions.size();


        // print methods so i ensure the user inputs from GUI actually work
        System.out.println("Dataset summary:");
        System.out.println("Total transactions: " + allTransactions.size());
        System.out.println("Yes transactions: " + yesCount + " (" + String.format("%.1f", yesAmount * 100) + "%)");
        System.out.println("No transactions: " + noCount + " (" + String.format("%.1f", noAmount * 100) + "%)");
        
        // Separate yes and no transactions
        List<Transaction> yesTransactions = new ArrayList<>();
        List<Transaction> noTransactions = new ArrayList<>();
        
        for (Transaction t : allTransactions) {
            if (t.getTransactionPending().equalsIgnoreCase("Yes")) {
                yesTransactions.add(t);
            } else {
                noTransactions.add(t);
            }
        }
        
        // Shuffle to randomize datasets
        Collections.shuffle(yesTransactions);
        Collections.shuffle(noTransactions);
        
        // Calculate counts for training set (75% of data)
        int trainingYesCount = (int) (yesCount * 0.75);
        int trainingNoCount = (int) (noCount * 0.75);
        
        // Create training and testing sets
        trainingData = new ArrayList<>();
        testingData = new ArrayList<>();
        
        // for all yes transactions add 75 of yes's to training and the remaining to test
        for (int i = 0; i < yesTransactions.size(); i++) {
            if (i < trainingYesCount) {
                trainingData.add(yesTransactions.get(i));
            } else {
                testingData.add(yesTransactions.get(i));
            }
        }
        
        //  for all no transactions add 75 of yes's to training and the remaining to test
        for (int i = 0; i < noTransactions.size(); i++) {
            if (i < trainingNoCount) {
                trainingData.add(noTransactions.get(i));
            } else {
                testingData.add(noTransactions.get(i));
            }
        }
        //print sizes of data set 
        System.out.println("\nTraining set: " + trainingData.size() + " transactions");
        System.out.println("Testing set: " + testingData.size() + " transactions");
    }
    

    // creating frequnecy table with training data
    private Map<String, int[]> trainModel(List<Transaction> trainingData) {
        FrequencyTable trainingTable = new FrequencyTable();
        return trainingTable.makeFrequencyTable(trainingData);
    }
    
    private double evaluateModel(List<Transaction> testingData, Map<String, int[]> frequencyTable) {
        int correct = 0;
        
        // loop each transaction in testing Data
        for (Transaction t : testingData) {
            String permutationKey = String.format("%s,%s,%s,%s",
                    t.getTransactionType(),
                    t.getPaymentMethod(),
                    t.getCustomerVerified(),
                    t.getWeekendTransfer());


            //int[] counts = frequencyTable.get(permutationKey);
            
            // this Code is from Claude, i was initially using frequencyTable.get(permutationKey); but since we had to stratify
            // the code based on the rate of yes's and no rather than splitting it into 75/25 total data,
            // some permutations did not exist in the datasets so claude gave me the idea of using the getOrDefault method which adds
            // a default value in the case the key does not exist (i didn't know this method existed). I then used a terniary operator to say if the yes count
            // is greated than 0 then it is yes ELSE make it no. Not "if the no count is greater" because there is no evidence
            // to support that the transaction did happen we default that it didn't. I believe this is just the limitaiton of the classifier.        
            int[] counts = frequencyTable.getOrDefault(permutationKey, new int[]{0, 0});



            // not claude from here on, if yes count is greater than no count return yes else return no even if no and yes count are both 0 since 
            // there is not proof of transaction is pending
            String prediction = counts[0] > counts[1] ? "Yes" : "No";
            
            // ignoreCase so it is case insensitive but not necessary, but best practise applies
            if (prediction.equalsIgnoreCase(t.getTransactionPending())) {
                correct++;
            }
        }
        
        // return the accuracy of the model using lassification accuracy metric
        return (double) correct / testingData.size();
    }
    
 
    // make prediction code for user input and passing in the parameters
    public String makePrediction(String transactionType, String paymentMethod, 
                               String customerVerified, String weekendTransfer) {

         // if this is null it means the model has not been trained, this is the frequency table with only 75% data                       
        if (frequencyTable == null) {
            return "Model not trained yet";
        }
        

        //permutation key to look up value
        String permutationKey = String.format("%s,%s,%s,%s",
                transactionType, paymentMethod, customerVerified, weekendTransfer);
        

        int[] counts = frequencyTable.getOrDefault(permutationKey, new int[]{0, 0});
        String prediction = counts[0] > counts[1] ? "Yes" : "No";
        
        return prediction;
    }
    
    // encapsulation because private 
    public Map<String, int[]> getTrainedFrequencyTable() {
        return frequencyTable;
    }
}