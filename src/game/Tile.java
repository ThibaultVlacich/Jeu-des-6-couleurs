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

import models.TileColor;

public class Tile {
  // Couleur de la case
  private TileColor color;
  
  // Joueur à qui la case appartient
  private Player player;
  
  /**
   * Initialise la case avec une couleur
   * 
   * @param c
   */
  Tile(TileColor c) {
    color = c;
  }
  
  /**
   * Permet d'obtenir la couleur de la case
   * 
   * @return  Couleur de la case
   */
  public TileColor getColor() {
    return color;
  }
  
  /**
   * Permet de modifier la couleur de la case
   * 
   * @param c Nouvelle couleur
   */
  public void setColor(TileColor c) {
    color = c;
  }
  
  /**
   * Permet d'obtenir le joueur à qui la case appartient
   * 
   * @return  Joueur à qui la case appartient
   */
  public Player getPlayer() {
    return player;
  }
  
  /**
   * Permet de modifier le joueur qui détient la case
   * 
   * @param p Nouveau joueur
   */
  public void setPlayer(Player p) {
    player = p;
  }
}
