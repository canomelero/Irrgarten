
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
    private static final int ROW = 0;       // casilla de entrada
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
        this.labyrinth = new char[nRows][nCols];
        this.monsters = new Monster[nRows][nCols];
        this.players = new Player[nRows][nCols];
        
        // Iniciar las casillas de cada matriz a los valores correspondientes
        for(int f = 0; f < nRows; f++) {
            for(int c = 0; c < nCols; c++) {
                if(f == exitRow && c == exitCol) {
                    labyrinth[f][c] = EXIT_CHAR;
                }
                else if(f == ROW && c == COL) {
                    labyrinth[f][c] = EMPTY_CHAR;
                }
                else if(f == 0 || f == nRows-1 || c == 0 || c == nCols-1) {
                    labyrinth[f][c] = BLOCK_CHAR;
                }
                else {
                    labyrinth[f][c] = EMPTY_CHAR;  
                }
               
                monsters[f][c] = null;
                players[f][c] = null;
            }
        }
    }
    
    public Labyrinth(Labyrinth otro) {
        this(otro.nRows, otro.nCols, otro.exitRow, otro.exitCol);
    }
    
    public Labyrinth() {
        this(0, 0, 0, 0);
    }
    
    
    // Métodos
    public void spreadPlayer(Player[] players) {
        throw new UnsupportedOperationException();
    }
    
    public boolean haveAWinner() {
        boolean ganador = false, encontrado = false;
        int fila = -1, col = -1;
        
        for(int f = 0; f < nRows && !encontrado; f++) {
            for(int c = 0; c < nCols && !encontrado; c++) {
                if(exitPos(f, c)) {
                    fila = f;
                    col = c;
                    encontrado = true;
                }
            }
        }
        
        if(encontrado && players[fila][col] != null) {
            ganador = true;
        }
        
        return ganador;   
    }

    @Override
    public String toString() {
        String strMonsters = "", 
               strPlayers = "", 
               strLabyrinth = "";
        
        for(int f = 0; f < nRows; f++) {
            for(int c = 0; c < nCols; c++) {
                strLabyrinth += labyrinth[f][c] + " ";
            }
           
            strLabyrinth += "\n";
        }
        
        return "Labyrinth{" + "nRows=" + nRows + ", nCols=" + nCols + ", exitRow=" + exitRow + ", exitCol=" 
                + exitCol + ", labyrinth= \n" + strLabyrinth + '}';
    }
    
    public void addMonster(int row, int col, Monster monster) {
        if(posOk(row, col) && emptyPos(row, col)) {
            labyrinth[row][col] = MONSTER_CHAR;
            monsters[row][col] = monster;
            monsters[row][col].setPos(row, col);
        }
    }
    
    public Monster putPlayer(Directions direction, Player player) {
        throw new UnsupportedOperationException();
    }
    
    public void addBlock(Orientation orientation, int startRow, int startCol, int length) {
        throw new UnsupportedOperationException();
    }
    
    public Directions[] validMoves(int row, int col) {
        throw new UnsupportedOperationException();
    }
    
    private boolean posOk(int row, int col) {
        return (row < nRows && row >= 0) && (col < nCols && col >= 0);
    }
    
    // Precondición: row y col deben estar dentro de las dimensiones de la matriz
    private boolean emptyPos(int row, int col) {
        return labyrinth[row][col] == EMPTY_CHAR;
    }
    
    // Precondición: row y col deben estar dentro de las dimensiones de la matriz
    private boolean monsterPos(int row, int col) {
        return labyrinth[row][col] == MONSTER_CHAR;
    }
    
    // Precondición: row y col deben estar dentro de las dimensiones de la matriz
    private boolean exitPos(int row, int col) {
        return labyrinth[row][col] == EXIT_CHAR;
    }
    
    // Precondición: row y col deben estar dentro de las dimensiones de la matriz
    private boolean combatPos(int row, int col) {
        return labyrinth[row][col] == COMBAT_CHAR;
    }
    
    private boolean canStepOn(int row, int col) {
        return posOk(row, col) && (emptyPos(row, col) || monsterPos(row, col) || exitPos(row, col));
    }
    
    private void updateOldPos(int row, int col) {
        if(posOk(row, col)) {
            if(combatPos(row, col)) {
                labyrinth[row][col] = MONSTER_CHAR;
                monsters[row][col] = new Monster("", Dice.randomIntelligence(), Dice.randomStrength(), Dice.healthReward());    // se crea un nuevo???
            }
            else {
                labyrinth[row][col] = EMPTY_CHAR;
                monsters[row][col] = null;      // ??
                players[row][col] = null;       // ??
            }
        }
    }
    
    private int[] dir2Pos(int row, int col, Directions direction) {
        int[] pos_final = new int[2];
        
        switch(direction) {
            case DOWN:
                pos_final[0] = row+1;
                pos_final[1] = col;
                break;
                
            case LEFT:
                pos_final[0] = row;
                pos_final[1] = col-1;
                break;
                
            case RIGHT:
                pos_final[0] = row;
                pos_final[1] = col+1;
                break;
                
            case UP:
                pos_final[0] = row-1;
                pos_final[1] = col;
                break;
        }
        
        return pos_final;
    }
    
    private int[] randomEmptyPos() {
        int[] pos = new int[2];
        int fila, col;
        
        do {
            fila = Dice.randomPos(nRows);
            col = Dice.randomPos(nCols);
            pos[0] = fila;
            pos[1] = col;
        }while(labyrinth[fila][col] != EMPTY_CHAR);
        
        return pos;
    }
    
    private Monster putPlayer2D(int oldRow, int oldCol, int row, int col, Player player) {
        throw new UnsupportedOperationException();
    }
}
