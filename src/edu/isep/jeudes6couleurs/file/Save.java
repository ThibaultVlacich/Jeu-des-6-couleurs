package edu.isep.jeudes6couleurs.file;

import java.io.File;
import java.io.FileWriter;

import org.json.simple.JSONObject;

import edu.isep.jeudes6couleurs.game.*;
import edu.isep.jeudes6couleurs.game.grids.Grid;
import edu.isep.jeudes6couleurs.game.grids.GridDiamond;
import edu.isep.jeudes6couleurs.game.grids.GridRectangle;
import edu.isep.jeudes6couleurs.game.grids.GridSquare;
import edu.isep.jeudes6couleurs.game.players.LocalPlayer;
import edu.isep.jeudes6couleurs.game.players.NoobIAPlayer;
import edu.isep.jeudes6couleurs.game.players.Player;
import edu.isep.jeudes6couleurs.game.players.RandomIAPlayer;
import edu.isep.jeudes6couleurs.models.TileColor;

/**
 * Jeu des 6 couleurs
 *
 * @package edu.isep.jeudes6couleurs.file
 * @class   Save
 * @desc    Gère la sauvegarde de la partie
 *
 * @author  Thibault Vlacich <thibault.vlacich@isep.fr>
 * @author  Hugo Michard <hugo.michard@isep.fr>
 */
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
    
    // Type et taille de la grille
    if (grid instanceof GridSquare) {
      jsonObject.put("type", "square");
      
      jsonObject.put("size", grid.getSize());
    } else if (grid instanceof GridDiamond) {
      jsonObject.put("type", "diamond");
      
      jsonObject.put("nbOfLines", grid.getSize());
    } else if (grid instanceof GridRectangle) {
      jsonObject.put("type", "rectangle");
      
      jsonObject.put("sizex", ((GridRectangle) grid).getSizeX());
      jsonObject.put("sizey", ((GridRectangle) grid).getSizeY());
    }
    
    // Joueur actuel
    jsonObject.put("currentPlayer", game.getCurrentPlayer().ID);
    
    // Objets joueurs
    JSONObject playersObject = new JSONObject();
    
    for(int i = 0; i < game.getNbOfPlayers() ; i++) {
      int pID = i + 1;
      
      JSONObject playerObject = new JSONObject();
      
      Player player = game.getPlayerWithID(pID);
            
      String colorCode = TileColor.getColorCode(player.getColor());
      playerObject.put("color", colorCode);
      
      if (player instanceof LocalPlayer) {
        playerObject.put("type", "local");
      } else if (player instanceof RandomIAPlayer) {
        playerObject.put("type", "ia-easy");
      } else if (player instanceof NoobIAPlayer) {
        playerObject.put("type", "ia-medium");
      }
      
      playersObject.put(pID, playerObject);
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
