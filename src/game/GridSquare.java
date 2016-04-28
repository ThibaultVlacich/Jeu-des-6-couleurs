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
   * Remplit la grille de manière aléatoire
   */
  public void initRandom() {
    for(int i = 0; i < size; i++) {
      for(int j = 0; j < size; j++) {
        TileColor randomColor = TileColor.getRandomColor();
        
        grid[j][i] = new Tile(randomColor);
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
    return grid[y][x];
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
          Tile tile = grid[j][i];
        
          if (tile.getPlayerID() == pID) {
            // La case appartient au joueur
            // On la met de la nouvelle couleur choisie par le joueur
            tile.setColor(c);
            
            if (j > 0) {
              if (
                  // La case est de la couleur voulue
                  grid[j - 1][i].getColor() == c
                  // Et la case n'appartient pas déjà au joueur
                  && grid[j - 1][i].getPlayerID() != pID
                  ) {
                // Case située en haut
                grid[j - 1][i].setPlayerID(pID);
                
                newAssignedTiles++;
              }
            }
  
            if (i > 0) {
              if (
                  // La case est de la couleur voulue
                  grid[j][i - 1].getColor() == c
                  // Et la case n'appartient pas déjà au joueur
                  && grid[j][i - 1].getPlayerID() != pID
                  ) {
                // Case située à gauche
                grid[j][i - 1].setPlayerID(pID);
                
                newAssignedTiles++;
              }
            }
            
            if (j < size - 1){
              if (
                  // La case est de la couleur voulue
                  grid[j + 1][i].getColor() == c
                  // Et la case n'appartient pas déjà au joueur
                  && grid[j + 1][i].getPlayerID() != pID
                  ) {
                // Case située en dessous
                grid[j + 1][i].setPlayerID(pID);
                
                newAssignedTiles++;
              }
            }
            
            if (i < size - 1){
              if (
                  // La case est de la couleur voulue
                  grid[j][i + 1].getColor() == c
                  // Et la case n'appartient pas déjà au joueur
                  && grid[j][i + 1].getPlayerID() != pID
                  ) {
                // Case située à droite
                grid[j][i + 1].setPlayerID(pID);
                
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
    Tile tile = grid[y][x];
    
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
        Tile tile = grid[j][i];
        
        if (tile.getPlayerID() == pID) {
          // La case est libre
          count++;
        }
      }
    }
    
    return count;
  }
  
  /**
   * Permet d'affichier la grille en mode console
   */
  public void showConsole() {
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        Tile tile = grid[j][i];
        
        TileColor tileColor  = tile.getColor();
        int       tilePlayerID = tile.getPlayerID();
        
        String tileColorCode = TileColor.getColorCode(tileColor);
        
        if (tilePlayerID != 0) {
          // La case appartient à un joueur
          tileColorCode = tileColorCode.toUpperCase();
        }
        
        System.out.print(tileColorCode+" ");
      }
      
      System.out.println("");
    }
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
          game.chooseTile(tile);
        });
          
        gameGrid.add(button, i, j);
      }
    }
    
    return gameGrid;
  }
}
