package GUI;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Factory.ArenaFactory;
import game.arena.IArena;
import game.arena.WinterArena;
import game.enums.ArenaType;
import game.enums.SnowSurface;
import game.enums.WeatherCondition;

public class BuildArena extends JPanel {
    private JLabel Build_Arena, Arena_len, Snow_surf, Weather_con,arenaType;
    private JTextField Textbox1_Arena;
    private JComboBox<ArenaType>arenaTypebox;
    private JComboBox<SnowSurface> opertion_Snow;
    private JComboBox<WeatherCondition> opertion_Weather;
    public JButton build_Arena;
    protected static IArena winterArena = null;

    protected static double IntArenaLength;


    public  BuildArena(){
        this.setLayout(new GridLayout(10,1, 0, 0));
        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        this.setPreferredSize(new Dimension(150,200));
        Build_Arena = new JLabel(" BUILD ARENA");
        Build_Arena.setFont(Build_Arena.getFont().deriveFont(Font.BOLD, 14f));
        this.add(Build_Arena);
        Arena_len = new JLabel(" Arena length");
        this.add(Arena_len);
        Textbox1_Arena = new JTextField("700");
        this.add(Textbox1_Arena);
        Snow_surf = new JLabel(" Snow surface");
        this.add(Snow_surf);
        opertion_Snow = new JComboBox<>(SnowSurface.values());
        this.add(opertion_Snow);
        Weather_con = new JLabel(" Weather condition");
        this.add(Weather_con);
        opertion_Weather = new JComboBox<>(WeatherCondition.values());
        this.add(opertion_Weather);
        arenaType = new JLabel(" Weather condition");
        this.add(arenaType);
        arenaTypebox = new JComboBox<>(ArenaType.values());
        this.add(arenaTypebox);
        build_Arena = new JButton("Build Arena");
        build_Arena.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (MainFrame.isGameStarted() == true) {
                    JOptionPane.showMessageDialog(null, "You can not build a new arena during a competition!");
                    return;
                }
                ArenaFactory fac =new ArenaFactory();
                IntArenaLength = 0;
                WeatherCondition arenaType = opertion_Weather.getItemAt(opertion_Weather.getSelectedIndex());
                SnowSurface SnowsurfaceType =  opertion_Snow.getItemAt(opertion_Snow.getSelectedIndex());

                try {
                    if (Textbox1_Arena.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Fill the empty text field! Please try again.");
                        return;
                    }
                    // ---------------------------Add 100 to the size of the arena so we can see the competitors when they finish competing.---------------------------------
                    IntArenaLength = Double.parseDouble(Textbox1_Arena.getText());
                    if (IntArenaLength >= 700 && IntArenaLength <= 900)
                        MainFrame.getInstance().setSize(1000, (int) (IntArenaLength)+100);
                    else {
                        JOptionPane.showMessageDialog(null, "Invalid input values! Please try again.");
                        return;
                    }
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "Invalid input values! Please try again.");
                    return;
                }
                //For creating a new arena after adding competitors.
                if (CreateCompetition.competition != null) {
                    CreateCompetition.competition.initializracer();
                    CreateCompetition.competition = null;
                }
                //setWinterArena(new WinterArena(IntArenaLength,SnowsurfaceType,arenaType));
                winterArena =  fac.buildArena(arenaTypebox.getSelectedItem().toString(),IntArenaLength,SnowsurfaceType,arenaType);
                setWinterArena(winterArena);
                MainFrame.getInstance().setGameStarted(false);
                ControlView.getInstance().setBackgr(arenaType.toString());

            }
        });
        this.add(build_Arena);

    }

    public static double getIntArenaLength() {
        return IntArenaLength;
    }
    public static IArena getWinterArena() { return BuildArena.winterArena; }
    public static void setWinterArena(IArena winterArena1) {
        winterArena = winterArena1;
    }
}