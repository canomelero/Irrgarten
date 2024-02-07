
package Clases;
import java.util.ArrayList;
import Enumerados.*;

public class Labyrinth {
    // Atributos
    private static final char BLOCK_CHAR = 'X';
    private static final char EMPTY_CHAR = '-';
    private static final char MONSTER_CHAR = 'M';
    private static final char COMBAT_CHAR = 'C';
    private static final char EXIT_CHAR = 'E';
    private static final int ROW = 0;
    private static final int COL = 1;
    private int nRows;
    private int nCols;
    private int exitRow;
    private int exitCol;
    private Monster[][] monsters;   // matriz de monster o de tipo char ???
    private char[][] labyrinth;
    private Player[][] players;     // matriz de players o de tipo char ???
    
    
    // Constructores
    public Labyrinth(int nRows, int nCols, int exitRow, int exitCol) {
        this.nRows = nRows;
        this.nCols = nCols;
        this.exitRow = exitRow;
        this.exitCol = exitCol;
        // iniciar matrices ???
    }
    
    public Labyrinth(Labyrinth otro) {
        this(otro.nRows, otro.nCols, otro.exitRow, otro.exitCol);
    }
    
    public Labyrinth() {
        this(0, 0, 0, 0);
    }
    
    
    // Métodos
    public void spreadPlayer(Player[] players) {
        
    }
    
    public boolean haveAWinner() {
        
    }

    @Override
    public String toString() {
        return "Labyrinth{" + "nRows=" + nRows + ", nCols=" + nCols + ", exitRow=" + exitRow + ", exitCol=" 
                + exitCol + ", monsters=" + monsters + ", labyrinth=" + labyrinth + ", players=" + players + '}';
    }
    
    public void addMonster(int row, int col, Monster monster) {
        
    }
    
    public Monster putPlayer(Directions direction, Player player) {
        
    }
    
    public void addBlock(Orientation orientation, int startRow, int startCol, int length) {
        
    }
    
    public Directions[] validMoves(int row, int col) {
        
    }
    
    private boolean posOk(int row, int col) {
        
    }
    
    private boolean emptyPos(int row, int col) {
        
    }
    
    private boolean monsterPos(int row, int col) {
        
    }
    
    private boolean exitPos(int row, int col) {
        
    }
    
    private boolean combatPos(int row, int col) {
        
    }
    
    private boolean canStepOn(int row, int col) {
        
    }
    
    private void updateOldPos(int row, int col) {
        
    }
    
    private int[] dir2Pos(int row, int col, Directions direction) {
        
    }
    
    private int[] randomEmptyPos() {
        
    }
    
    private Monster putPlayer2D(int oldRow, int oldCol, int row, int col, Player player) {
        
    }
}
