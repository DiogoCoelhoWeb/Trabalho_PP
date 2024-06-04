/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import Files.ImportJSON;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author diogo
 */
public class TesteJ {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {

        // Cria uma instância da classe ImportJSON
        ImportJSON importJSON = new ImportJSON();

        // O caminho para o arquivo JSON que você deseja processar
        String filePath = "C:\\Users\\diogo\\Desktop\\moodle\\ExemplosJSON\\jsonFiles\\AidBoxes.json";

        try {
            // Lê o arquivo JSON em uma string
            String jsonString = new String(Files.readAllBytes(Paths.get(filePath)));

            // Converte a string JSON em um JSONArray
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(jsonString);

            // Processa cada objeto no array
            for (Object obj : jsonArray) {
                JSONObject jsonObject = (JSONObject) obj;

                // Converte o JSONObject em uma string
                String jsonObjectString = jsonObject.toJSONString();

                // Chama o método getAidBoxJSONObject
                JSONObject aidBoxJson = importJSON.getAidBoxJSONObject(jsonObjectString);

                // Imprime o JSONObject resultante
                System.out.println(aidBoxJson.toJSONString());
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

}
