package src.nationalparkproject.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import com.groupdocs.search.*;

public class NationalParksData {
    public static HashMap<String, ArrayList<ADT>> npsData = new HashMap<>();

    //Adds parks to the stuff
    public static void integrateData(String parkCode, String[] results, ArrayList<String> activityList) {

        ADT url = new ADT(results[0]);
        ADT fullName = new ADT(results[1]);
        ADT description = new ADT(results[2]);
        ADT latitude = new ADT(Double.parseDouble(results[3]));
        ADT longitude = new ADT(Double.parseDouble(results[4]));
        ADT activities = new ADT(activityList);
        ADT weatherInfo = new ADT(results[5]);

        ArrayList<ADT> populate = new ArrayList<>();
        
        populate.add(url);
        populate.add(fullName);
        populate.add(description);
        populate.add(latitude);
        populate.add(longitude);
        populate.add(activities);
        populate.add(weatherInfo);

        npsData.put(parkCode, populate);
    }

    //Takes in list of flagged keywords, browses websites, returns parks that have necessary terms
    public static PriorityQueue<Park> findOptimalParks(ArrayList<String> flaggedKeywords) {
        ArrayList<String> second = new ArrayList<String>();
        for (String str : flaggedKeywords) {
            Index index = new Index();
            Collections.addAll(second, index.getDictionaries().getSynonymDictionary().getSynonyms(str));
            index.close();
        }
        flaggedKeywords.addAll(second);
        
        PriorityQueue<Park> optimalParks = new PriorityQueue<>();
        
        for (Map.Entry<String,ArrayList<ADT>> park : npsData.entrySet()) {
            HashSet<String> seen = new HashSet<>();
            int numFlags = 0;
            Park curr = new Park(park.getKey(), numFlags);

            ArrayList<String> activities = park.getValue().get(5).getArrList();

            for (String keyword : flaggedKeywords) {
                for (String activity : activities) {
                    if(keyword.equalsIgnoreCase(activity) && activity.length() >= 2 && !seen.contains(activity)) {
                        seen.add(activity);
                        curr.addFlag(keyword);
                        numFlags++;
                    }
                }

                String[] description = park.getValue().get(2).getString().split("[^a-zA-Z0-9']+");
                
                for (String descriptionTerm : description) {
                    if(keyword.equalsIgnoreCase(descriptionTerm) && descriptionTerm.length() >= 2 && !seen.contains(descriptionTerm)) {
                        seen.add(descriptionTerm);
                        curr.addFlag(keyword);
                        numFlags++;
                    }
                }
            }

            if(numFlags > 0) {
                curr.setPriority(numFlags);
                optimalParks.offer(curr); 
            }
        }

        return optimalParks;
    }

    public static void printData() {
        for (Map.Entry<String,ArrayList<ADT>> park : npsData.entrySet()) {
            System.out.println("[Name: " + park.getKey() + ", latitude: " + park.getValue().get(3).getLong() + ", longitude: " + park.getValue().get(4).getLong() + "]");
        }
    }
}

//Arbitrary Data Type
class ADT { 
    Double doubleStorage;
    String strStorage;
    ArrayList<String> arrListStorage;

    public ADT(Double param) { doubleStorage = param; }
    public ADT(String param) { strStorage = param; }
    public ADT(ArrayList<String> param) { arrListStorage = param; }

    public Double getLong() { return doubleStorage; }
    public String getString() { return strStorage; }
    public ArrayList<String> getArrList() { return arrListStorage; }
    public String toString() { 
        if(doubleStorage != null) return doubleStorage + "";
        else if(strStorage != null) return strStorage + "";
        else if (arrListStorage != null) {
            String ret = "[";
            for (int i = 0; i < arrListStorage.size() - 1; i++) {
                ret += arrListStorage.get(i) + ", ";
            }
            return ret + arrListStorage.get(arrListStorage.size() - 1) + "]";
        }
        return "";
    }
}