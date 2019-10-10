package utilities;

import GUI.MainFrame;

import java.awt.Dimension;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static javax.imageio.ImageIO.read;


public abstract class Game_Panel extends JPanel {

    public static void main(String[] args) {
        MainFrame.getInstance();
        MainFrame.getInstance().setVisible(true);

//            JFrame frame = new JFrame("Competition");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.setPreferredSize(new Dimension (1000,700));
//            Right_panel panel1 = new Right_panel();
//            //Image panel2= new Image();
//            frame.getContentPane().add(panel1,BorderLayout.EAST);
//            //frame.getContentPane().add(panel2);
//        try {
//            img = ImageIO.read(new File("src/Cloudy.jpg")); // eventually C:\\ImageTest\\pic2.jpg
//            ImageIcon p =new ImageIcon();
//            p.setImage(img);
//            JLabel o=new JLabel(p);
//            o.setLayout(null);
//            o.setBounds(0,0,50,50);
//            frame.add(o, BorderLayout.WEST);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        frame.pack();
//            frame.setVisible(true);
//        }
//    @Override
//
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        g.setColor(Color.RED);
//        g.drawImage(img,0,0, null);
    }
}



