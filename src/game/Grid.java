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

import models.TileColor;

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
}
