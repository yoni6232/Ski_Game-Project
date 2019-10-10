package designpatterns;

import game.competition.Competitor;
import game.entities.sportsman.WinterSportsman;

public class CompetitorClone
{
    /**
     * clone the competitor
     * @param com
     * @return
     */
    public WinterSportsman getClone(Competitor com){return (WinterSportsman) com.makecopy(); }
}
