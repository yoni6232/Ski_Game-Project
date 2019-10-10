package game.entities;
/**
 * @author yonatan mahlof 308038256
 * tair hadad 204651897
 */
import utilities.Point;

import java.util.Observable;

public abstract class Entity extends Observable implements Runnable {
    private  Point location;
    public Entity() {this.location= new Point(); }

    public Entity(Point location) {
        this.location = location;
    }
    /**
     * setters and getters
     *
     */
    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }
}
