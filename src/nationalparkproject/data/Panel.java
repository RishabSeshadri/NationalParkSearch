package src.nationalparkproject.data;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
public class Panel extends JPanel {
    JTextPane keywords; 
    JLabel imgHolder;
    JTextPane description;
    JTextPane activities;
    JLabel name;
    Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
    public Panel(ArrayList<String> aList) {
        name = new JLabel("label", SwingConstants.CENTER);
        imgHolder = new JLabel(new ImageIcon("src/nationalparkproject/data/topImage1.jpg"));
        description = new JTextPane();
        keywords = new JTextPane();
        activities = new JTextPane();
        this.setLayout(new BorderLayout());
        String str = aList.get(1);
        name.setText(str);
        name.setBorder(border);
        keywords.setBorder(border);
        imgHolder.setBorder(border);
        keywords.setText(aList.get(2));
        name.setPreferredSize(new Dimension(150,50));
        imgHolder.setPreferredSize(new Dimension(150, 50));
        description.setText(aList.get(3) + "\n\n" + aList.get(4));
        activities.setText(aList.get(4));
        description.setEditable(false);
        keywords.setEditable(false);
        activities.setEditable(false);
        this.add(name, BorderLayout.NORTH);
        this.add(description, BorderLayout.WEST);
        this.add(keywords, BorderLayout.SOUTH);
        this.add(imgHolder, BorderLayout.CENTER);
        this.add(activities, BorderLayout.EAST);
        
    }
}
