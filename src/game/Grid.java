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

import models.Color;

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
    this.size = s;
    
    this.grid = new Tile[s][s];
  }

  /**
   * Remplit la grille de manière aléatoire
   */
  public void initRandom() {
    for(int i = 0; i < this.size; i++) {
      for(int j = 0; j < this.size; j++) {
        Color randomColor = Color.getRandomColor();
        
        grid[j][i] = new Tile(randomColor);
      }
    }
  }
}
