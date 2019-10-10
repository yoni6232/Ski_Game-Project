package game.arena;
/**
 * @author yonatan mahlof 308038256
 * tair hadad 204651897
 */
import game.entities.IMobileEntity;

public interface IArena {
    public double getFriction();
    public boolean isFinished(IMobileEntity me) ;
    public IArena getArenaService(String Arena);

    double getLength();
}
