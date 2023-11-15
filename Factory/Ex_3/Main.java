import java.util.*;

public class Main
{
	public static void main(String[] args) {
		
		FurnitureCreator creator = new FurnitureCreator();
		
		FurnitureFactory modernFactory = new ModernWoodFactory();
		FurnitureFactory traditionalFactory = new TraditionalMetalFactory();
		FurnitureFactory industrialFactory = new IndustrialGlassFactory();
		
		List<Furniture> Samples = new ArrayList<>();
		
		creator.setFactory(modernFactory);
		
		Furniture modernChair = creator.createChair();
		Furniture modernTable = creator.createTable();
		Furniture modernSofa = creator.createSofa();
		
		Samples.add(modernChair);
		Samples.add(modernTable);
		Samples.add(modernSofa);
		
		creator.setFactory(traditionalFactory);
		
		Furniture traditionalChair = creator.createChair();
		Furniture traditionalTable = creator.createTable();
		Furniture traditionalSofa = creator.createSofa();
		
		Samples.add(traditionalChair);
		Samples.add(traditionalTable);
		Samples.add(traditionalSofa);
		
		creator.setFactory(industrialFactory);
		
		Furniture industrialChair = creator.createChair();
		Furniture industrialTable = creator.createTable();
		Furniture industrialSofa = creator.createSofa();
		
		Samples.add(industrialChair);
		Samples.add(industrialTable);
		Samples.add(industrialSofa);
		
		Informer informer = new Informer();
		
		for(Furniture sample : Samples){
		    informer.setFurniture(sample);
		    informer.getInfo();
		}
	}
}

class Informer{
    
    Furniture current;
    
    public void setFurniture(Furniture current){
        this.current = current;
    }
    
    public void getInfo(){
        System.out.println(this.current.name + " " + this.current.style + " " + this.current.material + " " + this.current.price);
    }
}

class Furniture{
    
    String name;
    String style;
    String material;
    String price;
    
    public void setName(String name){
        this.name = name;
    }
}

abstract class FurnitureFactory{
    
    abstract Chair createChair();
    abstract Table createTable();
    abstract Sofa createSofa();
}

class ModernWoodFactory extends FurnitureFactory{
    
    public Chair createChair(){
        return new ModernChair();
    }
    
    public Table createTable(){
        return new ModernTable();
    }
    
    public Sofa createSofa(){
        return new ModernSofa();
    }
}

class TraditionalMetalFactory extends FurnitureFactory{
    
    public Chair createChair(){
        return new TraditionalChair();
    }
    
    public Table createTable(){
        return new TraditionalTable();
    }
    
    public Sofa createSofa(){
        return new TraditionalSofa();
    }
}

class IndustrialGlassFactory extends FurnitureFactory{
    
    public Chair createChair(){
        return new IndustrialChair();
    }
    
    public Table createTable(){
        return new IndustrialTable();
    }
    
    public Sofa createSofa(){
        return new IndustrialSofa();
    }
}

class FurnitureCreator{
    
    FurnitureFactory currentFactory;
    
    public void setFactory(FurnitureFactory setValue){
        this.currentFactory = setValue;
    }
    
    public Chair createChair(){
        return this.currentFactory.createChair();
    }
    
    public Table createTable(){
        return this.currentFactory.createTable();
    }
    
    public Sofa createSofa(){
        return this.currentFactory.createSofa();
    }
}

class Chair extends Furniture{
    
    public Chair(){
        super.name = "Chair";
    }
}

class Table extends Furniture{
    
    public Table(){
        super.name = "Table";
    }
}

class Sofa extends Furniture{
    
    public Sofa(){
        super.name = "Sofa";
    }
}

class ModernChair extends Chair{
    
    public ModernChair(){
        super.style = "Modern";
        super.material = "Wood";
        super.price = "400";
    }
}

class TraditionalChair extends Chair{
    
    public TraditionalChair(){
        super.style = "Traditional";
        super.material = "Metal";
        super.price = "350";
    }
}

class IndustrialChair extends Chair{
    
    public IndustrialChair(){
        super.style = "Industrial";
        super.material = "Glass";
        super.price = "300";
    }
}

class ModernTable extends Table{
    
    public ModernTable(){
        super.style = "Modern";
        super.material = "Wood";
        super.price = "2000";
    }
}

class TraditionalTable extends Table{
    
    public TraditionalTable(){
        super.style = "Traditional";
        super.material = "Metal";
        super.price = "1800";
        
    }
}

class IndustrialTable extends Table{
    
    public IndustrialTable(){
        super.style = "Industrial";
        super.material = "Glass";
        super.price = "1600";
    }
}

class ModernSofa extends Sofa{
    
    public ModernSofa(){
        super.style = "Modern";
        super.material = "Wood";
        super.price = "600";
    }
}

class TraditionalSofa extends Sofa{
    
    public TraditionalSofa(){
        super.style = "Traditional";
        super.material = "Metal";
        super.price = "500";
    }
}

class IndustrialSofa extends Sofa{
    
    public IndustrialSofa(){
        super.style = "Industrial";
        super.material = "Glass";
        super.price = "450";
    }
}