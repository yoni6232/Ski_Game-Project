package game.entities.Decorator;

import game.competition.Competitor;
import utilities.Point;

public abstract class WSDecorator  implements IWinterSportsman,Runnable,Cloneable {

    protected IWinterSportsman competitor;
    public WSDecorator(IWinterSportsman decoratedRacer) {
        this.competitor = decoratedRacer;
    }

}
