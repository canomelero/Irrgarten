
package Clases;

public class Shield {
    // Atributos
    private float protection;
    private int uses;
    
    
    // Constructores
    public Shield(float protection, int uses) {
        this.protection = protection;
        this.uses = uses;
    }
    
    public Shield(Shield otro) {
        this(otro.protection, otro.uses);
    }
    
    public Shield() {
        this(Dice.shieldPower(), Dice.usesLeft());
    }
    
    
    // Métodos
    public float protect() {
        float escudo = protection;
        
        if(uses > 0) {
            uses--;
        }
        else {
            escudo = 0;
        }
        
        return escudo;
    }

    @Override
    public String toString() {
        return "Shield[" + protection + ", " + uses + ']';
    }
    
    public boolean discard() {
        return Dice.discardElement(uses);
    }
}
