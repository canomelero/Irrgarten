
package Clases;
import java.util.ArrayList;
import Enumerados.*;

public class Game {
    // Atributos
    private static final int MAX_ROUNDS = 10;
    private int currentPlayerIndex;
    private String log;
    private Player currentPlayer;
    private ArrayList<Player> players;
    private ArrayList<Monster> monsters;
    private Labyrinth labyrinth;
    
    
    // Constructores
    public Game(int nplayers) {
        this.players = new ArrayList<>(nplayers);
        
        for(int i = 0; i < nplayers; i++) {
            players.add(new Player(i, Dice.randomIntelligence(), Dice.randomStrength()));
        }
        
        this.currentPlayerIndex = Dice.whoStarts(nplayers);
        this.currentPlayer = players.get(currentPlayerIndex);
        this.log = "Iniciando el juego...";
        this.monsters = new ArrayList<>(nplayers*2);    // habrá el doble de monstruos que de jugadores
        this.labyrinth = new Labyrinth();       // a que valores pongo la matriz del laberinto ????
    }
    
    
    // Métodos
    public boolean finished() {
        
    }
    
    public boolean nextStep(Directions preferredDirection) {
        throw new UnsupportedOperationException();
    }
    
    public GameState getGameState() {
        
    }
    
    private void configureLabyrinth() {
        
    }
    
    private void nextPlayer() {
        
    }
    
    private Directions actualDirection(Directions preferredDirection) {
        throw new UnsupportedOperationException();
    }
    
    private GameCharacter combat(Monster monster) {
        throw new UnsupportedOperationException();
    }
    
    private void manageReward(GameCharcater winner) {
        throw new UnsupportedOperationException();
    }
    
    private void manageResurrection() {
        throw new UnsupportedOperationException();
    }
    
    private void logPlayerWon() {
        
    }
    
    private void logMonsterWon() {
        
    }
    
    private void logResurrected() {
        
    }
    
    private void logPlayerSkipTurn() {
        
    }
    
    private void logPlayerNoOrders() {
        
    }
    
    private void logNoMonster() {
        
    }
    
    private void logRounds(int rounds, int max) {
        
    }
}
