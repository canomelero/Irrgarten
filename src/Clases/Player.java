
package Clases;
import java.util.ArrayList;
import java.util.Arrays;
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
        int size = validMoves.length;
        boolean contained = false;
        Directions dirFinal;
        
        for(Directions dir : validMoves) {
            if(dir == direction) {
                contained = true;
                break;
            }
        }
        
        if(size > 0 && !contained) {
            dirFinal = validMoves[0];
        }
        else {
            dirFinal = direction;
        }
        
        return dirFinal;
    }
    
    public float attack() {
        return strength + sumWeapons();
    }
    
    public boolean defend(float receivedAttack) {
        return manageHit(receivedAttack);
    }
    
    public void receiveReward() {
        int wReward = Dice.weaponsReward(),
            sReward = Dice.shieldsReward();
        
        for(int i = 0; i < wReward; i++) {
            Weapon wnew = new Weapon();
            receiveWeapon(wnew);
        }
        
        for(int i = 0; i < sReward; i++) {
            Shield snew = new Shield();
            receiveShield(snew);
        }
        
        int extraHealth = Dice.healthReward();
        health += extraHealth;
    }

    @Override
    public String toString() {
        return "Player{" + "name=" + name + ", number=" + number + ", intelligence=" + intelligence + ", strength=" + strength + ", "
                + "health=" + health + ", row=" + row + ", col=" + col + ", consecutiveHits=" + consecutiveHits + ", weapons=" + weapons 
                    + ", shields=" + shields + "}\n";
    }

    private void receiveWeapon(Weapon w) {
        for(Weapon wi : weapons) {
            boolean discard = wi.discard();
            
            if(discard) {
                weapons.remove(wi);
            }
        }
        
        int size = weapons.size();
        
        if(size < MAX_WEAPONS) {
            weapons.add(w);
        }
    }
    
    private void receiveShield(Shield s) {
        for(Shield si : shields) {
            boolean discard = si.discard();
            
            if(discard) {
                shields.remove(si);
            }
        }
        
        int size = shields.size();
        
        if(size < MAX_SHIELDS) {
            shields.add(s);
        }
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
        float defense = defensiveEnergy();
        boolean lose;
        
        if(defense < receivedAttack) {
            gotWounded();
            incConsecutiveHits();
        }
        else {
            resetHits();
        }
        
        if(consecutiveHits == HITS2LOSE || dead()) {
            resetHits();
            lose = true;
        }
        else {
            lose = false;
        }
        
        return lose;
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
