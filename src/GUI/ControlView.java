package GUI;

import Builder.CreateComp;
import game.arena.IArena;
import game.arena.SummerArena;
import game.arena.WinterArena;
import game.competition.Competition;
import game.competition.Competitor;
import game.entities.sportsman.WinterSportsman;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class ControlView extends JPanel implements Runnable, Observer,ActionListener {


    private static final long serialVersionUID = 1L;
    static private volatile ControlView instance = null;
    public final static String PICTURE_PATH = "icons/";
    private BufferedImage backgroundImage;
    private String filename;
    private static final int RacerSize = 70;
    private static BufferedImage sportsmenimge;
    public static int i = 0;
    private Timer timer;
    private final int DELAY=20;

    public static ControlView getInstance() {
        if (instance == null)
            synchronized (ControlView.class) {
                if (instance == null) {
                    instance = new ControlView();
                }
            }
        return instance;
    }
    String type;


    private ControlView() {
        this.filename = null;
//        timer=new Timer (DELAY,this);
//        timer.start();
    }


    void updateView() {
        repaint();
    }


    public void setBackgr(String type) {
        IArena a= BuildArena.getWinterArena();
        if ( a instanceof SummerArena) {
            JOptionPane.showMessageDialog(null, "Summer arena dont exsit change to winter arena ! Please try again.");
            return;
        }
        filename = type + ".jpg";
        try {
            backgroundImage = ImageIO.read(new File(PICTURE_PATH + filename));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error loading file." + filename);

        }
        repaint();
    }
    public void setPlayer(String type){


        this.type=type;
    }
    public void run() {
        CreateCompetition.competition.playTurn();
        while (CreateCompetition.competition.getactiveCompetitors().size() != 0 ) {
            updateView();
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                JOptionPane.showMessageDialog(null, "Error InterruptedException");
            }
        }
        MainFrame.setGameFinished(true);
        MainFrame.setGameStarted(false);
    }
    public void paintComponent(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), null);
        AddRacers(g);
    }
    @Override
    /**
     *  This function return the string of name img
     */
    public String toString() {
        return "filename:" + filename + "  img:" + backgroundImage;
    }

    private void AddRacers(Graphics g) {

        if (BuildArena.getWinterArena() == null)
            return;
        if ( CreateCompetition.competition == null){
            return;
        }
        synchronized(CreateCompetition.competition) {
            if (CreateCompetition.competition.hasActiveCompetitors()) {
                for (int j = 0; j < CreateCompetition.competition.getactiveCompetitors().size(); j++) {
                    addRacer(CreateCompetition.competition.getactiveCompetitors().get(j), g);
                }

            }
            if (CreateCompetition.competition.getFinishedCompetitors().size() > 0) {
                for (int j = 0; j < CreateCompetition.competition.getFinishedCompetitors().size(); j++) {
                    addRacer(CreateCompetition.competition.getFinishedCompetitors().get(j), g);
                }

            }
            if (CreateCompetition.competition.getDisabledCompetitors().size() > 0) {
                for (int j = 0; j < CreateCompetition.competition.getDisabledCompetitors().size(); j++) {
                    addRacer(CreateCompetition.competition.getDisabledCompetitors().get(j), g);
                }

            }
            if (CreateCompetition.competition.getInjurCompetitors().size() > 0) {
                for (int j = 0; j < CreateCompetition.competition.getInjurCompetitors().size(); j++) {
                    addRacer(CreateCompetition.competition.getInjurCompetitors().get(j), g);
                }

            }


        }


    }


    private void addRacer(Competitor r , Graphics g) {

        filename = PICTURE_PATH;
        filename += r.className();
        filename += r.getColor().toString();
        filename += ".png";
        try{
            this.sportsmenimge =  ImageIO.read(new File( filename));
            g.drawImage(this.sportsmenimge,(int)(r.getLocation().getY()*8.5),(int)(r.getLocation().getX()),50,50, null);
            repaint();

        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error loading file." + filename);

        }


    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
        Competitor r = (Competitor)o;
        CreateCompetition.getCompetition().update(o, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}










