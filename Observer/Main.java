import java.util.*;

public class Main {
  public static void main(String[] args) {
    Stock stock0 = new Stock("aaa01", 100);
    Stock stock1 = new Stock("aaa02", 110);
    Stock stock2 = new Stock("aaa03", 120);

    Investor investor0 = new Investor("Daulet");
    Investor investor1 = new Investor("Arman");
    Investor investor2 = new Investor("Zangar");

    stock0.registerInvestor(investor0);
    stock0.registerInvestor(investor1);
    stock0.registerInvestor(investor2);

    stock0.unregisterInvestor(investor1);

    for(Investor i : stock0.investors){
      System.out.println(i.name);
    }
    System.out.println(stock0.investors);

    stock1.registerInvestor(investor0);
    stock1.registerInvestor(investor1);
    stock1.registerInvestor(investor2);

    stock1.unregisterInvestor(investor0);
    stock1.unregisterInvestor(investor2);

    stock2.registerInvestor(investor2);

    stock0.updatePrice(90);
    stock1.updatePrice(104);
    stock2.updatePrice(79);
}
}

class Stock{
  //Fields
  String symbol;
  double price;
  List<Investor> investors = new ArrayList<>();

  public Stock(String symbol, double price){
    this.symbol = symbol;
    this.price = price;
  }

  public void registerInvestor(Investor addValue){
    this.investors.add(addValue);
    //this.investors.get(this.investors.indexOf(addValue)).subscribeStock(this);
  }

  public void unregisterInvestor(Investor remValue){
    // for(int i = 0; i < this.investors.size(); i++){
    //   if(this.investors.get(i).equals(remValue)){
    //     this.investors.remove(i);
    //   }
    // }
    this.investors.remove(this.investors.indexOf(remValue));
    //this.investors.get(this.investors.indexOf(remValue)).unsubscribeStock(this);
  }

  public void setPrice(double price){
    this.price = price;
  }

  public double getPrice(){
    return this.price;
  }
  
  public String getSymbol(){
    return this.symbol;
  }

  public void updatePrice(double price){
    for(Investor currentInvestor : this.investors){
      currentInvestor.update(this, price);
    }
  }
}

class Investor{
  String name;
  List<Stock> stocks = new ArrayList<>();

  public Investor(String name){
    this.name = name;
  }

  public void subscribeStock(Stock stock){
    this.stocks.add(stock);
  }

  public void unsubscribeStock(Stock stock){
    this.stocks.remove(this.stocks.indexOf(stock));
  }

  public void update(Stock stock, double price){
    //Stock currentStock = stocks.get(stocks.indexOf(stock));
    // if(stocks.contains(stock)){
    //   currentStock.setPrice(price);
    //   System.out.println("The price of stock: \"" + currentStock.getSymbol() + "\" has changed to - " + currentStock.getPrice());
    // }
    // else{
    //   System.out.println("You don't have this stock: \"" + currentStock.getSymbol() + "\"");
    // }
    stock.setPrice(price);
    System.out.println(this.name + ", " + "The price of stock: \"" + stock.getSymbol() + "\" has changed to - " + stock.getPrice());
  }
}
