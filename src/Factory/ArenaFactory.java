package Factory;

import game.arena.IArena;
import game.arena.SummerArena;
import game.arena.WinterArena;
import game.enums.SnowSurface;
import game.enums.WeatherCondition;

public class ArenaFactory {
    private static ArenaFactory instance;

    public static ArenaFactory getInstance() {
        if (instance == null) {
            instance = new ArenaFactory();
        }
        return instance;
    }

    /**
     * build the default arena by the name how sent
     * @param arenaType
     * @return
     */
    public IArena buildDefaultArena(String arenaType) {
        if (arenaType == null) {
            return null;
        }
        switch(arenaType) {
            case "WINTER":
                return new WinterArena(700,SnowSurface.POWDER,WeatherCondition.CLOUDY);
            case "SUMMER":
                return new SummerArena();

        }
        return null;
    }
    /**
     * build the  arena by the name how sent
     * @param arenaType
     * @return
     */
    public IArena buildArena(String arenaType, double length, SnowSurface surface, WeatherCondition condition) {
        if (arenaType == null) {
            return null;
        }
        switch(arenaType) {
            case "WINTER":
                return new WinterArena(length,surface,condition);
            case "SUMMER":
                return new SummerArena();

        }
        return null;
    }
}
