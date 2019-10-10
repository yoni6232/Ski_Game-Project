package game.entities.State;

import game.competition.Competitor;
import game.enums.CompeState;

public class DisabledState implements  CompetitorState{


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
        return "Failed";
    }
}
