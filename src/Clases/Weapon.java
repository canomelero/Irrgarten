
package Clases;

public class Weapon {
    // Atributos
    private float power;
    private int uses;

    
    // Constructores
    public Weapon(float power, int uses) {
        this.power = power;
        this.uses = uses;
    }

    public Weapon(Weapon otra) {
        this(otra.power, otra.uses);
    }
    
    public Weapon(){
        this(Dice.weaponPower(), Dice.usesLeft());
    }
    
    
    // Métodos
    public float attack() {
        float ataque = this.power;
        
        if(uses > 0) {
            uses--;
        }
        else {
            ataque = 0;
        }
        
        return ataque;
    }

    @Override
    public String toString() {
        return "W[" + power + ", " + uses + ']';
    }
    
    public boolean discard() {
        return Dice.discardElement(uses);
    }
}
