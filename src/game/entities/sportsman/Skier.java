package game.entities.sportsman;


import game.competition.Competitor;
import game.entities.State.CompetitorState;
import game.entities.State.CompletedState;
import game.enums.ColorType;
import game.enums.Discipline;
import game.enums.Gender;
import utilities.ValidationUtils;

import java.util.Observer;

public class Skier extends WinterSportsman{
    private static final String CLASS_NAME = "Ski";

    public Skier(String name, double age, Gender gender, double acceleration, double max, Discipline discipline, ColorType Color,int NumComp) {
        super(name, age, gender, acceleration, max, discipline,Color,NumComp);
        ValidationUtils.assertNotNull(discipline);
        ValidationUtils.assertNotNull(gender);

    }
    public void registerObserver (Observer o){
        super.registerObserver(o);
    }



    public String className() {
        return CLASS_NAME;
    }

    @Override
    public String toString() {
        return "Skier " + this.getName();
    }

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
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public void setState(CompletedState activeState) {

    }
}
