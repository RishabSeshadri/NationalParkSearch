package src.nationalparkproject.data;
import java.util.ArrayList;

public class Park implements Comparable<Park> {
    private String name;
    private int priority;
    private ArrayList<String> flags;

    public Park(String name, int priority) {
        this.name = name;
        this.priority = priority;
        flags = new ArrayList<String>();
    }
    
    public void setName(String name) { this.name = name; }
    public String getName() { return name; }
    public void setPriority(int val) { priority = val; }
    public int getPriority() { return Integer.valueOf(priority); }
    public void addFlag(String flag) { flags.add(flag); }
    public ArrayList<String> getFlags() { return flags; }

    public int compareTo(Park park1) {
        if (getPriority() < park1.getPriority())
            return 1;
        else if (getPriority() > park1.getPriority())
            return -1;
        return 0;
    }

    public String toString() {
        return "[Name: " + name + ", priority: " + priority + ", flagged keywords: " + getFlags().toString() + "]";
    }

    public String toStringFlags() {
        String ret = "[";
        for(int i = 0; i < flags.size() - 1; i++) {
            ret += flags.get(i) + ", ";
        }
        return ret + flags.get(flags.size() - 1) +"]";
    }
}
