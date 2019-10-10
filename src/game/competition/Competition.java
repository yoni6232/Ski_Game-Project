package game.competition;
import GUI.ControlView;
import GUI.CreateCompetition;
import GUI.MainFrame;
import game.arena.IArena;
import game.entities.IMobileEntity;
import game.entities.State.*;
import game.entities.sportsman.WinterSportsman;
import game.enums.CompeState;
import game.enums.Discipline;
import game.enums.Gender;
import utilities.ValidationUtils;
import utilities.destiny;

import java.util.Observable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.ArrayList;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * @author yonatan mahlof 308038256
 * tair hadad 204651897
 */
public abstract class Competition implements Observer  {
    private static IArena arena;
    private int maxCompetition;
    CompetitorTime time;
    private ArrayList<Competitor> activeCompetitors;
    private ArrayList<Competitor> finushedCompetitors;



    private ArrayList<Competitor> allCompetitors;

    private ArrayList<Competitor> injurCompetitors;
    private ArrayList<Competitor> disabledCompetitors;
    public Competition(IArena arena, int maxCompetition) {
        ValidationUtils.assertNotNull(arena);
        this.arena = arena;
        ValidationUtils.assertNotNegative(maxCompetition);
        this.maxCompetition = maxCompetition;
        this.activeCompetitors(new ArrayList<>());
        this.finushedCompetitors(new ArrayList<>());
        this.injurCompetitors=new ArrayList<>();
        this.disabledCompetitors=new ArrayList<>();
        this.allCompetitors=new ArrayList<>();

    }

public void StartTime(){
    time=new CompetitorTime();
    time.start();
}
    public ArrayList<Competitor> getAllCompetitors() {
        return allCompetitors;
    }

    public void setAllCompetitors(ArrayList<Competitor> allCompetitors) {
        this.allCompetitors = allCompetitors;
    }
    @Override
    public synchronized void update(Observable o, Object arg) {

        Competitor r = (Competitor) o;

        synchronized(r)
        {
            CompetitorState state = r.getState();
            if(state instanceof CompletedState) {
                this.finushedCompetitors.add(r);
                this.activeCompetitors.remove(r);
                r.setMikom(this.finushedCompetitors.size());
                r.addTimeInjur(time.getTime(), "Completed");
                r.setFinishedTime(time.getTime());
            }
            else if(state instanceof InjuredState && !this.injurCompetitors.contains(r) && this.activeCompetitors.contains(r)) {
                this.injurCompetitors.add(r);
                this.activeCompetitors.remove(r);
                r.addTimeInjur(time.getTime(),"InjuredState");
            }
            else if(state instanceof ActiveState && this.injurCompetitors.contains(r) && !this.activeCompetitors.contains(r)) {
                this.injurCompetitors.remove(r);
                this.activeCompetitors.add(r);
                r.setMikom(this.activeCompetitors.indexOf(r)+1);
            }
            else if(state instanceof DisabledState && !this.disabledCompetitors.contains(r)) {
                this.disabledCompetitors.add(r);
                this.activeCompetitors.remove(r);
                r.addTimeInjur(time.getTime(),"Disabled");
                r.setMikom(999);
            }
        }
    }

    private synchronized void activeCompetitors(ArrayList<Competitor> objects) {
        this.activeCompetitors = objects;
    }

    private void finushedCompetitors(ArrayList<Competitor> objects) {
        this.finushedCompetitors = objects;
    }

    public boolean isVaildCometitor(Competitor competitor) {

        return true;
        }

    public  boolean hasActiveCompetitors() {
        if (this.activeCompetitors.isEmpty())
            return false;
        return  true;

    }
    /**
     * A function that add competitor if is standing in the requirements and init the race
     * @param competitor
     */
    public void addCompetitor(Competitor competitor) {
        ValidationUtils.assertNotNull(competitor);

        if (this.activeCompetitors.size() < this.getMaxCompetition()) {
            if (!this.isVaildCometitor(competitor)) {
                throw new IllegalArgumentException("Invalid competitor " + competitor);

            }
            else  {
                this.allCompetitors.add(competitor);
                this.activeCompetitors.add(competitor);

            }
        } else {
            throw new IllegalStateException(this.getArena() + " is full max = " + this.getMaxCompetition());
        }


    }
    /**
     * setters and getters
     *
     */
    public static IArena getArena() {
        return arena;
    }

    public void setArena(IArena arena) {
        this.arena = arena;
    }

    public int getMaxCompetition() {
        return maxCompetition;
    }

    public void setMaxCompetition(int maxCompetition) {
        this.maxCompetition = maxCompetition;
    }

    public synchronized ArrayList<Competitor> getactiveCompetitors() {
        return activeCompetitors; }

    /**
     * A function that passes over all players and promotes them as needed
     *
     */
    public void playTurn() {
        synchronized (this) {
            for (Competitor i : this.activeCompetitors) {
                new Thread(i).start();
            }
        }
    }


    public synchronized ArrayList<Competitor> getFinishedCompetitors(){
        return this.finushedCompetitors;}
    public void setActiveCompetitors(Competitor a) {

        this.activeCompetitors.remove(a);

    }

    public synchronized void setFinushedCompetitors(Competitor competitor) {
        this.finushedCompetitors.add((competitor));

    }

    public void initializracer() {
        this.activeCompetitors(new ArrayList<>());
        this.finushedCompetitors(new ArrayList<>());

    }

    public Competitor getcompetitor(String Comp) {
        for (Competitor i : this.getactiveCompetitors()){
            if (WinterSportsman.check(i).getName() == Comp){
                return i;
            }
        }
        return null;
    }

    public ArrayList<Competitor> getInjurCompetitors() {
        return injurCompetitors;
    }

    public void setInjurCompetitors(ArrayList<Competitor> injurCompetitors) {
        this.injurCompetitors = injurCompetitors;
    }

    public ArrayList<Competitor> getDisabledCompetitors() {
        return disabledCompetitors;
    }

    public void setDisabledCompetitors(ArrayList<Competitor> disabledCompetitors) {
        this.disabledCompetitors = disabledCompetitors;
    }
    public String getClassName(){
        return null;
    };
    public Gender getGender() {
        return null;
    }
    public Discipline getDiscipline() {
        return null;
    }

}

