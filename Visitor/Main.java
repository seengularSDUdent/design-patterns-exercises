import java.util.*;

public class Main {
  public static void main(String[] args) {
    Book book0 = new Book("book0", 2400, 400, 14, 24, 6);
    Book book1 = new Book("book1", 1800, 300, 12, 22, 4);
    Book book2 = new Book("book2", 2600, 600, 24, 36, 5);
    Book book3 = new Book("book3", 2100, 450, 18, 28, 8);
    Book book4 = new Book("book4", 3500, 800, 16, 22, 8);

    Movie movie0 = new Movie("movie0", 4000, 40, 8, 10, 2);
    Movie movie1 = new Movie("movie1", 3500, 36, 6, 8, 3);
    Movie movie2 = new Movie("movie2", 2800, 28, 6, 14, 1);
    Movie movie3 = new Movie("movie3", 4600, 56, 10, 12, 4);

    Music music0 = new Music("music0", 2000, 24, 8, 14, 2);
    Music music1 = new Music("music1", 1500, 18, 8, 14, 2);
    Music music2 = new Music("music2", 2500, 36, 8, 14, 2);

    ShippingCostVisitor visitor = new ShippingCostVisitor();
    Order order = new Order();
    order.setVisitor(visitor);

    order.addProduct(book0);
    order.addProduct(book3);
    order.addProduct(movie2);
    order.addProduct(music1);

    order.launch();

    System.out.println(visitor.calculateCost());
  }
}

class Product{
  String name;
  double price;
  double weight;
  double width;
  double height;
  double depth;

  public void accept(ShippingCostVisitor visitor){

  }

  public Product(String name, double price, double weight, double width, double height, double depth){
    this.name = name;
    this.price = price;
    this.weight = weight;
    this.width = width;
    this.height = height;
    this.depth = depth;
  }
}

class Book extends Product{
  public void accept(ShippingCostVisitor visitor){
    visitor.visitBook(this);
  }

  public Book(String name, double price, double weight, double width, double height, double depth){
    super(name, price, weight, width, height, depth);
  }
}

class Movie extends Product{
  public void accept(ShippingCostVisitor visitor){
    visitor.visitMovie(this);
  }

  public Movie(String name, double price, double weight, double width, double height, double depth){
    super(name, price, weight, width, height, depth);
  }
}

class Music extends Product{
  public void accept(ShippingCostVisitor visitor){
    visitor.visitMusic(this);
  }

  public Music(String name, double price, double weight, double width, double height, double depth){
    super(name, price, weight, width, height, depth);
  }
}

class ShippingCostVisitor{
  double totalWeight;
  double totalVolume;

  public void visitBook(Book product){
    double volume = product.width * product.height * product.depth;
    this.totalVolume = this.totalVolume + volume;
    this.totalWeight = this.totalWeight + product.weight;
  }
  
  public void visitMovie(Movie product){
    double volume = product.width * product.height * product.depth;
    this.totalVolume = this.totalVolume + volume;
    this.totalWeight = this.totalWeight + product.weight;
  }

  public void visitMusic(Music product){
    double volume = product.width * product.height * product.depth;
    this.totalVolume = this.totalVolume + volume;
    this.totalWeight = this.totalWeight + product.weight;
  }

  public double calculateCost(){
    double weightCost = this.totalWeight * 0.25;
    double volumeCost = this.totalVolume * 0.2;

    if(weightCost > volumeCost){
      return weightCost;
    }
    else if(volumeCost > weightCost){
      return volumeCost;
    }
    else{
      return weightCost;
    }
  }

  public void clearFields(){
    this.totalVolume = 0;
    this.totalWeight = 0;
  }
}

class Order{
  List<Product> list = new ArrayList<>();
  ShippingCostVisitor visitor;

  public void setVisitor(ShippingCostVisitor visitor){
    this.visitor = visitor;
  }

  public void addProduct(Product product){
    this.list.add(product);
  }
  
  public void launch(){
    if(this.visitor != null){
      for(Product i : this.list){
        i.accept(visitor);
      }
    }
    else{
      System.out.println("Visitor not set!");
    }
  }
}


















