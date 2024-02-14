
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
        configureLabyrinth();   
    }
    
    
    // Métodos
    public boolean finished() {
        return labyrinth.haveAWinner();
    }
    
    public boolean nextStep(Directions preferredDirection) {
        String log = "";
        boolean dead = currentPlayer.dead();
        
        if(!dead) {
            Directions direction = actualDirection(preferredDirection);
            
            if(direction != preferredDirection) {
                logPlayerNoOrders();
            }
            
            Monster monster = labyrinth.putPlayer(direction, currentPlayer);
            
            if(monster == null) {
                logNoMonster();
            }
            else {
                GameCharacter winner = combat(monster);
                manageReward(winner);
            }
        }
        else {
            manageResurrection();
        }
        
        boolean endGame = finished();
        
        if(!endGame) {
            nextPlayer();
        }
        
        return endGame;
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
        int numMonsters = 4;
        
        // Se añaden algún obstáculo 
        labyrinth.addBlock(Orientation.HORIZONTAL, 2, 2, 4);    
        labyrinth.addBlock(Orientation.VERTICAL, 6, 5, 3);
        
        // Se añaden monstruos al laberinto
        for(int i = 0; i < numMonsters; i++) {
            Monster m = new Monster("Monster" + String.valueOf(i), Dice.randomIntelligence(), Dice.randomStrength(), Dice.healthReward());
            monsters.add(m);
            labyrinth.addMonster(i+2, i+2, m);
            
            if(i == numMonsters-1) {    // se añade un mosntruo más
               labyrinth.addMonster(2, 6, m); 
            }
        }
    }
    
    private void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        currentPlayer = players.get(currentPlayerIndex);
    }
    
    private Directions actualDirection(Directions preferredDirection) {
        int currentRow = currentPlayer.getRow(),
            currentCol = currentPlayer.getCol();

        Directions[] validMoves = labyrinth.validMoves(currentRow, currentCol);
        Directions output = currentPlayer.move(preferredDirection, validMoves);
        
        return output;
    }
    
    private GameCharacter combat(Monster monster) {
        int rounds = 0;
        GameCharacter winner = GameCharacter.PLAYER;
        float playerAttack = currentPlayer.attack();
        boolean lose = monster.defend(playerAttack);
        
        while(!lose && rounds < MAX_ROUNDS) {
            winner = GameCharacter.MONSTER;
            rounds++;
            float monsterAttack = monster.attack();
            lose = currentPlayer.defend(monsterAttack);
            
            if(!lose) {
                playerAttack = currentPlayer.attack();
                winner = GameCharacter.PLAYER;
                lose = monster.defend(playerAttack);
            }
        }
        
        logRounds(rounds, MAX_ROUNDS);
        
        return winner;
    }
    
    private void manageReward(GameCharacter winner) {
        if(winner == GameCharacter.PLAYER) {
            currentPlayer.receiveReward();
            logPlayerWon();
        }
        else {
            logMonsterWon();
        }
    }
    
    private void manageResurrection() {
        boolean resurrect = Dice.resurrectPlayer();
        
        if(resurrect) {
            currentPlayer.resurrect();
            logResurrected();
        }
        else {
            logPlayerSkipTurn();
        }
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
        log += "El jugador " + currentPlayerIndex + " se ha movido a una celda vacia o no le ha sido posible moverse\n";
    }
    
    private void logRounds(int rounds, int max) {
        log += "Se han producido " + rounds + " rondas de un maximo de " + max + " rondas de combate\n";
    }
}
