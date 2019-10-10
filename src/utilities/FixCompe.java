package utilities;
/**
 * @author yonatan mahlof 308038256
 * tair hadad 204651897
 */
import java.text.DecimalFormat;

public class FixCompe {

    private boolean fixable;
    private double reductionFactor;
    private int turnsToFix;


    public FixCompe(boolean fixable, int turnsToFix, double reductionFactor) {
        this.setFixable(fixable);
        this.setTurnsToFix(turnsToFix);
        this.setReductionFactor(reductionFactor);
    }

    /**
     * Minimizes the number of turns to fix in one.
     */
    public void nextTurn() {
        this.setTurnsToFix(turnsToFix - 1);
    }

    /**
     * The function return string of fix.
     */
    public String toString() {
        return "(" + fixable + ", " + turnsToFix + ", " + new DecimalFormat("0.00").format(reductionFactor) + ")";
    }

    // Set

    /**
     * @param fixable
     * @return true if set correctly.
     */
    private boolean setFixable(boolean fixable) {
        this.fixable = fixable;
        return true;
    }

    /**
     * @param reductionFactor
     * @return true if set  reduction factor correctly.
     */
    private boolean setReductionFactor(double reductionFactor) {
        this.reductionFactor = reductionFactor;
        return true;
    }

    /**
     * Set  turns to fix.
     * @param turnsToFix
     * @return true if set  turns to fix correctly.
     */
    private boolean setTurnsToFix(int turnsToFix) {
        this.turnsToFix = turnsToFix;
        return true;
    }

    // Get

    /**
     * @return true if is fixable, else false.
     */
    public boolean isFixable() {
        return fixable;
    }

    /**
     * @return Turns who turns left to fix .
     */
    public int getTurnsToFix() {
        return turnsToFix;
    }
}
