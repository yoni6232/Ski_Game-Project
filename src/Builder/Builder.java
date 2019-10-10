package Builder;

import game.arena.IArena;
import game.arena.WinterArena;
import game.competition.Competition;
import game.competition.Competitor;

public interface Builder {
    public void setcompetitor(Competitor racer);
    public void setcompetition(Competition com);

    public void setArena(WinterArena arena);
    void setcompetitiongame();


}
