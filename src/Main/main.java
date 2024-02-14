
package Main;
import Clases.*;
import Enumerados.*;
import Irrgarten.UI.TextUI;
import irrgarten.controller.Controller;

public class main {
    public static void main(String[] args) {
        TextUI vista = new TextUI();
        Game juego1 = new Game(1);
        Controller controlador = new Controller(juego1, vista);
        
        controlador.play();
    }
}
