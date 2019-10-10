package GUI;
/**
 * @author yonatan mahlof 308038256
 * tair hadad 204651897
 */

import Builder.CreateComp;
import game.arena.WinterArena;
import game.competition.CompetitorTime;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

public class StartCompetition extends JPanel {
    private JTextField compnum;
    private static BufferedImage img = null;
    private static boolean flag ;
    private JButton Start, Show,Builder;
    RacerInfoTable infoTable;
    CompetitorTime time;
    public  StartCompetition() {
        this.setLayout(new GridLayout(4, 1, 0, 0));
        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        Start = new JButton("Start Competition");
        Builder = new JButton("Build competition");
        compnum = new JTextField();

        Show = new JButton("Show info");
        Start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (MainFrame.isGameStarted()) {
                    JOptionPane.showMessageDialog(null, "sorry the game started");
                    return;
                }
                if(BuildArena.getWinterArena() ==null) {
                    JOptionPane.showMessageDialog(null, "Please build arena first and then start the game!");
                    return;
                }
                if(CreateCompetition.competition == null) {
                    JOptionPane.showMessageDialog(null, "Please build competition first and then start the game!");
                    return;
                }
                if( CreateCompetition.competition.getactiveCompetitors().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please add racers to competition first and then start the game!");
                    return;
                }

                        CreateCompetition.competition.StartTime();
                        MainFrame.setGameStarted(true);
                        MainFrame.getInstance().addObserver();
                        CreateCompetition.competition.playTurn();
                        System.out.println("######################################################################");

            }

        });
        this.add(Start);
        Show.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
        infoTable = new RacerInfoTable(CreateCompetition.getCompetition());
            }
        });

        this.add(Show);
        Builder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CreateComp builder = new CreateComp();
                int NumComp;

                NumComp = Integer.parseInt(compnum.getText());
                builder.buildRace(NumComp);
                WinterArena arena = builder.getRace().getarena();
                ControlView.getInstance().setBackgr("Cloudy");
                AddCompetitor.setCompetibox();




            }

        });
        this.add(compnum);
        this.add(Builder);


    }



}
