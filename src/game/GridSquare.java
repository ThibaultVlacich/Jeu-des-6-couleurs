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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import models.TileColor;

public class GridSquare implements Grid {
  // Taille de la grille
  private int size;

  // Grille du jeu
  private Tile[][] grid;

  /**
   * Initialise la grille avec sa taille par défaut
   */
  public GridSquare() {
    this(13);
  }

  /**
   * Initialise la grille avec une taille donnée
   *
   * @param s Taille de la grille
   */
  public GridSquare(int s) {
    size = s;
    
    grid = new Tile[s][s];
  }

  /**
   * Permet de construire une copie d'une autre instance de GridSquare
   * 
   * @param copyInstance  L'instance à dupliquer
   */
  public GridSquare(GridSquare copyInstance) {
    size = copyInstance.getSize();
    
    grid = new Tile[size][size];
    
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        Tile tile = copyInstance.getTile(i, j);
        
        grid[i][j] = new Tile(tile.getColor());
        grid[i][j].setPlayerID(tile.getPlayerID());
      }
    }
  }
  
  /**
   * Remplit la grille de manière aléatoire
   */
  public void initRandom() {
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        TileColor randomColor = TileColor.getRandomColor();
        
        grid[i][j] = new Tile(randomColor);
      }
    }
  }
  
  /**
   * Remplit la grille à partir d'une sauvegarde
   */
  public void initWithSave(JSONArray colorGrid, JSONArray playerGrid) {
    for(int i = 0; i < size; i++) {
      JSONArray colorLine  = (JSONArray) colorGrid.get(i);
      JSONArray playerLine = (JSONArray) playerGrid.get(i);
      
      for(int j = 0; j < size; j++) {
        String    colorCode   = (String) colorLine.get(j);
        TileColor randomColor = TileColor.getColorFromCode(colorCode);
        int       pID         = ((Long) playerLine.get(j)).intValue();
        
        grid[i][j] = new Tile(randomColor);
        grid[i][j].setPlayerID(pID);
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
    return grid[x][y];
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
      for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
          Tile tile = grid[i][j];
        
          if (tile.getPlayerID() == pID) {
            // La case appartient au joueur
            // On la met de la nouvelle couleur choisie par le joueur
            tile.setColor(c);
            
            if (i > 0) {
              if (
                  // La case est de la couleur voulue
                  grid[i - 1][j].getColor() == c
                  // Et la case n'appartient pas déjà au joueur
                  && grid[i - 1][j].getPlayerID() != pID
                  ) {
                // Case située en haut
                grid[i - 1][j].setPlayerID(pID);
                
                newAssignedTiles++;
              }
            }
  
            if (j > 0) {
              if (
                  // La case est de la couleur voulue
                  grid[i][j - 1].getColor() == c
                  // Et la case n'appartient pas déjà au joueur
                  && grid[i][j - 1].getPlayerID() != pID
                  ) {
                // Case située à gauche
                grid[i][j - 1].setPlayerID(pID);
                
                newAssignedTiles++;
              }
            }
            
            if (i < size - 1){
              if (
                  // La case est de la couleur voulue
                  grid[i + 1][j].getColor() == c
                  // Et la case n'appartient pas déjà au joueur
                  && grid[i + 1][j].getPlayerID() != pID
                  ) {
                // Case située en dessous
                grid[i + 1][j].setPlayerID(pID);
                
                newAssignedTiles++;
              }
            }
            
            if (j < size - 1){
              if (
                  // La case est de la couleur voulue
                  grid[i][j + 1].getColor() == c
                  // Et la case n'appartient pas déjà au joueur
                  && grid[i][j + 1].getPlayerID() != pID
                  ) {
                // Case située à droite
                grid[i][j + 1].setPlayerID(pID);
                
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
    Tile tile = grid[x][y];
    
    tile.setPlayerID(pID);
  }
  
  /**
   * Permet d'obtenir la taille de la grille
   * 
   * @return  La taille de la grille
   */
  public int getSize() {
    return size;
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
    GridSquare newGameGrid = new GridSquare(this);
    
    // On assigne "virtuellement" au joueur la couleur
    newGameGrid.assignTiles(pID, c);
    
    // On compte le nombre de nouvelles cases obtenues
    int tilesTooked = (newGameGrid.countTilesOwnedBy(pID) - this.countTilesOwnedBy(pID));
    
    return tilesTooked;
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
    
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        Tile tile = grid[i][j];
        
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
      
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        Tile tile = getTile(i, j);
        int  pID  = tile.getPlayerID();
          
        Button button = new Button();
          
        button.getStyleClass().add("tile");
        button.getStyleClass().add(TileColor.getColorClassName(tile.getColor()));
          
        if (pID != 0) {
          button.setText(Integer.toString(pID));
        }
          
        button.setOnAction(event -> {
          game.chooseColor(tile.getColor());
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
    
    for (int i = 0; i < size; i++) {
      JSONArray lineArray = new JSONArray();
      
      for (int j = 0; j < size; j++) {
        TileColor color = grid[i][j].getColor();
        lineArray.add(TileColor.getColorCode(color));
      }
      
      colorGrid.add(lineArray);
    }
    
    jsonObject.put("colorGrid", colorGrid);
    
    JSONArray playerGrid = new JSONArray();
    
    for (int i = 0; i < size; i++) {
      JSONArray lineArray = new JSONArray();
      
      for (int j = 0; j < size; j++) {
        int pID = grid[i][j].getPlayerID();
        
        lineArray.add(pID);
      }
      
      playerGrid.add(lineArray);
    }
    
    jsonObject.put("playerGrid", playerGrid);

    return jsonObject;
  }
}
