package com.company;

import java.util.Scanner;
import java.util.*;
import java.io.FileWriter; //import the FileWriter class
import java.io.IOException; //import the IOException class to handle errors

class BankAccDetails{
    private String name;
    private long balance;
    private int acNumber;
    Scanner sc = new Scanner(System.in);

    //function to open new account
    public void openNewAcc(){
        System.out.print("enter the name of the customer :");
        name=sc.next();
        System.out.print("enter the account number :");
        acNumber=sc.nextInt();
        System.out.print("enter the initial balance of the account :");
        balance=sc.nextLong();
    }

    //function to display the account details
    public void showAccountDetails() {
        System.out.println("Name of account holder: " + name);
        System.out.println("Account no.: " + acNumber);
        System.out.println("Balance: " + balance);
    }

    //function to print the balance
    public void showBalance() {
        System.out.println("Balance: " + balance);
    }

    //method to deposit money
    public void deposit() {
        long amt;
        System.out.println("Enter the amount you want to deposit: ");
        amt = sc.nextLong();
        balance = balance + amt;
    }

    public void depositTransfer(long money){
        balance = balance + money;
        System.out.println("amount deposited in the reciever's account successfully");
    }

    //method to withdraw money
    public void withdrawal() {
        long amt;
        System.out.println("Enter the amount you want to withdraw: ");
        amt = sc.nextLong();
        if (balance >= amt) {
            balance = balance - amt;
            System.out.println("Balance after withdrawal: " + balance);
        } else {
            System.out.println("Your balance is less than " + amt + "\tTransaction failed...!!" );
        }
    }

    public void withdrawTransfer(long amt){
        if (balance >= amt) {
            balance = balance - amt;
            System.out.println("amount transferred from the sender's account succesfully ");
        } else {
            System.out.println("Your balance is less than " + amt + "\tTransaction failed...!!" );
        }
    }

    public boolean checkSendersBalance(long amt){
        if (balance < amt) {
            return (false);
        }
        return (true);
    }

    //method to search an account number
    public boolean search(int ac_no) {
        if (acNumber==ac_no) {
            return (true);
        }
        return (false);
    }

    //method to write to the text files
    public void createFile(){
        String fileName= String.valueOf(acNumber);
        String bal = String.valueOf(balance);
        String N = String.valueOf(acNumber);
        fileName=fileName+".txt";
        try{
            FileWriter myobj = new FileWriter(fileName);
            myobj.write("name : ");
            myobj.write(name);
            myobj.write(" , aacount number : ");
            myobj.write(N);
            myobj.write(" , final balance : ");
            myobj.write(bal);
            myobj.close();
        }catch(IOException e) {
            System.out.println("error occured. cannot open the file");
            e.printStackTrace();
        }

    }

}

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Vector<BankAccDetails> vec = new Vector<BankAccDetails>();
        int choice=0;
        System.out.println("you can perform following options. please enter the corresponding number.");
        do
        {
            System.out.println("1.create a bank account");
            System.out.println("2.close a bank account");
            System.out.println("3.withdraw money");
            System.out.println("4.deposit money");
            System.out.println("5.request balance");
            System.out.println("6.transfer money");
            System.out.println("7.exit from the system\n");
            System.out.println("enter the number : ");
            choice=sc.nextInt();

            switch(choice){
                case 1:
                    BankAccDetails b = new BankAccDetails();
                    b.openNewAcc();
                    b.createFile();
                    vec.add(b);
                    break;

                case 2:
                    int number;
                    System.out.println("enter the account number you want to close: ");
                    number=sc.nextInt();
                    boolean found= false;
                    for(int i=0;i<vec.size();i++){
                        found= vec.get(i).search(number);
                        if(found==true){
                            vec.remove(i);
                            System.out.println("account deleted successfully");
                            break;
                        }
                    }
                    if(found==false){
                        System.out.println("no account matching your input");
                    }
                    break;
                case 3:
                    int number3;
                    System.out.println("enter the account number you want to withdraw money: ");
                    number3=sc.nextInt();
                    boolean found3= false;
                    for(int i=0;i<vec.size();i++){
                        found3= vec.get(i).search(number3);
                        if(found3==true) {
                            vec.get(i).withdrawal();
                            System.out.println("withdrawed the amount successfully");
                            vec.get(i).createFile();
                            break;
                        }
                    }
                    if(found3==false){
                        System.out.println("no account matching your input");
                    }
                    break;
                case 4:
                    int number4;
                    System.out.println("enter the account number you want to deposit money: ");
                    number4=sc.nextInt();
                    boolean found4= false;
                    for(int i=0;i<vec.size();i++){
                        found4= vec.get(i).search(number4);
                        if(found4==true) {
                            vec.get(i).deposit();
                            System.out.println("deposited the amount successfully");
                            vec.get(i).createFile();
                            break;
                        }
                    }
                    if(found4==false){
                        System.out.println("no account matching your input");
                    }
                    break;
                case 5:
                    int number5;
                    System.out.println("enter the account number you want to check the balance: ");
                    number5=sc.nextInt();
                    boolean found5= false;
                    for(int i=0;i<vec.size();i++){
                        found5= vec.get(i).search(number5);
                        if(found5==true) {
                            vec.get(i).showBalance();
                            break;
                        }
                    }
                    if(found5==false){
                        System.out.println("no account matching your input");
                    }
                    break;
                case 6:
                    int num1,num2;
                    long amount;
                    System.out.println("enter the account number of the sender: ");
                    num1=sc.nextInt();
                    System.out.println("enter the account number of the reciever: ");
                    num2=sc.nextInt();
                    System.out.println("enter the amount of money need to transfer: ");
                    amount=sc.nextLong();
                    boolean f1=false,f2=false;
                    boolean bb=false; //check if the senders bank balance is enough to transfer

                    for(int i=0;i<vec.size();i++){//reciever
                        f2= vec.get(i).search(num2);
                        if(f2==true) {
                            break;
                        }
                    }
                    //if(f2==false)
                      //  System.out.println("no account number match with reciever");
                    for(int i=0;i<vec.size();i++){//sender
                        f1= vec.get(i).search(num1);
                        if(f1==true ) {
                            bb= vec.get(i).checkSendersBalance(amount);
                            break;
                        }
                    }
                    //if(f1==false)
                      //  System.out.println("no account number match with sender");
                    if(bb==true && f1==true && f2==true)
                    {
                        for(int i=0;i<vec.size();i++){
                            f1= vec.get(i).search(num1);
                            f2=vec.get(i).search(num2);
                            if(f1==true) { //sender
                                vec.get(i).withdrawTransfer(amount);
                                vec.get(i).createFile();
                            }
                            if(f2==true){//reciever
                                vec.get(i).depositTransfer(amount);
                                vec.get(i).createFile();
                            }
                        }
                    }
                    else
                        System.out.println("transaction failed: balance is not enough or accounts does not exist");
                    break;
                    /*
                    for(int i=0;i<vec.size();i++){ //sender
                        f1= vec.get(i).search(num1);
                        if(f1==true && f2==true) {
                            System.out.println("Enter the amount you want to transfer: ");
                            amount = sc.nextLong();
                            vec.get(i).withdrawTransfer(amount);
                            bb=vec.get(i).checkSendersBalance(amount);
                            break;
                        }
                    }
                    for(int i=0;i<vec.size();i++){//reciever
                        if(f2==true && f1==true && amount!=0 && bb==true) {
                            vec.get(i).depositTransfer(amount);
                            break;
                        }
                    }

                     */
                case 7:
                    System.out.println("thank you!! see you again!!");
                    break;
            }
        }while(choice!=7);

        int n = vec.size();
        System.out.println(n);
        for(int i=0;i<n;i++)
        {
            vec.get(i).showAccountDetails();
        }



    }
}
