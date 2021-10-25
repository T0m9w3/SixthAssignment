package com.company;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class Bank{
    private ArrayList<BankAccount> AllAccounts;
    private ArrayList<Customer> AllCustomers;

    public Bank(){
        AllAccounts = new ArrayList<BankAccount>();
        AllCustomers = new ArrayList<Customer>();
    }
    public void doBanking(){
        var menuReader = new Scanner(System.in);
        while(true){
            printMenu();
            var userChoice = menuReader.nextInt();
            switch(userChoice){
                case 1:
                    addCustomer(menuReader);
                case 2:
                    Optional<Customer> Current = selectCustomer(menuReader);
                    if (Current.isPresent()){
                        doCustomerMenu(menuReader, Current.get());
                    }
                    else{
                        System.out.println("No Customer with with that ID can be found");
                    }
                case 3:
                    doYearlyMaintenance();
                case 4:
                    System.exit(0);
            }
        }
    }

    private void doYearlyMaintenance(){
        for(var currentAccount: AllAccounts){
            currentAccount.addInterest();
            System.out.println("AccountID#: " + currentAccount.getAccountID() + " has a balance of " + currentAccount.checkBalance());
        }
    }

    private void doCustomerMenu(Scanner menuReader, Customer currentCustomer){
        while(true){
            printCustomerMenu();
            var customerChoice = menuReader.nextInt();
            switch(customerChoice){
                case 1:
                    openCustomerAccount(menuReader, currentCustomer);
                case 2:
                    closeCustomerAccount(menuReader, currentCustomer);
                case 3:
                    return;
            }
        }
    }

    private void closeCustomerAccount(Scanner menuReader, Customer currentCustomer){
        System.out.println("Enter the Account# of the Account to close");
        menuReader.nextInt();
        var accountNumber = menuReader.nextInt();
        Optional<BankAccount> accountToClose = currentCustomer.closeAccount(accountNumber);
        if(accountToClose.isPresent()){
            AllAccounts.remove(accountToClose.get());
        }
    }

    private void openCustomerAccount(Scanner menuReader, Customer currentCustomer){
        System.out.println("Creating a new Account....");
        System.out.println("What is the Initial Deposit for this Account: ");
        menuReader.nextDouble();
        var initialDeposit = menuReader.nextDouble();
        var newAccount = currentCustomer.openAccount(initialDeposit);
        AllAccounts.add(newAccount);
    }

    private void printCustomerMenu(){
        System.out.println("*****************************************************************************************");
        System.out.println("What would you like to do with this Customer");
        System.out.println("  [1] Open an Account");
        System.out.println("  [2] Close an Account");
        System.out.println("  [3] Return to Main Menu");
        System.out.println("*****************************************************************************************");
        System.out.println("Enter Choice: ");
    }

    private Optional<Customer> selectCustomer(Scanner Reader){
        System.out.println("Customer ID# of the Customer to select: ");
        Reader.nextInt();
        var customerIDNumberToFind = Reader.nextInt();
        for (var currentCustomer: AllCustomers){
            if (currentCustomer.getCustomerID() == customerIDNumberToFind){
                return Optional.of(currentCustomer);
            }
        }
    return Optional.empty();
    }

    private void addCustomer(Scanner inputReader){
        System.out.println("What is the new Customer's name: ");
        inputReader.nextLine();
        var CustomerName = inputReader.nextLine();
        System.out.println("What is the new Customer's TaxID# (SSN): ");
        var TaxID = inputReader.nextInt();
        var NewCustomer = new Customer(CustomerName, TaxID);
        AllCustomers.add(NewCustomer);
    }
    private void printMenu(){
        System.out.println("=========================================================================================");
        System.out.println("  [1] Add a Customer");
        System.out.println("  [2] Select a Customer by CustomerID#");
        System.out.println("  [3] Do the yearly maintenance and show all Accounts");
        System.out.println("  [4] Exit the Program");
        System.out.println("=========================================================================================");
        System.out.println("Enter Choice: ");
    }
}
