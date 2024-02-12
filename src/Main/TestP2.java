
package Main;
import Clases.*;
import Enumerados.*;

public class TestP2 {
    public void main() {
        // Prueba clase Monster
        System.out.println("...: MONSTRUO :...");
        Monster m1 = new Monster("Monstruo1", Dice.randomIntelligence(), Dice.randomStrength(), Dice.healthReward());
        
        System.out.println("Fuerza del monstruo: " + m1.attack());
        System.out.println("Esta muerto: " + m1.dead());
        System.out.println(m1.toString());
        m1.setPos(2, 2);    // Cambio de posición a 2, 2:
        System.out.println(m1.toString());
        
        
        // Prueba clase Player
        System.out.println("\n...: PLAYER :...");
        Player p1 = new Player(1, Dice.randomIntelligence(), Dice.randomStrength());
        
        System.out.println("Fila y columna: " + p1.getRow() + ", " + p1.getCol());
        System.out.println("Numero de jugador: " + p1.getNumber());
        p1.setPos(3, 3);
        System.out.println(p1.toString());
        System.out.println("Esta muerto: " + p1.dead());
        System.out.println("Fuerza total: " + p1.attack());
        // System.out.println("Defensa: " + p1.defend(5));      Lanza una excepción ahora mismo
     
        
        // Prueba clase Labyrinth
        System.out.println("\n...: LABYRINTH :... ");
        Labyrinth l1 = new Labyrinth(8, 8, 7, 6);
        
        System.out.println("Ganador: " + l1.haveAWinner());
        l1.addMonster(4, 3, m1);
        System.out.println(l1.toString());
        
        
        // Prueba clase Game
        System.out.println("\n...: GAME :... ");
        Game g1 = new Game(5);
        
        System.out.println("Estado del juego: " + g1.getGameState().toString());
    }
}
