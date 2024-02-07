
package Clases;

public class Monster {
    // Atributos
    private static final int INITIAL_HEALTH = 5;
    private String name;
    private float intelligence;
    private float strength;
    private float health;
    private int row;
    private int col;
    
    
    // Constructores
    public Monster(String name, float intelligence, float strength, float health, int row, int col) {
        this.name = name;
        this.intelligence = intelligence;
        this.strength = strength;
        this.health = health;
        setPos(row, col);
    }
    
    public Monster(Monster otro) {
        this(otro.name, otro.intelligence, otro.strength, otro.health, otro.row, otro.col);
    }
    
    public Monster() {
        this("", 0, 0, 0, 0, 0);
    }
    
    
    // Métodos
    public boolean dead() {
        boolean muerto = false;
        
        if(health <= 0) {
            muerto = true;
        }
        
        return muerto;
    }
    
    public float attack() {
        return Dice.intensity(strength);
    }
    
    public boolean defend(float receivedAttack) {
        throw new UnsupportedOperationException();
    }
    
    public void setPos(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public String toString() {
        return "Monster{" + "name=" + name + ", intelligence=" + intelligence + ", strength=" 
                + strength + ", health=" + health + ", row=" + row + ", col=" + col + '}';
    }
    
    private void gotWounded() {
        if(health > 0) {
            health--;
        }
    }
}
