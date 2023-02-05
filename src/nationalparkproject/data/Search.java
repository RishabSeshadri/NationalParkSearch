package src.nationalparkproject.data;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Search {

    public static ArrayList<ArrayList<String>> commenceSearch(String userInp, String[] natureWords) {
        ArrayList<ArrayList<String>> ret = new ArrayList<>();

        String[] input = userInp.split("[^a-zA-Z0-9']+");
        ArrayList<String> flaggedKeywords = new ArrayList<String>();
        for(String str : input) {
            for (String nature : natureWords) {
                if (str.equalsIgnoreCase(nature)) { flaggedKeywords.add(nature); }
            }
        } 
        PriorityQueue<Park> pq = NationalParksData.findOptimalParks(flaggedKeywords);
        

        while(!pq.isEmpty()) {
            Park park = pq.poll();
            ArrayList<ADT> amb = NationalParksData.npsData.get(park.getName());
            ArrayList<String> arrL = new ArrayList<>();

            for(ADT arbit : amb) {
                arrL.add(arbit.toString());
            }

            arrL.add(park.toStringFlags());
            ret.add(arrL);
        }

        return ret;
    }
}