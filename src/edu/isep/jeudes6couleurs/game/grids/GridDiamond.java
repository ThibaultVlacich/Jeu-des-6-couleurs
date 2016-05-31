package edu.isep.jeudes6couleurs.game.grids;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import edu.isep.jeudes6couleurs.game.Game;
import edu.isep.jeudes6couleurs.game.Tile;

import edu.isep.jeudes6couleurs.models.TileColor;

/**
 * Jeu des 6 couleurs
 *
 * @package edu.isep.jeudes6couleurs.game
 * @class   GridSquare
 * @desc    Classe définissant une grille de type carrée
 *
 * @author  Thibault Vlacich <thibault.vlacich@isep.fr>
 * @author  Hugo Michard <hugo.michard@isep.fr>
 */
public class GridDiamond implements Grid {
  // Nombre de lignes de la grille
  // Doit être un nombre impair
  private int nbOfLines;

  // Grille du jeu
  private ArrayList<ArrayList<Tile>> grid = new ArrayList<ArrayList<Tile>>();
  
  // Stock les coordonnées des coins
  public String[] cornersCoordinates = {
      "top",    // En haut - Joueur 1
      "bottom", // En bas  - Joueur 2
      "right",  // A droite - Joueur 3
      "left"    // A gauche  - Joueur 4
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
   * Permet de construire une copie d'une autre instance de GridDiamond
   * 
   * @param copyInstance  L'instance à dupliquer
   */
  public GridDiamond(GridDiamond copyInstance) {
    nbOfLines = copyInstance.getSize();
    
    for(int i = 0; i < nbOfLines; i++) {
      ArrayList<Tile> line = new ArrayList<Tile>();
      
      int numberOfTilesForLine;
      
      if (i < nbOfLines / 2) {
        numberOfTilesForLine = i + 1;
      } else {
        numberOfTilesForLine = -i + nbOfLines;
      }
      
      for(int j = 0; j < numberOfTilesForLine; j++) {
        Tile tile = copyInstance.getTile(i, j);

        line.add(j, new Tile(tile.getColor()));
        line.get(j).setPlayerID(tile.getPlayerID());
      }
      
      grid.add(i, line);
    }
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
      ArrayList<Tile> line = new ArrayList<Tile>();
      
      JSONArray colorLine  = (JSONArray) colorGrid.get(i);
      JSONArray playerLine = (JSONArray) playerGrid.get(i);
      
      int numberOfTilesForLine;
      
      if (i < nbOfLines / 2) {
        numberOfTilesForLine = i + 1;
      } else {
        numberOfTilesForLine = -i + nbOfLines;
      }
      
      for(int j = 0; j < numberOfTilesForLine; j++) {
        String    colorCode   = (String) colorLine.get(j);
        TileColor randomColor = TileColor.getColorFromCode(colorCode);
        int       pID         = ((Long) playerLine.get(j)).intValue();

        Tile      tile        = new Tile(randomColor);
        tile.setPlayerID(pID);
        
        line.add(j, tile);
      }
      
      grid.add(i, line);
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

            if (i < nbOfLines/2) {
              // Case au dessus a droite
              if(i > 0 && j < line.size() - 1){
                if(checkAndAssign(i - 1, j, c, pID)){
                  newAssignedTiles++;
                }
              }

              // Case en dessous a gauche
              if (checkAndAssign(i + 1, j, c, pID)){                
                newAssignedTiles++;
              }      

              // Case en haut a gauche
              if (i > 0 && j > 0) {
                if (checkAndAssign(i - 1, j - 1, c, pID)){                
                  newAssignedTiles++;
                }
              }

              // Case en bas a droite
              if (checkAndAssign(i + 1, j + 1, c, pID)){                
                newAssignedTiles++;
              }      
            } else if (i > nbOfLines/2) {
              // Case au dessus a droite
              if(checkAndAssign(i - 1, j + 1, c, pID)){
                newAssignedTiles++;
              }

              // Case en dessous a gauche
              if(j > 0){
                if (checkAndAssign(i + 1, j - 1, c, pID)){                
                  newAssignedTiles++;
                }      
              }
              // Case en haut a gauche
              if (checkAndAssign(i - 1, j, c, pID)){                
                newAssignedTiles++;
              }

              // Case en bas a droite
              if(j < line.size() - 1){
                if (checkAndAssign(i + 1, j, c, pID)){                
                  newAssignedTiles++;
                }      
              }
            }
            else{
              // Case en dessous a gauche
              if(j > 0){
                if (checkAndAssign(i + 1, j - 1, c, pID)){                
                  newAssignedTiles++;
                }      
              }
              // Case en haut a gauche
              if(j > 0){
                if (checkAndAssign(i - 1, j - 1, c, pID)){                
                  newAssignedTiles++;
                }      
              }

              // Case en bas a droite
              if(j < line.size() - 1){
                if (checkAndAssign(i + 1, j, c, pID)){                
                  newAssignedTiles++;
                }      
              }

              // Case au dessus a droite
              if(j < line.size() - 1){
                if (checkAndAssign(i - 1, j, c, pID)){                
                  newAssignedTiles++;
                }      
              }
            }
          }
        }
      }
    }
  }

  /**
   * Vérifie qu'une case existe et, le cas échéant, lui assigne un joueur si elle est de la couleur demandée
   * 
   * @param i   La ligne de la case
   * @param j   La colonne de la case
   * @param c   La couleur demandée
   * @param pID L'ID du joueur à assigner
   * 
   * @return  Boolean Indique si la case a été assignée au joueur ou non
   */
  public Boolean checkAndAssign(int i, int j, TileColor c, int pID) {
    if (
        // La case est de la couleur voulue
        grid.get(i).get(j).getColor() == c
        // Et la case n'appartient pas déjà au joueur
        && grid.get(i).get(j).getPlayerID() != pID
        ) {
      grid.get(i).get(j).setPlayerID(pID);
      return true;
    } else {
      return false;
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
   * Compte le nombre de cases qu'un joueur pourrait obtenir avec la couleur spécifiée
   * 
   * @param pID   L'ID du joueur
   * @param color La couleur choisie
   * 
   * @return  Le nombre de cases qu'il obtiendrait
   */
  public int countTilesTakeable(int pID, TileColor c) {
    // On créé une copie de la grille actuelle
    GridDiamond newGameGrid = new GridDiamond(this);
    
    // On assigne "virtuellement" au joueur la couleur
    newGameGrid.assignTiles(pID, c);
    
    // On compte le nombre de nouvelles cases obtenues
    int tilesTooked = (newGameGrid.countTilesOwnedBy(pID) - this.countTilesOwnedBy(pID));
    
    return tilesTooked;
  }
  
  /**
   * Permet d'obtenir le nombre de cases dans la grille
   * 
   * @return
   */
  public int totalNumberOfTiles() {
    int total = 0;
    
    for (ArrayList<Tile> line: grid) {
      total += line.size();
    }
    
    return total;
  }
  
  /**
   * Permet d'assigner un coin au joueur
   *
   * @param   playerID  L'ID du joueur
   *
   * @return  La couleur du coin assigné
   */
  public TileColor assignCornerTo(int playerID) {
    String cornerCoordinate = cornersCoordinates[playerID - 1];
    
    int x, y;
    
    switch (cornerCoordinate) {
      default:
      case "top":
        x = 0;
        y = 0;
      break;
      case "bottom":
        x = nbOfLines - 1;
        y = 0;
      break;
      case "right":
        x = nbOfLines / 2;
        y = 0;
      break;
      case "left":
        x = nbOfLines / 2;
        y = grid.get(x).size() - 1;
      break;
    }
 
    assignTile(x, y, playerID);
    
    return getTile(x, y).getColor();
  }

  /**
   * Permet d'assigner un coin au joueur avec une couleur donnée
   *
   * @param   playerID  L'ID du joueur
   * @param   color     La couleur à associer
   *
   * @return  La couleur du coin assigné
   */
  public TileColor assignCornerTo(int playerID, TileColor color) {
    String cornerCoordinate = cornersCoordinates[playerID - 1];
    
    int x, y;
    
    switch (cornerCoordinate) {
      default:
      case "top":
        x = 0;
        y = 0;
      break;
      case "bottom":
        x = nbOfLines - 1;
        y = 0;
      break;
      case "right":
        x = nbOfLines / 2;
        y = 0;
      break;
      case "left":
        x = nbOfLines / 2;
        y = grid.get(x).size() - 1;
      break;
    }
    
    assignTile(x, y, playerID);
    getTile(x, y).setColor(color);
    
    return getTile(x, y).getColor();
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
        
        if (nbOfLines > 19) {
          button.getStyleClass().add("small");
        }

        if (pID != 0) {
          button.setText(Integer.toString(pID));
        }
        
        button.setOnAction(event -> {
          game.chooseColor(tile.getColor());
        });
        
        int columnIndex;
        
        if (i < nbOfLines/2) {
          columnIndex = new Double(j*2+(Math.floor((nbOfLines-i)))).intValue();
        } else {
          columnIndex = j*2+i+1;
        }
        
        gameGrid.add(button, columnIndex, i);
        
        Button blank = new Button();
        blank.getStyleClass().add("tile");
        blank.getStyleClass().add("white");
        
        if (nbOfLines > 19) {
          blank.getStyleClass().add("small");
        }
        
        gameGrid.add(blank, columnIndex+1, i);
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

    for (ArrayList<Tile> line: grid) {
      JSONArray lineArray = new JSONArray();

      for (Tile tile: line) {
        TileColor color = tile.getColor();
        lineArray.add(TileColor.getColorCode(color));
      }

      colorGrid.add(lineArray);
    }

    jsonObject.put("colorGrid", colorGrid);

    JSONArray playerGrid = new JSONArray();

    for (ArrayList<Tile> line: grid) {
      JSONArray lineArray = new JSONArray();

      for (Tile tile: line) {
        int pID = tile.getPlayerID();

        lineArray.add(pID);
      }

      playerGrid.add(lineArray);
    }

    jsonObject.put("playerGrid", playerGrid);

    return jsonObject;
  }
}
