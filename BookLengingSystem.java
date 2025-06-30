import java.util.ArrayList;
import java.util.Scanner;

// Base Book class
abstract class Book {
    protected String title;
    protected String author;
    protected boolean isBorrowed;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
    }

    public void borrow() {
        if (!isBorrowed) {
            isBorrowed = true;
            System.out.println("You have borrowed \"" + title + "\".");
        } else {
            System.out.println("This book is already borrowed.");
        }
    }

    public void returnBook() {
        if (isBorrowed) {
            isBorrowed = false;
            System.out.println("You have returned \"" + title + "\".");
        } else {
            System.out.println("This book was not borrowed.");
        }
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public String getTitle() {
        return title;
    }

    public abstract void displayInfo();
}

// Derived class: Textbook
class Textbook extends Book {
    private String subject;

    public Textbook(String title, String author, String subject) {
        super(title, author);
        this.subject = subject;
    }

    @Override
    public void displayInfo() {
        String status = isBorrowed ? "Borrowed" : "Available";
        System.out.println("[Textbook] " + title + " by " + author + " | Subject: " + subject + " | Status: " + status);
    }
}

// Derived class: Novel
class Novel extends Book {
    private String genre;

    public Novel(String title, String author, String genre) {
        super(title, author);
        this.genre = genre;
    }

    @Override
    public void displayInfo() {
        String status = isBorrowed ? "Borrowed" : "Available";
        System.out.println("[Novel] " + title + " by " + author + " | Genre: " + genre + " | Status: " + status);
    }
}

// Main application class
public class BookLengingSystem {
    private static ArrayList<Book> books = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Sample books
        books.add(new Textbook("Physics 101", "Isaac Newton", "Science"));
        books.add(new Textbook("Calculus Made Easy", "Silvanus Thompson", "Math"));
        books.add(new Novel("The Hobbit", "J.R.R. Tolkien", "Fantasy"));
        books.add(new Novel("1984", "George Orwell", "Dystopian"));

        boolean running = true;
        while (running) {
            System.out.println("\n--- Library Menu ---");
            System.out.println("1. Search Books");
            System.out.println("2. Borrow Book");
            System.out.println("3. Return Book");
            System.out.println("4. Show All Books");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = readIntInput();

            switch (choice) {
                case 1 -> searchBooks();
                case 2 -> borrowBook();
                case 3 -> returnBook();
                case 4 -> showAllBooks();
                case 5 -> {
                    running = false;
                    System.out.println("Thank you for using LibraryApp. Goodbye!");
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
        scanner.close();
    }

    // Helper method: Read safe integer input
    private static int readIntInput() {
        int input = -1;
        try {
            input = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
        return input;
    }

    private static void searchBooks() {
        System.out.print("Enter part of the book title to search: ");
        String titleQuery = scanner.nextLine().toLowerCase();
        boolean found = false;

        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(titleQuery)) {
                book.displayInfo();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No books found with that title.");
        }
    }

    private static void borrowBook() {
        System.out.print("Enter part of the book title to borrow: ");
        String inputTitle = scanner.nextLine().toLowerCase();
        boolean found = false;
    
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(inputTitle)) {
                book.borrow();
                found = true;
                break;  // exit after first matching book is borrowed
            }
        }
    
        if (!found) {
            System.out.println("Book not found.");
        }
    }
    
    private static void returnBook() {
        System.out.print("Enter part of the book title to return: ");
        String inputTitle = scanner.nextLine().toLowerCase();
        boolean found = false;
    
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(inputTitle)) {
                book.returnBook();
                found = true;
                break;  // exit after first matching book is returned
            }
        }
    
        if (!found) {
            System.out.println("Book not found.");
        }
    }
    

    private static void showAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            for (Book book : books) {
                book.displayInfo();
            }
        }
    }
}
