package game.entities.State;

import game.competition.Competitor;
import game.enums.CompeState;

public class InjuredState implements  CompetitorState{
    /**
     * set the competitor state
     * @param racer
     */
    public void doAction(Competitor racer) {
        racer.setState(this);
    }
    /**
     * Return State.
     */
    public String toString() {
        return "Injured";
    }
}

