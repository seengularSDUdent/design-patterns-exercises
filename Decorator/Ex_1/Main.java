import java.util.*;

public class Main
{
	public static void main(String[] args) {
		Pizza pizza = new Pizza();
		PizzaDecorator decorator = new PizzaDecorator();
		
		decorator.setWrappee(pizza);
		
		System.out.println(pizza.getPrice() + " " + decorator.getPrice());
		
		PepperoniTopping pt = new PepperoniTopping();
		pt.setWrappee(pizza);
		pt.updatePrice();
		
		System.out.println(decorator.getPrice());
		
		MushroomTopping mt = new MushroomTopping();
		mt.setWrappee(pizza);
		pt.updatePrice();
		
		System.out.println(decorator.getPrice());
	}
}

class Pizza{
    
    int price = 500;
    
    public int getPrice(){
        return this.price;
    }
}

class PizzaDecorator{
    
    Pizza wrappee;
    
    public void setWrappee(Pizza setValue){
        this.wrappee = setValue;
    }
    
    public int getPrice(){
        return wrappee.getPrice();
    }
}

class PepperoniTopping extends PizzaDecorator{
    
    public void updatePrice(){
        super.wrappee.price = super.wrappee.price + 150;
    }
}

class MushroomTopping extends PizzaDecorator{
    
    public void updatePrice(){
        super.wrappee.price = super.wrappee.price + 100;
    }
}