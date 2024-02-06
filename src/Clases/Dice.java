
package Clases;
import java.util.Random;

public class Dice {
    // Atributos
    private static final int MAX_USES = 5;
    private static final float MAX_INTELLIGENCE = (float) 10.0;
    private static final float MAX_STRENGTH = (float) 10.0;
    private static final float RESURRECT_PROB = (float) 0.3;
    private static int WEAPONS_REWARD = 2;
    private static int SHIELDS_REWARD = 3;
    private static int HEALTH_REWARD = 5;
    private static int MAX_ATTACK = 3;
    private static int MAX_SHIELD = 2;
    private static Random generator = new Random();
    
    
    // Métodos
    // Precondición: max >= 0
    public static int randomPos(int max) {
        return generator.nextInt(max) + 0;
    }
    
    // Precondición: nplayers >= 0
    public static int whoStarts(int nplayers) {
        return generator.nextInt(nplayers) + 0;
    }
    
    public static float randomIntelligence() {
       return generator.nextFloat(MAX_INTELLIGENCE) + 0; 
    }
    
    public static float randomStrength() {
        return generator.nextFloat(MAX_STRENGTH) + 0;
    }
    
    public static boolean resurrectPlayer() {
        float prob = generator.nextFloat();     // genera un float [0, 1[
        boolean resucita = false;
        
        if(prob <= RESURRECT_PROB) {
            resucita = true;
        }
        
        return resucita;
    }
    
    public static int weaponsReward() {
        return generator.nextInt(WEAPONS_REWARD+1) + 0;
    }
    
    public static int shieldsReward() {
        return generator.nextInt(SHIELDS_REWARD) + 0;
    }
    
    public static int healthReward() {
        return generator.nextInt(HEALTH_REWARD) + 0;
    }
    
    public static float weaponPower() {
        return generator.nextFloat(MAX_ATTACK) + 0;
    }
    
    public static float shieldPower() {
        return generator.nextFloat(MAX_SHIELD) + 0;
    }
    
    public static int usesLeft() {
        return generator.nextInt(MAX_USES) + 0;
    }
    
    public static float intensity(float competence) {
        return generator.nextFloat(competence) + 0;
    }
    
    public static boolean discardElement(int usesLeft) {
        // Calculo de la probabilidad inversamente proporcional a los usos
        float prob = 1/(float) usesLeft;
        
        // Devuelve true con una probabilidad inversamente proporcional a los usos restantes
        return generator.nextFloat() < prob;
    }
}
