
package Clases;
import java.util.ArrayList;
import Enumerados.*;

public class Player {
    // Atributos
    private static int MAX_WEAPONS = 2;
    private static int MAX_SHIELDS = 3;
    private static int INITIAL_HEALTH = 10;
    private static int HITS2LOSE = 3;
    private String name;
    private int number;     // uso un int en vez de char para evitar castings
    private float intelligence;
    private float strength;
    private float health;
    private int row;
    private int col;
    private int consecutiveHits;
    private ArrayList<Weapon> weapons;
    private ArrayList<Shield> shields;
    
    
    // Constructores
    public Player(int number, float intelligence, float strength) {
        this.number = number;
        this.name = "Player " + String.valueOf(number);
        this.intelligence = intelligence;
        this.strength = strength;
        this.row = 0;   // ??
        this.col = 0;   // ??
        resurrect();    // al crear un jugador toma los mismos valores iniciales que si resucita
    }
    
    public Player(Player otro) {
        this(otro.number, otro.intelligence, otro.strength);
    }
    
    public Player() {
        this('0', 0, 0);
    }
    
    
    // Métodos
    public void resurrect() {
        resetHits();
        this.health = INITIAL_HEALTH;
        this.weapons = new ArrayList<>(MAX_WEAPONS);
        this.shields = new ArrayList<>(MAX_SHIELDS);
    }
    
    public int getRow() {
        return row;
    }
    
    public int getCol() {
        return col;
    }
    
    public int getNumber() {
        return number;
    }
    
    public void setPos(int row, int col) {
        this.row = row;
        this.col = col;
    }
    
    public boolean dead() {
        return health <= 0;
    }
    
    public Directions move(Directions direction, Directions[] validMoves) {
        throw new UnsupportedOperationException();
    }
    
    public float attack() {
        return strength + sumWeapons();
    }
    
    public boolean defend(float receivedAttack) {
        return manageHit(receivedAttack);
    }
    
    public void receiveReward() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return "Player{" + "name=" + name + ", number=" + number + ", intelligence=" + intelligence + ", strength=" + strength + ", "
                + "health=" + health + ", row=" + row + ", col=" + col + ", consecutiveHits=" + consecutiveHits + ", weapons=" + weapons 
                    + ", shields=" + shields + "}\n";
    }

    private void receiveWeapon(Weapon w) {
        throw new UnsupportedOperationException();
    }
    
    private void receiveShield(Shield s) {
        throw new UnsupportedOperationException();
    }
    
    private Weapon newWeapon() {
        return new Weapon(Dice.weaponPower(), Dice.usesLeft());
    }
    
    private Shield newShield() {
        return new Shield(Dice.shieldPower(), Dice.usesLeft());
    }
    
    private float sumWeapons() {
        float suma_armas = 0;
        
        for(Weapon arma : weapons) {
            suma_armas += arma.attack();
        }
        
        return suma_armas;
    }
    
    private float sumShields() {
        float suma_escudos = 0;
        
        for(Shield escudo : shields) {
            suma_escudos += escudo.protect();
        }
        
        return suma_escudos;
    }
    
    private float defensiveEnergy() {
        return intelligence + sumShields();
    }
    
    private boolean manageHit(float receivedAttack) {
        throw new UnsupportedOperationException();
    }
    
    private void resetHits() {
        consecutiveHits = 0;
    }
    
    private void gotWounded() {
        if(health > 0) {            // sin el if o con el ?????
            health--;
        }
    }
    
    private void incConsecutiveHits() {
        consecutiveHits++;
    }
}
