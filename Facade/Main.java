import java.util.*;

public class Main
{
	public static void main(String[] args) {
		Database database = new Database();
		database.data().put(new Book("hi", "yau"), 13);
		
		//System.out.println(database.data().keySet().forEach((key) -> {System.out.println(key.title + " " + key.author);}) + " " + database.data().values());
	}
}

interface LibraryLightInterface{
    
    public void borrowBook(Book book);
    public void returnBook(Book book);
    public Book checkByTitle(String title);
    public Book checkByAuthor(String author);
    public boolean checkAvailability(Book book);
}

class LibraryLight implements LibraryLightInterface{
    
    Database libraryDatabase = new Database();
    
    public void borrowBook(Book book){
        if(checkAvailability(book)){
            libraryDatabase.decreaseBook(book);
        }
    }
    
    public void returnBook(Book book){
        libraryDatabase.increaseBook(book);
    }
    
    public Book checkByTitle(String title){
        return libraryDatabase.getByTitle(title);
    }
    
    public Book checkByAuthor(String author){
        return libraryDatabase.getByAuthor(author);
    }
    
    public boolean checkAvailability(Book book){
        if(libraryDatabase.getQuantity(book) > 0){
            return true;
        }
        return false;
    }
}

class Book{
    public String title;
    public String author;
    
    public Book(String title, String author){
        this.title = title;
        this.author = author;
    }
    
    public String getTitle(){
        return this.title;
    }
    
    public String getAuthor(){
        return this.author;
    }
}

class Library{
    
    Database libraryDatabase;
    
    public Library(Database database){
        this.libraryDatabase = database;
    }
    
    
    
}

class Database{
    private Map<Book, Integer> databaseCurrent = new HashMap<>();
    
    public Database(){
        databaseCurrent.put(new Book("War and Peace", "Lev Tolstoy"), 20);
        databaseCurrent.put(new Book("Karamazov Brothers", "Fedor Dostaevsky"), 20);
        databaseCurrent.put(new Book("The Maze Runner", "James Dashner"), 12);
        databaseCurrent.put(new Book("Java 8.0", "Gerbert Shildt"), 5);
    }
    
    public Book getByTitle(String title){
        for(Map.Entry<Book, Integer> entry : this.databaseCurrent.entrySet()){
            if(entry.getKey().getTitle().equals(title)){
                return entry.getKey();
            }
        }
        return new Book("Unknown", "Unknown");
    }
    
    public Book getByAuthor(String author){
        for(Map.Entry<Book, Integer> entry : this.databaseCurrent.entrySet()){
            if(entry.getKey().getAuthor().equals(author)){
                return entry.getKey();
            }
        }
        return new Book("Unknown", "Unknown");
    }
    
    public Map data(){
        return this.databaseCurrent;
    }
    
    public void addBook(Book setValue){
        databaseCurrent.put(setValue, null);
    }
    
    public void increaseBook(Book setValue){
        if(databaseCurrent.containsKey(setValue)){
            databaseCurrent.put(setValue, databaseCurrent.get(setValue) + 1);
        }
        else{
            databaseCurrent.put(setValue, 1);
        }
    }
    
    public void increaseBookQuantity(Book setValue, int setQuantity){
        if(databaseCurrent.containsKey(setValue)){
            databaseCurrent.put(setValue, databaseCurrent.get(setValue) + setQuantity);
        }
        else{
            databaseCurrent.put(setValue, setQuantity);
        }
    }
    
    public void removeBookAll(Book remValue){
        databaseCurrent.remove(remValue);
    }
    
    public void decreaseBook(Book remValue){
        if(databaseCurrent.containsKey(remValue) && (databaseCurrent.get(remValue) > 0)){
            databaseCurrent.put(remValue, databaseCurrent.get(remValue) - 1);
        }
        else{
            databaseCurrent.put(remValue, 0);
        }
    }
    
    public void decreaseBookQuantity(Book remValue, int remQuantity){
        if(databaseCurrent.containsKey(remValue) && (databaseCurrent.get(remValue) > remQuantity)){
            databaseCurrent.put(remValue, databaseCurrent.get(remValue) - remQuantity);
        }
        else{
            databaseCurrent.put(remValue, 0);
        }
    }
    
    public int getQuantity(Book getValue){
        return databaseCurrent.get(getValue);
    }
}