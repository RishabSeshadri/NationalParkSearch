package src.nationalparkproject.data;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;


public class finalDisplay implements ActionListener {
    JButton button;
    JPanel mainPanel;
    JFrame frame;
    GridBagConstraints constraints;
    JLabel name;
    JLabel imgHolder;
    JLabel topImage;
    JTextArea description;
    JPanel bottomPanel;
    JTextArea keywords; 
    JTextArea activities;
    CardLayout cardLayout;
    private int index = 0;
    Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
    public finalDisplay() {
        cardLayout = new CardLayout();
        constraints = new GridBagConstraints();
        frame = new JFrame("Final Display");
        mainPanel = new JPanel(new GridBagLayout());
        bottomPanel = new JPanel();
	    button=new JButton("Back");
        button.addActionListener(this);
        }
    public void init(ArrayList<ArrayList<String> > aList) {
        frame.setPreferredSize(new Dimension(1535, 993));
        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.setBackground(Color.RED);
        bottomPanel.add(button);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridheight = 1;
		constraints.gridwidth = 6;
        constraints.gridx = 0;
        constraints.gridy = 0;
		constraints.insets = new Insets(5, 5,5, 5);
		constraints.anchor = GridBagConstraints.NORTH;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.setVisible(true);
        frame.setResizable(true);
        int ctr = 0;
        for (ArrayList<String> each : aList) {
            ++ctr;
            Panel panel = new Panel(each);
            mainPanel.add(panel, constraints);
            constraints.gridy++;
            if(ctr >= 4) break;
        }
        mainPanel.repaint();
        constraints.gridy++;
        frame.add(mainPanel, constraints);
        constraints.gridy++;
        frame.add(bottomPanel, constraints);
        frame.pack();
        frame.revalidate();
    }
    public static void runNow(Runnable target) {
        Thread t = new Thread(target);
            t.setDaemon(true);
            t.start();
    }
    public void actionPerformed(ActionEvent e) {
             runNow(() -> buttonPressed());
    } // actionPerformed
    public void buttonPressed() {
        GUI gui = new GUI();
        gui.init();
    }
}
