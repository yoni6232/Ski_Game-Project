package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class InputPanel extends JPanel {
    JLabel lblInput;
    JTextField txfInput;

    public InputPanel(String name,String input) {
        this.lblInput = new JLabel(name + ":");
        this.txfInput = new JTextField(input);
        this.setLayout(new GridLayout(2,1));
        this.txfInput.addMouseListener(new MouseListener() {
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mousePressed(MouseEvent e) {
                txfInput.setText("");

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub

            }
        });
        this.add(lblInput);
        this.add(txfInput);
    }

    public JLabel getLblInput() {
        return lblInput;
    }

    public JTextField getTxfInput() {
        return txfInput;
    }

    public String getInputText() {
        return txfInput.getText();
    }

}
