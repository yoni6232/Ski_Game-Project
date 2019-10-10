package game.entities.sportsman;

import game.competition.Competitor;
import game.entities.State.CompetitorState;
import game.entities.State.CompletedState;
import game.enums.ColorType;
import game.enums.Discipline;
import game.enums.Gender;

import java.util.Observer;

public class Snowboarder extends WinterSportsman {
    private static final String CLASS_NAME = "Snowboard";

    public Snowboarder(String name, double age, Gender gender, double acceleration, double max, Discipline discipline, ColorType Color,int NumComp) {
        super(name, age, gender, acceleration, max, discipline,Color,NumComp);
    }
    public String className() {
        return CLASS_NAME;
    }

    /**
     * copy the racer
     * @return
     */
    @Override
    public Competitor makecopy(){
        Skier ski1 = null;
        try {
            ski1 = (Skier) super.clone();

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return ski1;
    }

    @Override
    public void registerObserver (Observer o){
        super.registerObserver(o);
    }

    @Override
    public String toString() {
        return "Snowboarder " + this.getName();
    }
    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public void setState(CompletedState activeState) {

    }


}
