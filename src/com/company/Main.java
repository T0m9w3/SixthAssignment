package com.company;

public class Main {

    public static void main(String[] args) {
        var theBank = new Bank();
        theBank.doBanking();
        var Account = new BankAccount();
        var Account2 = new BankAccount(10000, 0.05f);
        Account.deposit(1000);
        Account.addInterest();
        var Succeeded = Account.withdraw(2000);
        if(Succeeded){
            System.out.println("You managed to withdraw");
        }
        else{
            System.out.println("Couldn't withdraw 2000 from Account: " + Account.getAccountID() + " when you only have " + Account.checkBalance() + " in your Account");
        }
        Account2.addInterest();
        System.out.println("The second Account (AccountID# " + Account2.getAccountID() + ") currently has a new Current Balance of " + Account2.checkBalance() + " after the InterestRate was added to the Account's Initial Balance");
        Account2.withdraw(5000);
        System.out.println("After the withdrawal of the amount that was withdraw from the second Account currently there is " + Account2.checkBalance() + " remaining in the second Account as its Current Balance");
    }
}
