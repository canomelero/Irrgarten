
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
        
    }
}
