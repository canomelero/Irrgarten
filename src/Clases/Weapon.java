
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
        this.power = otra.power;
        this.uses = otra.uses;
    }
    
    public Weapon(){
        this.power = 0;
        this.uses = 0;
    }
    
    
    // Métodos
    public float attack() {
        float ataque = this.power;
        
        if(this.uses > 0) {
            this.uses--;
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
}
