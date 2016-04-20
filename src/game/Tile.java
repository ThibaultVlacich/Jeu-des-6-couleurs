/**
 * Jeu des 6 couleurs
 *
 * @package game
 * @class   Tile
 * @desc    Classe définissant une case du jeu
 *
 * @author  Thibault Vlacich <thibault.vlacich@isep.fr>
 * @author  Hugo Michard <hugo.michard@isep.fr>
 */

package game;

import models.Color;

public class Tile {
  private Color  color;
  private Player player;
  
  /**
   * Initialiser la case avec une couleur
   * 
   * @param c
   */
  Tile(Color c) {
    color = c;
  }
  
  public Color getColor() {
    return color;
  }
  
  public void setColor(Color c) {
    this.color = c;
  }
  
  public Player getPlayer() {
    return player;
  }
  
  public void setPlayer(Player p) {
    this.player = p;
  }
}
