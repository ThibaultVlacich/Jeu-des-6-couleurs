/**
 * Jeu des 6 couleurs
 *
 * @package game
 * @class   Grid
 * @desc    Classe permettant de gérer la grille du jeu
 *
 * @author  Thibault Vlacich <thibault.vlacich@isep.fr>
 * @author  Hugo Michard <hugo.michard@isep.fr>
 */

package game;

// Importe les modèles utiles à la grille
import models.TileColor;

// Importe l'objet View
import view.View;

public class Grid {
  // Taille de la grille
  private int size;

  // Grille du jeu
  Tile[][] grid;

  /**
   * Initialise la grille avec sa taille par défaut
   */
  public Grid() {
    this(13);
  }

  /**
   * Initialise la grille avec une taille donnée
   *
   * @param s Taille de la grille
   */
  public Grid(int s) {
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
  
  public void assignTiles(int pID, TileColor c) {
    // On assigne les cases de la couleur demandée au joueur
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        Tile tile = grid[j][i];
        
        if (tile.getPlayerID() == pID) {
          // La case appartient au joueur
          // On la met de la nouvelle couleur choisie par le joueur
          tile.setColor(c);
          
          // On parcourt les cases situées à proximité et on les lui assigne si elles sont de la couleur choisie
          if (j > 0) {
            if(grid[j-1][i].getColor() == c) {
              grid[j-1][i].setPlayerID(pID);
            }
          }

          if (i > 0) {
            if (grid[j][i-1].getColor() == c){
              grid[j][i-1].setPlayerID(pID);
            }
          }
          
          if (j < size - 1){
            if (grid[j+1][i].getColor() == c) {
              grid[j+1][i].setPlayerID(pID);
            }
          }
          
          if (i < size - 1){
            if (grid[j][i+1].getColor() == c){
              grid[j][i+1].setPlayerID(pID);
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
  public void show2D() {
    View.showGrid(this);
  }
}
