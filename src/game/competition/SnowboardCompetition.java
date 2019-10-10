package game.competition;

import game.arena.WinterArena;
import game.entities.sportsman.Skier;
import game.enums.Discipline;
import game.enums.Gender;
import game.enums.League;

import java.util.Observable;

public  class SnowboardCompetition extends WinterCompetition {


    private static final String CLASS_NAME = "Snowboard";
    public SnowboardCompetition(WinterArena arena, int maxCompetition, Discipline discipline, League league, Gender gender) {
        super(arena, maxCompetition, discipline, league, gender);

    }
    /**
     * check if the competitor stand in the requirements
     * @param competitor
     * @return
     */
    public boolean isVaildCometitor(Competitor competitor) {
        if (competitor instanceof Skier){
            return super.isVaildCometitor(competitor);
        }
        return false;
    }
    public String getClassName() {
        return CLASS_NAME;
    }

}
