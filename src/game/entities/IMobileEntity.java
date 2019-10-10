package game.entities;

import GUI.MainFrame;
import game.entities.State.ActiveState;
import game.entities.State.CompetitorState;
import game.entities.State.CompletedState;
import javafx.beans.Observable;
import utilities.Point;

public interface IMobileEntity {
    public void move(double friction);
    public Point getLocation();
    public void setLocation(Point location);

    public void setName(String name);

    void setState(CompletedState state);

}
