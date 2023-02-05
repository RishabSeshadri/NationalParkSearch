package src.nationalparkproject.data;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
public class Panel extends JPanel {
    JTextArea keywords; 
    JLabel imgHolder;
    JTextArea description;
    JTextArea activities;
    JLabel name;
    Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
    public Panel(ArrayList<String> aList) {
        /* 
        int ctr = 0;
        for(String str : aList){
            System.out.println("ctr: " + (++ctr) + ", str: " + str);
        }
        */
        name = new JLabel("label", SwingConstants.CENTER);
        imgHolder = new JLabel(new ImageIcon("src/nationalparkproject/data/topImage1.jpg"));
        description = new JTextArea(5, 15);
        keywords = new JTextArea(3, 18);
        activities = new JTextArea(5,18);
        this.setLayout(new BorderLayout());
        String str = aList.get(1);
        name.setText(str);
        name.setBorder(border);
        keywords.setBorder(border);
        imgHolder.setBorder(border);
        keywords.setText(aList.get(2));
        name.setPreferredSize(new Dimension(100,50));
        imgHolder.setPreferredSize(new Dimension(100, 50));
        description.setText(aList.get(3));
        activities.setText(aList.get(4));
        this.add(name, BorderLayout.NORTH);
        this.add(description, BorderLayout.WEST);
        this.add(keywords, BorderLayout.SOUTH);
        this.add(imgHolder, BorderLayout.CENTER);
        this.add(activities, BorderLayout.EAST);
        
    }
}
