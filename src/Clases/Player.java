
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
    private char number;
    private float intelligence;
    private float strength;
    private float health;
    private int row;
    private int col;
    private int consecutiveHits;
    private ArrayList<Weapon> weapons = new ArrayList<>();
    private ArrayList<Shield> shields = new ArrayList<>();
    
    
    // Constructores
    public Player(char number, float intelligence, float strength) {
        this.number = number;
        this.name = "Player " + number;
        this.intelligence = intelligence;
        this.strength = strength;
        this.health = 0;
        this.row = 0;
        this.col = 0;
        this.consecutiveHits = 0;
    }
    
    public Player(Player otro) {
        this(otro.number, otro.intelligence, otro.strength);
    }
    
    public Player() {
        this('0', 0, 0);
    }
    
    
    // Métodos
    public void resurrect() {
        
    }
    
    public int getRow() {
        
    }
    
    public int getCol() {
        
    }
    
    public char getNumber() {
        
    }
    
    public void setPos(int row, int col) {
        
    }
    
    public boolean dead() {
        
    }
    
    public Directions move(Directions direction, Directions[] validMoves) {
        
    }
    
    public float attack() {
        
    }
    
    public boolean defend(float receivedAttack) {
        
    }
    
    public void receiveReward() {
        
    }

    @Override
    public String toString() {
        return "Player{" + "name=" + name + ", number=" + number + ", intelligence=" + intelligence + ", strength=" + strength + ", "
                + "health=" + health + ", row=" + row + ", col=" + col + ", consecutiveHits=" + consecutiveHits + ", weapons=" + weapons 
                    + ", shields=" + shields + '}';
    }

    private void receiveWeapon(Weapon w) {
        
    }
    
    private void receiveShield(Shield s) {
        
    }
    
    private Weapon newWeapon() {
        
    }
    
    private Shield newShield() {
        
    }
    
    private float sumWeapons() {
        
    }
    
    private float sumShields() {
        
    }
    
    private float defensiveEnergy() {
        
    }
    
    private boolean manageHit(float receivedAttack) {
        
    }
    
    private void resetHits() {
        
    }
    
    private void gotWounded() {
        
    }
    
    private void incConsecutiveHits() {
        
    }
}
