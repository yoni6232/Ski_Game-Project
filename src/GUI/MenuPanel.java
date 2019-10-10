package GUI;

import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import utilities.Point;

public class MenuPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private AddCompetitor AddCompetitorPanel;
    private BuildArena ArenaPanel;
    private CreateCompetition  CompetitionPanel;
    private StartCompetition startPanel;
    private RacerInfoTable info;
    public MenuPanel() {
        this.AddCompetitorPanel = new AddCompetitor();
        this.ArenaPanel = new BuildArena();
        this.CompetitionPanel = new CreateCompetition();
        this.startPanel = new StartCompetition();
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        ArenaPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        CompetitionPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        AddCompetitorPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        startPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(2,1,0,0));

        this.add(ArenaPanel, BorderLayout.PAGE_START);
        this.add(center,BorderLayout.AFTER_LINE_ENDS);
        center.add(CompetitionPanel, BorderLayout.NORTH);
        center.add(AddCompetitorPanel, BorderLayout.SOUTH);
        this.add(startPanel, BorderLayout.PAGE_END);
    }


}
