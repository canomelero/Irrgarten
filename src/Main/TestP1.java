
package Main;
import Clases.*;
import Enumerados.*;

public class TestP1 {
    public void main() {
        // Weapon
        Weapon arma1 = new Weapon((float) 2.0, 5);
        
        System.out.println("Potencia del arma: " + arma1.attack());
        System.out.println(arma1.toString());
        System.out.println("Descarte del arma: " + arma1.discard());
        
        // Shield
        Shield escudo1 = new Shield((float) 4.0, 2);
        
        System.out.println("\nDefensa del escudo: " + escudo1.protect());
        System.out.println(escudo1.toString());
        System.out.println("Descarte del arma: " + escudo1.discard());
        
        // Dice
        System.out.println("\nPosicion: " + Dice.randomPos(4));
        System.out.println("Jugador inicio: " + Dice.whoStarts(3));
        System.out.println("Inteligencia: " + Dice.randomIntelligence());
        System.out.println("Fuerza: " + Dice.randomStrength());
        System.out.println("Resucitar jugador: " + Dice.resurrectPlayer());
        System.out.println("Arma recibida por combate ganado: " + Dice.weaponsReward());
        System.out.println("Escudo recibido por combate ganado: " + Dice.shieldsReward());
        System.out.println("Salud recibida por combate ganado: " + Dice.healthReward());
        System.out.println("Fuerza del arma: " + Dice.weaponPower());
        System.out.println("Fuerza del escudo " + Dice.shieldPower());
        System.out.println("Número de usos: " + Dice.usesLeft());
        System.out.println("Competencia: " + Dice.intensity(3));
        System.out.println("Descartar elemento: " + Dice.discardElement(3));
        
        // GameState
        
    }
}
