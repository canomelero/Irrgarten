
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
    public Monster(String name, float intelligence, float strength, float health) {
        this.name = name;
        this.intelligence = intelligence;
        this.strength = strength;
        this.health = health;
        setPos(0, 0);
    }
    
    public Monster(Monster otro) {
        this(otro.name, otro.intelligence, otro.strength, otro.health);
    }
    
    public Monster() {
        this("", 0, 0, 0);
    }
    
    
    // Métodos
    public boolean dead() {
        return health <= 0;
    }
    
    public float attack() {
        return Dice.intensity(strength);
    }
    
    public boolean defend(float receivedAttack) {
        boolean isDead = dead();
        
        if(!isDead) {
            float defensiveEnergy = Dice.intensity(intelligence);
            
            if(defensiveEnergy < receivedAttack) {
                gotWounded();
                isDead = dead();
            }
        }
        
        return isDead;
    }
    
    public void setPos(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public String toString() {
        return "Monster{" + "name=" + name + ", intelligence=" + intelligence + ", strength=" 
                + strength + ", health=" + health + ", row=" + row + ", col=" + col + "}\n";
    }
    
    private void gotWounded() {
        if(health > 0) {
            health--;
        }
    }
}
