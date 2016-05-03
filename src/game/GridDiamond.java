/**
 * Jeu des 6 couleurs
 *
 * @package game
 * @class   GridSquare
 * @desc    Classe définissant une grille de type carrée
 *
 * @author  Thibault Vlacich <thibault.vlacich@isep.fr>
 * @author  Hugo Michard <hugo.michard@isep.fr>
 */

package game;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import models.TileColor;

public class GridDiamond implements Grid {
  // Nombre de lignes de la grille
  // Doit être un nombre impair
  private int nbOfLines;

  // Grille du jeu
  private ArrayList<ArrayList<Tile>> grid = new ArrayList<ArrayList<Tile>>();
  
  // Stock les coordonnées des coins
  public String[][] cornersCoordinates = {
      {"min", "min"}, // En haut - Joueur 1
      {"max", "min"}, // En bas  - Joueur 2
      {"min", "max"}, // A droite - Joueur 3
      {"max", "min"}  // A gauche  - Joueur 4
  };

  /**
   * Initialise la grille avec sa taille par défaut
   */
  public GridDiamond() {
    this(13);
  }

  /**
   * Initialise la grille avec une taille donnée
   *
   * @param s Taille de la grille
   */
  public GridDiamond(int s) {
    nbOfLines = ((s & 1) == 0) ? (s + 1) : s;
  }

  /**
   * Remplit la grille de manière aléatoire
   */
  public void initRandom() {
    for(int i = 0; i < nbOfLines; i++) {
      ArrayList<Tile> line = new ArrayList<Tile>();
      
      int numberOfTilesForLine;
      
      if (i < nbOfLines / 2) {
        numberOfTilesForLine = i + 1;
      } else {
        numberOfTilesForLine = -i + nbOfLines;
      }
      
      for(int j = 0; j < numberOfTilesForLine; j++) {
        TileColor randomColor = TileColor.getRandomColor();

        line.add(j, new Tile(randomColor));
      }
      
      grid.add(i, line);
    }
  }

  /**
   * Remplit la grille à partir d'une sauvegarde
   */
  public void initWithSave(JSONArray colorGrid, JSONArray playerGrid) {
    for(int i = 0; i < nbOfLines; i++) {
      JSONArray colorLine  = (JSONArray) colorGrid.get(i);
      JSONArray playerLine = (JSONArray) playerGrid.get(i);

      for(int j = 0; j < nbOfLines; j++) {
        String    colorCode   = (String) colorLine.get(j);
        TileColor randomColor = TileColor.getColorFromCode(colorCode);
        int       pID         = ((Long) playerLine.get(j)).intValue();

        grid.get(i).get(j).setColor(randomColor);
        grid.get(i).get(j).setPlayerID(pID);
      }
    }
  }
  
  /**
   * Permet d'obtenir la case de la grille aux coordonnées demandées
   *
   * @param x, y  Coordonnées de la case demandée
   *
   * @return  La case située aux coordonnés (x, y)
   */
  public Tile getTile(int x, int y) {
    return grid.get(x).get(y);
  }

  /**
   * Permet d'assigner à un joueur toutes les cases de la couleur qu'il demande
   * et qui touchent une case qu'il possède déjà
   *
   * @param pID Le joueur à qui assigner les cases
   * @param c   La couleur choisie par le joueur
   */
  public void assignTiles(int pID, TileColor c) {
    int newAssignedTiles = -1;

    while (newAssignedTiles != 0) {
      // Tant que la grille a été modifiée à l'éxécution précédente de la boucle,
      // on continue à vérifier si des cases ne peuvent pas être contrôlées
      newAssignedTiles = 0;

      // On assigne les cases de la couleur demandée au joueur
      for (int i = 0; i < nbOfLines; i++) {
        ArrayList<Tile> line = grid.get(i);
        
        for (int j = 0; j < line.size(); j++) {
          Tile tile = line.get(j);

          if (tile.getPlayerID() == pID) {
            // La case appartient au joueur
            // On la met de la nouvelle couleur choisie par le joueur
            tile.setColor(c);
            
            // Case au dessus
            if (i > 0 && ((i > nbOfLines/2 + 1) || (j < i))) {
              if (
                  // La case est de la couleur voulue
                  grid.get(i - 1).get(j).getColor() == c
                  // Et la case n'appartient pas déjà au joueur
                  && grid.get(i - 1).get(j).getPlayerID() != pID
                  ) {
                grid.get(i - 1).get(j).setPlayerID(pID);
                
                newAssignedTiles++;
              }
            }
            
            // Case en dessous
            if (i < nbOfLines - 1 && ((i < nbOfLines/2 - 1) || (j > i))) {
              if (
                  // La case est de la couleur voulue
                  grid.get(i + 1).get(j).getColor() == c
                  // Et la case n'appartient pas déjà au joueur
                  && grid.get(i + 1).get(j).getPlayerID() != pID
                  ) {
                grid.get(i + 1).get(j).setPlayerID(pID);
                
                newAssignedTiles++;
              }
            }
            
            // Case à gauche
            if (j > 0) {
              if (
                  // La case est de la couleur voulue
                  grid.get(i).get(j - 1).getColor() == c
                  // Et la case n'appartient pas déjà au joueur
                  && grid.get(i).get(j - 1).getPlayerID() != pID
                  ) {
                grid.get(i).get(j - 1).setPlayerID(pID);
                
                newAssignedTiles++;
              }
            }
            
            // Case à droite
            if (j < line.size() - 1) {
              if (
                  // La case est de la couleur voulue
                  grid.get(i).get(j + 1).getColor() == c
                  // Et la case n'appartient pas déjà au joueur
                  && grid.get(i).get(j + 1).getPlayerID() != pID
                  ) {
                grid.get(i).get(j + 1).setPlayerID(pID);
                
                newAssignedTiles++;
              }
            }
          }
        }
      }
    }
  }

  /**
   * Permet d'assigner un joueur à une case
   *
   * @param x, y  Coordonnées de la case demandée
   * @param pID   Le joueur à qui assigner la case
   */
  public void assignTile(int x, int y, int pID) {
    Tile tile = grid.get(x).get(y);

    tile.setPlayerID(pID);
  }

  /**
   * Permet d'obtenir la taille de la grille
   *
   * @return  La taille de la grille
   */
  public int getSize() {
    return nbOfLines;
  }
  
  /**
   * Permet d'obtenir les coordonnées du coin à associer au joueur
   * 
   * @param   player  Le numéro du joueur
   *
   * @return  Les Coordonnées du coin
   */
  public String[] getCornerCoordinate(int player) {
    return cornersCoordinates[player];
  }

  /**
   * Permet de compter le nombre de cases possédées par un joueur
   *
   * @param   pID L'ID du joueur (0 pour avoir les cases libres)
   *
   * @return  Le nombre de cases libres
   */
  public int countTilesOwnedBy(int pID) {
    int count = 0;

    for (int i = 0; i < nbOfLines; i++) {
      ArrayList<Tile> line = grid.get(i);
      
      for (int j = 0; j < line.size(); j++) {
        Tile tile = line.get(j);

        if (tile.getPlayerID() == pID) {
          // La case est libre
          count++;
        }
      }
    }

    return count;
  }

  /**
   * Permet d'afficher la grille en mode 2D
   */
  public GridPane show2D(Game game) {
    GridPane gameGrid = new GridPane();

    for (int i = 0; i < nbOfLines; i++) {
      ArrayList<Tile> line = grid.get(i);
      
      for (int j = 0; j < line.size(); j++) {
        Tile tile = line.get(j);
        int  pID  = tile.getPlayerID();

        Button button = new Button();

        button.getStyleClass().add("tile");
        button.getStyleClass().add(TileColor.getColorClassName(tile.getColor()));

        if (pID != 0) {
          button.setText(Integer.toString(pID));
        }

        button.setOnAction(event -> {
          game.chooseTile(tile);
        });

        gameGrid.add(button, j, i);
      }
    }

    return gameGrid;
  }
  
  /**
   * Permet d'exporter la grille au format JSON
   *
   * @return  L'objet JSON représentant la grille
   */
  public JSONObject exportToJSON() {
    JSONObject jsonObject = new JSONObject();

    JSONArray colorGrid = new JSONArray();

    for (int i = 0; i < nbOfLines; i++) {
      JSONArray lineArray = new JSONArray();

      for (int j = 0; j < nbOfLines; j++) {
        TileColor color = grid.get(i).get(j).getColor();
        lineArray.add(TileColor.getColorCode(color));
      }

      colorGrid.add(lineArray);
    }

    jsonObject.put("colorGrid", colorGrid);

    JSONArray playerGrid = new JSONArray();

    for (int i = 0; i < nbOfLines; i++) {
      JSONArray lineArray = new JSONArray();

      for (int j = 0; j < nbOfLines; j++) {
        int pID = grid.get(i).get(j).getPlayerID();

        lineArray.add(pID);
      }

      playerGrid.add(lineArray);
    }

    jsonObject.put("playerGrid", playerGrid);

    return jsonObject;
  }
}
