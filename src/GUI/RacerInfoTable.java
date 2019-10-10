package GUI;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import game.arena.IArena;
import game.arena.WinterArena;
import game.arena.WinterArena.*;
import game.competition.Competition;
import game.competition.Competitor;
import game.entities.sportsman.Sportsman;
import game.entities.sportsman.WinterSportsman;

import static java.lang.Thread.sleep;


public class RacerInfoTable extends JFrame{

    private static final long serialVersionUID = 1L;
    private DefaultTableModel  infoTable;
    protected static game.entities.sportsman.WinterSportsman racer1;
    Competition co=CreateCompetition.competition;
    /**
     * constructor responsible open the info racer table
     * @param arena Arena
     */
    public RacerInfoTable(Competition arena) {
        if(arena==null) {
            JOptionPane.showMessageDialog(null, "Please build the arena first!");
            return;
        }
        if(CreateCompetition.competition == null)
        {
            JOptionPane.showMessageDialog(null, "Please create competition and add competitors!");
            return;
        }
        JPanel jp = new JPanel();
        JTable table = new JTable();
        int i = 0;
        JScrollPane tableContainer = new JScrollPane(table);
        jp.add(tableContainer, BorderLayout.CENTER);
        infoTable = new DefaultTableModel(new Object[] {"Place","Name", "Speed","Max speed", "Loaction", "Finished","Time"},0);
        table.setModel(infoTable);

        upd();
        jp.setPreferredSize(new Dimension(453,(CreateCompetition.getCompetition().getAllCompetitors().size()*18)+(CreateCompetition.getCompetition().getFinishedCompetitors().size()*18) + 30));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().add(jp);
        pack();
        setVisible(true);
    }

    private synchronized void upd() {
        for (Competitor racer : CreateCompetition.competition.getAllCompetitors()) {

            Object data[] = {WinterSportsman.check(racer).getMikom(),(Object) WinterSportsman.check(racer).getName(), (Object) WinterSportsman.check(racer).getSpeed(), (Object) WinterSportsman.check(racer).getMaxSpeed(), racer.getLocation().getX(),racer.getState() != null ? racer.getState().toString() : "None" ,WinterSportsman.check(racer).getFinishedTime()};
            infoTable.addRow(data);
        }

    }

}
