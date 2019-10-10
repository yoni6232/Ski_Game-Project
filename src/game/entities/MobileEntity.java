package game.entities;

public abstract class MobileEntity extends Entity implements IMobileEntity {
    private double maxSpeed;
    private double acceleration;
    private double speed;
    public MobileEntity(double max , double acceleration) {
        this.maxSpeed=max;
        this.acceleration= acceleration;
        this.speed=0;
    }


    /**
     * setters and getters
     *
     */
    public void setCurrentSpeed (double currentSpeed) {
        this.speed = currentSpeed;
    }


    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
