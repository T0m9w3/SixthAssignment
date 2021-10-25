package com.company;

import java.util.ArrayList;
import java.util.Optional;

public class Customer{
    private int CustomerID;
    private String Name;
    private ArrayList<BankAccount> Accounts;

    public Customer(String Name, int TaxID){
        this.Name = Name;
        CustomerID = TaxID;
        Accounts = new ArrayList<BankAccount>();
    }
    public BankAccount openAccount(double InitialDeposit){
        var newAccount = new BankAccount();
        newAccount.deposit(InitialDeposit);
        var didSucceed = Accounts.add(newAccount);
        return newAccount;
    }
    public Optional<BankAccount> closeAccount(int AccountNumber){
        for(var Account: Accounts){
            if(Account.getAccountID() == AccountNumber){
                System.out.println("Removing Account with AccountID# " + AccountNumber + " from Customer " + Name);
                Accounts.remove(Account);
                return Optional.of(Account);
            }
        }
    System.out.println("Account with AccountID# " + AccountNumber + " is not " + Name+"'s Account");
    return Optional.empty();
    }
    public int getCustomerID(){
        return CustomerID;
    }
    public String getName(){
        return Name;
    }
}
