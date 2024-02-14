
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
    private Monster[][] monsters;   
    private char[][] labyrinth;
    private Player[][] players;     
    
    
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
        for(Player p : players) {
            int[] pos = randomEmptyPos();
            putPlayer2D(-1, -1, pos[ROW], pos[COL], p);
        }      
    }
    
    public boolean haveAWinner() {
        boolean ganador = false;
        
        if(players[exitRow][exitCol] != null) {
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
        int oldRow = player.getRow(),
            oldCol = player.getCol();
        int[] newPos = dir2Pos(oldRow, oldCol, direction);
        
        return putPlayer2D(oldRow, oldCol, newPos[ROW], newPos[COL], player);
    }
    
    public void addBlock(Orientation orientation, int startRow, int startCol, int length) {
        int incRow, incCol,
            row, col;
        
        if(orientation == Orientation.VERTICAL) {
            incRow = 1;
            incCol = 0;
        }
        else {
            incRow = 0;
            incCol = 1;
        }
        
        row = startRow;
        col = startCol;
        
        while(posOk(row, col) && emptyPos(row, col) && length > 0) {
            labyrinth[row][col] = BLOCK_CHAR;
            length -= 1;
            row += incRow;
            col += incCol;
        }
    }
    
    public Directions[] validMoves(int row, int col) {
        Directions[] output;
        ArrayList<Directions> dir = new ArrayList<>();
        
        if(canStepOn(row+1, col)) {
            dir.add(Directions.DOWN);
        }
        
        if(canStepOn(row-1, col)) {
            dir.add(Directions.UP);
        }
        
        if(canStepOn(row, col+1)) {
            dir.add(Directions.RIGHT);
        }
        
        if(canStepOn(row, col-1)) {
            dir.add(Directions.LEFT);
        }
        
        output = new Directions[dir.size()];
        
        for(int i = 0; i < dir.size(); i++) {
            output[i] = dir.get(i);
        }
        
        return output;
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
                monsters[row][col] = null;      
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
        Monster output = null;
        
        if(canStepOn(row, col)) {
            if(posOk(oldRow, oldCol)) {
                Player p = players[oldRow][oldCol];
                
                if(p == player) {
                    updateOldPos(oldRow, oldCol);
                }
                
                players[oldRow][oldCol] = null;
            }
            
            boolean monsterPos = monsterPos(row, col);
        
            if(monsterPos) {
                labyrinth[row][col] = COMBAT_CHAR;
                output = monsters[row][col];
            }
            else {
                int number = player.getNumber();
                labyrinth[row][col] = (char) (number + '0');
            }

            players[row][col] = player;
            player.setPos(row, col);
        }
 
        return output;
    }
}
