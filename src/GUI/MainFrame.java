package GUI;

import game.competition.CompetitorTime;
import game.entities.sportsman.Sportsman;

import javax.swing.*;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

public class MainFrame extends JFrame implements Observer {
    private static final long serialVersionUID = 1L;
    protected static MenuPanel pMenu;
    protected static ControlView pCenter;
    protected static int DEFAULT_WIDTH = 1000;
    protected static int DEFAULT_HEIGHT = 900;
    static private volatile MainFrame instance = null;
    private static boolean gameStarted = false;
    private static boolean gamefinshed = true;

    public static MainFrame getInstance() {
        if (instance == null)
            synchronized (MainFrame.class) {
                if (instance == null)
                    instance = new MainFrame();
            }
        return instance;
    }

    public MainFrame() {
        this.setTitle("Competition");
        this.setLocation(0, 0);
        this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        pMenu = new MenuPanel();
        pCenter = ControlView.getInstance();
        setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(pMenu, BorderLayout.EAST);
        this.add(pCenter, BorderLayout.CENTER);
        setVisible(true);

    }

    public static ControlView getpCenter() {
        return pCenter;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static int getDefaultWidth() {
        return DEFAULT_WIDTH;
    }

    public static int getDefaultHeight() {
        return DEFAULT_HEIGHT;
    }

    public static void setGameFinished(boolean b) {
        gamefinshed=b;
    }

    public MenuPanel getpMenu() {
        return pMenu;
    }

    public static boolean isGameStarted() {
        return gameStarted;
    }

    public static void setGameStarted(boolean gameStarted) {
        MainFrame.gameStarted = gameStarted;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (CreateCompetition.competition.hasActiveCompetitors()) {

        }revalidate();
        repaint();
        }

    public void addObserver() {
        for (int j = 0; j < CreateCompetition.competition.getactiveCompetitors().size(); j++) {
            ((Sportsman) CreateCompetition.competition.getactiveCompetitors().get(j)).addObserver(this);
        }
    }
}

