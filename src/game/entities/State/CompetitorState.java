package game.entities.State;

import game.arena.IArena;
import game.competition.Competitor;

public interface CompetitorState {
    public void doAction(Competitor racer);
}
