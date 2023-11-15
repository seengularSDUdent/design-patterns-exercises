import java.util.*;
import java.lang.*;

public class Main {
  public static void main(String[] args) {

    Character character = new Character(3,3);
    Character enemy = new Character(7,3);

    character.setEnemy(enemy);
    enemy.setEnemy(character);

    System.out.println(character.locationByX + " " + character.locationByY);
    System.out.println(enemy.locationByX + " " + enemy.locationByY);

    BowAndArrow sword = new BowAndArrow();
    character.setWeapon(sword);

    character.attackEnemy();
    System.out.println(enemy.getHealth());
  }
}

interface Weapon{

  public int attack();
  public int getDistance();
}

class WeaponSample implements Weapon{

  int damage = 15;
  int distance = 2;

  public int getDistance(){
    return this.distance;
  }

  public int attack(){
    return this.damage;
  }

}

class Sword extends WeaponSample{

  public Sword(){
    super.damage = 15;
    super.distance = 2;
  }
}

class Axe extends WeaponSample{

  public Axe(){
    super.damage = 20;
    super.distance = 2;
  }
}

class BowAndArrow extends WeaponSample{

  public BowAndArrow(){
    super.damage = 5;
    super.distance = 10;
  }
}

class MagicSpell extends WeaponSample{

  public MagicSpell(){
    super.damage = 8;
    super.distance = 4;
  }
}

class Character{
  private Weapon weapon;
  private int health = 100;
  private Character enemy;
  int locationByX;
  int locationByY;

  Character(int x, int y){
    this.locationByX = x;
    this.locationByY = y;
  }
  
  public int getHealth(){
    return this.health;
  }

  public void setHealth(int health){
    this.health = health;
  }

  public void setWeapon(Weapon setValue){
    this.weapon = setValue;
  }

  public void setEnemy(Character setValue){
    this.enemy = setValue;
  }

  public int getSubjectDistance(Character targetCharacter){
    return (int)Math.sqrt(Math.pow((targetCharacter.locationByX - this.locationByX), 2) + Math.pow((targetCharacter.locationByY - this.locationByY), 2));
  }

  public void attackEnemy(){
    if(getSubjectDistance(enemy) <= weapon.getDistance()){ 
      this.enemy.setHealth(enemy.getHealth() - weapon.attack());
    }
  }
}
