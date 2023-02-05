package src.nationalparkproject.data;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.File;
import java.util.Scanner;

public class InterpretorAPI {
    private static String url;
    private static String fullName = "";
    private static String description = "";
    private static String latitude = "";
    private static String longitude = "";
    private static String[] activities;
    private static String weatherInfo = "";

    public static String[] natureWords;
    
    public void getData(String parkCode) {
        String inline = "";
        try {
            String link = "https://developer.nps.gov/api/v1/parks?parkCode="+ parkCode +"&api_key=ljzxY1Uwa61FMu6ebbdqn4bBAiHQia5BWBapxKaJ";
            URL url1 = new URL(link);
            HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Getting the response code
            int responsecode = conn.getResponseCode();
            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {
          
                Scanner scanner = new Scanner(url1.openStream());
          
                //Write all the JSON data into a string using a scanner
                while (scanner.hasNext()) {
                inline += scanner.nextLine();
                }
               
            
                //Close the scanner
                scanner.close();
        
                //Using the JSON simple library parse the string into a json object
                JSONParser parse = new JSONParser();
                JSONObject data_obj = (JSONObject) parse.parse(inline);
        
                //Get the required object from the above created object
                JSONArray arr = (JSONArray) data_obj.get("data");
                JSONObject new_obj = null;
                for (int i = 0; i < arr.size(); i++) {

                    new_obj = (JSONObject) arr.get(i);
                }
                //Get the required data using its key
                //System.out.println(new_obj.get("activities"));
                 url =(String)new_obj.get("url");
                 fullName = (String)new_obj.get("fullName");
                 description = (String)new_obj.get("description");
                 latitude = (String)new_obj.get("latitude");
                 longitude = (String)new_obj.get("longitude");
                 weatherInfo = (String)new_obj.get("weatherInfo");
                 String activity = new_obj.get("activities").toString();
                 String[] data = activity.split("\"");
                 int j = 0;
                 String[] temp = new String[100];
                 for(int i = 0; i <data.length; i++) {
                    if(data[i].equals("name")) {
                        temp[j] = data[i+2];
                        j++;
                    }
                 } // for
                 activities = new String[j + 1];
                 int i = 0;
                 while(temp != null) {
                    activities[i] = temp[i];
                    i++;
                 }
                 //System.out.println(activities);
             }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    } // getData
    public static void main(String[] args) {

        File file = new File("./src/nationalparkproject/data/message.txt");
        String[] codes = new String[30];
        codes = readFile(file);
        file = new File("./src/nationalparkproject/data/natureWords.txt");
        natureWords = readFile(file); 
        
        InterpretorAPI obj = new InterpretorAPI();
        int ctr = 0;
        for (String code : codes) {
            System.out.print(++ctr + ", code: " + code + ": ");
            obj.getData(code);
            String[] params = new String[]{url, fullName, description, latitude, longitude, weatherInfo};
            ArrayList<String> acts = new ArrayList<String>();
            Collections.addAll(acts, activities);

            NationalParksData.integrateData(code, params, acts);
        } 
        
    } // main

    public static String[] readFile(File file) {
        String[] codes = new String[0];
        try {
            Scanner myReader = new Scanner(file);
            String data = myReader.nextLine();
            codes = data.split("[^a-zA-Z0-9']+");
            myReader.close();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return codes;
    }
} // DataGathering