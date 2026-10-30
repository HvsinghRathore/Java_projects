import java.util.Scanner;

class Account {
    private static int counter = 0;
    private final int accountNo;
    private int balance = 0;
    private final String password;
    private final String username;

    Account(String password, String username) {
        this.accountNo = ++counter;
        this.username = username;
        this.password = password;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public boolean checkPassword(String pass) {
        return password.equals(pass);
    }

    public void deposit(int money) {
        if (money <= 0) {
            System.out.println("Amount must be positive");
            return;
        }
        balance += money;
        System.out.println("Done. New balance: " + balance + "$");
    }

    public void withdraw(int money) {
        if (money <= 0) {
            System.out.println("Amount must be positive");
            return;
        }
        if (balance < money) {
            System.out.println("Insufficient balance");
            return;
        }
        balance -= money;
        System.out.println("Done. New balance: " + balance + "$");
    }

    public void checkBalance() {
        System.out.println("Current balance: " + balance + "$");
    }

    public void printName() {
        System.out.println("Account #" + accountNo + " - " + username);
    }
}

public class Banking {
    static Scanner sc = new Scanner(System.in);
    static Account[] accounts = new Account[100];
    static int count = 0;

    static Account login() {
        System.out.print("Enter your account number: ");
        int id = sc.nextInt();
        sc.nextLine(); // consume leftover newline

        System.out.print("Enter your password: ");
        String pass = sc.nextLine();

        if (id < 1 || id > count) {
            System.out.println("Account not found");
            return null;
        }
        Account acc = accounts[id - 1];
        if (!acc.checkPassword(pass)) {
            System.out.println("Wrong password");
            return null;
        }
        return acc;
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nMain menu");
            System.out.println("1. Open new account");
            System.out.println("2. Deposit amount");
            System.out.println("3. Withdraw amount");
            System.out.println("4. Check balance");
            System.out.println("5. List all accounts");
            System.out.println("6. Exit");
            System.out.print("Make your choice (1-6): ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume leftover newline

            switch (choice) {
                case 1: {
                    if (count == accounts.length) {
                        System.out.println("No space remaining");
                        break;
                    }
                    System.out.print("Username: ");
                    String username = sc.nextLine();
                    System.out.print("Password: ");
                    String password = sc.nextLine();

                    accounts[count] = new Account(password, username);
                    count++;
                    System.out.println("Account created. Your account no is: "
                            + accounts[count - 1].getAccountNo());
                    break;
                }
                case 2: {
                    Account acc = login();
                    if (acc != null) {
                        System.out.print("Enter the amount: ");
                        int amount = sc.nextInt();
                        sc.nextLine();
                        acc.deposit(amount);
                    }
                    break;
                }
                case 3: {
                    Account acc = login();
                    if (acc != null) {
                        System.out.print("Enter the amount: ");
                        int amount = sc.nextInt();
                        sc.nextLine();
                        acc.withdraw(amount);
                    }
                    break;
                }
                case 4: {
                    Account acc = login();
                    if (acc != null) {
                        acc.checkBalance();
                    }
                    break;
                }
                case 5:
                    if (count == 0) {
                        System.out.println("No accounts yet");
                    }
                    for (int i = 0; i < count; i++) {
                        accounts[i].printName();
                    }
                    break;
                case 6:
                    System.out.println("Goodbye");
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}