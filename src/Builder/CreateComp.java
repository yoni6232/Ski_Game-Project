package Builder;

public class CreateComp  {
    public CompetitionBuilder comp;


    public CreateComp(){
        this.comp = new CompetitionBuilder();
    }

    /**
     * build the default race
     * @param amountOfRacers
     */
    public void buildRace(int amountOfRacers) {
        comp.buildWinterArena();
        comp.setarenagame();
        comp.buildSki();
        comp.setcompetitiongame();
        comp.addcompetitor(amountOfRacers);
        comp.buildCompRace();
        //comp.setcompetitiongame();
    }
    public GenricComp getRace() {
        return comp.getRace();
    }
}
