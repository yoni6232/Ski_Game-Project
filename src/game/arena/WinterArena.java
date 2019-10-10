package game.arena;

import game.entities.IMobileEntity;
import game.enums.SnowSurface;
import game.enums.WeatherCondition;

public class WinterArena implements IArena {
    private double length;
    private SnowSurface surface;
    private WeatherCondition condition;

    public WinterArena(double length, SnowSurface surface, WeatherCondition condition) {
        this.length = length;
        this.surface = surface;
        this.condition = condition;
    }

    public WinterArena() {

    }

    @Override
    public double getFriction() {
        return this.surface.getFriction();
    }

    /**
     * A function that returns true or false if a player crossed the finish line

     */
    @Override
    public boolean isFinished(IMobileEntity me) {
        if (me.getLocation().getX()>= this.length){return true;}
        return false;
    }

    @Override
    public IArena getArenaService(String Arena) {
        return null;
    }

    /**
     * setters and getters
     *
     */
    public double getLength() {
        return this.length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public SnowSurface getSurface() {
        return surface;
    }

    public void setSurface(SnowSurface surface) {
        this.surface = surface;
    }

    public WeatherCondition getCondition() {
        return condition;
    }

    public void setCondition(WeatherCondition condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "WinterArena" ;
    }
}
