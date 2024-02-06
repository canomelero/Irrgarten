
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
        this.protection = otro.protection;
        this.uses = otro.uses;
    }
    
    public Shield() {
        this.protection = 0;
        this.uses = 0;
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
