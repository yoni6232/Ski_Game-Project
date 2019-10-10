package game.competition;

import GUI.MainFrame;
import game.entities.IMobileEntity;
import game.entities.State.CompetitorState;
import utilities.Point;

public interface Competitor extends IMobileEntity, Runnable ,Cloneable {
    public void initRace();

    @Override
    void move(double friction);

    @Override
    Point getLocation();

    @Override
    void run();

    String className();

    Object getColor();

    int getNum();
    Competitor makecopy();

    CompetitorState getState();

    void setMikom(int size);

    void addTimeInjur(String timeTime, String time);

    void setState(CompetitorState active);

    void setFinishedTime(String time);
}
