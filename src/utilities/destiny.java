package utilities;
/**
 * @author yonatan mahlof 308038256
 * tair hadad 204651897
 */
import game.competition.Competitor;
import game.entities.State.ActiveState;
import game.entities.State.DisabledState;
import game.entities.State.InjuredState;
import game.enums.CompeState;

import java.util.Random;

public class destiny {
    private static Random rand = new Random();
    public static boolean breakDown(double failureProbability) {
        return rand.nextFloat()<=failureProbability;
    }
    /**
     * @return true if  is fixable, else false.
     */
    public static boolean generateFixable() {
        return rand.nextInt(100) < 98;
    }
    /**
     * @return random decision if the competitor can be fixable
     */
    private static float generateReduction() {
        return rand.nextFloat();
    }
    /**
     * @return returns how much turn the competitor will back
     */
    private static int generateTurns() {
        return rand.nextInt(5) + 1;
    }

    public static FixCompe generatefix() {
        return new FixCompe(generateFixable(), generateTurns(), generateReduction());
    }

}
