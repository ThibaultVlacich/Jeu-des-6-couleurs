package file;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileInputStream;

import game.Game;
import game.Grid;
import game.GridSquare;
import models.TileColor;

public class Load {
  public static Game loadSave(File file) {
    Game game = new Game();
    
    try {
      FileInputStream input = new FileInputStream(file);
      byte[] bytes          = new byte[input.available()];
      
      input.read(bytes);
      String fileContent = new String(bytes, "UTF-8");
      input.close();
      
      JSONObject jsonObject = (JSONObject) (new JSONParser().parse(fileContent));
      
      String gridType = (String) jsonObject.get("type");
      int    gridSize = ((Long) jsonObject.get("size")).intValue();
      
      JSONObject playerObject = (JSONObject) jsonObject.get("players");
      
      JSONArray colorGrid  = (JSONArray) jsonObject.get("colorGrid");
      JSONArray playerGrid = (JSONArray) jsonObject.get("playerGrid");
      
      Grid grid;
      
      switch (gridType) {
        case "square":
          // Grille carrée
          grid = new GridSquare(gridSize);
          
          grid.initWithSave(colorGrid, playerGrid);
        break;
        default:
          // Type de grille inconnue
          grid = null;
      }
      
      if (grid != null) {
        game = new Game(grid);
        
        for (int i = 0; i < playerObject.size(); i++) {
          int    playerID    = i + 1;
          String playerColor = (String) playerObject.get(Integer.toString(playerID));
          
          game.setColor(playerID, TileColor.getColorFromCode(playerColor));
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return game;
  }
}
