package GUI;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import game.arena.IArena;
import game.arena.SummerArena;
import game.arena.WinterArena;
import game.competition.Competition;
import game.competition.SkiCompetition;
import game.competition.WinterCompetition;
import game.entities.sportsman.Snowboarder;
import game.enums.*;

public class CreateCompetition extends JPanel {
    private static String competitionSting;
    private JLabel Create_comp, choose_comp, Max_comp, Discpline, Legue, Gander;
    private JTextField Max_field;
    private JComboBox<CompetitionType> choose_compbox;
    private JComboBox<Discipline> DiscBox;
    private JComboBox<League> LeaBox;
    private JComboBox<Gender> GanBox;
    public JButton Create_competition;
    protected static int maxComp;


    protected static game.competition.Competition competition;
    protected static IArena arena = BuildArena.getWinterArena();
    protected static Gender gender;
    private static League league;


    private static Discipline discipline;
    private ImageIcon racersImages[] = null;
    private CompetitionType competitionType;



    public CreateCompetition() {
        this.setLayout(new GridLayout(12, 1, 0, 0));
        //this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        this.setPreferredSize(new Dimension(150, 50));

        Create_comp = new JLabel(" CREATE COMPETITION");
        Create_comp.setFont(Create_comp.getFont().deriveFont(Font.BOLD, 14f));
        this.add(Create_comp);

        choose_comp = new JLabel(" Choose competition");
        this.add(Create_comp);
        choose_compbox = new JComboBox<>(CompetitionType.values());
        this.add(choose_compbox);
        Max_comp = new JLabel(" Max competitiors number");
        this.add(Max_comp);
        Max_field = new JTextField("10");
        Max_field.setText("10");
        this.add(Max_field);
        Discpline = new JLabel(" Discpline");
        this.add(Discpline);
        DiscBox = new JComboBox<>(Discipline.values());
        this.add(DiscBox);
        Legue = new JLabel(" League");
        this.add(Legue);
        LeaBox = new JComboBox<>(League.values());
        this.add(LeaBox);
        Gander = new JLabel(" Gander");
        this.add(Gander);
        GanBox = new JComboBox<>(Gender.values());
        this.add(GanBox);
        Create_competition = new JButton("Create_competition ");
        Create_competition.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                arena = BuildArena.getWinterArena();
                competition = null;

                if (arena == null) {
                    JOptionPane.showMessageDialog(null, "Enter arena first! Please try again.");
                    return;
                }
                if (arena instanceof SummerArena) {
                    JOptionPane.showMessageDialog(null, "Summer arena dont exsit change to winter arena ! Please try again.");
                    return;
                }
                if (Max_field.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Fill the empty text field! Please try again.");
                    return;
                }

                competitionType = choose_compbox.getItemAt(choose_compbox.getSelectedIndex());
                discipline = DiscBox.getItemAt(DiscBox.getSelectedIndex());
                league = LeaBox.getItemAt(LeaBox.getSelectedIndex());
                gender = GanBox.getItemAt(GanBox.getSelectedIndex());
                try {
                    int maxComp = Integer.parseInt(Max_field.getText());
                    setMaxComp(maxComp);
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "Invalid input values! Please try again.");
                    return;
                }
                if (maxComp < 1 || maxComp > 20) {
                    JOptionPane.showMessageDialog(null, "Invalid input values! Please try again.");
                    return;
                }
                    //Increase the width of the arena if there is not enough room.
                if(maxComp>10)
                    MainFrame.getInstance().setSize(1000+(maxComp-10)*85,(int)BuildArena.IntArenaLength+100);
                try {
                    ClassLoader classLoader = CreateCompetition.class.getClassLoader();
                    Class c = classLoader.loadClass("game.competition." + competitionType.toString() + "Competition");
                    Constructor con = c.getConstructor(WinterArena.class, int.class, Discipline.class, League.class, Gender.class);
                    competition = (game.competition.Competition) con.newInstance(arena, maxComp, discipline, league, gender);
                    racersImages = new ImageIcon[Integer.parseInt(Max_field.getText())];
                    competitionSting = competitionType.name();
                    competition.initializracer();

                    repaint();
                } catch (ClassNotFoundException er) {
                    er.printStackTrace();
                } catch (NoSuchMethodException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (SecurityException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (InstantiationException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (IllegalAccessException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (IllegalArgumentException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (InvocationTargetException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();

                }

            }
        });

        this.add(Create_competition);

    }
        public static void setMaxComp ( int maxComp){
            CreateCompetition.maxComp = maxComp;
        }
        public static String getCompetitionSting () {
            return competitionSting;
        }
        public static int getMaxComp () {
            return maxComp;
        }

        public static Competition getCompetition () {
            return competition;
        }
        public static Gender getGender () {
            return gender;
        }
        public static Discipline getDiscipline () {
            return discipline;
        }
    public static void setCompetition(Competition competition) {
        CreateCompetition.competition = competition;
    }



}
