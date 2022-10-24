import java.util.Random;
import java.util.Scanner;
class BookstoreBook {
  private String author;
  private String title;
  private String isbn;
  private double price;
  private double sale;
  static int numBooks;
  public BookstoreBook() {
    this.author = "";
    this.title = "";
    this.isbn = "";
  }

  public BookstoreBook(String author) {
    this.author = author;
  }
  public BookstoreBook(String author, String title) {
    this.author = author;
    this.title = title;
  }
  public BookstoreBook(String author, String title, String isbn) {
    this.author = author;
    this.title = title;
    this.isbn = isbn;
  }
  public String getAuthor() {
    return author;
  }
  public String getTitle() {
    return title;
  }
  public String getIsbn() {
    return isbn;
  }
  public double getPrice() {
    return price;
  }
  public double getSale() {
    return sale;
  }
  public void setAuthor(String a) {
    this.author = a;
  }
  public void setTitle(String t) {
    this.title = t;
  }
  public void setIsbn(String i) {
    this.isbn = i;
  }
  public void setPrice(double p) {
    this.price = p;
  }
  public void setSale(double s) {
    this.sale = s;
  }
  @Override
  public String toString() {
    return "[" + getIsbn() + "-" + getTitle().toUpperCase() + " by " +
      getAuthor().toUpperCase() + ", $" + String.format("%.2f", getPrice()) + " listed for $" + String.format("%.2f ", getSale()) +
      "]";
  }
}

class LibraryBook {
  private String author;
  private String title;
  private String isbn;
  static int numBooks;
  public LibraryBook() {
    this.author = "";
    this.title = "";
    this.isbn = "";
  }
  public LibraryBook(String author) {
    this.author = author;
  }
  public LibraryBook(String author, String title) {
    this.author = author;
    this.title = title;
  }
  public LibraryBook(String author, String title, String isbn) {
    this.author = author;
    this.title = title;
    this.isbn = isbn;
  }
  public String getAuthor() {
    return author;
  }
  public String getTitle() {
    return title;
  }
  public String getIsbn() {
    return isbn;
  }
  public String getCallNumber() {
    Random random = new Random();
    return random.nextInt(99 - 1 + 1) + 1 + "." + getAuthor().substring(0, 3) + "." +
      getIsbn().substring(getIsbn().length() - 1);
  }
  public void setAuthor(String a) {
    this.author = a;
  }
  public void setTitle(String t) {
    this.title = t;
  }
  public void setIsbn(String i) {
    this.isbn = i;
  }
  @Override
  public String toString() {
    return "[" + getIsbn() + "-" + getTitle().toUpperCase() + " by " +
      getAuthor().toUpperCase() + "-" + getCallNumber() + "]";
  }
}
public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String option;
    String userInput;
    String typeOfBook;
    String sale;

    BookstoreBook BB[] = new BookstoreBook[100];
    LibraryBook LB[] = new LibraryBook[200];
    while (true) {
      System.out.println("Would you like to create a book object? (yes/no)");
      option = sc.nextLine();
      if (!(option.equals("yes") || option.equals("no"))) {
        System.out.print("Please!!! Enter yes/No: ");
        continue;
      }
      if (option.equalsIgnoreCase("yes")) {
        System.out.println("Please enter the author, title ad the isbn of the book separated by /: ");
        userInput = sc.nextLine();
        String[] split = userInput.split("/");
        System.out.println("Got it!");
        System.out.println("Now, tell me if it is a bookstore book or a library book (enter BB for bookstore book or LB for library book): ");
        typeOfBook = sc.nextLine();
        if (typeOfBook.equalsIgnoreCase("BB")) {
          BookstoreBook book = new BookstoreBook();
          book.setAuthor(split[0]);
          book.setTitle(split[1]);
          book.setIsbn(split[2]);
          System.out.println("Got it!");
          System.out.println("Please enter the list price of " +
            book.getTitle().toUpperCase() + " by " + book.getAuthor().toUpperCase());
          book.setPrice(sc.nextDouble());
          System.out.println("Is it on sale? (y/n)");
          sale = sc.next();
          if (sale.equalsIgnoreCase("y")) {
            System.out.println("Enter deduction percentage");
            book.setSale(((100 - sc.nextInt()) * 0.01) * book.getPrice());
            System.out.println("Got it!");
          } else if(sale.equalsIgnoreCase("n")){
            book.setSale(book.getPrice());
            System.out.println("Got it!");
          }
          for (int i = 0; i < 100; i++) {
            if (BB[i] == null) {
              BB[i] = book;
              break;
            }
          }
          System.out.println("Here is your bookstore book information\n" + book.toString());
          BookstoreBook.numBooks += 1;
        }
        if (typeOfBook.equalsIgnoreCase("LB")) {
          LibraryBook book = new LibraryBook();

          book.setAuthor(split[0]);
          book.setTitle(split[1]);
          book.setIsbn(split[2]);
          System.out.println("Got it!");
          System.out.println("Here is your library information\n" +
            book.toString());
          LibraryBook.numBooks += 1;
          for (int i = 0; i < 200; i++) {
            if (LB[i] == null) {
              LB[i] = book;
              break;
            }
          }
        }
      }
      if (option.equalsIgnoreCase("no")) {
        System.out.println("Sure!\nHere are all your books");
        System.out.println("Library books (" + LibraryBook.numBooks + ")");

        int i = 0;
        while (LB[i] != null) {
          System.out.println(LB[i].toString());
          i += 1;
        }
        System.out.println("Bookstore books (" + BookstoreBook.numBooks + ")");

        i = 0;
        while (BB[i] != null) {
          System.out.println(BB[i].toString());
          i += 1;
        }
        System.out.println("Take care!");
      }
    }
  }
}