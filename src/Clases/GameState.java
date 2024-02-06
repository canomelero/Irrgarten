
package Clases;

public class GameState {
    // Atributos
    private String labyrinthv;  // estado del laberinto
    private String players;     // estado de los jugadores
    private String monsters;    // estado de los monstruos
    private int currentPlayer;  // jugador actual
    private boolean winner;     // ganador o no
    private String log;         // eventos interesantes ocurridos en el turno anterior
    
    
    // Constructores
    public GameState(String labyrinthv, String players, String monsters, int currentPlayer, boolean winner, String log) {
        this.labyrinthv = labyrinthv;
        this.players = players;
        this.monsters = monsters;
        this.currentPlayer = currentPlayer;
        this.winner = winner;
        this.log = log;
    }
    
    public GameState(GameState otro) {
        this.labyrinthv = otro.labyrinthv;
        this.players = otro.players;
        this.monsters = otro.monsters;
        this.currentPlayer = otro.currentPlayer;
        this.winner = otro.winner;
        this.log = otro.log;
    }
    
    public GameState() {
        this.labyrinthv = "";
        this.players = "";
        this.monsters = "";
        this.currentPlayer = -1;
        this.winner = false;
        this.log = "";
    }
 
    
    // Métodos
    public String getLabyrinthv() {
        return labyrinthv;
    }

    public String getPlayers() {
        return players;
    }

    public String getMonsters() {
        return monsters;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isWinner() {
        return winner;
    }

    public String getLog() {
        return log;
    }
}
