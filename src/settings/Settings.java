package settings;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;

public class Settings {
  // Objet contenant les settings utilisateurs du jeu
  private static JSONObject settings = new JSONObject();
  
  // Réglages par défauts
  private static JSONObject defaultSettings = new JSONObject();
  
  // Nom du fichier contenant les réglages utilisateurs du jeu
  private static String fileName = "settings.txt";
  
  public static void init() {
    Settings.loadDefaults();
    
    try {
      File file             = new File(fileName);
      FileInputStream input = new FileInputStream(file);
      byte[] bytes          = new byte[input.available()];
      
      input.read(bytes);
      String fileContent = new String(bytes, "UTF-8");
      input.close();
      
      settings = (JSONObject) (new JSONParser().parse(fileContent));
    } catch (FileNotFoundException e) {
      // Le fichier de settings n'existe pas
      Settings.save();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public static void loadDefaults() {
    defaultSettings.put("gridType", "square");
    defaultSettings.put("gridSize", new Long(13));
  }
  
  public static void save() {
    try {
      String content = settings.toString();
      
      FileWriter fileWriter = new FileWriter(fileName);
      fileWriter.write(content);
      fileWriter.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public static void set(String keyName, Object value) {
    settings.put(keyName, value);
  }
  
  public static Object get(String keyName) {
    if (settings.containsKey(keyName)) {
      return settings.get(keyName);
    } else if (defaultSettings.containsKey(keyName)) {
      return defaultSettings.get(keyName);
    }
    
    return null;
  }
}
