package edu.isep.jeudes6couleurs.file;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileInputStream;

import edu.isep.jeudes6couleurs.game.*;
import edu.isep.jeudes6couleurs.game.grids.Grid;
import edu.isep.jeudes6couleurs.game.grids.GridDiamond;
import edu.isep.jeudes6couleurs.game.grids.GridRectangle;
import edu.isep.jeudes6couleurs.game.grids.GridSquare;

/**
 * Jeu des 6 couleurs
 *
 * @package edu.isep.jeudes6couleurs.file
 * @class   Load
 * @desc    Gère le chargement des sauvegardes
 *
 * @author  Thibault Vlacich <thibault.vlacich@isep.fr>
 * @author  Hugo Michard <hugo.michard@isep.fr>
 */
public class Load {
  /**
   * Permet de charger le jeu depuis un fichier
   * 
   * @param   file  Le fichier depuis lequel charger
   * 
   * @return  L'objet Game réprésentant la partie sauvegardée
   */
  public static Game loadSave(File file) {
    Game game;
    
    try {
      FileInputStream input = new FileInputStream(file);
      byte[] bytes          = new byte[input.available()];
      
      input.read(bytes);
      String fileContent = new String(bytes, "UTF-8");
      input.close();
      
      JSONObject jsonObject = (JSONObject) (new JSONParser().parse(fileContent));
      
      if (
             !jsonObject.containsKey("type")
          || !jsonObject.containsKey("currentPlayer")
          || !jsonObject.containsKey("players")
          || !jsonObject.containsKey("colorGrid")
          || !jsonObject.containsKey("playerGrid")
          )
      {
        // Le fichier de sauvegarde n'est pas correct
        return null;
      }
      
      String gridType = (String) jsonObject.get("type");
      
      int currentPlayer = ((Long) jsonObject.get("currentPlayer")).intValue();
      
      JSONObject playerObject = (JSONObject) jsonObject.get("players");
      
      JSONArray colorGrid  = (JSONArray) jsonObject.get("colorGrid");
      JSONArray playerGrid = (JSONArray) jsonObject.get("playerGrid");
      
      Grid grid;
      
      switch (gridType) {
        case "square":
          // Grille carrée
          int gridSize = ((Long) jsonObject.get("size")).intValue();

          grid = new GridSquare(gridSize);
          
          grid.initWithSave(colorGrid, playerGrid);
        break;
        case "diamond":
          // Grille en losange
          int nbOfLines = ((Long) jsonObject.get("nbOfLines")).intValue();

          grid = new GridDiamond(nbOfLines);
          
          grid.initWithSave(colorGrid, playerGrid);
        break;
        case "rectangle":
          // Grille rectangulaire
          int gridSizeX = ((Long) jsonObject.get("sizex")).intValue();
          int gridSizeY = ((Long) jsonObject.get("sizey")).intValue();

          grid = new GridRectangle(gridSizeX, gridSizeY);
          
          grid.initWithSave(colorGrid, playerGrid);
        break;
        default:
          // Type de grille inconnue
          grid = null;
      }
      
      if (grid != null) {
        game = new Game(playerObject, grid);
        
        game.setStartingPlayer(currentPlayer);
        
        return game;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return null;
  }
}
