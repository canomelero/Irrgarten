
package Clases;
import java.util.ArrayList;

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
        // reservar espacio para el resto de atributos
    }
    
    
    // Métodos
    
}
