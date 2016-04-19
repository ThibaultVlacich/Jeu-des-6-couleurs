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

import java.util.Random;

public class Grid {
  // Taille de la grille
  private int size;
  
  
  String[][] colorGrid;
  
  // Liste des couleurs disponibles
  private static String[] availableColors = {"r", "o", "j", "v", "b", "i"};
  
  /**
   * Initialise la grille avec sa taille par défaut
   */
  public Grid() {
    this.size = 13;
  }
  
  /**
   * Initialise la grille avec une taille donnée
   * 
   * @param s Taille de la grille
   */
  public Grid(int s) {
    this.size = s;
  }
  
  /**
   * Remplit la grille de manière aléatoire
   */
  public void initRandom() {
    Random r = new Random();
    
    int Min = 1;
    int Max = 6;
    
    for(int i = 0; i < this.size; i++) {
      for(int j = 0; j < this.size; j++) {
        int Result = r.nextInt(Max - Min) + Min;
        
        colorGrid[j][i] = availableColors[Result - 1];
      }
    }
  }
}
