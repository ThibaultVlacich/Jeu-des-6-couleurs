/**
 * Jeu des 6 couleurs
 *
 * @package file
 * @class   Save
 * @desc    Gère la sauvegarde de la partie
 *
 * @author  Thibault Vlacich <thibault.vlacich@isep.fr>
 * @author  Hugo Michard <hugo.michard@isep.fr>
 */

package file;

import java.io.File;
import java.io.FileWriter;

import org.json.simple.JSONObject;

import game.Game;
import game.Grid;
import game.Player;
import models.TileColor;

public class Save {
  /**
   * Permet de sauvegarder le jeu dans un fichier
   * 
   * @param game  L'instance du jeu à sauvegarder
   */
  public static String save(Game game) {
    // Récupération de la grille
    Grid grid = game.getGrid();
    
    // Objet JSON réprésentant le jeu 
    JSONObject jsonObject = grid.exportToJSON();
    
    // Type de grille
    String gridType = grid.getClass().getName();
    
    if (grid.getClass().getName().equals("game.GridSquare")) {
      gridType = "square";
    } else {
        gridType = null;
    }
    
    jsonObject.put("type", gridType);
    
    // Taille de la grille
    jsonObject.put("size", grid.getSize());
    
    // Objets joueurs
    JSONObject playersObject = new JSONObject();
    
    for(int i = 0; i < game.getNbOfPlayers() ; i++) {
      int pID = i + 1;
      
      Player player = game.getPlayerWithID(pID);
      
      String colorCode = TileColor.getColorCode(player.getColor());
      
      playersObject.put(pID, colorCode);
    }
    
    jsonObject.put("players", playersObject);
    
    // JSON String à écrire dans le fichier
    String jsonString = jsonObject.toJSONString();
    
    return jsonString;
  }
  
  /**
   * Permet de sauvegarder dans un fichier
   * 
   * @param content Le contenu à sauvegarder dans le fichier
   * @param file    Le fichier dans lequel écrire
   */
  public static void saveToFile(String content, File file) {
    try {
      FileWriter fileWriter = new FileWriter(file);
      fileWriter.write(content);
      fileWriter.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
