package src.nationalparkproject.data;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class GUI implements ActionListener {
    private ArrayList<ArrayList<String>> dataToOutput = new ArrayList<>();

    JLabel imageHolder1;
    JLabel imageHolder2;
    JLabel question;
    JTextArea answer;
    JButton button;
    JPanel panel;
    JFrame frame;
    GridBagConstraints constraints;
    ImageIcon topImage;
    ImageIcon bottomImage;

    public GUI() {
        topImage = new ImageIcon("src/nationalparkproject/data/img/parktop1.jfif");
        bottomImage = new ImageIcon("src/nationalparkproject/data/img/bottom_picture1.jpg");
        answer = new JTextArea(5, 50);
        constraints = new GridBagConstraints();
        frame = new JFrame("Park Questions");
        question = new JLabel("Hello! Please tell me a little about activities you like to do and " +
         "what kind of parks you would like to see :)");
        panel = new JPanel();
	    imageHolder1=new JLabel(topImage);
        imageHolder2=new JLabel(bottomImage);
	    button = new JButton("submit");
        button.addActionListener(this);
    } // GUI
    public void init() {
        frame.setPreferredSize(new Dimension(1535, 993));
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder());
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.anchor = GridBagConstraints.NORTH;
        panel.setLayout(new GridBagLayout());
        panel.add(imageHolder1, constraints);
        constraints.gridy = 1;
        panel.add(question, constraints);
        constraints.gridy = 2;
        panel.add(button, constraints);
        constraints.gridy = 3;
        panel.add(answer, constraints);
        constraints.gridy = 4;
        panel.add(imageHolder2, constraints);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        frame.setResizable(false);
        frame.add(panel);
        frame.pack();
        panel.repaint();

        InterpretorAPI.main(null);
    }
    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.init();
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
        String text = answer.getText();
        if(text.length() == 0) return; //TELL USER TO FIX.
        dataToOutput = Search.commenceSearch(text, InterpretorAPI.natureWords);
        /*for(ArrayList<String> strArr : dataToOutput) {
            for(String str : strArr) {
                System.out.print(str + ", ");
            }
            
            System.out.println("\n---------------------------------------\n");
        } */
        finalDisplay display = new finalDisplay();
        display.init(dataToOutput);
    }
}
