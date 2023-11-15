import java.util.*;

public class Main
{
	public static void main(String[] args) {
	    
		CharacterFactory warrior = new WarriorFactory();
		CharacterFactory mage = new MageFactory();
		CharacterFactory archer = new ArcherFactory();
		
		CharacterCreator creator = new CharacterCreator();
		
		creator.setFactory(warrior);
		Character warriorObject = creator.createCharacter("Warrior");
		
		creator.setFactory(mage);
		Character mageObject = creator.createCharacter("Mage");
		
		creator.setFactory(archer);
		Character archerObject = creator.createCharacter("Archer");
		
		System.out.println(warriorObject + " " + warriorObject.name + " " + warriorObject.classification);
		System.out.println(mageObject + " " + mageObject.name + " " + mageObject.classification);
		System.out.println(archerObject + " " + archerObject.name + " " + archerObject.classification);
		
		CharacterChanger changer = new CharacterChanger();
		CharacterInventory inventory = new CharacterInventory();
		
		changer.setCharacter(warriorObject);
		inventory.setCharacter(warriorObject);
		
		changer.changeName("Aragorn");
		
		inventory.addAbility(new BerserkAbility());
		inventory.addAbility(new ArmorsphereAbility());
		
		inventory.addEquipment(new ChestarmorEquipment());
		inventory.addEquipment(new HelmetEquipment());
		inventory.addEquipment(new GlovesEquipment());
		
		changer.setCharacter(mageObject);
		inventory.setCharacter(mageObject);
		
		changer.changeName("Gendalf");
		
		inventory.addAbility(new LightningpowerAbility());
		inventory.addAbility(new FireballAbility());
		
		inventory.addEquipment(new CloakEquipment());
		inventory.addEquipment(new HookEquipment());
		inventory.addEquipment(new HatEquipment());
		
		changer.setCharacter(archerObject);
		inventory.setCharacter(archerObject);
		
		changer.changeName("Legolas");
		
		inventory.addAbility(new HawkeyeAbility());
		inventory.addAbility(new ArmorsphereAbility());
		
		inventory.addEquipment(new GlovesEquipment());
		inventory.addEquipment(new ChestarmorEquipment());
		
		System.out.println(warriorObject + " " + warriorObject.name + " " + warriorObject.classification + " " + warriorObject.Abilities + " " + warriorObject.Equipment);
		System.out.println(mageObject + " " + mageObject.name + " " + mageObject.classification + " " + mageObject.Abilities + " " + mageObject.Equipment);
		System.out.println(archerObject + " " + archerObject.name + " " + archerObject.classification + " " + archerObject.Abilities + " " + archerObject.Equipment);
	}
}

class Character{
    
    protected String name;
    protected Object Appearance;
    protected List<Object> Abilities = new ArrayList<Object>();
    protected List<Object> Equipment = new ArrayList<Object>();
    protected Object Attributes;
    protected String classification;
    
    Character(String name){
        this.name = name;
    }
}

class CharacterChanger{
    
    Character currentCharacter;
    
    void setCharacter(Character setValue){
        this.currentCharacter = setValue;
    }
    
    void changeName(String setValue){
        currentCharacter.name = setValue;
    }
    
    void changeAppearance(Object setValue){
        currentCharacter.Appearance = setValue;
    }
    
    void changeAbilities(List<Object> setValue){
        currentCharacter.Abilities = setValue;
    }
    
    void changeEquipment(List<Object> setValue){
        currentCharacter.Equipment = setValue;
    }
    
    void changeAttributes(Object setValue){
        currentCharacter.Attributes = setValue;
    }
}

class CharacterInventory{
    
    Character currentCharacter;
    
    void setCharacter(Character setValue){
        this.currentCharacter = setValue;
    }
    
    void addAbility(Object ability){
        currentCharacter.Abilities.add(ability);
    }
    
    void addEquipment(Object equipment){
        currentCharacter.Equipment.add(equipment);
    }
}

abstract class CharacterFactory{
    abstract Character createCharacter(String name);
}

class WarriorFactory extends CharacterFactory{
    
    Character characterHolder;
    
    @Override
    Character createCharacter(String name){
        this.characterHolder = new Character(name);
        characterHolder.classification = "Warrior";
        return characterHolder;
    }
}

class MageFactory extends CharacterFactory{
    Character characterHolder;
    
    @Override
    Character createCharacter(String name){
        this.characterHolder = new Character(name);
        characterHolder.classification = "Mage";
        return characterHolder;
    }
}

class ArcherFactory extends CharacterFactory{
    Character characterHolder;
    
    @Override
    Character createCharacter(String name){
        this.characterHolder = new Character(name);
        characterHolder.classification = "Archer";
        return characterHolder;
    }
}

class CharacterCreator{
    
    CharacterFactory currentFactory;
    Character currentCharacter;
    
    void setFactory(CharacterFactory setValue){
        this.currentFactory = setValue;
    }
    
    Character createCharacter(String name){
        currentCharacter = currentFactory.createCharacter(name);
        return currentCharacter;
    }
}

interface Ability{
    
}

class BerserkAbility implements Ability{
    
}

class LightningpowerAbility implements Ability{
    
}

class HawkeyeAbility implements Ability{
    
}

class ArmorsphereAbility implements Ability{
    
}

class FireballAbility implements Ability{
    
}

class ArrowrainAbility implements Ability{
    
}

interface Equipment{
    
}

class HookEquipment implements Equipment{
    
}

class HatEquipment implements Equipment{
    
}

class HelmetEquipment implements Equipment{
    
}

class ChestarmorEquipment implements Equipment{
    
}

class CloakEquipment implements Equipment{
    
}

class GlovesEquipment implements Equipment{
    
}