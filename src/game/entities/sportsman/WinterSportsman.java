package game.entities.sportsman;
/**
 * @author yonatan mahlof 308038256
 * tair hadad 204651897
 */
import GUI.BuildArena;
import GUI.CreateCompetition;
import GUI.MainFrame;
import GUI.RacerInfoTable;
import game.competition.Competition;
import game.competition.Competitor;
import game.entities.Decorator.IWinterSportsman;
import game.entities.State.*;
import game.enums.*;
import utilities.FixCompe;
import utilities.Point;
import utilities.ValidationUtils;
import utilities.destiny;

import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public abstract class WinterSportsman extends Sportsman  implements Competitor, IWinterSportsman  {
    private final double failureProbability;
    private Discipline discipline;
    private Competition compe = CreateCompetition.getCompetition();
    private ArrayList<Observer>  list = new ArrayList<>();
    public FixCompe fix ;
    private final double FAILURE_DEFAULT = 0.35;
    private static final CompetitorState activeState = new ActiveState();
    private static final CompetitorState brokenState = new InjuredState();
    private static final CompetitorState disabledState = new DisabledState();
    private static final CompetitorState completedState = new CompletedState();
    public WinterSportsman(String name, double age, Gender gender, double acceleration, double max, Discipline discipline, ColorType Color,int NumComp) {
        super(name, age, gender, acceleration , max,Color,NumComp);
        ValidationUtils.assertNotNull(discipline);
        ValidationUtils.assertNotNull(gender);
        this.discipline = discipline;
        this.initRace();
        this.failureProbability = FAILURE_DEFAULT;

    }

    public void registerObserver (Observer o){
        list.add( o);
    }
    public void run() {
        while(!compe.getArena().isFinished(this)) {
            move(compe.getArena().getFriction());
            setChanged();
            notifyObservers();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(this.Fix() && this.fix.isFixable() == false) break;

        }
        MainFrame.setGameStarted(false);
        MainFrame.setGameFinished(true);
        completedState.doAction(this);
  //      this.notifyObservers(CompeState.COMPLETED);

    }
    @Override
    public Competitor makecopy() {
        return null;
    }
    public Discipline getDiscipline() {
        return discipline;
    }
    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }
    /**
     * Initializing the race
     */
    @Override
    public void initRace() {
        this.setLocation(new Point(0,0));
    }

    /**
     * move the player in a step
     */

    @Override
    public void move(double friction)
        {
            if (this.getState() instanceof DisabledState) {
                return;
            }
        if (!(this.Fix()) && destiny.breakDown(this.FAILURE_DEFAULT)) {
            this.fix = destiny.generatefix();
            this.setChanged();
        }
        if (this.Fix()) {
            if (this.fix.isFixable() == false) {
                this.setChanged();
                disabledState.doAction(this);
                this.notifyObservers(CompeState.DISABLED);

            } else {
                this.setChanged();
                brokenState.doAction(this);
                this.notifyObservers(CompeState.INJURED);
            }
            this.fix.nextTurn();
            return;
        }
        else {
            this.setChanged();
            activeState.doAction(this);
            this.notifyObservers(CompeState.ACTIVE);
        }
        if (this.getSpeed() < this.getMaxSpeed()) {
            this.setCurrentSpeed(this.getSpeed() + (this.getAcceleration() * (1 - friction)));
        }
        if (this.getSpeed() > this.getMaxSpeed()) {
            this.setCurrentSpeed(this.getMaxSpeed());
        }

            double lenForObserver = 0.5;
            double newLocationX = this.getSpeed() + (this.getAcceleration() * (1 - friction));

            for (double i = 0; i < newLocationX; i += lenForObserver) {
                Point Newlocation = new Point((this.getLocation().getX()) + (this.getSpeed()), this.getLocation().getY());
                this.setLocation(Newlocation);
                setChanged();
                notifyObservers();
            }
        if (compe.getArena().isFinished(this)){
            Point Newl = new Point(compe.getArena().getLength() , this.getLocation().getY());
            this.setLocation(Newl);
            this.setState(this.completedState);
    }

    }

    /**
     * function that return if the competitor injure
     * @return
     */
    private boolean Fix() {
        if (this.fix != null && this.fix.getTurnsToFix() == 0)
            this.fix = null;
        return this.fix != null;

    }
    /*/
    check the Competitor object
     */
    public static Sportsman check(Competitor c){
        if (c instanceof Skier){
            return ((Skier)c);
        }
        else
            return ((Snowboarder)c);
    }

}
