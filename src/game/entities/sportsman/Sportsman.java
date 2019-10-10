package game.entities.sportsman;

import game.entities.MobileEntity;
import game.entities.State.CompetitorState;
import game.enums.ColorType;
import game.enums.Gender;
import game.enums.League;
import utilities.ValidationUtils;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Timer;

public abstract class Sportsman extends MobileEntity {
    private String name;
    private double age;
    private Gender gender;
    int NumComp;
    public ArrayList<String> timeBroken = new ArrayList<>();
    public String finisftime;
    int mikom;
    private ColorType Color;
    private CompetitorState state;
    private Hashtable<String, Object> attributes;

    public Sportsman(String name, double age, Gender gender, double acceleration, double max, ColorType Color,int Num) {
        super(max, acceleration+ League.calcAccelerationBonus(age));
        ValidationUtils.assertNotNull(gender);
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.Color = Color;
        this.NumComp=Num;
    }
    /**
     * setters and getters
     */
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getAge() {
        return age;
    }
    public void setAge(double age) {
        this.age = age;
    }
    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    public ColorType getColor() {
        return this.Color;
    }
    public void setColor(ColorType color) {
        this.Color = color;
    }
    public int getNum(){
        return NumComp;
    }
    public void setNumComp(int numComp) {
        NumComp = numComp;
    }
    public CompetitorState getState() {
        return this.state;
    }
    public void setState(CompetitorState state) {
        this.state = state;
    }

    /**
     * return the competitor place
     * @return
     */
    public int getMikom() {
        return mikom;
    }

    public void setMikom(int mikom) {
        this.mikom = mikom;
    }

    /**
     * add the time
     * @param timeBroken
     * @param r
     */
    public void addTimeInjur(String timeBroken, String r) {
        this.timeBroken.add(timeBroken);
        System.out.println("Racer num:" + this.getNum() + " : has " + r + " at: " + timeBroken);
    }

    /**
     * return the competitor finished time
     * @return
     */
    public String getFinishedTime() {

        return this.finisftime;
    }
    public void setFinishedTime(String time) {
        this.finisftime = time;
    }
    public ArrayList<String> getTimeBroken() {
        return timeBroken;
    }

    public void setTimeBroken(ArrayList<String> timeBroken) {
        this.timeBroken = timeBroken;
    }
    public void ChangeAcee(double acc){
        double a = this.getAcceleration();
        a+=acc;
        this.setAcceleration(a);
    }
    public void ChangeColor(ColorType c){
        this.Color = c;

    }
}
