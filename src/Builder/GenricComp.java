package Builder;

import GUI.BuildArena;
import GUI.CreateCompetition;
import game.arena.IArena;
import game.arena.WinterArena;
import game.competition.Competition;
import game.competition.Competitor;
import utilities.Point;

import java.util.ArrayList;

public class GenricComp implements Builder{
    private WinterArena arena;
    private ArrayList<Competitor> Comp;


    private Competition competition;

    /**
     * add racer to array
     * @param racer
     */
    @Override
    public void setcompetitor(Competitor racer) {

        if(Comp == null) {
            Comp = new ArrayList<Competitor>();
            Comp.add(racer);
        }
        else {
            Comp.add(racer);
        }
    }

    /**
     * add the competitor to race
     */
    public void addRacersToComp() {
        for (Competitor racer : Comp) {
            try {
                competition.addCompetitor(racer);
            } catch (IllegalArgumentException j) {
                j.printStackTrace(System.out);
            }
        }
    }

    /**
     * set competition
     * @param com
     */
    @Override
    public void setcompetition(Competition com) {
        this.competition = com;

    }

    /**
     * initialize the competition to the gui
     */
    public void setcompetitiongame(){
        CreateCompetition.setCompetition(this.competition);
    }
    /**
     * set arena
     * @param
     */
    @Override
    public void setArena(WinterArena arena1) {
        this.arena =arena1;

    }

    public WinterArena getarena() {
        return arena;
    }
    public Competition getCompetition() {
        return competition;
    }
    public void setarenagame(){
        BuildArena.setWinterArena(this.arena);
    }
}
