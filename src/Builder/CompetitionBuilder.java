package Builder;

import Factory.ArenaFactory;
import GUI.ControlView;
import game.arena.IArena;
import game.arena.WinterArena;
import game.competition.Competition;
import game.competition.Competitor;
import game.competition.SkiCompetition;
import game.entities.sportsman.Skier;
import game.enums.*;
import utilities.Point;

import java.util.ArrayList;
import java.util.Observer;


public class CompetitionBuilder implements CompBuilder {
public int i =0;

    private GenricComp competition ;

    public CompetitionBuilder() {
        this.competition =new GenricComp();
    }

    /**
     * clone the default competitor to the competition
     * @param amountOfRacer
     */
    @Override
    public void addcompetitor(int amountOfRacer) {
        Skier racer = new Skier("Ski",23, Gender.MALE,4,60, Discipline.DOWNHILL, ColorType.BLUE,i);
        competition.setcompetitor(racer);
        for( i = 1 ; i < amountOfRacer ; i++) {
            competition.setcompetitor(racer.makecopy());
            racer.setLocation(new Point(0,i*6));

            racer.setNumComp(i);
        }
        racer.registerObserver(competition.getCompetition() );
        (racer).addObserver( competition.getCompetition());

    }
    /**
     * clone the default arena
     * @param
     */
    @Override
    public void buildWinterArena() {
        //competition.setArena(new WinterArena(700, SnowSurface.POWDER,WeatherCondition.CLOUDY));
        competition.setArena((WinterArena) new ArenaFactory().buildDefaultArena("WINTER"));
    }
    /**
     * clone the competition
     * @param
     */
    @Override
    public void buildSki() {
        competition.setcompetition(new SkiCompetition((WinterArena) competition.getarena(), 20,Discipline.DOWNHILL, League.ADULT,Gender.MALE));

    }
    public void buildCompRace() {
        competition.addRacersToComp();
    }
    @Override
    public GenricComp getRace() {
        return this.competition;
    }

    @Override
    public void setcompetitiongame() {
        competition.setcompetitiongame();

    }

    @Override
    public void setarenagame() {
        competition.setarenagame();
    }
}
