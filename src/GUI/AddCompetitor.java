package GUI;

import designpatterns.CompetitorClone;
import game.arena.WinterArena;
import game.competition.Competition;
import game.competition.Competitor;
import game.entities.Decorator.ColoredSportsman;
import game.entities.Decorator.IWinterSportsman;
import game.entities.Decorator.SpeedySportsman;
import game.entities.Decorator.WSDecorator;
import game.entities.sportsman.WinterSportsman;
import game.enums.*;
import utilities.Point;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Observable;

import static GUI.CreateCompetition.arena;
import static GUI.CreateCompetition.competition;

public class AddCompetitor extends JPanel {
    private JLabel Name, Age, Max_speed, Accelertion, ADD_C , Co,Number,colorlabel,accelertionlabel;
    private JTextField Textbox1_Name, Textbox1_speed, Textbox1_Accelertion, Textbox1_Age ,Num,num2,accelertiontext;
    private JComboBox<ColorType> Colo,colo2,color3;


    public static JComboBox<String> Competibox;
    private JButton Add_comp,BTNDuplicateCompetitior,BTNSubmit,BTNDecorator,BTNSubmit1;
    static int racernum=0;
    protected static game.entities.sportsman.WinterSportsman racer;
    String racerName;
    double racerMaxSpeed;
    double racerAcceleration;
    double racerAge;
    ColorType ColorComp;
    int NumComp;
    public static void setCompetibox() {
        Competibox.removeAllItems();
        for (Competitor i : competition.getactiveCompetitors()) {
            Competibox.addItem((WinterSportsman.check(i).getName()));
        }

    }

    public AddCompetitor(){

        this.setLayout(new GridLayout(17,1,0,1));
        //this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        this.setPreferredSize(new Dimension(150,300));

        ADD_C = new JLabel(" ADD COMPETITOR");
        ADD_C.setFont(ADD_C.getFont().deriveFont(Font.BOLD, 14f));
        this.add(ADD_C);
        Name = new JLabel("Name");
        this.add(Name);
        Textbox1_Name = new JTextField();
        this.add(Textbox1_Name);
        Age = new JLabel("Age");
        this.add(Age);
        Textbox1_Age = new JTextField();
        this.add(Textbox1_Age);
        Max_speed = new JLabel("Max_speed");
        this.add(Max_speed);
        Textbox1_speed = new JTextField();
        this.add(Textbox1_speed);
        Accelertion = new JLabel("Acceleration");
        this.add(Accelertion);
        Textbox1_Accelertion = new JTextField();
        this.add(Textbox1_Accelertion);
        Co = new JLabel("Color") ;
        this.add(Co);
        Colo = new JComboBox<>(ColorType.values());
        colo2= new JComboBox<>(ColorType.values());
        this.add(Colo);
        Number = new JLabel("Racer Number");
        this.add(Number);
        Num = new JTextField();
        num2= new JTextField();
        this.add(Num);
        Competibox = new JComboBox<>();

        Add_comp = new JButton("Add competitor");
        BTNDuplicateCompetitior = new JButton("Duplicate");
        BTNDecorator = new JButton("Decorator");
        colorlabel = new JLabel("Choose color");

        color3= new JComboBox<>(ColorType.values());
        color3.insertItemAt(null,0);
        accelertionlabel = new JLabel("Choose Acceleration");
        accelertiontext = new JTextField("0");
        Add_comp.addActionListener(new ActionListener() {

            /**
             * This function is responsible for the actions that occur on the button Add racer
             */
            public void actionPerformed(ActionEvent e) {
                if(BuildArena.getWinterArena()== null)
                {
                    JOptionPane.showMessageDialog(null, "Please build arena,create competition and add competitors!");
                    return;
                }
                if (MainFrame.isGameStarted() == true)
                {
                    JOptionPane.showMessageDialog(null, "You can not add a competitor during a contest!");
                    return;
                }
                if (competition.getFinishedCompetitors().size() != 0)
                {
                    JOptionPane.showMessageDialog(null, "You can not add a competitor while the contest is over!");
                    return;
                }

                if(CreateCompetition.competition == null)
                {
                    JOptionPane.showMessageDialog(null, "Please create competition and add competitors!");
                    return;
                }
                if (CreateCompetition.competition.getactiveCompetitors().size() >= CreateCompetition.getMaxComp()) {
                    JOptionPane.showMessageDialog(null, "The competition are full");
                    return;
                }
                if (Textbox1_Name.getText().equals("") || Textbox1_speed.getText().equals("") || Textbox1_Accelertion.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Fill the empty text field! Please try again.");
                    return;
                }

                if (MainFrame.getInstance().isGameStarted()) {
                    JOptionPane.showMessageDialog(null, "cannot add racers while race is runing!");
                    return;
                }
                racerName = Textbox1_Name.getText();
                racerMaxSpeed = Double.parseDouble(Textbox1_speed.getText());
                racerAcceleration = Double.parseDouble(Textbox1_Accelertion.getText());
                racerAge = Double.parseDouble(Textbox1_Age.getText());
                ColorComp  = Colo.getItemAt(Colo.getSelectedIndex());
                NumComp = Integer.parseInt(Num.getText());
                if (!competition.getactiveCompetitors().isEmpty()){
                    for (Competitor i : competition.getactiveCompetitors()) {
                        if (NumComp == i.getNum()){
                            JOptionPane.showMessageDialog(null, "Competitor number is taken! Please try again.");
                            return;
                        }
                    }
                }
                if (Num.getText().equals("") ||Textbox1_Name.getText().equals("") || Textbox1_speed.getText().equals("") || Textbox1_Accelertion.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Fill the empty text field! Please try again.");
                    return;
                }

                if (MainFrame.getInstance().isGameStarted()) {
                    JOptionPane.showMessageDialog(null, "cannot add racers while race is runing!");
                    return;
                }
                try {
                    ClassLoader classLoader = AddCompetitor.class.getClassLoader();
                    Class c = classLoader.loadClass("game.entities.sportsman." + CreateCompetition.competition.getClassName() + "er");
                    Constructor con = c.getConstructor(String.class, double.class, Gender.class, double.class, double.class, Discipline.class,ColorType.class,int.class);
                    racer = (game.entities.sportsman.WinterSportsman) con.newInstance(racerName, racerAge, CreateCompetition.competition.getGender(), racerAcceleration, racerMaxSpeed, CreateCompetition.competition.getDiscipline(),ColorComp,NumComp);


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
                try {
                    racer.setLocation(new Point(0,CreateCompetition.competition.getactiveCompetitors().size()*6));
                    CreateCompetition.getCompetition().addCompetitor(racer);
                    racer.registerObserver(competition);
                    ControlView.getInstance().updateView();
                    (racer).addObserver(competition);
                    racernum++;
                    Competibox.removeAllItems();
                    for (Competitor i : competition.getactiveCompetitors()){
                        Competibox.addItem(( WinterSportsman.check(i).getName()));
                    }
                } catch (IllegalArgumentException j) {
                    j.printStackTrace(System.out);
                }



            }
            });
            this.add(Add_comp);
            this.add(BTNDuplicateCompetitior);
            this.add(Competibox);
            this.add(BTNDecorator);

        BTNDuplicateCompetitior.addActionListener(new ActionListener() {
            /**
             * This function is responsible for the actions that occur on the button Duplicate Competitor
             */
            public void actionPerformed(ActionEvent e) {
                JFrame fra = new JFrame("Duplicate Frame");
                fra.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                fra.setLocationRelativeTo(null);
                fra.setResizable(false);
                JPanel Upper = new JPanel();
                JPanel Center = new JPanel();
                JPanel Lower = new JPanel();

                fra.getContentPane().add(Upper);
                fra.getContentPane().add(Center);
                fra.getContentPane().add(Lower);

                Upper.setPreferredSize(new Dimension(200,20));
                Upper.setLayout(new GridLayout(1,1,0,0));
                Center.setPreferredSize(new Dimension(200,20));
                Center.setLayout(new GridLayout(1,1,0,0));
                Upper.setPreferredSize(new Dimension(200,20));
                Center.setLayout(new GridLayout(1,1,0,0));

                BTNSubmit = new JButton("Submit");
                Upper.add(Co);
                Upper.add(colo2);
                Center.add(Number);
                Center.add(num2);
                Lower.add(BTNSubmit);

                fra.add(Upper, BorderLayout.NORTH);
                fra.add(Center, BorderLayout.CENTER);
                fra.add(Lower,BorderLayout.SOUTH);
                fra.pack();
                fra.setVisible(true);
                BTNSubmit.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        if (num2.getText().equals("")){
                            JOptionPane.showMessageDialog(null, "please enter number competitor! Please try again.");
                            return;
                        }
                        if (!competition.getactiveCompetitors().isEmpty()){
                            for (Competitor i : competition.getactiveCompetitors()) {
                                if (Integer.parseInt(num2.getText()) == i.getNum()){
                                    JOptionPane.showMessageDialog(null, "Competitor number is taken! Please try again.");
                                    return;
                                }
                            }
                        }
                        Competitor clonecompitetor = competition.getcompetitor(Competibox.getSelectedItem().toString());
                        CompetitorClone C = new CompetitorClone();
                        racer = C.getClone(clonecompitetor);
                        racer.setLocation(new Point(0,CreateCompetition.competition.getactiveCompetitors().size()*6));
                        racer.setColor(colo2.getItemAt(colo2.getSelectedIndex()));
                        racer.setNumComp( Integer.parseInt(num2.getText()));
                        //racer.setColor();
                        CreateCompetition.getCompetition().addCompetitor(racer);
                        racer.registerObserver(competition);
                        ControlView.getInstance().updateView();
                        (racer).addObserver(competition);
                        racernum++;
                        setCompetibox();
                        fra.dispose();

                    }
                });
            }
        });
        BTNDecorator.addActionListener(new ActionListener() {
            /**
             * This function is responsible for the actions that occur on the button Duplicate Competitor
             */
            public void actionPerformed(ActionEvent e) {
                JFrame fra = new JFrame("Duplicate Frame");
                fra.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                fra.setLocationRelativeTo(null);
                fra.setResizable(false);
                JPanel Upper = new JPanel();
                JPanel Center = new JPanel();
                JPanel Lower = new JPanel();

                fra.getContentPane().add(Upper);
                fra.getContentPane().add(Center);
                fra.getContentPane().add(Lower);

                Upper.setPreferredSize(new Dimension(200,20));
                Upper.setLayout(new GridLayout(1,1,0,0));
                Center.setPreferredSize(new Dimension(200,20));
                Center.setLayout(new GridLayout(1,1,0,0));
                Upper.setPreferredSize(new Dimension(200,20));
                Center.setLayout(new GridLayout(1,1,0,0));

                BTNSubmit1 = new JButton("Submit");
                Upper.add(colorlabel);
                Upper.add(color3);
                Center.add(accelertionlabel);
                Center.add(accelertiontext);
                Lower.add(BTNSubmit1);

                fra.add(Upper, BorderLayout.NORTH);
                fra.add(Center, BorderLayout.CENTER);
                fra.add(Lower,BorderLayout.SOUTH);
                fra.pack();
                fra.setVisible(true);

                BTNSubmit1.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {

                        Competitor Competitor1 = competition.getcompetitor(Competibox.getSelectedItem().toString());
                        if (color3.getItemAt(color3.getSelectedIndex())!=null) {
                            new ColoredSportsman((IWinterSportsman) Competitor1, color3.getItemAt(color3.getSelectedIndex()));
                        }

                        if (Double.parseDouble(accelertiontext.getText())!=0) {
                            new SpeedySportsman((IWinterSportsman) Competitor1, Double.parseDouble(accelertiontext.getText()));
                        }
                        fra.dispose();

                    }

                });
            }
        });

    }


    }


