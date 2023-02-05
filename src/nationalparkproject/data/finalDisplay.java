package src.nationalparkproject.data;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
    JPanel Panel1;
    JPanel Panel2;
    JPanel Panel3;
    JPanel Panel4;
    JPanel Panel5;
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
        mainPanel = new JPanel();
        bottomPanel = new JPanel();
	    button=new JButton("return to input");
        button.addActionListener(this);
        }
    public void init(ArrayList<ArrayList<String> > aList) {

        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.setBackground(Color.RED);
        bottomPanel.add(button);
        GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.anchor = GridBagConstraints.NORTH;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.setVisible(true);
        frame.setResizable(false);
        try{
            Panel1 = new Panel(aList.get(0));
            frame.add(Panel1, constraints);
            constraints.gridx = 1;
            constraints.gridx = 2;
        } catch (Exception e) {
            System.out.println(":("); 
        }
        try{
            Panel2 = new Panel(aList.get(1));
            frame.add(Panel2, constraints);
            constraints.gridx = 0;
            constraints.gridy = 1;
        } catch (Exception e) {
            System.out.println(":("); 
        }
        try{
            Panel3 = new Panel(aList.get(2));
            frame.add(Panel3, constraints);
            constraints.gridx = 2;
        } catch (Exception e) {
            System.out.println(":("); 
        }
        try{
            Panel4 = new Panel(aList.get(3));
            frame.add(Panel4, constraints);
            constraints.gridx = 1;
            constraints.gridy = 2;
        } catch (Exception e) {
            System.out.println(":("); 
        }
        frame.add(bottomPanel, constraints);
        frame.pack();
        mainPanel.repaint();
        frame.revalidate();
    }
    public static void runNow(Runnable target) {
        Thread t = new Thread(target);
            t.setDaemon(true);
            t.start();
    }
    public void actionPerformed(ActionEvent e) {
            System.out.println("HI");
             runNow(() -> buttonPressed());
    } // actionPerformed
    public void buttonPressed() {
        GUI gui = new GUI();
        gui.init();
    }
}
