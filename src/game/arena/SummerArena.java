package game.arena;

import game.entities.IMobileEntity;

public class SummerArena implements IArena {


    @Override
    public double getFriction() {
        return 0;
    }

    @Override
    public boolean isFinished(IMobileEntity me) {
        return false;
    }

    @Override
    public IArena getArenaService(String Arena) {
        return null;
    }

    @Override
    public double getLength() {
        return 0;
    }
}
