
package Clases;
import java.util.ArrayList;
import Enumerados.*;
import java.util.Random;

public class Game {
    // Atributos
    private static final int NUM_ROW = 8;
    private static final int NUM_COL = 8;
    private static final int EXIT_ROW = 7;
    private static final int EXIT_COL = 6;
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
        this.monsters = new ArrayList<>();    // habrá el mismo número de monstruos que de jugadores
        this.labyrinth = new Labyrinth(NUM_ROW, NUM_COL, EXIT_ROW, EXIT_COL);
        configureLabyrinth();   // ??
    }
    
    
    // Métodos
    public boolean finished() {
        return labyrinth.haveAWinner();
    }
    
    public boolean nextStep(Directions preferredDirection) {
        throw new UnsupportedOperationException();
    }
    
    public GameState getGameState() {
        String strPlayers = "", 
               strMonsters = "";
        
        for(Player p : players) {
            strPlayers += p.toString();
        }
        
        for(Monster m : monsters) {
            strMonsters += m.toString();
        }
        
        return new GameState(labyrinth.toString(), strPlayers, strMonsters, currentPlayerIndex, labyrinth.haveAWinner(), log);
    }
    
    private void configureLabyrinth() {
        int numMonsters = players.size();
        
        // Se añaden los obstáculos (valores de prueba no aleatorios)
        // labyrinth.addBlock(Orientation.HORIZONTAL, 2, 2, 4);    
        // labyrinth.addBlock(Orientation.VERTICAL, 5, 5, 3);
        
        // Se añaden monstruos al laberinto
        for(int i = 0; i < numMonsters; i++) {
            Monster m = new Monster("Monster" + String.valueOf(i), Dice.randomIntelligence(), Dice.randomStrength(), Dice.healthReward());
            monsters.add(m);
            labyrinth.addMonster(i+2, i+2, m);
        }
        
        // Se añaden jugadores ????
    }
    
    private void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        currentPlayer = players.get(currentPlayerIndex);
    }
    
    private Directions actualDirection(Directions preferredDirection) {
        throw new UnsupportedOperationException();
    }
    
    private GameCharacter combat(Monster monster) {
        throw new UnsupportedOperationException();
    }
    
    private void manageReward(GameCharacter winner) {
        throw new UnsupportedOperationException();
    }
    
    private void manageResurrection() {
        throw new UnsupportedOperationException();
    }
    
    private void logPlayerWon() {
        log += "El jugador " + currentPlayerIndex + " ha ganado el combate\n";
    }
    
    private void logMonsterWon() {
        log += "El monstruo ha ganado el combate\n";
    }
    
    private void logResurrected() {
        log += "El jugador " + currentPlayerIndex + " ha resucitado\n";
    }
    
    private void logPlayerSkipTurn() {
        log += "El jugador " + currentPlayerIndex + " ha perdido el turno por estar muerto\n";
    }
    
    private void logPlayerNoOrders() {
        log += "El jugador " + currentPlayerIndex + " no ha seguido las instrucciones del jugador humano\n";
    }
    
    private void logNoMonster() {
        log += "El jugador " + currentPlayerIndex + " se ha movido a una celda vacía o no le ha sido posible moverse\n";
    }
    
    private void logRounds(int rounds, int max) {
        log += "Se han producido " + rounds + " rondas de un máximo de " + max + " rondas de combate\n";
    }
}
