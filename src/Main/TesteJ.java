package Main;

import Classes.Location;
import Files.ImportJSONAPI;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TesteJ {

    public static void main(String[] args) throws ParseException {

        ImportJSONAPI myClass = new ImportJSONAPI();
        JSONObject[] aidBoxJsonArray = myClass.getAidBoxJSONObjectArray(); // Assuming the method returns JSONArray

        // Print the data
        for (Object obj : aidBoxJsonArray) {
            JSONObject jsonObject = (JSONObject) obj;
            System.out.println(jsonObject.toJSONString());
        } 
    }
}