package game.entities.Decorator;

import game.enums.ColorType;

public class SpeedySportsman extends WSDecorator {
    /**
     * getting the racer how need to change
     * @param competitor
     * @param acc
     */
    public SpeedySportsman(IWinterSportsman competitor , double acc) {
        super(competitor);
        ChangeAcee(acc);
    }
    @Override
    public void run() {

    }

    /**
     * call to function that change the acceleration
     * @param acc
     */
    @Override
    public void ChangeAcee(double acc) {
        competitor.ChangeAcee(acc);
    }

    @Override
    public void ChangeColor(ColorType c) {

    }
}
