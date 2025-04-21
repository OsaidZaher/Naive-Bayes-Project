## Getting Started

This project is a simple classifier inspired by the Naive Bayes approach, but it functions more like a Look-Up Classifier. It predicts whether a transaction is pending based on several features inputted by the user through a GUI.

The model looks up the input's feature sequence in a frequency table and returns the most frequent result ("yes" or "no") based on previous data. It is trained using 75% of the dataset and evaluated on the remaining 25%.


Classes:
Transaction: A public class which is responsible for creating the object of type "transaction" so that the other files can use it for making the prediction. It has 5 string attributes and uses encapsulation. It has a toString method, which is necessary for adding the transaction to the file

TransactionModel: A singleton class that is used to ensure the singleton pattern design, where only one instance of this class can exist. Has a load transaction method that reads from the CSV file. An addTransaction method which is used to add transactions to the CSV file and uses the toString method from Transaction. 

FrequencyTable: A public Java class that returns a string key's Map structure and an integer array value. The class is responsible for creating a permutationKey, which will be used throughout the code to look up the prediction, and an integer array where the first index is incremented if the permutation instance's label is yes, and the second index is incremented if the label is no.

TrainTest: Public class responsible for training and testing the model
Methods:
trainAndEvaluate: Method that takes the allTransaction array and calls the split method, trainModel method, and evaluate method.
splitData: Splits the data into 75% training and 25% testing based on the yes and no total count, shuffles the dataset for common practice in machine learning, and     prints the dataset summary
trainModel: Creates a frequency table with the training dataset, effectively training the model
evaluateModel: Tests the accuracy of the model on the training data and returns the accuracy of the model using the classification accuracy metric
makePrediction: Takes in the user input from the GUI and predicts by looking up the permutation key
getTrainedFrequencyTable: returns the trained frequency table


TransactionGUI: Public class responsible for the front end of the code and calls the logic function from the tranAndEvaluate class
Contains: 
2 Combo boxes, 6 check boxes, 3 buttons
Method:
addTransactionAction: Action listener for the add button, used to add a transaction from checkboxes and combo boxes
PredictTransaction: Action listener for the calc button, used to make the prediction based on GUI input
trainAndEvaluateAction: Action listener for the train button, used to train the model and print the evaluation in the terminal  
main: main method

