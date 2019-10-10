package game.entities.Decorator;

import game.competition.Competitor;
import game.enums.ColorType;
import javafx.beans.InvalidationListener;

import java.awt.*;

public class ColoredSportsman extends WSDecorator{
    /**
     * getting the racer how need to change
     * @param competitor
     * @param
     */
    public ColoredSportsman(IWinterSportsman competitor,ColorType c) {
        super(competitor);
        ChangeColor(c);

    }
    @Override
    public void run() {
    }
    @Override
    public void ChangeAcee(double acc) {
    }
    /**
     * call to function that change the color
     * @param
     */
    @Override
    public void ChangeColor(ColorType c) {
        competitor.ChangeColor(c);
    }
}
