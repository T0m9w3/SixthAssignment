package com.company;

public class BankAccount {
    private double Balance;
    private float InterestRate;
    private int AccountID;
    private static int NextID = 1000;

    public BankAccount(){
        InterestRate = 0.02f;
        AccountID = NextID;
        NextID = NextID+1;
    }
    public BankAccount(double initialBalance, float initialInterestRate){
        Balance = initialBalance;
        InterestRate = initialInterestRate;
        AccountID = NextID;
        NextID++;
    }
    public int getAccountID(){
        return AccountID;
    }
    public void deposit(double amount){
        Balance += amount;
    }
    public boolean withdraw(double amount){
        if (amount > Balance){
            return false;
        }
        else
            Balance = Balance - amount;
        return true;
    }
    public double checkBalance(){
        return Balance;
    }
    public double addInterest(){
        Balance += InterestRate*Balance;
        return Balance;
    }
}
