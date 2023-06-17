package com.driver;

public class BankAccount {

    private String name;
    private double balance;
    private double minBalance;

    public BankAccount(String name, double balance, double minBalance) {
        this.name=name;
        this.balance=balance;
        this.minBalance=minBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String generateAccountNumber(int digits, int sum) throws Exception{
        //Each digit of an account number can lie between 0 and 9 (both inclusive)
        //Generate account number having given number of 'digits' such that the sum of digits is equal to 'sum'
        //If it is not possible, throw "Account Number can not be generated" exception
        String ans="";
        int start=(int)Math.pow(10,digits-1);
        int end=(int)Math.pow(10,digits) -1;
        for(int i=start;i<=end;i++){
            if(checkSum(i,sum)) {
              ans=i+"";
              return ans;
            }
        }
        if(ans.equals("")) throw new Exception("Account Number can not be generated");
        return null;
    }

    private boolean checkSum(int i,int sum) {
        int curSum=0;
        while (i != 0) {
            curSum+=i%10;
            i=i/10;
        }
        if(curSum==sum) return true;
        else return false;
    }

    public void deposit(double amount) {
        //add amount to balance
        this.balance+=amount;
    }

    public void withdraw(double amount) throws Exception {
        // Remember to throw "Insufficient Balance" exception, if the remaining amount would be less than minimum balance
        if(balance<amount) throw new Exception ("Insuffiecient Balance");
        else {
            balance-=amount;
        }
    }

}