

    public class Transaction {

        // intilizing variables 
        private String transactionType;
        private String paymentMethod;
        private String customerVerified;
        private String weekendTransfer;
        private String transactionPending;

        // creating object
        public Transaction(String transactionType, String paymentMethod, String customerVerified, String weekendTransfer, String transactionPending) {
            
            //constructor
            setCustomerVerified(customerVerified);
            setPaymentMethod(paymentMethod);
            setWeekendTransfer(weekendTransfer);
            setTransactionType(transactionType);
            setTransactionPending(transactionPending);
        }

        // Getters
        public String getTransactionType() { return transactionType; }
        public String getPaymentMethod() { return paymentMethod; }
        public String getCustomerVerified() { return customerVerified; }
        public String getWeekendTransfer() { return weekendTransfer; }
        public String getTransactionPending() { return transactionPending; }

            //setters
        public void setPaymentMethod(String paymentMethod){this.paymentMethod = paymentMethod;}
        public void setTransactionType(String transactionType){this.transactionType = transactionType;}
        public void setCustomerVerified(String customerVerified){this.customerVerified = customerVerified;}
        public void setWeekendTransfer(String weekendTransfer){this.weekendTransfer = weekendTransfer;}
        public void setTransactionPending(String transactionPending){this.transactionPending = transactionPending;}

        @Override
        // string method to add to csv
        public String toString() {
            return String.format("%s,%s,%s,%s,%s",
                    transactionType, paymentMethod, customerVerified, weekendTransfer, transactionPending);
        }
    }