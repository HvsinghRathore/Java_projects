
import java.util.ArrayList;
import java.util.Scanner;

class Book{

    private int bookId;
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isAvailable = true; 
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public void displayBookInfo() {
        System.out.println("Book ID: " + bookId);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Available: " + isAvailable);
    }

}

class Member {
    private int memberId;
    private String name;
    private ArrayList<Book> borrowedBooks;

    public Member(int memberId, String name) {
        this.memberId = memberId;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }
    
    public void displayMemberInfo() {
        System.out.println("Member ID      : " + memberId);
        System.out.println("Name           : " + name);
        System.out.println("Books borrowed : " + borrowedBooks.size());
    }
}

class Libarar {
    
    ArrayList<Book> books = new ArrayList<>();      
    ArrayList<Member> members = new ArrayList<>(); 
    int bookCount = 0;
    int memberCount = 0;

    public Member findMember(int Id){
        
        for (Member m : members) {
            if(m.getMemberId()==Id)
                return m;
        }
        return null;
         
    }

    public Book findBook(int Id){
        
        for (Book m : books) {
            if(m.getBookId()==Id)
                return m;
        }
        return null;
         
    }


    public void addBook(String name,String author){
        bookCount++;
        Book book = new Book(bookCount, name, author) ;
        books.add(book);
    }

    public void addMember(String name){
        memberCount++;
        Member member = new Member(memberCount, name) ;
        members.add(member);
    }

    public void viewBook(){
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
            return;
        }
        for (Book bk : books) {
            bk.displayBookInfo();
        }
        
    }

    public void viewMember(){
        if (members.isEmpty()) {
            System.out.println("No members registered.");
            return;
        }
        for (Member member : members) {
            member.displayMemberInfo();
        }
        
    }

    public void viewBorrowedBooks(int memberId) {
        Member member = findMember(memberId);
        if (member == null) {
            System.out.println("Member not found.");
            return;
        }
        if (member.getBorrowedBooks().isEmpty()) {
            System.out.println(member.getName() + " has no borrowed books.");
            return;
        }
        System.out.println("Books borrowed by " + member.getName() + ":");
        for (Book b : member.getBorrowedBooks()) {
            System.out.println("  [" + b.getBookId() + "] " + b.getTitle() + " by " + b.getAuthor());
        }
    }

    public void borrowBook(int memberid,int bookid){
        Member member = findMember(memberid);
        Book book = findBook(bookid);

        if(member==null||book==null){
            System.out.println("No match found");
            return ;
        }
        if(!book.isAvailable()){
            System.out.println("Book is not Available");
            return ;
        }
        member.getBorrowedBooks().add(book);
        book.setAvailable(false);
        System.out.println(member.getName() + " borrowed " + book.getTitle());
    }

    public void returnBook(int memberid,int bookid) {
        Member member = findMember(memberid);
        Book book = findBook(bookid);

        if(member==null||book==null){
            System.out.println("No match found");
            return ;
        }
        if (member.getBorrowedBooks().remove(book)) {
            book.setAvailable(true);
            System.out.println(member.getName() + " returned " + book.getTitle());
        } else {
            System.out.println(member.getName() + " did not borrow " + book.getTitle());
        }
    }


}
    

public class Libarary {

    static Scanner sc = new Scanner(System.in);

    static void displayMenu() {
        System.out.println("\n===== LIBRARY MANAGEMENT =====");
        System.out.println("1. Add Book");
        System.out.println("2. Add Member");
        System.out.println("3. View Books");
        System.out.println("4. View Members");
        System.out.println("5. Borrow Book");
        System.out.println("6. Return Book");
        System.out.println("7. View Member's Borrowed Books");
        System.out.println("0. Exit");
    }

    static String readString(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }

    static int readInt(String prompt) {
    while (true) {
        System.out.print(prompt);
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }
}
    public static void main(String[] args){
        Libarar libarary = new Libarar();
        while(true){
            displayMenu();
            int choice = readInt("Enter your choice: ");
            switch(choice){
                case 1:
                    String title = readString("Enter Book's name:");
                    String author = readString("Enter Author's name:");
                    libarary.addBook(title, author);
                    break;

                case 2:
                    String name = readString("Enter Your name");
                    libarary.addMember(name);
                    break;
                
                case 3:
                    libarary.viewBook();
                    break;
                
                case 4:
                    libarary.viewMember();
                    break;

                case 5:
                    int bookId = readInt("Enter book Id: ");
                    int memId = readInt("Enter your user Id: ");
                    libarary.borrowBook(memId, bookId);
                    break;

                case 6:
                    int bookId1 = readInt("Enter book Id: ");
                    int memId1 = readInt("Enter your user Id: ");
                    libarary.returnBook(memId1, bookId1);
                    break;

                case 7:
                    int id = readInt("Enter your id: ");
                    libarary.viewBorrowedBooks(id);
                    break;

                case 0:
                    sc.close();
                    return ;

                default:
                    System.out.println("Enter vaild choise!!");
            }
        }
        

    }
}
