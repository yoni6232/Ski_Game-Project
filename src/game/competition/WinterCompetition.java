package game.competition;

import game.arena.WinterArena;
import game.entities.sportsman.WinterSportsman;
import game.enums.Discipline;
import game.enums.Gender;
import game.enums.League;
import utilities.ValidationUtils;

public abstract class WinterCompetition extends Competition{
   private Discipline discipline;
    private League league;
    private Gender gender;


    public WinterCompetition(WinterArena arena, int maxCompetition,Discipline discipline,League league,Gender gender) {
        super(arena, maxCompetition);
        ValidationUtils.assertNotNull(discipline);
        ValidationUtils.assertNotNull(league);
        this.discipline=discipline;
        this.gender=gender;
        this.league=league;
    }

    /**
     * setters and getters
     */
    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * check if the competitor stand in the requirements
     * @param competitor
     * @return
     */
    public boolean isVaildCometitor(Competitor competitor) {

        if (league.isInLeague(((WinterSportsman) competitor).getAge()) && (((WinterSportsman) competitor).getDiscipline() == this.getDiscipline()) && (((WinterSportsman) competitor).getGender() == this.getGender()))
            return true;
        return false;
    }

}
